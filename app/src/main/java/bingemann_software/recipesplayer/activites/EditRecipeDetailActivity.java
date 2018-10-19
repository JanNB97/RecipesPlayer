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

    public static void start(Context context, Class<?> parent)
    {
        Intent intent = new Intent(context, EditRecipeDetailActivity.class);
        addDataToIntent(intent, parent);
        context.startActivity(intent);
    }
}
