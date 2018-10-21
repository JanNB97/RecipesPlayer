package bingemann_software.recipesplayer.tasks;

import android.os.AsyncTask;

import bingemann_software.recipesplayer.http_client.ServerResponse;

public class AServerTask extends AsyncTask<Void, Void, ServerResponse>
{
    private ServerResponseBackgroundRunnable backgroundRunnable;
    private ServerResponsePostExeRunnable onPostExecuteRunnable;

    public AServerTask(ServerResponseBackgroundRunnable backgroundRunnable,
                       ServerResponsePostExeRunnable onPostExecuteRunnable)
    {
        this.backgroundRunnable = backgroundRunnable;
        this.onPostExecuteRunnable = onPostExecuteRunnable;
    }

    public interface ServerResponseBackgroundRunnable
    {
        ServerResponse run();
    }

    public interface ServerResponsePostExeRunnable
    {
        void run(ServerResponse serverResponse);
    }

    @Override
    protected ServerResponse doInBackground(Void... voids)
    {
        return backgroundRunnable.run();
    }

    @Override
    protected void onPostExecute(ServerResponse serverResponse)
    {
        this.onPostExecuteRunnable.run(serverResponse);
    }
}
