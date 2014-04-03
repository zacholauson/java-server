package main.Routing.Routes;

import main.Requests.IRequest;
import main.Response.IResponse;
import main.Response.ResponseCodes;

import java.util.HashMap;
import java.util.Map;

public class GetParamsRoute implements IRoute {
    public GetParamsRoute() {}

    public IResponse buildResponse(IRequest request, IResponse response) {
        response.setHeaders(ResponseCodes.codeString(200) + "\r\n");
        try {
            StringBuilder keyValuePairs = new StringBuilder();
            HashMap<String, String> results = request.getParams();
            for (Map.Entry<String, String> entry : results.entrySet() ) {
                keyValuePairs.append(entry.getKey() + " = " + entry.getValue());
                keyValuePairs.append("<br>");
            }
            response.setBody(("<!DOCTYPE html><html><body>" + keyValuePairs.toString() + "</body></html>").getBytes());
        } catch (Exception exception) {
            System.out.println(exception);
        }

        return response;
    }
}
