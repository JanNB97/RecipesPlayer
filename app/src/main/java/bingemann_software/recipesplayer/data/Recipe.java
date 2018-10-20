package bingemann_software.recipesplayer.data;

import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;

import java.net.MalformedURLException;
import java.net.URL;

import bingemann_software.recipesplayer.R;

public class Recipe
{
    private int id;
    private Creator creator;
    private String name;

    private String description;
    private URL pictureURL;
    private Occasion occasion;    // meal, desert, ...

    public enum Occasion
    {
        APPETIZER("Vorspeise", R.drawable.appetizer_icon_trans_v2),
        MEAL("Hauptspeise", R.drawable.meal_icon_trans),
        DESERT("Dessert", R.drawable.dessert_icon_trans);

        String value;
        @DrawableRes int icon;
        Occasion(String value, @DrawableRes int icon)
        {
            this.value = value;
            this.icon = icon;
        }

        public String getValue()
        {
            return value;
        }

        public int getIcon()
        {
            return icon;
        }
    }

    public static class Creator
    {
        private String name;
        private int icon;

        public Creator(String name)
        {
            this.name = name;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public int getIcon()
        {
            return icon;
        }

        public void setIcon(int icon)
        {
            this.icon = icon;
        }
    }

    public Recipe(Creator creator, String name)
    {
        this.creator = creator;
        this.name = name;

        this.description = "";
        this.occasion = Occasion.MEAL;
        // TODO set standart for pictureULR - google image search
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public Creator getCreator()
    {
        return creator;
    }

    public void setCreator(Creator creator)
    {
        this.creator = creator;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public URL getPictureURL()
    {
        return pictureURL;
    }

    public void setPictureURL(URL pictureURL)
    {
        this.pictureURL = pictureURL;
    }

    public Occasion getOccasion()
    {
        return occasion;
    }

    public void setOccasion(Occasion occasion)
    {
        this.occasion = occasion;
    }
}
