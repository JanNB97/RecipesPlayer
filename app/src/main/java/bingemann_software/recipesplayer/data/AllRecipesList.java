package bingemann_software.recipesplayer.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import bingemann_software.recipesplayer.http_client.RecipeDbHttpClient;
import bingemann_software.recipesplayer.http_client.ServerCannotBeReachedException;

public class AllRecipesList implements RecipeList
{
    private Map<Recipe.Occasion, List<Recipe>> allRecipes;
    private Recipe.Occasion occasion = Recipe.Occasion.MEAL;

    private AllRecipesList()
    {
        try
        {
            this.allRecipes = new HashMap<>();

            for(Recipe.Occasion occasion : Recipe.Occasion.values())
            {
                this.allRecipes.put(occasion, new ArrayList<>());
            }

            for(Recipe recipe : RecipeDbHttpClient.getAllRecipes())
            {
                List<Recipe> recipeList = allRecipes.get(recipe.getOccasion());
                recipeList.add(recipe);
            }

        } catch (ServerCannotBeReachedException e)
        {
            // TODO - handle properly
            e.printStackTrace();
        }
    }

    // --- --- --- Singleton --- --- ---
    private static AllRecipesList instance;

    public static AllRecipesList getInstance()
    {
        if(instance == null)
        {
            instance = new AllRecipesList();
        }

        return instance;
    }

    // --- --- --- Overwritten methods --- --- ---
    @Override
    public Recipe get(int id)
    {
        return this.allRecipes.get(occasion).get(id);
    }

    @Override
    public List<Recipe> getAll()
    {
        return this.allRecipes.get(occasion);
    }

    @Override
    public boolean add(Recipe recipe)
    {
        return this.allRecipes.get(occasion).add(recipe);
    }

    @Override
    public boolean remove(int id)
    {
        this.allRecipes.get(occasion).remove(id);
        return true;
    }

    @Override
    public boolean isEmpty()
    {
        this.allRecipes.get(occasion).isEmpty();
        return false;
    }

    @Override
    public int size()
    {
        return this.allRecipes.get(occasion).size();
    }

    @Override
    public void setOccasion(Recipe.Occasion occasion)
    {
        this.occasion = occasion;
    }

    @Override
    public Recipe.Occasion getOccasion()
    {
        return this.occasion;
    }
}
