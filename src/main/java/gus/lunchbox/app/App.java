package gus.lunchbox.app;

import gus.lunchbox.app.server.Configurator;
import gus.lunchbox.app.server.Kickstart;
import gus.lunchbox.app.server.Router;

public class App {

    public static void main(String[] args) throws Exception {
        Kickstart.build(new Configurator(8000, 5), new Router()).
            createServer().
            loadRoutes().
            setDefaultExecutor().
            kickstart()
        ;
    }
}