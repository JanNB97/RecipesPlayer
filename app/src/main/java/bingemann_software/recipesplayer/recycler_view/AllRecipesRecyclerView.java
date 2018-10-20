package bingemann_software.recipesplayer.recycler_view;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import bingemann_software.recipesplayer.R;
import bingemann_software.recipesplayer.data.AllRecipesList;

public class AllRecipesRecyclerView
{
    private AppCompatActivity activity;
    private RecyclerView recyclerView;

    private RecipeAdapter recipeAdapter;

    public AllRecipesRecyclerView(AppCompatActivity activity, @IdRes int id)
    {
        this.activity = activity;
        this.recyclerView = activity.findViewById(id);

        this.initLayout();

        new Thread(() -> {
            this.recipeAdapter = new RecipeAdapter(AllRecipesList.getInstance());

            this.activity.runOnUiThread(() -> this.recyclerView.setAdapter(this.recipeAdapter));
        }).start();
    }

    private void initLayout()
    {
        // use linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
        this.recyclerView.setLayoutManager(layoutManager);
    }

    public RecipeAdapter getAdapter()
    {
        return recipeAdapter;
    }
}
