package main.authorization;

import main.requests.IRequest;

public interface IAuthorize {
    public Boolean authorized(IRequest request);
}
