package bingemann_software.recipesplayer.activites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import bingemann_software.recipesplayer.R;

public class RecipeDetailActivity extends ToolbarGoBackActivity
{
    protected TextView nameTextView;
    protected ImageView imageView;
    protected TextView descriptionTextView;
    protected Spinner occasionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentViewAndAddToolbar(R.layout.activity_recipe_detail);

        this.findViewsById();
    }

    private void findViewsById()
    {
        this.nameTextView = this.findViewById(R.id.nameTextView);
        this.imageView = this.findViewById(R.id.imageView);
        this.descriptionTextView = this.findViewById(R.id.descriptionTextView);
        this.occasionSpinner = this.findViewById(R.id.occasionSpinner);
    }

    protected void setEnabled(boolean enabled)
    {
        this.nameTextView.setEnabled(enabled);
        this.imageView.setEnabled(enabled);
        this.descriptionTextView.setEnabled(enabled);
        this.occasionSpinner.setEnabled(enabled);
    }

    // --- --- --- call from other activity --- --- ---
    public static void start(Context context, Class<?> parent)
    {
        Intent intent = new Intent(context, RecipeDetailActivity.class);
        addDataToIntent(intent, parent);
        context.startActivity(intent);
    }
}
