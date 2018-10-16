package bingemann_software.recipesplayer.httpClient;

import android.app.DownloadManager;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import bingemann_software.recipesplayer.data.Recipe;

public class RecipeDbHttpClient
{
    private static final String SERVER_IP = "192.168.0.143";
    private static final String RECIPE_DB_URL = "http://" + SERVER_IP + "/recipes_db";

    public static ServerResponse sendAddRecipe(Recipe recipe)
    {
        if(recipe == null)
        {
            throw new NullPointerException();
        }

        try
        {
            JSONObject response = HttpHelper.sendGet(getAddRecipeURL(recipe));

            // TODO
            return null;
        } catch (ServerCannotBeReachedException e)
        {
            e.printStackTrace();
            return ServerResponse.SERVER_CANNOT_BE_REACHED;
        }
    }

    private static URL getAddRecipeURL(Recipe recipe)
    {
        try
        {
            return new URL(RECIPE_DB_URL + "/add?" + toAddRequest(recipe));
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

        builder.append("&id=");
        builder.append(recipe.getId());

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
}
