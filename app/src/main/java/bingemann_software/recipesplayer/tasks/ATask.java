package bingemann_software.recipesplayer.tasks;

import android.os.AsyncTask;

public class ATask extends AsyncTask<Void, Void, Void>
{
    private Runnable runInBackground;
    private Runnable runOnPostExecute;

    public ATask(Runnable runInBackground, Runnable runOnPostExecute)
    {
        this.runInBackground = runInBackground;
        this.runOnPostExecute = runOnPostExecute;
    }

    @Override
    protected Void doInBackground(Void... voids)
    {
        this.runInBackground.run();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        this.runOnPostExecute.run();
    }
}
