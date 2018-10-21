package bingemann_software.recipesplayer.data;

import java.util.List;

public interface RecipeList
{
    Recipe get(int id);
    List<Recipe> getAll();

    boolean add(Recipe recipe);
    boolean remove(int id);

    boolean isEmpty();

    int size();
}
