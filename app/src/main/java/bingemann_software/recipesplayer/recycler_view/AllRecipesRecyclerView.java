package bingemann_software.recipesplayer.recycler_view;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import bingemann_software.recipesplayer.R;
import bingemann_software.recipesplayer.data.OccasionRecipesList;
import bingemann_software.recipesplayer.data.Recipe;
import bingemann_software.recipesplayer.http_client.ServerCannotBeReachedException;

public class AllRecipesRecyclerView
{
    private AppCompatActivity activity;
    private RecyclerView recyclerView;

    private RecipeAdapter[] allRecipeAdapters;

    public AllRecipesRecyclerView(AppCompatActivity activity, @IdRes int id)
    {
        this.activity = activity;
        this.recyclerView = activity.findViewById(id);

        this.initRecyclerView();
        this.initLayout();
        new Thread(() -> {
            try
            {
                this.initAdapters();
                this.activity.runOnUiThread(() ->
                        this.recyclerView.setAdapter(this.allRecipeAdapters[Recipe.Occasion.MEAL.ordinal()]
                ));

            } catch (ServerCannotBeReachedException e)
            {
                // TODO - handle properly
                e.printStackTrace();
            }
        }).start();
    }

    private void initRecyclerView()
    {
        LayoutAnimationController animation
                = AnimationUtils.loadLayoutAnimation(this.activity, R.anim.layout_fall_down);
        this.recyclerView.setLayoutAnimation(animation);
    }

    private void initLayout()
    {
        // use linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
        this.recyclerView.setLayoutManager(layoutManager);
    }

    private void initAdapters() throws ServerCannotBeReachedException
    {
        this.allRecipeAdapters = new RecipeAdapter[Recipe.Occasion.values().length];

        for(int i = 0; i < allRecipeAdapters.length; i++)
        {
            Recipe.Occasion occasion = Recipe.Occasion.values()[i];
            allRecipeAdapters[i] = new RecipeAdapter(OccasionRecipesList.getInstance(occasion));
        }
    }

    public void swapAdapters(Recipe.Occasion occasion)
    {
        this.recyclerView.swapAdapter(allRecipeAdapters[occasion.ordinal()], false);
        this.recyclerView.scheduleLayoutAnimation();
    }
}
