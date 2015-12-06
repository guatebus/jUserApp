package gus.lunchbox.app.server;

/**
 * Created by alejandro.bustamante on 06/12/15.
 */
public class Configurator {

    private int port;

    private int backlog;

    public int getBacklog() {
        return backlog;
    }

    public int getPort() {
        return port;
    }

    public Configurator(int port, int backlog) {
        this.port = port;
        this.backlog = backlog;
    }

    public Configurator(String configFile) {
        //setup of class members from config file read goes here...
    }
}
