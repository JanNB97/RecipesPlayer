package bingemann_software.recipesplayer.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bingemann_software.recipesplayer.http_client.RecipeDbHttpClient;
import bingemann_software.recipesplayer.http_client.ServerCannotBeReachedException;

public class OccasionRecipesList implements RecipeList
{
    private List<Recipe> recipes;

    private OccasionRecipesList()
    {
        this.recipes = new ArrayList<>();
    }

    // --- --- --- Tripleton --- --- ---
    private static OccasionRecipesList[] instances;

    public static OccasionRecipesList getInstance(Recipe.Occasion filterOccasion) throws ServerCannotBeReachedException
    {
        if(instances == null)
        {
            initInstances();
        }

        return instances[filterOccasion.ordinal()];
    }

    private static void initInstances() throws ServerCannotBeReachedException
    {
        instances = new OccasionRecipesList[Recipe.Occasion.values().length];

        for(Recipe.Occasion occasion : Recipe.Occasion.values())
        {
            instances[occasion.ordinal()] = new OccasionRecipesList();
        }

        loadInstances();
    }

    public static void loadInstances() throws ServerCannotBeReachedException
    {
        for(Recipe.Occasion occasion : Recipe.Occasion.values())
        {
            instances[occasion.ordinal()].clear();
        }

        List<Recipe> recipeList = RecipeDbHttpClient.getAllRecipes();
        for(int i = recipeList.size() - 1; i >= 0; i--)
        {
            Recipe recipe = recipeList.get(i);
            instances[recipe.getOccasion().ordinal()].add(recipe);
        }
    }

    // --- --- --- Overwritten methods --- --- ---
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
        return this.recipes.add(recipe);
    }

    @Override
    public boolean remove(int id)
    {
        this.recipes.remove(id);
        return true;
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

    @Override
    public boolean clear()
    {
        recipes.clear();
        return true;
    }
}
