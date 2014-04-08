package test.routing.routes;

import main.form.IForm;
import main.form.forms.BaseForm;
import main.response.IResponse;
import main.response.responses.Response;
import main.routing.routes.DeleteFormDataRoute;
import main.routing.routes.IRoute;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import test.mocks.MockRequest;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class DeleteFormDataRouteTest {
    private IForm form = new BaseForm();

    @Test
    public void buildResponseEmptyFormTest() {
        form.form().put("Hello", "World");
        IRoute route = new DeleteFormDataRoute(form);
        assertFalse(form.form().isEmpty());
        IResponse response = route.buildResponse(new MockRequest("GET", "/"), new Response());
        assertEquals("HTTP/1.1 200 OK\r\n\r\n", response.getHeaders());
        assertTrue(form.form().isEmpty());
    }
}
