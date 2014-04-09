package main.routing.routes;

import main.form.IForm;
import main.requests.IRequest;
import main.response.IResponse;

import java.util.Map;

public class GetFormDataRoute implements IRoute {
    private IForm form;

    public GetFormDataRoute(IForm form) {
        this.form = form;
    }

    public IResponse buildResponse(IRequest request, IResponse response) {
        response.setStatus(200);
        response.setBody(buildFormString().getBytes());
        return response;
    }

    private String buildFormString() {
        StringBuilder body = new StringBuilder();
        for (Map.Entry<String, String> entry : form.form().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            body.append(key + " = " + value);
            body.append("\r\n");
        }
        return body.toString();
    }
}
