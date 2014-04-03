package main.Routing;

import main.Authorization.Authorizers.Authorization;

import main.Routing.Routes.FileRoute;
import main.Routing.Routes.GetDirectoryRoute;
import main.Routing.Routes.GetParamsRoute;
import main.Routing.Routes.LogsRoute;
import main.Routing.Routes.MethodNotAllowedRoute;
import main.Routing.Routes.OptionsRoute;
import main.Routing.Routes.PostFormDataRoute;
import main.Routing.Routes.PutFormDataRoute;
import main.Routing.Routes.RedirectRoute;
import main.Routing.Routes.SleepRoute;
import main.Routing.Routes.TextRoute;

import main.Server;

public class RouteMappings {
    public static void initializeRoutes() {
        Router.addRoute("GET",     "/",               new TextRoute("Hello World"));
        Router.addRoute("GET",     "/image",          new FileRoute(Server.getDirectory(), "/public/pic.png"));
        Router.addRoute("GET",     "/directory",      new GetDirectoryRoute(Server.getDirectory(), "/public"));
        Router.addRoute("OPTIONS", "/method_options", new OptionsRoute("GET,HEAD,POST,OPTIONS,PUT"));
        Router.addRoute("GET",     "/sleep",          new SleepRoute(1000));
    }

    public static void initializeCobSpecRoutes() {
        Router.addRoute("GET",     "/",                    new GetDirectoryRoute(Server.getDirectory(), "/"));
        Router.addRoute("POST",    "/form",                new PostFormDataRoute());
        Router.addRoute("PUT",     "/form",                new PutFormDataRoute());
        Router.addRoute("OPTIONS", "/method_options",      new OptionsRoute("GET,HEAD,POST,OPTIONS,PUT"));
        Router.addRoute("GET",     "/file1",               new FileRoute(Server.getDirectory(), "/file1"));
        Router.addRoute("GET",     "/image.jpeg",          new FileRoute(Server.getDirectory(), "/image.jpeg"));
        Router.addRoute("GET",     "/image.png",           new FileRoute(Server.getDirectory(), "/image.png"));
        Router.addRoute("GET",     "/image.gif",           new FileRoute(Server.getDirectory(), "/image.gif"));
        Router.addRoute("GET",     "/partial_content.txt", new FileRoute(Server.getDirectory(), "/partial_content.txt"));
        Router.addRoute("PUT",     "/file1",               new MethodNotAllowedRoute());
        Router.addRoute("POST",    "/text-file.txt",       new MethodNotAllowedRoute());
        Router.addRoute("GET",     "/parameters",          new GetParamsRoute());
        Router.addRoute("GET",     "/redirect",            new RedirectRoute("http://localhost:5000/"));
        Router.addRoute("GET",     "/sleep",               new SleepRoute(1000));
        Router.addRoute("GET",     "/logs",                new LogsRoute(new Authorization("admin:hunter2")));
    }
}