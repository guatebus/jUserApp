package gus.lunchbox.app.server;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alejandro.bustamante on 06/12/15.
 */
public class Router {

    private HttpServer server;
    private List<Route> routes;

    public static Router build() {
        return new Router(new ArrayList<>());
    }

    public Router(ArrayList<Route> routes) {
        this.routes = routes;
    }

    public <T extends HttpHandler> Router addRoute(String path, Class<T> c) {
        this.routes.add(new Route(path, c));
        return this;
    }

    public Router setServer(HttpServer server) {
        this.server = server;
        return this;
    }

    public Router loadRoutes() {
        this.routes.stream().forEach((route) -> {
            try {
                this.server.createContext(route.getPath(), route.getHandler().newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return this;
    }

    public class Route {
        private String path;
        private Class<HttpHandler> handler;

        public <T extends HttpHandler> Route(String path, Class<T> handler) {
            this.path = path;
            this.handler = (Class<HttpHandler>)handler;
        }

        public String getPath() {
            return this.path;
        }

        public Class<HttpHandler> getHandler() {
            return this.handler;
        }
    }

}
