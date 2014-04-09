package test.routing.routes;

import main.authorization.authorizers.BasicAuthorization;
import main.logging.loggers.Logger;
import main.response.IResponse;
import main.response.responses.Response;
import main.routing.routes.IRoute;
import main.routing.routes.LogsRoute;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import test.mocks.MockRequest;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class LogsRouteTest {
    @Test
    public void basicBuildResponseTest() {
        IRoute route = new LogsRoute(new BasicAuthorization("admin:hunter2"), new Logger());
        IResponse response = route.buildResponse(new MockRequest("GET", "/"), new Response());
        assertEquals("HTTP/1.1 200 OK\r\n\r\n", response.getHeaders());
    }

    @Test
    public void basicBuildResponseTestUnauthorized() {
        IRoute route = new LogsRoute(new BasicAuthorization("foo:bar"), new Logger());
        IResponse response = route.buildResponse(new MockRequest("GET", "/"), new Response());
        assertEquals("HTTP/1.1 401 Unauthorized\r\n\r\n", response.getHeaders());
        try {
            assertEquals("<!DOCTYPE html><html><body>Authentication required to access this route</body></html>", new String(response.getBody(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
}
