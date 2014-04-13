package main.routing.routeinitializers;

import main.logging.ILogger;
import main.routing.IRouter;
import main.routing.routes.FourOhFourRoute;

public class BaseRoutes {
    protected IRouter router;
    protected String  baseDirectory;
    protected ILogger logger;

    public BaseRoutes(IRouter router, ILogger logger, String baseDirectory) {
        this.router        = router;
        this.logger        = logger;
        this.baseDirectory = baseDirectory;
    }

    public void init() {
        router.addFourOhFourRoute(new FourOhFourRoute());
    }
}
