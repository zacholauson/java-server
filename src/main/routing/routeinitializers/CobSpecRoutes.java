package main.routing.routeinitializers;

import main.authorization.authorizers.BasicAuthorization;
import main.form.IForm;
import main.form.forms.BaseForm;
import main.logging.ILogger;
import main.routing.IRouteInitializer;
import main.routing.IRouter;
import main.routing.routes.DeleteFormDataRoute;
import main.routing.routes.FileRoute;
import main.routing.routes.FourOhFourRoute;
import main.routing.routes.GetDirectoryRoute;
import main.routing.routes.GetFormDataRoute;
import main.routing.routes.GetParamsRoute;
import main.routing.routes.LogsRoute;
import main.routing.routes.MethodNotAllowedRoute;
import main.routing.routes.OptionsRoute;
import main.routing.routes.PostFormDataRoute;
import main.routing.routes.PutFormDataRoute;
import main.routing.routes.RedirectRoute;
import main.routing.routes.SleepRoute;

public class CobSpecRoutes extends BaseRoutes implements IRouteInitializer {
    public CobSpecRoutes(IRouter router, ILogger logger, String baseDirectory) {
        super(router, logger, baseDirectory);
    }

    @Override
    public void init() {
        router.addFourOhFourRoute(new FourOhFourRoute());

        router.addRoute("GET",        "/",                    new GetDirectoryRoute(baseDirectory, "/"));

        IForm baseForm = new BaseForm();
        router.addRoute("GET",        "/form",                new GetFormDataRoute(baseForm));
        router.addRoute("POST",       "/form",                new PostFormDataRoute(baseForm));
        router.addRoute("PUT",        "/form",                new PutFormDataRoute(baseForm));
        router.addRoute("DELETE",     "/form",                new DeleteFormDataRoute(baseForm));

        router.addRoute("OPTIONS",    "/method_options",      new OptionsRoute("GET,HEAD,POST,OPTIONS,PUT"));
        router.addRoute("GET",        "/file1",               new FileRoute(baseDirectory, "/file1"));
        router.addRoute("GET",        "/image.jpeg",          new FileRoute(baseDirectory, "/image.jpeg"));
        router.addRoute("GET",        "/image.png",           new FileRoute(baseDirectory, "/image.png"));
        router.addRoute("GET",        "/image.gif",           new FileRoute(baseDirectory, "/image.gif"));
        router.addRoute("GET",        "/partial_content.txt", new FileRoute(baseDirectory, "/partial_content.txt"));
        router.addRoute("PUT",        "/file1",               new MethodNotAllowedRoute());
        router.addRoute("POST",       "/text-file.txt",       new MethodNotAllowedRoute());
        router.addRoute("GET",        "/parameters",          new GetParamsRoute());
        router.addRoute("GET",        "/redirect",            new RedirectRoute("http://localhost:5000/"));
        router.addRoute("GET",        "/sleep",               new SleepRoute(1000));
        router.addRoute("GET",        "/logs",                new LogsRoute(new BasicAuthorization("admin:hunter2"), logger));
    }
}
