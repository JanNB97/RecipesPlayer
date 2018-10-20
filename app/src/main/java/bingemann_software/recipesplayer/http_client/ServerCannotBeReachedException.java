package bingemann_software.recipesplayer.http_client;

public class ServerCannotBeReachedException extends Exception
{
    public ServerCannotBeReachedException(StackTraceElement[] stackTraceElement)
    {
        super();
        this.setStackTrace(stackTraceElement);
    }
}
