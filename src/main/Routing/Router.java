package main.Routing;

import main.Requests.IRequest;
import main.Response.IResponse;
import main.Routing.Routes.FourOhFourRoute;
import main.Routing.Routes.IRoute;

import java.util.HashMap;

public class Router {
    public static HashMap<String, IRoute> routesMap = new HashMap<>();

    public static void addRoute(String _method, String _route, IRoute _router) {
        routesMap.put(_method + " " + _route, _router);
    }

    public static IResponse route(IRequest request, IResponse response) {
        try {
            response = routesMap.get(request.getMethod() + " " + request.getRoute()).buildResponse(request, response);
        } catch (Exception e) {
            response = new FourOhFourRoute().buildResponse(request, response);
        }
        return response;
    }
}
