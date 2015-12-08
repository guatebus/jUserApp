package gus.lunchbox.app;

import gus.lunchbox.app.handler.LoginHandler;
import gus.lunchbox.app.handler.LogoutHandler;
import gus.lunchbox.app.handler.ResourceHandler;
import gus.lunchbox.app.server.Configurator;
import gus.lunchbox.app.server.Kickstart;
import gus.lunchbox.app.server.Router;

public class App {

    public static void main(String[] args) throws Exception {

        //This object should be built dynamically from a config file
        Router router = Router.build().
            addRoute("/login", LoginHandler.class).
            addRoute("/logout", LogoutHandler.class).
            addRoute("/resource", ResourceHandler.class)
        ;

        Kickstart.build(router, new Configurator(8000, 5)).
            createServer().
            loadRoutes().
            setDefaultExecutor().
            kickstart()
        ;
    }
}