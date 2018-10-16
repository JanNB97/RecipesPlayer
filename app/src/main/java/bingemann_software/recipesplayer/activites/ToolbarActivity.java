package bingemann_software.recipesplayer.activites;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import bingemann_software.recipesplayer.R;

public class ToolbarActivity extends AppCompatActivity
{
    protected void setContentViewAndAddToolbar(@LayoutRes int layoutResID)
    {
        this.setContentView(layoutResID);
        this.addToolbar();
    }

    private void addToolbar()
    {
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }
}
