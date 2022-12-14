package in.cloudfocused.isthesiteup.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {
    private final String IS_SITE_UP = "Site is up!";
    private final String IS_SITE_DOWN = "Site is down. Please try later!";
    private final String INCORRECT_URL = "URL is incorrect!";

    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url) {
        String returnMessage = "";
        try {
            URL urlobj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlobj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCodeCatagory = conn.getResponseCode() / 100;
            System.out.println(responseCodeCatagory);

            if (responseCodeCatagory != 2 && responseCodeCatagory != 3) {
                System.out.println("Inside If loop");
                returnMessage = IS_SITE_DOWN;
            } else {
                System.out.println("Inside else loop");
                returnMessage = IS_SITE_UP;
            }
        } catch (MalformedURLException e) {
          returnMessage = INCORRECT_URL;
        } catch (IOException e) {
            returnMessage = IS_SITE_DOWN;
        }
        
        return returnMessage;
    }
}
