package test.routing.routes;

import main.response.IResponse;
import main.response.responses.Response;
import main.routing.routes.IRoute;
import main.routing.routes.MethodNotAllowedRoute;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import test.mocks.MockRequest;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class MethodNotAllowedRouteTest {
    @Test
    public void buildResponseTest() {
        IRoute route = new MethodNotAllowedRoute();
        IResponse response = route.buildResponse(new MockRequest("HEAD", "/"), new Response());
        assertEquals("HTTP/1.1 405 Method Not Allowed\r\n\r\n", response.getHeaders());
    }
}
