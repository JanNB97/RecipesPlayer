package bingemann_software.recipesplayer.activites;

import android.content.Context;
import android.content.Intent;

public class AddRecipeActivity extends RecipeDetailActivity
{
    @Override
    protected void modifyToolbar()
    {
        // TODO
    }

    public static void start(Context context)
    {
        Intent intent = new Intent(context, AddRecipeActivity.class);
        context.startActivity(intent);
    }
}
