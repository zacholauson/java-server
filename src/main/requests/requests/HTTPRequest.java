package main.requests.requests;

import main.requests.IRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTTPRequest implements IRequest {
    private BufferedReader           input;
    private String                   headerString;
    private HashMap<String, String>  headers;
    private String                   body;
    private String                   method;
    private String                   route;
    private HashMap<String, String>  params;
    private HashMap<String, Integer> range;
    private HashMap<String, String>  auth;

    public String getHeaderString()                   { return headerString; }
    public HashMap<String, String> getHeaders()       { return headers; }
    public String getBody()                           { return body; }
    public String getMethod()                         { return method; }
    public String getRoute()                          { return route; }
    public HashMap<String, String> getParams()        { return params; }
    public HashMap<String, Integer> getRange()        { return range; }
    public HashMap<String, String> getAuthorization() { return auth; }

    public HTTPRequest(InputStream _input) throws IOException {
        this.input        = getBufferedInput(_input);
        this.headerString = parseHeaderString();
        this.headers      = parseHeadersHash();
        this.body         = parseBody();
        this.method       = parseMethod();
        this.route        = parseRoute();
        this.params       = parseParams();
        this.range        = parseRange();
        this.auth         = parseAuthorization();
    }

    // read in request
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

    // request parsing
    private String parseHeaderString() throws IOException {
        StringBuilder requestHeaders = new StringBuilder();
        String readLine = input.readLine();
        while (readLine != null && !readLine.equals("")) {
            requestHeaders.append(readLine).append("\r\n");
            readLine = input.readLine();
        }
        return requestHeaders.toString();
    }

    private HashMap<String, String> parseHeadersHash() {
        HashMap<String, String> headers = new HashMap<>();
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

    private HashMap<String, String> parseParams() {
        if (parseURLParams() == null) {
            return parsePOSTParams();
        } else {
            HashMap<String, String> params = parsePOSTParams();
            params.putAll(parseURLParams());
            return params;
        }
    }

    private HashMap<String, String> parsePOSTParams() {
        HashMap<String, String> paramMap = new HashMap<>();
        String[] baseParams = body.split("&");
        for (String param : baseParams) {
            String[] paramsKV = param.split("=");
            try {
                String key = paramsKV[0];
                String value = URLDecoder.decode(paramsKV[paramsKV.length - 1], "UTF-8");
                paramMap.put(key, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return paramMap;
    }

    private HashMap<String, String> parseURLParams() {
        HashMap<String, String> urlParamMap = new HashMap<>();
        Pattern pattern = Pattern.compile("(?<=\\?)(\\S+)");
        Matcher matcher = pattern.matcher(headerString);
        if (matcher.find()) {
            String[] paramQueries = matcher.group(1).split("&");
            for (String paramQuery : paramQueries) {
                String[] queryPair = paramQuery.split("=");
                String key;
                String value;
                try {
                    key   = URLDecoder.decode(queryPair[0], "UTF-8");
                    value = URLDecoder.decode(queryPair[queryPair.length - 1], "UTF-8");
                    urlParamMap.put(key, value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            urlParamMap = null;
        }

        return urlParamMap;
    }

    private HashMap<String, Integer> parseRange() {
        HashMap<String, Integer> rangeMap = new HashMap<>();
        Pattern pattern = Pattern.compile("((?<=\\nRange: bytes=)([^\\r]+))");
        Matcher matcher = pattern.matcher(headerString);
        if (matcher.find()) {
            String[] range = matcher.group(1).split("-");
            if (range.length == 2) {
                rangeMap.put("Begin", Integer.parseInt(range[0]));
                rangeMap.put("End",   Integer.parseInt(range[1]));
                int length = rangeMap.get("End") - rangeMap.get("Begin") + 1;
                rangeMap.put("Length", length);
            }
        }
        return rangeMap;
    }

    private HashMap<String, String> parseAuthorization() {
        HashMap<String, String> authMap = new HashMap<>();
        String auth = headers.get("Authorization");
        if (auth != null) {
            String[] creds = auth.split(" ");
            authMap.put(creds[0], creds[1]);
        }
        return authMap;
    }
}
