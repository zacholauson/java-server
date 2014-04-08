package test.routing.routes;

import main.response.IResponse;
import main.response.responses.Response;
import main.routing.routes.IRoute;
import main.routing.routes.RedirectRoute;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import test.mocks.MockRequest;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class RedirectRouteTest {
    @Test
    public void buildResponseTest() {
        IRoute route = new RedirectRoute("http://localhost:9999");
        IResponse response = route.buildResponse(new MockRequest("GET", "/"), new Response());
        assertEquals("HTTP/1.1 301 Moved Permanently\r\nLocation: http://localhost:9999\r\n\r\n", response.getHeaders());

        try {
            assertEquals("<html><head><meta http-equiv='refresh' content='0 ; url=/'></head></html>", new String(response.getBody(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
}
