package main.routing.routes;

import main.form.IForm;
import main.requests.IRequest;
import main.response.IResponse;

import java.util.Map;

public class DeleteFormDataRoute implements IRoute {
    private IForm form;

    public DeleteFormDataRoute(IForm form) {
        this.form = form;
    }

    public IResponse buildResponse(IRequest request, IResponse response) {
        response.setStatus(200);
        for (Map.Entry<String, String> entry : request.getParams().entrySet()) {
            form.form().remove(entry.getKey());
        }
        return response;
    }
}
