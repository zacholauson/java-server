package main.responsepackage;

import main.respond.IRespond;
import main.response.IResponse;

public interface IResponsePackage {
    public IResponse response();
    public IRespond  responder();
    public void      respond();
}
