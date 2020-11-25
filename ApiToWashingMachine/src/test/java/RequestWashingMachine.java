import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RequestWashingMachine {
    public String sendState(String state, String error){
        String postUrl = "http://localhost:8080/washingMachineApi/api/state";
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(postUrl);

        List<NameValuePair> params = new ArrayList<>(2);
        params.add(new BasicNameValuePair("state", state));
        params.add(new BasicNameValuePair("error", error));
        String result = "";
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            HttpResponse response = httpclient.execute(httppost);
            result = response.getStatusLine().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getTaskFrService(){
        String getUrl = "http://localhost:8080/washingMachineApi/api/gettask";

        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(getUrl);
        String result = "";
        try {
            HttpResponse response = httpclient.execute(httpGet);
            String statusLine = response.getStatusLine().toString();
            if(statusLine.equals("HTTP/1.1 200 OK")){
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    InputStream in = entity.getContent();
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = in.read(buffer)) != -1) {
                        out.write(buffer, 0, length);
                    }
                    result = out.toString("UTF-8");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
