package main;

import main.requestpackage.IRequestPackage;
import main.responsepackage.IResponsePackage;
import main.socket.ISocket;

public class ConnectionWrapper implements Runnable {
    private ISocket          socketWrapper;
    private IRequestPackage  requestPackage;
    private IResponsePackage responsePackage;

    public ConnectionWrapper(ISocket socketWrapper, IRequestPackage requestPackage, IResponsePackage responsePackage) {
        this.socketWrapper   = socketWrapper;
        this.requestPackage  = requestPackage;
        this.responsePackage = responsePackage;
    }

    public void run() {
        requestPackage.logRequest();
        requestPackage.routeRequest().buildResponse(requestPackage.request(), responsePackage.response());
        responsePackage.respond();
        socketWrapper.close();
    }
}
