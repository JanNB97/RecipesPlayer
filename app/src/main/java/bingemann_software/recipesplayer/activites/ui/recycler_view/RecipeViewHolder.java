package bingemann_software.recipesplayer.activites.ui.recycler_view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import bingemann_software.recipesplayer.R;
import bingemann_software.recipesplayer.data.Recipe;

public class RecipeViewHolder extends RecyclerView.ViewHolder
{
    private TextView nameTextView;
    private View view;

    public RecipeViewHolder(View itemView)
    {
        super(itemView);
        this.nameTextView = itemView.findViewById(R.id.name);
        this.view = itemView;
    }

    public void showRecipe(Recipe recipe)
    {
        this.nameTextView.setText(recipe.getName());
        this.view.setBackgroundColor(recipe.getOccasion().getColor());
    }
}
