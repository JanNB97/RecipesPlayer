package bingemann_software.recipesplayer.activites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import bingemann_software.recipesplayer.MainActivity;
import bingemann_software.recipesplayer.R;
import bingemann_software.recipesplayer.activites.occasion_spinner.OccasionArrayAdapter;
import bingemann_software.recipesplayer.data.Recipe;
import bingemann_software.recipesplayer.http_client.RecipeDbHttpClient;

public class AddRecipeActivity extends RecipeDetailActivity
{
    protected MenuItem addItem;
    protected Recipe.Occasion selectedOccasion;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        this.initNameTextView();
        this.initOccasionSpinner();
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

        if(this.selectedOccasion != Recipe.Occasion.MEAL)
        {
            recipe.setOccasion(this.selectedOccasion);
        }

        // TODO - occasion spinner
        return recipe;
    }

    private void initNameTextView()
    {
        this.nameTextView.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence newText, int i, int i1, int i2)
            {
                setAddActionButtonEnabled(newText.length() != 0);
            }

            @Override
            public void afterTextChanged(Editable editable){}
        });
    }

    private void initOccasionSpinner()
    {
        super.occasionSpinner.setAdapter(new OccasionArrayAdapter(this));

        super.occasionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                selectedOccasion = Recipe.Occasion.values()[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        super.occasionSpinner.setSelection(Recipe.Occasion.MEAL.ordinal());
    }

    // --- --- --- Action menu --- --- ---
    private void setAddActionButtonEnabled(boolean enabled)
    {
        this.addItem.setVisible(enabled);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        addItem = menu.findItem(R.id.action_add);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_add:
                // TODO make task
                new Thread(() ->
                    RecipeDbHttpClient.sendAddRecipe(this.getDisplayedRecipe())
                ).start();
                MainActivity.start(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static void start(Context context)
    {
        Intent intent = new Intent(context, AddRecipeActivity.class);
        addDataToIntent(intent, MainActivity.class);
        context.startActivity(intent);
    }
}
