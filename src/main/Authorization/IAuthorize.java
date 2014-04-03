package main.Authorization;

import main.Requests.IRequest;

public interface IAuthorize {
    public Boolean authorized(IRequest request);
}
