package PawzdCO.scoreWhisperer;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import org.springframework.web.client.RestTemplate;

import PawzdCO.common.services.*;

public class ScoreWhisperer implements IScoreSPI {

    private static final RestTemplate restTemplate = new RestTemplate();

    @Override
    public int getScore() {
        return sendRequest("score");
    }

    @Override
    public int setScore(int score) {
        // /score/set
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setScore'");
    }

    @Override
    public int addScore(int score) {
        // /score/add
        return sendRequest("score/add?points=" + score);
    }

    private int sendRequest(String url) {
        URI uri = URI.create("http://localhost:8080/" + url);
        Integer response;
        try {
            response = Integer.parseInt(restTemplate.getForObject(uri, String.class));
            return response != null ? response : 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; // Default return value in case of error
    }
}