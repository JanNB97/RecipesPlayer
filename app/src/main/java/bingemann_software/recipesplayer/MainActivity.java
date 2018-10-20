package bingemann_software.recipesplayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import bingemann_software.recipesplayer.activites.AddRecipeActivity;
import bingemann_software.recipesplayer.activites.ToolbarActivity;
import bingemann_software.recipesplayer.recycler_view.AllRecipesRecyclerView;

public class MainActivity extends ToolbarActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentViewAndAddToolbar(R.layout.activity_main, R.string.main_activity_title);

        AllRecipesRecyclerView allRecipesRecyclerView = new AllRecipesRecyclerView(this, R.id.allRecipesRecyclerView);
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
