package main.response;

import java.io.*;
import java.lang.Exception;

public class Responder {
    public static void respond(IResponse response, OutputStream outputStream) throws Exception {
        PrintWriter output                = new PrintWriter(outputStream, false);
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(outputStream));

        writeHeadersToOutputStream(dataOutputStream, response);
        writeBodyToOutputStream(dataOutputStream,    response);

        dataOutputStream.flush();
        dataOutputStream.close();
        outputStream.flush();
        outputStream.close();
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
