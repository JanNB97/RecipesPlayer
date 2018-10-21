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

import bingemann_software.recipesplayer.MainActivity;
import bingemann_software.recipesplayer.R;
import bingemann_software.recipesplayer.activites.ui.occasion_spinner.OccasionArrayAdapter;
import bingemann_software.recipesplayer.data.Recipe;
import bingemann_software.recipesplayer.http_client.RecipeDbHttpClient;
import bingemann_software.recipesplayer.http_client.ServerResponse;
import bingemann_software.recipesplayer.notifications.NotificationMan;
import bingemann_software.recipesplayer.tasks.AServerTask;
import bingemann_software.recipesplayer.tasks.ATask;

import static bingemann_software.recipesplayer.http_client.ServerResponse.SERVER_CANNOT_BE_REACHED;
import static bingemann_software.recipesplayer.http_client.ServerResponse.SERVER_RESPONSE_NOT_VALID;

public class AddRecipeActivity extends RecipeDetailActivity
{
    protected MenuItem addItem;
    protected Recipe.Occasion selectedOccasion;

    // --- --- --- Initialization --- --- ---
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        this.initNameTextView();
        this.initOccasionSpinner();
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

    // --- --- --- Get Recipe --- --- ---
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
                this.handleClickedOnAddAction();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // --- --- --- Handle actions --- --- ---
    public void handleClickedOnAddAction()
    {
        super.setEnabled(false);

        new AServerTask(
                () -> RecipeDbHttpClient.sendAddRecipe(this.getDisplayedRecipe()),
                this::onPostAddedRecipe
        ).execute();
    }

    private void onPostAddedRecipe(ServerResponse response)
    {
        switch (response)
        {
            case SERVER_CANNOT_BE_REACHED:
                NotificationMan.showShortToast(this, getResources().getString(R.string.server_cannot_be_reached));
                // TODO
                break;
            case SERVER_RESPONSE_NOT_VALID:
                NotificationMan.showShortToast(this, getResources().getString(R.string.major_error_message));
                // TODO
                break;
        }

        MainActivity.start(this);
    }

    // --- --- --- Call from other activity --- --- ---
    public static void start(Context context)
    {
        Intent intent = new Intent(context, AddRecipeActivity.class);
        addDataToIntent(intent, MainActivity.class);
        context.startActivity(intent);
    }
}
