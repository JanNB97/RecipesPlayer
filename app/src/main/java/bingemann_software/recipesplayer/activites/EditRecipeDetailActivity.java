package bingemann_software.recipesplayer.activites;

import android.content.Context;
import android.content.Intent;

public class EditRecipeDetailActivity extends RecipeDetailActivity
{
    @Override
    protected void modifyToolbar()
    {
        // TODO
    }

    public static void start(Context context)
    {
        Intent intent = new Intent(context, EditRecipeDetailActivity.class);
        context.startActivity(intent);
    }
}
