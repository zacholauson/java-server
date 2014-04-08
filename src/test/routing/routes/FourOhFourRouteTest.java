package test.routing.routes;

import main.response.IResponse;
import main.response.responses.Response;
import main.routing.routes.IRoute;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import test.mocks.MockRequest;
import main.routing.routes.FourOhFourRoute;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class FourOhFourRouteTest {
    @Test
    public void buildResponseTest() {
        IRoute route = new FourOhFourRoute();
        IResponse response = route.buildResponse(new MockRequest("GET", "/"), new Response());
        assertEquals("HTTP/1.1 404 Not Found\r\n\r\n", response.getHeaders());
    }
}
