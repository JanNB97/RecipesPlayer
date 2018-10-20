package bingemann_software.recipesplayer;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import bingemann_software.recipesplayer.activites.AddRecipeActivity;
import bingemann_software.recipesplayer.activites.RecipeDetailActivity;
import bingemann_software.recipesplayer.activites.ToolbarActivity;
import bingemann_software.recipesplayer.data.Recipe;
import bingemann_software.recipesplayer.httpClient.RecipeDbHttpClient;

public class MainActivity extends ToolbarActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
