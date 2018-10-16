package bingemann_software.recipesplayer.httpClient;

public class ServerCannotBeReachedException extends Exception
{
    public ServerCannotBeReachedException(StackTraceElement[] stackTraceElement)
    {
        super();
        this.setStackTrace(stackTraceElement);
    }
}
