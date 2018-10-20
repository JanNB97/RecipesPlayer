package bingemann_software.recipesplayer.googe_image_api;

import java.net.MalformedURLException;
import java.net.URL;

import bingemann_software.recipesplayer.http_client.HttpHelper;
import bingemann_software.recipesplayer.http_client.ServerCannotBeReachedException;

public class GoogleImageURLScraper
{
    public static final String SEARCH_ENGINE_URL = "https://www.googleapis.com/customsearch/v1?key=AIzaSyD2YPHnQZnCociVtywJ4P4dNzR9H334swo&cx=009727475642647432181:6zx8--dz84s&q=";

    public static String findImageURLs()
    {
        String searchword = "burger";
        try
        {
            return HttpHelper.sendGet(new URL(SEARCH_ENGINE_URL + searchword));
        } catch (ServerCannotBeReachedException e)
        {
            e.printStackTrace();
            return null;
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
