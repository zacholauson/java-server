package main;

import java.io.*;
import java.lang.Exception;

public class Responder {
    public static void respond(Response response, OutputStream _output) throws Exception {
        PrintWriter output   = new PrintWriter(_output, false);
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(_output));

        if (response.getFile() != null) {
            FileInputStream fis = new FileInputStream(response.getFile());
            int element;
            while ((element = fis.read()) != -1) { dos.write(element); }
            fis.close();
        }

        if (response.getBody() != null) {
            dos.write(response.getBody().getBytes());
        }

        dos.flush();
        dos.close();
        _output.flush();
        _output.close();
        output.flush();
        output.close();
    }
}
