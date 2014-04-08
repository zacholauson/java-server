package test.routing.routes;

import main.response.IResponse;
import main.response.responses.Response;
import main.routing.routes.GetDirectoryRoute;
import main.routing.routes.IRoute;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import test.mocks.MockRequest;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class GetDirectoryRouteTest {
    @Test
    public void buildResponseTest() {
        IRoute route = new GetDirectoryRoute(System.getProperty("user.dir"), "/src/test/mocks/");
        IResponse response = route.buildResponse(new MockRequest("GET", "/"), new Response());
        assertEquals("HTTP/1.1 200 OK\r\n\r\n", response.getHeaders());

        try {
            assertEquals("<!DOCTYPE HTML><html><body>" +
                         "<a href=index.html>index.html</a><br>" +
                         "<a href=MockRequest.java>MockRequest.java</a><br>" +
                         "<a href=MockRoute.java>MockRoute.java</a><br>" +
                         "</body></html>",
                    new String(response.getBody(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
}
