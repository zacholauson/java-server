package main.Response;

import java.io.*;
import java.lang.Exception;

public class Responder {
    public static void respond(IResponse response, OutputStream _output) throws Exception {
        PrintWriter output                = new PrintWriter(_output, false);
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(_output));

        writeHeadersToOutputStream(dataOutputStream, response);
        writeBodyToOutputStream(dataOutputStream,    response);

        dataOutputStream.flush();
        dataOutputStream.close();
        _output.flush();
        _output.close();
        output.flush();
        output.close();
    }

    private static boolean bodyPresent(IResponse response) {
        return response.getBody() != null;
    }

    private static void writeHeadersToOutputStream(DataOutputStream dataOutputStream, IResponse response) throws Exception {
        dataOutputStream.write(response.getHeaders().getBytes());
    }

    private static void writeBodyToOutputStream(DataOutputStream dataOutputStream, IResponse response) throws Exception {
        if (bodyPresent(response)) {
            dataOutputStream.write(response.getBody());
        }
    }
}
