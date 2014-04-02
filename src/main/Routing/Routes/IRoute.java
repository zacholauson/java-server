package main.Routing.Routes;

import main.Requests.IRequest;
import main.Response.IResponse;

public interface IRoute {
    public IResponse buildResponse(IRequest request, IResponse response);
}
