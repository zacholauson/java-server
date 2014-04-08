package test.authorization.authorizers;

import main.authorization.IAuthorize;
import main.authorization.authorizers.BasicAuthorization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import test.mocks.MockRequest;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class BasicAuthorizationTest {
    @Test
    public void basicAuthorizationTest() {
        IAuthorize authorizer = new BasicAuthorization("admin:hunter2");
        assertTrue(authorizer.authorized(new MockRequest("GET", "/")));
    }

    @Test
    public void basicUnauthorizedTest() {
        IAuthorize authorizer = new BasicAuthorization("foo:bar");
        assertFalse(authorizer.authorized(new MockRequest("GET", "/")));
    }
}
