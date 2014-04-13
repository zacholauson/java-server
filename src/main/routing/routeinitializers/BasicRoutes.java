package main.routing.routeinitializers;

import main.logging.ILogger;
import main.routing.IRouteInitializer;
import main.routing.IRouter;
import main.routing.routes.FileRoute;
import main.routing.routes.FourOhFourRoute;
import main.routing.routes.GetDirectoryRoute;
import main.routing.routes.OptionsRoute;
import main.routing.routes.SleepRoute;
import main.routing.routes.TextRoute;

public class BasicRoutes extends BaseRoutes implements IRouteInitializer {
    public BasicRoutes(IRouter router, ILogger logger, String baseDirectory) {
        super(router, logger,  baseDirectory);
    }

    @Override
    public void init() {
        router.addFourOhFourRoute(new FourOhFourRoute());

        router.addRoute("GET",     "/",               new TextRoute("Hello World"));
        router.addRoute("GET",     "/image",          new FileRoute(baseDirectory, "/public/pic.png"));
        router.addRoute("GET",     "/directory",      new GetDirectoryRoute(baseDirectory, "/public"));
        router.addRoute("OPTIONS", "/method_options", new OptionsRoute("GET,HEAD,POST,OPTIONS,PUT"));
        router.addRoute("GET",     "/sleep",          new SleepRoute(1000));
    }
}
