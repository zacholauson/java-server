package test.routing.routes;

import main.form.IForm;
import main.form.forms.BaseForm;
import main.response.IResponse;
import main.response.responses.Response;
import main.routing.routes.GetFormDataRoute;
import main.routing.routes.IRoute;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import test.mocks.MockRequest;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class GetFormDataRouteTest {
    private IForm form = new BaseForm();

    @Test
    public void buildResponseEmptyFormTest() {
        IRoute route = new GetFormDataRoute(form);
        IResponse response = route.buildResponse(new MockRequest("GET", "/"), new Response());
        assertEquals("HTTP/1.1 200 OK\r\n\r\n", response.getHeaders());

        try {
            assertEquals("", new String(response.getBody(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void buildResponseFormDataTest() {
        form.form().put("hello", "world");

        IRoute route = new GetFormDataRoute(form);
        IResponse response = route.buildResponse(new MockRequest("GET", "/"), new Response());
        assertEquals("HTTP/1.1 200 OK\r\n\r\n", response.getHeaders());

        try {
            assertEquals("hello = world\r\n", new String(response.getBody(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail();
        }
    }
}
