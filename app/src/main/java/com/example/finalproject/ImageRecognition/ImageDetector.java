package com.example.finalproject.ImageRecognition;

import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class ImageDetector {
    private static final String EOL = "\r\n";

    public int send(String filename, String url, String method) throws IOException {
        try (FileInputStream file = new FileInputStream(new File(filename))) {
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            final String boundary = UUID.randomUUID().toString();
            con.setDoOutput(true);
            con.setRequestMethod(method);
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            try (OutputStream out = con.getOutputStream()) {
                out.write(("--" + boundary + EOL +
                        "Content-Disposition: form-data; name=\"file\"; " +
                        "filename=\"" + filename + "\"" + EOL +
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

                return con.getResponseCode();
            } finally {
                con.disconnect();
            }
        }
    }

        public void writeResponse(HttpURLConnection http) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream(), "utf-8"));

                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(">>>>>>>>"+response.toString());
            } catch (Exception e) {

            }
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
