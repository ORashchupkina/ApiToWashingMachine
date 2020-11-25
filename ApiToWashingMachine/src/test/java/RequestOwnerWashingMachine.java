import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RequestOwnerWashingMachine {
    public String setTask(String mode, String temperature, String speed, String time){
        String postUrl = "http://localhost:8080/washingMachineApi/api/settask";
        DefaultHttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(postUrl);

        List<NameValuePair> params = new ArrayList<>(2);//?mode=quick&temperature=60&speed=700&time=0#
        params.add(new BasicNameValuePair("mode", mode));
        params.add(new BasicNameValuePair("temperature", temperature));
        params.add(new BasicNameValuePair("speed", speed));
        params.add(new BasicNameValuePair("time", time));
        String result = "";
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream in = response.getEntity().getContent();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) != -1) {
                    out.write(buffer, 0, length);
                }
                result = out.toString("UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
