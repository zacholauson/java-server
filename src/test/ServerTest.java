package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ServerTest {
    @Test
    public void clientConnectionTest() {
        Socket socket = new Socket();
        InetSocketAddress endPoint;
        try {
            endPoint = new InetSocketAddress(InetAddress.getLocalHost().getHostAddress(), 9000);
            if (!endPoint.isUnresolved()) {
                assertFalse(endPoint.isUnresolved());
                try {
                    socket.connect(endPoint, 2000);

                    assertTrue(socket.isConnected());
                    assertEquals(socket.getPort(), 9000);
                } catch (IOException e) {
                    System.err.println("NOTE: Server must be running on port 9000, for ServerTest.clientConnectionTest to run\n");
                    e.printStackTrace();
                    fail();
                }
            } else {
                new Exception("End Point Unresolved").printStackTrace();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
            fail();
        }
    }
}
