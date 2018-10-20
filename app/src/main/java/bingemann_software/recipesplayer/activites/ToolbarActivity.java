package bingemann_software.recipesplayer.activites;

import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import bingemann_software.recipesplayer.R;

public abstract class ToolbarActivity extends AppCompatActivity
{
    protected void setContentViewAndAddToolbar(@LayoutRes int layoutResID)
    {
        this.setContentView(layoutResID);
        this.addToolbar();
    }

    protected void setContentViewAndAddToolbar(@LayoutRes int layoutResID, @StringRes int titleRes)
    {
        this.setContentView(layoutResID);
        this.addToolbar();
        this.getSupportActionBar().setTitle(titleRes);
    }

    private void addToolbar()
    {
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_items, menu);

        return true;
    }
}
