package main.Requests;

import main.IRequest;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTTPRequest implements IRequest {
    private BufferedReader          input;
    private String                  headerString;
    private HashMap<String, String> headers;
    private String                  body;
    private String                  method;
    private String                  route;

    public String getHeaderString()             { return headerString; }
    public HashMap<String, String> getHeaders() { return headers; }
    public String getBody()                     { return body; }
    public String getMethod()                   { return method; }
    public String getRoute()                    { return route; }

    public HTTPRequest(InputStream _input) throws IOException {
        input        = getBufferedInput(_input);
        headerString = parseHeaderString();
        headers      = parseHeadersHash();
        body         = parseBody();
        method       = parseMethod();
        route        = parseRoute();
    }

    private String parseHeaderString() throws IOException {
        StringBuilder requestHeaders = new StringBuilder();
        String readLine = input.readLine();
        while (!readLine.equals("") && readLine != null) {
            requestHeaders.append(readLine).append("\r\n");
            readLine = input.readLine();
        }
        return requestHeaders.toString();
    }

    private HashMap<String, String> parseHeadersHash() {
        HashMap<String, String> headers = new HashMap<String, String>();
        String[] headerStrings = headerString.split("\r\n");
        for (String header : headerStrings) {
            String[] headerPair = header.split(": ");
            headers.put(headerPair[0], headerPair[headerPair.length - 1]);
        }
        return headers;
    }

    private String parseBody() throws IOException {
        StringBuilder body = new StringBuilder();
        String length = headers.get("Content-Length");
        if (length != null) {
            while (body.length() < Integer.parseInt(length)) {
                body.append((char) input.read());
            }
        }
        return body.toString();
    }

    private String parseMethod() {
        Pattern pattern = Pattern.compile("^GET|POST|PUT|DELETE|OPTIONS|PATCH");
        Matcher matcher = pattern.matcher(headerString);
        if (matcher.find()) {
            return matcher.group(0);
        } else {
            return null;
        }
    }

    private String parseRoute() throws UnsupportedEncodingException {
        Pattern pattern = Pattern.compile("(\\/[^\\s\\?]*)");
        Matcher matcher = pattern.matcher(headerString);
        if (matcher.find()) {
            return URLDecoder.decode(matcher.group(0), "UTF-8");
        } else {
            return null;
        }
    }

    private BufferedReader getBufferedInput(InputStream input) {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(readInput(input));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return bufferedReader;
    }

    private InputStreamReader readInput(InputStream input) throws Exception {
        return new InputStreamReader(input, "UTF-8");
    }
}
