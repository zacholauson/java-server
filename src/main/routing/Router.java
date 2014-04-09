package main.routing;

import main.requests.IRequest;
import main.routing.routes.IRoute;

import java.util.HashMap;

public class Router implements IRouter {
    public HashMap<String, IRoute> routesMap = new HashMap<>();
    public IRoute fourOhFourRoute;

    public void addRoute(String method, String route, IRoute router) {
        routesMap.put(method + " " + route, router);
    }

    public void addFourOhFourRoute(IRoute route) {
        fourOhFourRoute = route;
    }

    public IRoute route(IRequest request) {
        IRoute route;
        route = routesMap.get(methodRoute(request));
        if (route == null) { route = fourOhFourRoute; }
        return route;
    }

    public HashMap<String, IRoute> routes() {
        return routesMap;
    }

    public IRoute fourOhFourRoute() {
        return fourOhFourRoute;
    }

    private String methodRoute(IRequest request) {
        return request.getMethod() + " " + request.getRoute();
    }
}
