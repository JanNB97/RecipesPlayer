package bingemann_software.recipesplayer.activites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import bingemann_software.recipesplayer.MainActivity;
import bingemann_software.recipesplayer.R;
import bingemann_software.recipesplayer.data.Recipe;
import bingemann_software.recipesplayer.httpClient.RecipeDbHttpClient;

public class AddRecipeActivity extends RecipeDetailActivity
{
    protected MenuItem addItem;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    private Recipe getDisplayedRecipe()
    {
        // TODO - creator
        String name = nameTextView.getText().toString();
        if(name.isEmpty())
        {
            // TODO - enable field only if name not empty
            return null;
        }

        Recipe recipe = new Recipe(new Recipe.Creator("Jan"), name);
        String description = descriptionTextView.getText().toString();
        if(!description.isEmpty())
        {
           recipe.setDescription(description);
        }

        // TODO - occasion spinner
        return recipe;
    }

    // --- --- --- Option menu --- --- ---
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
                new Thread(() -> {
                    RecipeDbHttpClient.sendAddRecipe(this.getDisplayedRecipe());
                }).start();
                MainActivity.start(this);
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
