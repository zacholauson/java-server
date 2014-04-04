package main.Routing.Routes;

import main.Form.IForm;
import main.Requests.IRequest;
import main.Response.IResponse;
import main.Response.ResponseCodes;

import java.util.Map;

public class PostFormDataRoute implements IRoute {
    private IForm form;

    public PostFormDataRoute(IForm _form) {
        form = _form;
    }

    public IResponse buildResponse(IRequest request, IResponse response) {
        response.setHeaders(ResponseCodes.codeString(200) + "\r\n");
        for (Map.Entry<String, String> entry : request.getParams().entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            form.form().put(key, value);
        }
        return response;
    }
}
