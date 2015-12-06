package gus.lunchbox.app.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
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
        this.server.createContext("/z", new MyHandler());
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

    static class MyHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            String response = "This is the response zzzzz";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

}
