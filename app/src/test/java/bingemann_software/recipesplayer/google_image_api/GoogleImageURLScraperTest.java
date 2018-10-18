package bingemann_software.recipesplayer.google_image_api;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

import bingemann_software.recipesplayer.googe_image_api.GoogleImageURLScraper;

public class GoogleImageURLScraperTest
{
    @Test
    public void testfindImageURLs()
    {
        String result = GoogleImageURLScraper.findImageURLs();
        JSONParser parser = new JSONParser();

        JSONObject json;
        try
        {
            json = (JSONObject) parser.parse(result);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
    }
}
