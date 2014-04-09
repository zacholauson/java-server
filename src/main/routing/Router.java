package main.routing;

import main.requests.IRequest;
import main.routing.routes.IRoute;

import java.util.HashMap;

public class Router {
    public static HashMap<String, IRoute> routesMap = new HashMap<>();
    public static IRoute fourOhFourRoute;

    public static void addRoute(String method, String route, IRoute router) {
        routesMap.put(method + " " + route, router);
    }

    public static void addFourOhFourRoute(IRoute route) {
        fourOhFourRoute = route;
    }

    public static IRoute route(IRequest request) {
        IRoute route;
        route = routesMap.get(methodRoute(request));
        if (route == null) {
            route = fourOhFourRoute;
        }

        return route;
    }

    private static String methodRoute(IRequest request) {
        return request.getMethod() + " " + request.getRoute();
    }
}
