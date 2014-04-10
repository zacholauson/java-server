package main.responsepackage.responsepackages;

import main.responsepackage.IResponsePackage;
import main.respond.IRespond;
import main.response.IResponse;

public class ResponsePackage implements IResponsePackage {
    private final IRespond RESPONDER;
    private IResponse response;

    public ResponsePackage(IResponse response, IRespond responder) {
        this.response = response;
        RESPONDER     = responder;
    }

    public IResponse response() {
        return response;
    }

    public IRespond responder() {
        return RESPONDER;
    }

    public void respond() {
        RESPONDER.respond(response);
    }
}
