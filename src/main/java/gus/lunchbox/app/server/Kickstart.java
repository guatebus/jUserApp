package gus.lunchbox.app.server;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

/**
 * Created by alejandro.bustamante on 06/12/15.
 */
public class Kickstart
{
    private Configurator configurator;
    private Router router;
    private HttpServer server;

    public Kickstart(Configurator config, Router rtr) {
        this.configurator = config;
        this.router = rtr;
    }

    public static Kickstart build(Configurator config, Router rtr) {
        return new Kickstart(config, rtr);
    }

    public Kickstart createServer() throws Exception {
        this.server = HttpServer.create(new InetSocketAddress(this.configurator.getPort()), this.configurator.getBacklog());
        return this;
    }

    public Kickstart loadRoutes() {
        this.router.
            setServer(this.server).
            loadRoutes();
        return this;
    }

    public Kickstart setDefaultExecutor() {
        this.server.setExecutor(null); // creates a default executor
        return this;
    }

    public Kickstart kickstart() {
        this.server.start();
        return this;
    }

}
