package bingemann_software.recipesplayer;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.view.View;

import bingemann_software.recipesplayer.activites.AddRecipeActivity;
import bingemann_software.recipesplayer.activites.ToolbarActivity;
import bingemann_software.recipesplayer.data.Recipe;
import bingemann_software.recipesplayer.activites.ui.recycler_view.AllRecipesRecyclerView;

public class MainActivity extends ToolbarActivity
{
    private AllRecipesRecyclerView allRecipesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentViewAndAddToolbar(R.layout.activity_main, R.string.main_activity_title);

        this.initTabItems();

        this.allRecipesRecyclerView = new AllRecipesRecyclerView(this, R.id.allRecipesRecyclerView);
    }

    private void initTabItems()
    {
        TabLayout tabLayout = this.findViewById(R.id.occasionTabLayout);

        View view1 = getLayoutInflater().inflate(R.layout.custom_tab, null);
        view1.findViewById(R.id.icon).setBackgroundResource(R.drawable.appetizer_icon_trans_v2);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view1));

        View view2 = getLayoutInflater().inflate(R.layout.custom_tab, null);
        view2.findViewById(R.id.icon).setBackgroundResource(R.drawable.meal_icon_trans);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view2));

        View view3 = getLayoutInflater().inflate(R.layout.custom_tab, null);
        view3.findViewById(R.id.icon).setBackgroundResource(R.drawable.dessert_icon_trans);
        tabLayout.addTab(tabLayout.newTab().setCustomView(view3));

        // select meal tab
        TabLayout.Tab tab = tabLayout.getTabAt(Recipe.Occasion.MEAL.ordinal());
        tab.select();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                handleOnTabSelected(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void handleOnTabSelected(TabLayout.Tab tab)
    {
        int position = tab.getPosition();
        Recipe.Occasion selectedOccasion = Recipe.Occasion.values()[position];
        this.allRecipesRecyclerView.swapAdapters(selectedOccasion);
    }

    public void handleClickOnAddRecipe(View view)
    {
        AddRecipeActivity.start(this);
    }

    // --- --- --- call from other activities --- --- ---
    public static void start(Context context)
    {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
