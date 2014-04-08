package test.routing;

import main.response.IResponse;
import main.response.responses.Response;
import main.routing.Router;
import main.routing.routes.FourOhFourRoute;
import main.routing.routes.IRoute;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import test.mocks.MockRequest;
import test.mocks.MockRoute;

import static org.junit.Assert.*;

import java.util.HashMap;

@RunWith(JUnit4.class)
public class RouterTest {

    @Test
    public void setRoutesTest() {
        Router.routesMap = new HashMap<>();
        assertTrue(Router.routesMap.isEmpty());
        Router.addRoute("GET", "/", new MockRoute());
        assertEquals(1, Router.routesMap.size());
    }

    @Test
    public void setFourOhFourRouteTest() {
        // reset 404 route
        Router.fourOhFourRoute = null;
        assertNull(Router.fourOhFourRoute);
        Router.addFourOhFourRoute(new FourOhFourRoute());
        assertEquals(FourOhFourRoute.class, Router.fourOhFourRoute.getClass());
    }

    @Before
    public void setRoutes() {
        Router.addRoute("GET", "/", new MockRoute());
        Router.addFourOhFourRoute(new FourOhFourRoute());
    }

    @Test
    public void returnsCorrectRouteForGivenRequest() {
        IRoute route = Router.route(new MockRequest("GET", "/"));
        assertEquals(MockRoute.class, route.getClass());
    }

    @Test
    public void returnsResponseFromRouteBuildResponse() {
        IRoute route = Router.route(new MockRequest("GET", "/"));
        IResponse response = route.buildResponse(new MockRequest("GET", "/"), new Response());
        assertEquals(Response.class, response.getClass());
    }

    @Test
    public void returnsCorrectResponseFromResponseBuilder() {
        IRoute route = Router.route(new MockRequest("GET", "/"));
        IResponse response = route.buildResponse(new MockRequest("GET", "/"), new Response());
        assertEquals("HTTP/1.1 200 OK\r\n\r\n", response.getHeaders());
    }

    @Test
    public void returns404WhenRouteNotFound() {
        IRoute route = Router.route(new MockRequest("GET", "/foo"));
        assertEquals(FourOhFourRoute.class, route.getClass());
    }
}
