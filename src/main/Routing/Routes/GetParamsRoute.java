package main.routing.routes;

import main.requests.IRequest;
import main.response.IResponse;

import java.util.HashMap;
import java.util.Map;

public class GetParamsRoute implements IRoute {
    public GetParamsRoute() {}

    public IResponse buildResponse(IRequest request, IResponse response) {
        response.setStatus(200);
        try {
            StringBuilder keyValuePairs = new StringBuilder();
            HashMap<String, String> results = request.getParams();
            for (Map.Entry<String, String> entry : results.entrySet()) {
                keyValuePairs.append(entry.getKey() + " = " + entry.getValue());
            }
            response.setBody((keyValuePairs.toString()).getBytes());
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return response;
    }
}
