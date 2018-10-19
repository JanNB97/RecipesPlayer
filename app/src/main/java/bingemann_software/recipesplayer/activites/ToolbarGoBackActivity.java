package bingemann_software.recipesplayer.activites;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import bingemann_software.recipesplayer.MainActivity;
import bingemann_software.recipesplayer.R;
import bingemann_software.recipesplayer.httpClient.RecipeDbHttpClient;

public abstract class ToolbarGoBackActivity extends ToolbarActivity
{
    public static final int ITEM_BACK_ID = 16908332;

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
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected abstract void onGoBackClicked();
}
