package test.routing.routes;

import main.form.IForm;
import main.form.forms.BaseForm;
import main.response.IResponse;
import main.response.responses.Response;
import main.routing.routes.IRoute;
import main.routing.routes.PostFormDataRoute;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import test.mocks.MockRequest;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class PostFormDataRouteTest {
    private IForm form = new BaseForm();

    @Test
    public void buildResponseEmptyFormTest() {
        IRoute route = new PostFormDataRoute(form);
        assertTrue(form.form().isEmpty());
        IResponse response = route.buildResponse(new MockRequest("GET", "/"), new Response());
        assertEquals("HTTP/1.1 200 OK\r\n\r\n", response.getHeaders());
        assertFalse(form.form().isEmpty());
    }
}
