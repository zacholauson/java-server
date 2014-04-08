package test.socket.socketWrappers;

import main.socket.socketWrappers.SocketWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.net.Socket;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class SocketWrapperTest {
    @Test
    public void socketWrapperSocketTest() {
        SocketWrapper socketWrapper = new SocketWrapper(new Socket());
        assertEquals(Socket.class, socketWrapper.getSocket().getClass());
    }
}
