package test.response.responses;

import main.response.responses.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ResponseTest {
    @Test
    public void setStatusTest() {
        Response response = new Response();
        response.setStatus(200);
        assertEquals(response.getHeaders(), "HTTP/1.1 200 OK\r\n\r\n");
    }

    @Test
    public void addHeaderTest() {
        Response response = new Response();
        response.setStatus(200);
        response.addHeader("Allow", "GET");
        assertEquals(response.getHeaders(), "HTTP/1.1 200 OK\r\nAllow: GET\r\n\r\n");
    }
}
