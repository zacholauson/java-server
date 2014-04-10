package main.requestpackage;

import main.requests.IRequest;
import main.routing.routes.IRoute;

public interface IRequestPackage {
    public IRequest request();
    public IRoute routeRequest();
    public void logRequest();
}
