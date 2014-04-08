package main.routing.routes;

import main.requests.IRequest;
import main.response.IResponse;

public interface IRoute {
    public IResponse buildResponse(IRequest request, IResponse response);
}
