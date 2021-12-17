package com.example.finalproject.ImageRecognition;

import android.os.AsyncTask;
import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class ImageDetector extends AsyncTask<Void, Void, Void> {
    private static final String EOL = "\r\n";
    int responseCode;

    String fileName, url, method, response;

    public ImageDetector(String filename, String url, String method){
        this.fileName = filename;
        this.url = url;
        this.method = method;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

//    public int send(String filename, String url, String method) throws IOException {
//        try (FileInputStream file = new FileInputStream(new File(filename))) {
//            System.out.println(">>>>>>Granted Access");
//            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
//            final String boundary = UUID.randomUUID().toString();
//            con.setDoOutput(true);
//            con.setRequestMethod(method);
//            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
//            try (OutputStream out = con.getOutputStream()) {
//                out.write(("--" + boundary + EOL +
//                        "Content-Disposition: form-data; name=\"file\"; " +
//                        "filename=\"" + filename + "\"" + EOL +
//                        "Content-Type: application/octet-stream" + EOL + EOL)
//                        .getBytes(StandardCharsets.UTF_8));
//                byte[] buffer = new byte[128];
//                int size = -1;
//                while (-1 != (size = file.read(buffer))) {
//                    out.write(buffer, 0, size);
//                }
//                out.write((EOL + "--" + boundary + "--" + EOL).getBytes(StandardCharsets.UTF_8));
//                out.flush();
//
//                System.out.println(con.getResponseCode() + " " + con.getResponseMessage());
//                writeResponse(con);
//
//                return con.getResponseCode();
//            } finally {
//                con.disconnect();
//            }
//        }
//    }

        public void writeResponse(HttpURLConnection http) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream(), "utf-8"));

                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                this.response = response.toString();
                System.out.println(">>>>>>>>"+response.toString());
            } catch (Exception e) {

            }
        }

    @Override
    public Void doInBackground(Void... voids) {
        try (FileInputStream file = new FileInputStream(new File(fileName))) {
            System.out.println(">>>>>>Granted Access");
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            final String boundary = UUID.randomUUID().toString();
            con.setDoOutput(true);
            con.setRequestMethod(method);
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            try (OutputStream out = con.getOutputStream()) {
                out.write(("--" + boundary + EOL +
                        "Content-Disposition: form-data; name=\"file\"; " +
                        "filename=\"" + fileName + "\"" + EOL +
                        "Content-Type: application/octet-stream" + EOL + EOL)
                        .getBytes(StandardCharsets.UTF_8));
                byte[] buffer = new byte[128];
                int size = -1;
                while (-1 != (size = file.read(buffer))) {
                    out.write(buffer, 0, size);
                }
                out.write((EOL + "--" + boundary + "--" + EOL).getBytes(StandardCharsets.UTF_8));
                out.flush();

                System.out.println(con.getResponseCode() + " " + con.getResponseMessage());
                writeResponse(con);

                responseCode =  con.getResponseCode();
            } finally {
                con.disconnect();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        super.onPostExecute(aVoid);
    }

    public String getResponse(){
        if (!this.response.isEmpty()){
            return this.response;
        }
        else
            return "";
    }


}



    /*
    public static void main(String[] args) throws IOException {

        String filename = "/Users/youssef/Desktop/TestApi/src/porsche.jpeg";
        String url = "https://www.nyckel.com/v1/functions/km6svjpscep917bc/invoke";

        System.out.println("Calling Nyckel...");
        Send(filename, url, "POST");
    }

     */
