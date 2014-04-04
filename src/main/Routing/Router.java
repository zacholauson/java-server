package main.Routing;

import main.Requests.IRequest;
import main.Response.IResponse;
import main.Routing.Routes.IRoute;

import java.util.HashMap;

public class Router {
    public static HashMap<String, IRoute> routesMap = new HashMap<>();
    public static IRoute fourOhFourRoute;

    public static void addRoute(String _method, String _route, IRoute _router) {
        routesMap.put(_method + " " + _route, _router);
    }

    public static void addFourOhFourRoute(IRoute route) {
        fourOhFourRoute = route;
    }

    public static IResponse route(IRequest request, IResponse response) {
        try {
            response = routesMap.get(methodRoute(request)).buildResponse(request, response);
        } catch (Exception e) {
            response = fourOhFourRoute.buildResponse(request, response);
        }
        return response;
    }

    private static String methodRoute(IRequest request) {
        return request.getMethod() + " " + request.getRoute();
    }
}
