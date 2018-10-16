package bingemann_software.recipesplayer.activites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import bingemann_software.recipesplayer.R;

public class RecipeDetailActivity extends ToolbarActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentViewAndAddToolbar(R.layout.activity_recipe_detail);
    }

    public static void start(Context context)
    {
        Intent intent = new Intent(context, RecipeDetailActivity.class);
        context.startActivity(intent);
    }
}
