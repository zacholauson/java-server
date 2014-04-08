package test.routing.routes;

import main.response.IResponse;
import main.response.responses.Response;
import main.routing.routes.FileRoute;
import main.routing.routes.IRoute;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import test.mocks.MockRequest;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class FileRouteTest {
    @Test
    public void buildResponseTest() {
        IRoute route = new FileRoute(System.getProperty("user.dir"), "/src/test/mocks/index.html");
        IResponse response = route.buildResponse(new MockRequest("HEAD", "/"), new Response());
        assertEquals("HTTP/1.1 200 OK\r\n\r\n", response.getHeaders());

        try {
            assertEquals("<!DOCTYPE HTML><html><body>Index File</body></html>", new String(response.getBody(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
}
