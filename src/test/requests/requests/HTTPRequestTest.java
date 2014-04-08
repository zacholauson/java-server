package test.requests.requests;

import main.requests.IRequest;
import main.requests.requests.HTTPRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class HTTPRequestTest {
    private IRequest request = newRequest("GET /partial_content.txt?hello=world HTTP/1.1 Range: bytes=0-4 Connection: close Host: localhost:5000");

    @Test
    public void parsesMethod() {
        assertEquals(request.getMethod(), "GET");
    }

    @Test
    public void parsedRoute() {
        assertEquals(request.getRoute(), "/partial_content.txt");
    }

    @Test
    public void parsesParams() {
        assertTrue(request.getParams().containsKey("hello"));
        assertEquals(request.getParams().get("hello"), "world");
    }

    private IRequest newRequest(String headerString) {
        InputStream inputStream = new ByteArrayInputStream(Charset.forName("UTF-8").encode(headerString).array());
        IRequest request = null;
        try {
            request = new HTTPRequest(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
        return request;
    }
}
