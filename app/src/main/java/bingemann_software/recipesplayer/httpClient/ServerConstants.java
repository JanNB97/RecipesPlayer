package bingemann_software.recipesplayer.httpClient;

public final class ServerConstants
{
    public static final String SERVER_IP = "192.168.0.143";
    public static final String REQUEST_PORT = "8080";

    public static final String DB_URL = "http://" + SERVER_IP + ":" + REQUEST_PORT + "/recipes-db";

    public static final String ALL_URL = DB_URL + "/all";
    public static final String ADD_URL = DB_URL + "/add?";
    public static final String UPDATE_URL = DB_URL + "/update?";
    public static final String DELETE_URL = DB_URL + "/delete?";

    public static final String ALL_CLIENTS_URL = DB_URL + "/all-clients";
    public static final String SET_ICON_URL = DB_URL + "/set-icon?";
    public static final String GET_ICON_URL = DB_URL + "/get-icon?";
    public static final String IS_UP_TO_DATE_URL = DB_URL + "/is-up-to-date?";
    public static final String LAST_TIME_CHANGED = DB_URL + "/last-time-changed?";

    public static final String CREATOR_NAME_KEY = "creator";
    public static final String NAME_KEY = "name";
    public static final String OCCASION_KEY = "occasion";
    public static final String DESCRIPTION_KEY = "description";
    public static final String PICTURE_URL = "pictureURL";
}
