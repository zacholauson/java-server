package test.routing.routes;

import main.response.IResponse;
import main.response.responses.Response;
import main.routing.routes.IRoute;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import test.mocks.MockRequest;
import main.routing.routes.TextRoute;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class TextRouteTest {
    @Test
    public void buildResponseTest() {
        IRoute route = new TextRoute("Test Text");
        IResponse response = route.buildResponse(new MockRequest("GET", "/"), new Response());
        assertEquals("HTTP/1.1 200 OK\r\n\r\n", response.getHeaders());

        try {
            String body = new String(response.getBody(), "UTF-8");
            assertEquals("<!DOCTYPE HTML><html><body>Test Text</body></html>", body);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
}
