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

    protected static final String PARENT_KEY = "parent_key";
    private Class<?> parent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentViewAndAddToolbar(R.layout.activity_recipe_detail);

        this.setParent();

        this.nameTextView = this.findViewById(R.id.nameTextView);
        this.imageView = this.findViewById(R.id.imageView);
        this.descriptionTextView = this.findViewById(R.id.descriptionTextView);
        this.occasionSpinner = this.findViewById(R.id.occasionSpinner);
    }

    private void setParent()
    {
        String parentName = this.getIntent().getStringExtra(PARENT_KEY);
        try
        {
            this.parent = Class.forName(parentName);
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public static void start(Context context, Class<?> parent)
    {
        Intent intent = new Intent(context, RecipeDetailActivity.class);
        addDataToIntent(intent, parent);
        context.startActivity(intent);
    }

    public static void addDataToIntent(Intent intent, Class<?> parent)
    {
        intent.putExtra(PARENT_KEY, parent.getCanonicalName());
    }

    @Override
    protected void onGoBackClicked()
    {
        Intent intent = new Intent(this, this.parent);
        this.startActivity(intent);
    }
}
