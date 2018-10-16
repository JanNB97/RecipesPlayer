package bingemann_software.recipesplayer.activites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class EditRecipeDetailActivity extends RecipeDetailActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public static void start(Context context)
    {
        Intent intent = new Intent(context, EditRecipeDetailActivity.class);
        context.startActivity(intent);
    }
}
