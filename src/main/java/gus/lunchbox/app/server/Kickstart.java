package gus.lunchbox.app.server;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

/**
 * Created by alejandro.bustamante on 06/12/15.
 */
public class Kickstart
{
    private Router router;
    private Configurator configurator;
    private HttpServer server;

    public Kickstart(Router rtr, Configurator config) {
        this.router = rtr;
        this.configurator = config;
    }

    public static Kickstart build(Router rtr, Configurator config) {
        return new Kickstart(rtr, config);
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
