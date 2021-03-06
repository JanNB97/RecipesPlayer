package bingemann_software.recipesplayer.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

public abstract class ToolbarGoBackActivity extends ToolbarActivity
{
    public static final int ITEM_BACK_ID = 16908332;

    protected static final String PARENT_KEY = "parent_key";
    protected Class<?> parent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setParent();
    }

    private void setParent()
    {
        String parentName = this.getIntent().getStringExtra(PARENT_KEY);
        if(parentName == null)
        {
            // use addDataToIntent to send parent-activity-name to your activity
            throw new NullPointerException();
        }

        try
        {
            this.parent = Class.forName(parentName);
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        super.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case ITEM_BACK_ID:
                this.onGoBackClicked();
                Intent intent = new Intent(this, this.parent);
                this.startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void onGoBackClicked(){}

    public static void addDataToIntent(Intent intent, Class<?> parent)
    {
        intent.putExtra(PARENT_KEY, parent.getCanonicalName());
    }
}
