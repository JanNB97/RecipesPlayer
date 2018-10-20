package bingemann_software.recipesplayer.data;

import java.util.ArrayList;
import java.util.List;

import bingemann_software.recipesplayer.http_client.RecipeDbHttpClient;
import bingemann_software.recipesplayer.http_client.ServerCannotBeReachedException;

public class AllRecipesList implements RecipeList
{
    private List<Recipe> recipes;

    private static AllRecipesList instance;

    public static AllRecipesList getInstance()
    {
        if(instance == null)
        {
            instance = new AllRecipesList();
        }

        return instance;
    }

    private AllRecipesList()
    {
        try
        {
            this.recipes = RecipeDbHttpClient.getAllRecipes();
        } catch (ServerCannotBeReachedException e)
        {
            // TODO - handle properly
            this.recipes = new ArrayList<>();
            e.printStackTrace();
        }
    }

    @Override
    public Recipe get(int id)
    {
        return this.recipes.get(id);
    }

    @Override
    public List<Recipe> getAll()
    {
        return this.recipes;
    }

    @Override
    public boolean add(Recipe recipe)
    {
        this.recipes.add(recipe);
        return true;
    }

    @Override
    public boolean remove(int id)
    {
        this.recipes.remove(id);
        return false;
    }

    @Override
    public boolean isEmpty()
    {
        this.recipes.isEmpty();
        return false;
    }

    @Override
    public int size()
    {
        return this.recipes.size();
    }
}
