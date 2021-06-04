package cn.getech.test.util;

import sun.net.www.protocol.https.Handler;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HttpClientUtil {


    public HttpURLConnection createConnection(String uri) throws IOException {
        //URL url = uri.toURL();
        URL url= new URL(null, uri, new Handler());
        URLConnection connection = url.openConnection();
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) connection;
        httpsURLConnection.setSSLSocketFactory(new TLSSocketConnectionFactory());
        return httpsURLConnection;
    }
}