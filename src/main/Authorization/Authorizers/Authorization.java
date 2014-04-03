package main.Authorization.Authorizers;

import main.Authorization.IAuthorize;
import main.Requests.IRequest;

import javax.xml.bind.DatatypeConverter;

public class Authorization implements IAuthorize {
    private String authorizationPair;

    public Authorization(String _authorizationPair) {
        authorizationPair = _authorizationPair;
    }

    public Boolean authorized(IRequest request) {
        String auth = request.getAuthorization().get("Basic");
        String creds = null;
        if (auth != null) {
            try {
                creds = new String(DatatypeConverter.parseBase64Binary(auth), "UTF-8");
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return authorizationPair.equals(creds);
    }
}
