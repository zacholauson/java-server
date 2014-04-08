package test.routing.routes;

import main.response.IResponse;
import main.response.responses.Response;
import main.routing.routes.IRoute;
import main.routing.routes.OptionsRoute;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import test.mocks.MockRequest;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class OptionsRouteTest {
    @Test
    public void buildResponseTest() {
        IRoute route = new OptionsRoute("GET,POST");
        IResponse response = route.buildResponse(new MockRequest("OPTIONS", "/"), new Response());
        assertEquals("HTTP/1.1 200 OK\r\nAllow: GET,POST\r\n\r\n", response.getHeaders());
    }
}
