package main.routing;

import main.authorization.authorizers.BasicAuthorization;

import main.form.forms.BaseForm;
import main.form.IForm;

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
import main.routing.routes.TextRoute;

import main.Server;

public class RouteInitializers {
    public static void basicRoutes() {
        Router.addFourOhFourRoute(new FourOhFourRoute());

        Router.addRoute("GET",     "/",               new TextRoute("Hello World"));
        Router.addRoute("GET",     "/image",          new FileRoute(Server.getDirectory(), "/public/pic.png"));
        Router.addRoute("GET",     "/directory",      new GetDirectoryRoute(Server.getDirectory(), "/public"));
        Router.addRoute("OPTIONS", "/method_options", new OptionsRoute("GET,HEAD,POST,OPTIONS,PUT"));
        Router.addRoute("GET",     "/sleep",          new SleepRoute(1000));
    }

    public static void cobSpecRoutes() {
        Router.addFourOhFourRoute(new FourOhFourRoute());

        Router.addRoute("GET",        "/",                    new GetDirectoryRoute(Server.getDirectory(), "/"));

        IForm baseForm = new BaseForm();
        Router.addRoute("GET",        "/form",                new GetFormDataRoute(baseForm));
        Router.addRoute("POST",       "/form",                new PostFormDataRoute(baseForm));
        Router.addRoute("PUT",        "/form",                new PutFormDataRoute(baseForm));
        Router.addRoute("DELETE",     "/form",                new DeleteFormDataRoute(baseForm));

        Router.addRoute("OPTIONS",    "/method_options",      new OptionsRoute("GET,HEAD,POST,OPTIONS,PUT"));
        Router.addRoute("GET",        "/file1",               new FileRoute(Server.getDirectory(), "/file1"));
        Router.addRoute("GET",        "/image.jpeg",          new FileRoute(Server.getDirectory(), "/image.jpeg"));
        Router.addRoute("GET",        "/image.png",           new FileRoute(Server.getDirectory(), "/image.png"));
        Router.addRoute("GET",        "/image.gif",           new FileRoute(Server.getDirectory(), "/image.gif"));
        Router.addRoute("GET",        "/partial_content.txt", new FileRoute(Server.getDirectory(), "/partial_content.txt"));
        Router.addRoute("PUT",        "/file1",               new MethodNotAllowedRoute());
        Router.addRoute("POST",       "/text-file.txt",       new MethodNotAllowedRoute());
        Router.addRoute("GET",        "/parameters",          new GetParamsRoute());
        Router.addRoute("GET",        "/redirect",            new RedirectRoute("http://localhost:5000/"));
        Router.addRoute("GET",        "/sleep",               new SleepRoute(1000));
        Router.addRoute("GET",        "/logs",                new LogsRoute(new BasicAuthorization("admin:hunter2")));
    }
}
