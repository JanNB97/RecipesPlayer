package bingemann_software.recipesplayer.recycler_view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bingemann_software.recipesplayer.R;
import bingemann_software.recipesplayer.data.Recipe;
import bingemann_software.recipesplayer.data.RecipeList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder>
{
    private RecipeList recipeList;

    public RecipeAdapter(RecipeList recipeList)
    {
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_item, parent, false);
        return new RecipeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position)
    {
        Recipe recipeToShow = recipeList.get(position);
        if(recipeToShow != null)
        {
            holder.showRecipe(recipeToShow);
        }
    }

    @Override
    public int getItemCount()
    {
        return recipeList.size();
    }
}
