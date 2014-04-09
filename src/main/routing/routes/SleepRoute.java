package main.routing.routes;

import main.requests.IRequest;
import main.response.IResponse;

public class SleepRoute implements IRoute {
    Integer timeToSleep;

    public SleepRoute(int timeToSleep) {
        this.timeToSleep = timeToSleep;
    }

    public IResponse buildResponse(IRequest request, IResponse response) {
        try {
            Thread.sleep(timeToSleep);
        } catch (InterruptedException e) {
            new Exception("Failed to sleep").printStackTrace();
            e.printStackTrace();
        }

        response.setStatus(200);
        response.setBody("<!DOCTYPE html><html><body><h1>Just Slept</h1></body></html>".getBytes());
        return response;
    }
}
