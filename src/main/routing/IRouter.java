package main.routing;

import main.requests.IRequest;
import main.routing.routes.IRoute;

import java.util.HashMap;

public interface IRouter {
    public void addRoute(String method, String route, IRoute router);
    public void addFourOhFourRoute(IRoute route);
    public IRoute route(IRequest request);
    public HashMap<String, IRoute> routes();
    public IRoute fourOhFourRoute();
}
