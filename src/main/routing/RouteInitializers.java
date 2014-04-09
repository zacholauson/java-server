package main.routing;

import main.authorization.authorizers.BasicAuthorization;

import main.form.IForm;
import main.form.forms.BaseForm;

import main.logging.ILogger;
import main.routing.routes.*;

public class RouteInitializers {
    public static void basicRoutes(IRouter router, String baseDirectory, ILogger logger) {
        router.addFourOhFourRoute(new FourOhFourRoute());

        router.addRoute("GET",     "/",               new TextRoute("Hello World"));
        router.addRoute("GET",     "/image",          new FileRoute(baseDirectory, "/public/pic.png"));
        router.addRoute("GET",     "/directory",      new GetDirectoryRoute(baseDirectory, "/public"));
        router.addRoute("OPTIONS", "/method_options", new OptionsRoute("GET,HEAD,POST,OPTIONS,PUT"));
        router.addRoute("GET",     "/sleep",          new SleepRoute(1000));
    }

    public static void cobSpecRoutes(IRouter router, String baseDirectory, ILogger logger) {
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
