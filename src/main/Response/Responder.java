package main.Response;

import main.Response.IResponse;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.Exception;

public class Responder {
    public static void respond(IResponse response, OutputStream _output) throws Exception {
        PrintWriter output   = new PrintWriter(_output, false);
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(_output));

        dos.write(response.getHeaders().getBytes());

        if (filePresent(response)) {
            FileInputStream fis = new FileInputStream(response.getFile());
            int element;
            while ((element = fis.read()) != -1) { dos.write(element); }
            fis.close();
        }

        if (bodyPresent(response)) {
            dos.write(response.getBody().getBytes());
        }

        dos.flush();
        dos.close();
        _output.flush();
        _output.close();
        output.flush();
        output.close();
    }

    private static boolean filePresent(IResponse response) {
        return response.getFile() != null;
    }

    private static boolean bodyPresent(IResponse response) {
        return response.getBody() != null;
    }
}
