package bingemann_software.recipesplayer.activites.ui.recycler_view;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
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
    private MainActivity activity;
    private RecyclerView recyclerView;

    private RecipeAdapter[] allRecipeAdapters;

    public AllRecipesRecyclerView(MainActivity activity, @IdRes int id)
    {
        this.activity = activity;
        this.recyclerView = activity.findViewById(id);

        this.initRecyclerView();
        this.initLayout();

        new AServerTask(this::initAdapters, this::onPostInitAdapters).execute();
    }

    private void onPostInitAdapters(ServerResponse response)
    {
        switch (response)
        {
            case SERVER_CANNOT_BE_REACHED:
                NotificationMan.showShortToast(this.activity,
                        activity.getResources().getString(R.string.server_cannot_be_reached));
                // TODO
                return;
            case SERVER_RESPONSE_NOT_VALID:
                NotificationMan.showShortToast(this.activity,
                        activity.getResources().getString(R.string.server_cannot_be_reached));
                // TODO
                break;
        }

        this.recyclerView.setAdapter(this.allRecipeAdapters[Recipe.Occasion.MEAL.ordinal()]);
        this.recyclerView.scheduleLayoutAnimation();

        this.activity.setEnabled(true);
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

    public void swapAdapters(Recipe.Occasion occasion)
    {
        this.recyclerView.swapAdapter(allRecipeAdapters[occasion.ordinal()], false);
        this.recyclerView.scheduleLayoutAnimation();
    }

    public RecyclerView get()
    {
        return this.recyclerView;
    }
}
