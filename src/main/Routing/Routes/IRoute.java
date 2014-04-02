package main.Routing.Routes;

import main.Response.IResponse;

public interface IRoute {
    public IResponse buildResponse(IResponse response);
}
