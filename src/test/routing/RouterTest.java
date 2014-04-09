package test.routing;

import main.response.IResponse;
import main.response.responses.Response;
import main.routing.IRouter;
import main.routing.Router;
import main.routing.routes.FourOhFourRoute;
import main.routing.routes.IRoute;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import test.mocks.MockRequest;
import test.mocks.MockRoute;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class RouterTest {

    @Test
    public void setRoutesTest() {
        IRouter router = new Router();
        assertTrue(router.routes().isEmpty());
        router.addRoute("GET", "/", new MockRoute());
        assertEquals(1, router.routes().size());
    }

    @Test
    public void setFourOhFourRouteTest() {
        IRouter router = new Router();
        router.addFourOhFourRoute(null);
        assertNull(router.fourOhFourRoute());
        router.addFourOhFourRoute(new FourOhFourRoute());
        assertEquals(FourOhFourRoute.class, router.fourOhFourRoute().getClass());
    }

    @Test
    public void returnsCorrectRouteForGivenRequest() {
        IRouter router = new Router();
        router.addRoute("GET", "/", new MockRoute());
        router.addFourOhFourRoute(new FourOhFourRoute());

        IRoute route = router.route(new MockRequest("GET", "/"));
        assertEquals(MockRoute.class, route.getClass());
    }

    @Test
    public void returnsResponseFromRouteBuildResponse() {
        IRouter router = new Router();
        router.addRoute("GET", "/", new MockRoute());
        router.addFourOhFourRoute(new FourOhFourRoute());

        IRoute route = router.route(new MockRequest("GET", "/"));
        IResponse response = route.buildResponse(new MockRequest("GET", "/"), new Response());
        assertEquals(Response.class, response.getClass());
    }

    @Test
    public void returnsCorrectResponseFromResponseBuilder() {
        IRouter router = new Router();
        router.addRoute("GET", "/", new MockRoute());
        router.addFourOhFourRoute(new FourOhFourRoute());

        IRoute route = router.route(new MockRequest("GET", "/"));
        IResponse response = route.buildResponse(new MockRequest("GET", "/"), new Response());
        assertEquals("HTTP/1.1 200 OK\r\n\r\n", response.getHeaders());
    }

    @Test
    public void returns404WhenRouteNotFound() {
        IRouter router = new Router();
        router.addRoute("GET", "/", new MockRoute());
        router.addFourOhFourRoute(new FourOhFourRoute());

        IRoute route = router.route(new MockRequest("GET", "/foo"));
        assertEquals(FourOhFourRoute.class, route.getClass());
    }
}
