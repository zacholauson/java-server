package main.routing.routes;

import main.form.IForm;
import main.requests.IRequest;
import main.response.IResponse;

import java.util.Map;

public class PostFormDataRoute implements IRoute {
    private IForm form;

    public PostFormDataRoute(IForm form) {
        this.form = form;
    }

    public IResponse buildResponse(IRequest request, IResponse response) {
        response.setStatus(200);
        for (Map.Entry<String, String> entry : request.getParams().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            form.form().put(key, value);
        }
        return response;
    }
}
