import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class EmailService {

    @Value("${BREVO_API_KEY}")
    private String apiKey;

    public void sendEmail(String to, String subject, String body) {
        try {
            URL url = new URL("https://api.brevo.com/v3/smtp/email");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("accept", "application/json");
            conn.setRequestProperty("api-key", apiKey);
            conn.setRequestProperty("content-type", "application/json");
            conn.setDoOutput(true);

            String json = "{"
                    + "\"sender\":{\"email\":\"venuthurlanikhithareddy@gmail.com\"},"
                    + "\"to\":[{\"email\":\"" + to + "\"}],"
                    + "\"subject\":\"" + subject + "\","
                    + "\"htmlContent\":\"<p>" + body + "</p>\""
                    + "}";

            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.getBytes("utf-8"));
            }

            int responseCode = conn.getResponseCode();
            System.out.println("Email API response: " + responseCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
