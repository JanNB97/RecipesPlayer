package bingemann_software.recipesplayer.activites.ui.recycler_view;

import android.support.annotation.IdRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import bingemann_software.recipesplayer.MainActivity;
import bingemann_software.recipesplayer.R;
import bingemann_software.recipesplayer.data.OccasionRecipesList;
import bingemann_software.recipesplayer.data.Recipe;
import bingemann_software.recipesplayer.http_client.ServerCannotBeReachedException;
import bingemann_software.recipesplayer.http_client.ServerResponse;
import bingemann_software.recipesplayer.notifications.NotificationMan;
import bingemann_software.recipesplayer.tasks.AServerTask;

public class AllRecipesRecyclerView
{
    private MainActivity mainActivity;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private RecipeAdapter[] allRecipeAdapters;

    // --- --- --- initialization --- --- ---
    public AllRecipesRecyclerView(MainActivity mainActivity, @IdRes int id)
    {
        this.mainActivity = mainActivity;
        this.findViewsByIds(id);

        this.initRecyclerView();
        this.initLayout();
        this.initSwipeRefreshLayout();

        new AServerTask(this::initAdapters, this::onPostInitAdapters).execute();
    }

    private void findViewsByIds(@IdRes int recyclerViewId)
    {
        this.recyclerView = mainActivity.findViewById(recyclerViewId);
        this.swipeRefreshLayout = mainActivity.findViewById(R.id.swiperefresh);
    }

    private void initRecyclerView()
    {
        LayoutAnimationController animation
                = AnimationUtils.loadLayoutAnimation(this.mainActivity, R.anim.layout_fall_down);
        this.recyclerView.setLayoutAnimation(animation);
    }

    private void initLayout()
    {
        // use linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mainActivity);
        this.recyclerView.setLayoutManager(layoutManager);
    }

    // --- --- --- adapter --- --- ---
    private ServerResponse initAdapters()
    {
        try
        {
            this.allRecipeAdapters = new RecipeAdapter[Recipe.Occasion.values().length];

            for(int i = 0; i < allRecipeAdapters.length; i++)
            {
                Recipe.Occasion occasion = Recipe.Occasion.values()[i];
                allRecipeAdapters[i] = new RecipeAdapter(OccasionRecipesList.getInstance(occasion));
            }

            return ServerResponse.SUCCESS;
        }
        catch (ServerCannotBeReachedException ignored)
        {
            return ServerResponse.SERVER_CANNOT_BE_REACHED;
        }
    }

    private void onPostInitAdapters(ServerResponse response)
    {
        switch (response)
        {
            case SERVER_CANNOT_BE_REACHED:
                NotificationMan.showShortToast(this.mainActivity,
                        mainActivity.getResources().getString(R.string.server_cannot_be_reached));
                // TODO
                return;
            case SERVER_RESPONSE_NOT_VALID:
                NotificationMan.showShortToast(this.mainActivity,
                        mainActivity.getResources().getString(R.string.server_cannot_be_reached));
                // TODO
                break;
        }

        this.recyclerView.setAdapter(this.allRecipeAdapters[Recipe.Occasion.MEAL.ordinal()]);
        this.recyclerView.scheduleLayoutAnimation();
    }

    public void swapAdapters(Recipe.Occasion occasion)
    {
        this.recyclerView.swapAdapter(allRecipeAdapters[occasion.ordinal()], false);
        this.recyclerView.scheduleLayoutAnimation();
    }

    private void notifyAllDataSetChanged()
    {
        for(RecipeAdapter recipeAdapter : allRecipeAdapters)
        {
            recipeAdapter.notifyDataSetChanged();
        }
    }

    // --- --- --- Swipe-Refresh-Layout --- --- ---
    private void initSwipeRefreshLayout()
    {
        this.swipeRefreshLayout.setOnRefreshListener(this::handleOnRefreshDragged);
    }

    private void handleOnRefreshDragged()
    {
        this.recyclerView.setEnabled(false);
        new AServerTask(this::loadRecipesInBackground, this::onPostLoadingRecipes).execute();
    }

    private ServerResponse loadRecipesInBackground()
    {
        try
        {
            OccasionRecipesList.loadInstances();
            return ServerResponse.SUCCESS;
        } catch (ServerCannotBeReachedException e)
        {
            return ServerResponse.SERVER_CANNOT_BE_REACHED;
        }
    }

    private void onPostLoadingRecipes(ServerResponse response)
    {
        this.swipeRefreshLayout.setRefreshing(false);

        switch(response)
        {
            case SERVER_CANNOT_BE_REACHED:
                NotificationMan.showShortToast(this.mainActivity,
                        this.mainActivity.getResources().getString(R.string.server_cannot_be_reached));
                // TODO
                break;
            case SERVER_RESPONSE_NOT_VALID:
                NotificationMan.showShortToast(this.mainActivity,
                        this.mainActivity.getResources().getString(R.string.major_error_message));
                // TODO
                break;
        }

        this.recyclerView.setEnabled(true);
        this.notifyAllDataSetChanged();
        this.recyclerView.scheduleLayoutAnimation();
    }
}
