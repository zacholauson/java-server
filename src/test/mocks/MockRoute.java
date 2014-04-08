package test.mocks;

import main.requests.IRequest;
import main.response.IResponse;
import main.routing.routes.IRoute;

public class MockRoute implements IRoute {
    public IResponse buildResponse(IRequest request, IResponse response) {
        response.setStatus(200);
        return response;
    }
}
