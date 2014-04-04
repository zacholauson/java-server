package main.Routing.Routes;

import main.Form.IForm;
import main.Requests.IRequest;
import main.Response.IResponse;

import java.util.Map;

public class GetFormDataRoute implements IRoute {
    private IForm form;

    public GetFormDataRoute(IForm _form) {
        form = _form;
    }

    public IResponse buildResponse(IRequest request, IResponse response) {
        response.setStatus(200);
        response.setBody(buildFormString().getBytes());
        return response;
    }

    private String buildFormString() {
        StringBuilder body = new StringBuilder();
        body.append("<!DOCTYPE html><html><body>");

        for (Map.Entry<String, String> entry : form.form().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            body.append(key + " = " + value);
            body.append("<br>");
        }

        body.append("</body></html>");
        return body.toString();
    }
}
