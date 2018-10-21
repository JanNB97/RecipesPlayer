package bingemann_software.recipesplayer.activites.ui.occasion_spinner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import bingemann_software.recipesplayer.R;
import bingemann_software.recipesplayer.data.Recipe;

public class OccasionArrayAdapter extends ArrayAdapter<Recipe.Occasion>
{
    public OccasionArrayAdapter(Context context)
    {
        super(context, 0, Recipe.Occasion.values());
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Recipe.Occasion occasion = Recipe.Occasion.values()[position];

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.occasion_spinner_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.occasionImageView);
        imageView.setImageResource(occasion.getIcon());

        TextView occasionDescriptionTextView = convertView.findViewById(R.id.occasionSpinnerTextView);
        occasionDescriptionTextView.setText(occasion.getValue());

        return convertView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Recipe.Occasion occasion = Recipe.Occasion.values()[position];

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.occasion_spinner_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.occasionImageView);
        imageView.setImageResource(occasion.getIcon());

        return convertView;
    }
}
