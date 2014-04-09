package main.routing;

import main.authorization.authorizers.BasicAuthorization;

import main.form.IForm;
import main.form.forms.BaseForm;

import main.logging.ILogger;
import main.routing.routes.*;

public class RouteInitializers {
    public static void basicRoutes(String baseDirectory, ILogger logger) {
        Router.addFourOhFourRoute(new FourOhFourRoute());

        Router.addRoute("GET",     "/",               new TextRoute("Hello World"));
        Router.addRoute("GET",     "/image",          new FileRoute(baseDirectory, "/public/pic.png"));
        Router.addRoute("GET",     "/directory",      new GetDirectoryRoute(baseDirectory, "/public"));
        Router.addRoute("OPTIONS", "/method_options", new OptionsRoute("GET,HEAD,POST,OPTIONS,PUT"));
        Router.addRoute("GET",     "/sleep",          new SleepRoute(1000));
    }

    public static void cobSpecRoutes(String baseDirectory, ILogger logger) {
        Router.addFourOhFourRoute(new FourOhFourRoute());

        Router.addRoute("GET",        "/",                    new GetDirectoryRoute(baseDirectory, "/"));

        IForm baseForm = new BaseForm();
        Router.addRoute("GET",        "/form",                new GetFormDataRoute(baseForm));
        Router.addRoute("POST",       "/form",                new PostFormDataRoute(baseForm));
        Router.addRoute("PUT",        "/form",                new PutFormDataRoute(baseForm));
        Router.addRoute("DELETE",     "/form",                new DeleteFormDataRoute(baseForm));

        Router.addRoute("OPTIONS",    "/method_options",      new OptionsRoute("GET,HEAD,POST,OPTIONS,PUT"));
        Router.addRoute("GET",        "/file1",               new FileRoute(baseDirectory, "/file1"));
        Router.addRoute("GET",        "/image.jpeg",          new FileRoute(baseDirectory, "/image.jpeg"));
        Router.addRoute("GET",        "/image.png",           new FileRoute(baseDirectory, "/image.png"));
        Router.addRoute("GET",        "/image.gif",           new FileRoute(baseDirectory, "/image.gif"));
        Router.addRoute("GET",        "/partial_content.txt", new FileRoute(baseDirectory, "/partial_content.txt"));
        Router.addRoute("PUT",        "/file1",               new MethodNotAllowedRoute());
        Router.addRoute("POST",       "/text-file.txt",       new MethodNotAllowedRoute());
        Router.addRoute("GET",        "/parameters",          new GetParamsRoute());
        Router.addRoute("GET",        "/redirect",            new RedirectRoute("http://localhost:5000/"));
        Router.addRoute("GET",        "/sleep",               new SleepRoute(1000));
        Router.addRoute("GET",        "/logs",                new LogsRoute(new BasicAuthorization("admin:hunter2"), logger));
    }
}
