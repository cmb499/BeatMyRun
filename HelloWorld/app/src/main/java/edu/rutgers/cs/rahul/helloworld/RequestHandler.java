package edu.rutgers.cs.rahul.helloworld;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/*
* Created by Careena Braganza
* Tested by Careena Braganza
* Debugged by Careena Braganza
 */
public class RequestHandler {

    //Method to send httpPostRequest
    //This method is taking two arguments
    //First argument is the URL of the script to which we will send the request
    //Other is an HashMap with name value pairs containing the data to be send with the request
    public String sendPostRequest(String requestURL,
                                  HashMap<String, String> postDataParams) {
        //Creating a URL
        URL url;

        //StringBuilder object to store the message retrieved from the server
        StringBuilder sb = new StringBuilder();
        try {
            //Initializing Url
            url = new URL(requestURL);

            //Creating an httmlurl connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //Configuring connection properties
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //Creating an output stream
            OutputStream os = conn.getOutputStream();

            //Writing parameters to the request
            //We are using a method getPostDataString which is defined below
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                sb = new StringBuilder();
                String response;
                //Reading server response
                while ((response = br.readLine()) != null){
                    sb.append(response);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String sendGetRequest(String requestURL){
        StringBuilder sb =new StringBuilder();
        try {
            URL url = new URL(requestURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String s;
            while((s=bufferedReader.readLine())!=null){
                sb.append(s+"\n");
            }
        }catch(Exception e){
        }
        return sb.toString();
    }

    public String sendGetRequestParam(String requestURL, String id){
        StringBuilder sb =new StringBuilder();

        try {
            URL url = new URL(requestURL+id+"");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String s;
            while((s=bufferedReader.readLine())!=null){
                sb.append(s+"\n");
            }
        }catch(Exception e){
        }
        return sb.toString();
    }


//getChallenge based on receiver_id/sender_id and datetime
    public String sendGetRequestTwoParamDatetime(String requestURL, String id, String id2){
        StringBuilder sb =new StringBuilder();
        try {

             id2 = URLEncoder.encode(id2, "UTF-8");
            URL url = new URL(requestURL+id+"&datetime="+id2+"");
            System.out.println(url);
            //localhost:8888/getEmpDatetimeTwoParams.php?receiver_id=Nirali&datetime=2015-12-22%00:00:00


            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String s;
            while((s=bufferedReader.readLine())!=null){
                sb.append(s+"\n");
            }
        }catch(Exception e){
        }
        return sb.toString();
    }


    public String sendGetRequestThreeParamDatetime(String requestURL, String sender_id, String receiver_id, String datetime){
        StringBuilder sb =new StringBuilder();
        try {

            sender_id = URLEncoder.encode(sender_id, "UTF-8");
            receiver_id = URLEncoder.encode(receiver_id, "UTF-8");
            datetime = URLEncoder.encode(datetime, "UTF-8");
            URL url = new URL(requestURL+sender_id+"&receiver_id="+receiver_id+"&datetime="+datetime+"");
            System.out.println(url);
            //localhost:8888/getEmpDatetimeTwoParams.php?receiver_id=Nirali&datetime=2015-12-22%00:00:00


            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String s;
            while((s=bufferedReader.readLine())!=null){
                sb.append(s+"\n");
            }
        }catch(Exception e){
        }
        return sb.toString();
    }



    //getChallenge based on receiver_id/sender_id and datetime
   /* public String sendGetRequestThreeParamDatetime(String requestURL, String id, String id2, String id3){
        StringBuilder sb =new StringBuilder();
        try {

            id3 = URLEncoder.encode(id3, "UTF-8");
            URL url = new URL(requestURL+id+"&receiver_id="+id2+"&datetime="+id3+"");
            //localhost:8888/getEmpDatetimeThreeParams.php?sender_id=careena&receiver_id=Nirali&datetime=2015-12-15%2023:08:33


            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String s;
            while((s=bufferedReader.readLine())!=null){
                sb.append(s+"\n");
            }
        }catch(Exception e){
        }
        return sb.toString();
    }*/






    //added for datetime
    public String sendGetRequestParamDatetime(String requestURL, String id) throws UnsupportedEncodingException {
        StringBuilder sb =new StringBuilder();

        id = URLEncoder.encode(id, "UTF-8");

        try {
            URL url = new URL(requestURL+id+"");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String s;
            while((s=bufferedReader.readLine())!=null){
                sb.append(s+"\n");
            }
        }catch(Exception e){
        }
        return sb.toString();
    }



    public String sendGetRequestTwoParam(String requestURL, String id, String id2){
        StringBuilder sb =new StringBuilder();
        try {

           // id2 = URLEncoder.encode(id, "UTF-8");
            URL url = new URL(requestURL+id+"&receiver_id="+id2+"");

          //  http://localhost/addStatusNew.php?sender_id=Careena&receiver_id=Thara

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String s;
            while((s=bufferedReader.readLine())!=null){
                sb.append(s+"\n");
            }
        }catch(Exception e){
        }
        return sb.toString();
    }


    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }


    public String getAllUsers(String requestURL){
        StringBuilder sb =new StringBuilder();
        try {
            URL url = new URL(requestURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String s;
            while((s=bufferedReader.readLine())!=null){
                sb.append(s+"\n");
            }
        }catch(Exception e){
        }
        return sb.toString();
    }
}
