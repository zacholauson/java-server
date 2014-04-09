package main.response;

import java.io.*;
import java.lang.Exception;

public class Responder {
    public static void respond(IResponse response, OutputStream outputStream) {
        PrintWriter output                = new PrintWriter(outputStream, false);
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(outputStream));
        writeResponseToOutputStream(dataOutputStream, response);

        try {
            dataOutputStream.close();
            outputStream.close();
            output.close();
        } catch (IOException e) {
            new Exception("Failed to close OutputStreams").printStackTrace();
            e.printStackTrace();
        }
    }

    private static void writeResponseToOutputStream(DataOutputStream dataOutputStream, IResponse response) {
        try {
            writeHeadersToOutputStream(dataOutputStream, response);
            writeBodyToOutputStream(dataOutputStream,    response);
        } catch (IOException e) {
            new Exception("Failed to write response to OutputStream").printStackTrace();
            e.printStackTrace();
        }
    }

    private static void writeHeadersToOutputStream(DataOutputStream dataOutputStream, IResponse response) throws IOException {
        dataOutputStream.write(response.getHeaders().getBytes());
    }

    private static void writeBodyToOutputStream(DataOutputStream dataOutputStream, IResponse response) throws IOException {
        if (bodyIsPresent(response)) {
            dataOutputStream.write(response.getBody());
        }
    }

    private static boolean bodyIsPresent(IResponse response) {
        return response.getBody() != null;
    }
}
