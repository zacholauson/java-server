package main;

import main.Routing.Router;
import main.Routing.Routes.FileRoute;
import main.Routing.Routes.GetDirectoryRoute;
import main.Routing.Routes.MethodNotAllowedRoute;
import main.Routing.Routes.OptionsRoute;
import main.Routing.Routes.PostFormData;
import main.Routing.Routes.PutFormData;
import main.Routing.Routes.RedirectRoute;
import main.Routing.Routes.TextRoute;

public class Routes {
    public static void initializeRoutes() {
        Router.addRoute("GET", "/",                   new TextRoute("Hello World"));
        Router.addRoute("GET", "/image",              new FileRoute("/public/pic.png"));
        Router.addRoute("GET", "/directory",          new GetDirectoryRoute(Server.getDirectory(), "/public"));
        Router.addRoute("OPTIONS", "/method_options", new OptionsRoute("GET,HEAD,POST,OPTIONS,PUT"));
    }

    public static void initializeCobSpecRoutes() {
        Router.addRoute("GET",     "/",               new GetDirectoryRoute(Server.getDirectory(), "/"));
        Router.addRoute("POST",    "/form",           new PostFormData());
        Router.addRoute("PUT",     "/form",           new PutFormData());
        Router.addRoute("OPTIONS", "/method_options", new OptionsRoute("GET,HEAD,POST,OPTIONS,PUT"));
        Router.addRoute("GET",     "/file1",          new FileRoute("/file1"));
        Router.addRoute("GET",     "/image.jpeg",     new FileRoute("/image.jpeg"));
        Router.addRoute("GET",     "/image.png",      new FileRoute("/image.png"));
        Router.addRoute("GET",     "/image.gif",      new FileRoute("/image.gif"));
        Router.addRoute("PUT",     "/file1",          new MethodNotAllowedRoute());
        Router.addRoute("POST",    "/text-file.txt",  new MethodNotAllowedRoute());
        Router.addRoute("GET",     "/redirect",       new RedirectRoute("http://localhost:5000/"));
    }
}
