package main;

public interface IController {
    public IController sendRequest(IRequest _request);
    public Response getResponse();
}
