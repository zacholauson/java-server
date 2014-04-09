package main.routing.routes;

import main.form.IForm;
import main.requests.IRequest;
import main.response.IResponse;

import java.util.Map;

public class PutFormDataRoute implements IRoute {
    private IForm form;

    public PutFormDataRoute(IForm form) {
        this.form = form;
    }

    public IResponse buildResponse(IRequest request, IResponse response) {
        response.setStatus(200);
        for (Map.Entry<String, String> entry : request.getParams().entrySet()) {
            form.form().put(entry.getKey(), entry.getValue());
        }
        return response;
    }
}
