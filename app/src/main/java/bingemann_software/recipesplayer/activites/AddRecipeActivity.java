package bingemann_software.recipesplayer.activites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import bingemann_software.recipesplayer.R;

public class AddRecipeActivity extends RecipeDetailActivity
{
    protected MenuItem addItem;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_toolbar_items, menu);

        this.addItem = menu.findItem(R.id.action_add);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_add:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    public static void start(Context context)
    {
        Intent intent = new Intent(context, AddRecipeActivity.class);
        context.startActivity(intent);
    }
}
