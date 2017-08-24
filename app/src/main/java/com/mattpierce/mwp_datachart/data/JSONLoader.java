package com.mattpierce.mwp_datachart.data;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * Created by mattpierce on 8/23/17.
 */

public class JSONLoader {
    static String response = null;
    public final static int GET = 1;
    public final static int POST = 2;

    public JSONLoader() {

    }

    /**
     * Making service call
     * @url - url to make request
     * @method - http request method
     * */
    public String makeServiceCall(String url, int method) {
        try {
            URL myUrl = new URL(url);
            URLConnection myConnection = myUrl.openConnection();
            InputStream response = myConnection.getInputStream();
            myConnection.connect();

            try (Scanner scanner = new Scanner(response)) {
                String responseBody = scanner.useDelimiter("\\A").next();
                // System.out.println(responseBody);
                return responseBody;
            }
        }
        catch (MalformedURLException e) {
            // URL Failed
            return e.toString();
        }
        catch (IOException e) {
            // openConnection() failed
            return e.toString();
        }
    }
}
