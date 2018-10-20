package bingemann_software.recipesplayer.httpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import bingemann_software.recipesplayer.data.Recipe;

public class RecipeDbHttpClient
{
    public static ServerResponse sendAddRecipe(Recipe recipe)
    {
        if(recipe == null)
        {
            throw new NullPointerException();
        }

        String response;

        try
        {
            response = HttpHelper.sendGet(getAddRecipeURL(recipe));
        } catch (ServerCannotBeReachedException e)
        {
            e.printStackTrace();
            return ServerResponse.SERVER_CANNOT_BE_REACHED;
        }

        try
        {
            return ServerResponse.valueOf(response);
        }
        catch (IllegalArgumentException ignored)
        {
            return ServerResponse.SERVER_RESPONSE_NOT_VALID;
        }
    }

    public static List<Recipe> getAllRecipes() throws ServerCannotBeReachedException
    {
        JSONArray response = HttpHelper.sendGetJSONArrayResponse(getAllRecipeURL());
        List<Recipe> allRecipes = toRecipes(response);

        return allRecipes;
    }

    // --- --- --- supporting methods --- --- ---
    private static URL getAddRecipeURL(Recipe recipe)
    {
        try
        {
            return new URL(ServerConstants.ADD_URL + toAddRequest(recipe));
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private static String toAddRequest(Recipe recipe)
    {
        StringBuilder builder = new StringBuilder();

        // essentials
        builder.append("client=");
        builder.append(recipe.getCreator().getName());

        builder.append("&name=");
        builder.append(recipe.getName());

        // optional
        if(recipe.getOccasion() != Recipe.Occasion.MEAL)
        {
            builder.append("&occasion=");
            builder.append(recipe.getOccasion());
        }
        if(!recipe.getDescription().isEmpty())
        {
            builder.append("&description=");
            builder.append(recipe.getDescription());
        }

        // TODO - add recipe-picture-URL

        return builder.toString();
    }

    private static URL getAllRecipeURL()
    {
        try
        {
            return new URL(ServerConstants.DB_URL + "/all");
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private static List<Recipe> toRecipes(JSONArray jsonArray)
    {
        List<Recipe> recipes = new ArrayList<>();

        for(int i = 0; i < jsonArray.length(); i++)
        {
            try
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                recipes.add(toRecipe(jsonObject));
            } catch (JSONException e)
            {
                e.printStackTrace();
            }
        }

        return recipes;
    }

    private static Recipe toRecipe(JSONObject jsonObject)
    {
        Recipe recipe;
        try
        {
            String creatorName = jsonObject.getString(ServerConstants.CREATOR_NAME_KEY);
            String name = jsonObject.getString(ServerConstants.NAME_KEY);

            recipe = new Recipe(new Recipe.Creator(creatorName), name);
        } catch (JSONException e)
        {
            e.printStackTrace();
            return null;
        }

        try
        {
            String description = jsonObject.getString(ServerConstants.DESCRIPTION_KEY);
            recipe.setDescription(description);
        } catch (JSONException ignored) {}

        try
        {
            Recipe.Occasion occasion = Recipe.Occasion.valueOf(
                    jsonObject.getString(ServerConstants.OCCASION_KEY));
            recipe.setOccasion(occasion);
        } catch (JSONException ignored) {}

        try
        {
            String pictureURLString = jsonObject.getString(ServerConstants.PICTURE_URL);
            recipe.setPictureURL(new URL(pictureURLString));
        } catch (JSONException | MalformedURLException ignored) {}

        return recipe;
    }
}
