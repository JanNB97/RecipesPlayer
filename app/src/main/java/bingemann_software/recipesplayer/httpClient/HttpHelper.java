package bingemann_software.recipesplayer.httpClient;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class HttpHelper
{
    public static JSONObject sendGet(URL url) throws ServerCannotBeReachedException
    {
        if(url == null)
        {
            return null;
        }

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response;
        try
        {
            response = client.newCall(request).execute();
        } catch (IOException e)
        {
            throw new ServerCannotBeReachedException(e.getStackTrace());
        }

        try
        {
            String jsonData = response.body().string();
            return new JSONObject(jsonData);
        } catch (IOException | JSONException e)
        {
            // string could not be decoded or was not in json-format
            e.printStackTrace();
            return null;
        }
    }
}
