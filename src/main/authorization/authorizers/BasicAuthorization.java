package main.authorization.authorizers;

import main.authorization.IAuthorize;
import main.requests.IRequest;

import javax.xml.bind.DatatypeConverter;

public class BasicAuthorization implements IAuthorize {
    private String authorizationPair;

    public BasicAuthorization(String authorizationPair) {
        this.authorizationPair = authorizationPair;
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
