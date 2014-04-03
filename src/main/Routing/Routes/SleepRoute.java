package main.Routing.Routes;

import main.Requests.IRequest;
import main.Response.IResponse;

public class SleepRoute implements IRoute {
    Integer timeToSleep;

    public SleepRoute(int _timeToSleep) {
        timeToSleep = _timeToSleep;
    }

    public IResponse buildResponse(IRequest request, IResponse response) {
        response.setHeaders("HTTP/1.1 200 OK\r\n\r\n");

        try {
            Thread.sleep(timeToSleep);
        } catch (Exception e) {
            System.out.println(e);
        }

        response.setBody("<!DOCTYPE html><html><body><h1>Just Slept</h1></body></html>");
        return response;
    }
}
