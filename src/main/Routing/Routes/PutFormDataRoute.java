package main.Routing.Routes;

import main.Form.IForm;
import main.Requests.IRequest;
import main.Response.IResponse;

import java.util.Map;

public class PutFormDataRoute implements IRoute {
    private IForm form;

    public PutFormDataRoute(IForm _form) {
        form = _form;
    }

    public IResponse buildResponse(IRequest request, IResponse response) {
        response.setStatus(200);
        for (Map.Entry<String, String> entry : request.getParams().entrySet()) {
            form.form().put(entry.getKey(), entry.getValue());
        }
        return response;
    }
}
