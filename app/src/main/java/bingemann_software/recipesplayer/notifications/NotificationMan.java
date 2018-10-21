package bingemann_software.recipesplayer.notifications;

import android.content.Context;
import android.widget.Toast;

public class NotificationMan
{
    public static void showShortToast(Context context, String text)
    {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
