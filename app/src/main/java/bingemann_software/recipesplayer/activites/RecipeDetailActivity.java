package bingemann_software.recipesplayer.activites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
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

        this.nameTextView = this.findViewById(R.id.nameTextView);
        this.imageView = this.findViewById(R.id.imageView);
        this.descriptionTextView = this.findViewById(R.id.descriptionTextView);
        this.occasionSpinner = this.findViewById(R.id.occasionSpinner);
    }

    public static void start(Context context, Class<?> parent)
    {
        Intent intent = new Intent(context, RecipeDetailActivity.class);
        addDataToIntent(intent, parent);
        context.startActivity(intent);
    }

    @Override
    protected void onGoBackClicked()
    {
        Intent intent = new Intent(this, super.parent);
        this.startActivity(intent);
    }
}
