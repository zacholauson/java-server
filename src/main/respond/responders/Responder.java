package main.respond.responders;

import main.respond.IRespond;
import main.response.IResponse;

import java.io.*;
import java.lang.Exception;

public class Responder implements IRespond {
    private OutputStream outputStream;

    public Responder(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void respond(IResponse response) {
        PrintWriter output                = new PrintWriter(outputStream, false);
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(outputStream));
        writeResponseToOutputStream(dataOutputStream, response);
        closeOutputStreams(dataOutputStream, outputStream);
        output.close();
    }

    private void closeOutputStreams(OutputStream... outputStreams) {
        for(OutputStream outputStream : outputStreams) {
            try {
                outputStream.close();
            } catch (IOException e) {
                new Exception("Unable to close OutputStream").printStackTrace();
                e.printStackTrace();
            }
        }
    }

    private void writeResponseToOutputStream(DataOutputStream dataOutputStream, IResponse response) {
        try {
            writeHeadersToOutputStream(dataOutputStream, response);
            writeBodyToOutputStream(dataOutputStream,    response);
        } catch (IOException e) {
            new Exception("Failed to write response to OutputStream").printStackTrace();
            e.printStackTrace();
        }
    }

    private void writeHeadersToOutputStream(DataOutputStream dataOutputStream, IResponse response) throws IOException {
        dataOutputStream.write(response.getHeaders().getBytes());
    }

    private void writeBodyToOutputStream(DataOutputStream dataOutputStream, IResponse response) throws IOException {
        if (bodyIsPresent(response)) {
            dataOutputStream.write(response.getBody());
        }
    }

    private boolean bodyIsPresent(IResponse response) {
        return response.getBody() != null;
    }
}
