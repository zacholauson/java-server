package main.socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public interface ISocket {
    public Socket getSocket();
    public InputStream socketInputStream();
    public OutputStream socketOutputStream();
    public void close();
}
