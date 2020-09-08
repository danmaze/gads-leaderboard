package com.example.gadsleaderboard;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import static com.example.gadsleaderboard.ApiUtil.Endpoint.LEARNING_HOURS;
import static com.example.gadsleaderboard.ApiUtil.Endpoint.SKILL_IQ;

public class ApiUtil {

    private final String BASE_API_URL = "https://gadsapi.herokuapp.com";
    private static ApiUtil INSTANCE;

    enum Endpoint {

        LEARNING_HOURS("/api/hours"),
        SKILL_IQ("/api/skilliq");

        private String route;

        Endpoint(String route) {
            this.route = route;
        }

        public String getRoute() {
            return route;
        }

    }

    private ApiUtil() {}

    static ApiUtil getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ApiUtil();
        }
        return INSTANCE;
    }

    public URL buildUrl(Endpoint endpoint) {
        URL url = null;
        try {
            url = new URL(BASE_API_URL + endpoint.getRoute());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return url;
    }

    public String getJson(URL url) throws IOException {
        String json = "";
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try {
            InputStream stream = connection.getInputStream();
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");
            boolean hasData = scanner.hasNext();
            if (hasData) {
                json = scanner.next();
            } else {
                json = null;
            }
        } catch (Exception exception) {
            Log.d(ApiUtil.getInstance().toString(), exception.toString());
        } finally {
            connection.disconnect();
        }
        return json;
    }

    public ArrayList<Leader> getLeadersFromJson(Endpoint endpoint) {
        ArrayList<Leader> leaders = new ArrayList<>();
        URL url = buildUrl(endpoint);
        try {
            String jsonString = getJson(url);
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String country = jsonObject.getString("country");
                String badgeUrl = jsonObject.getString("badgeUrl");
                if (endpoint == LEARNING_HOURS) {
                    int hours = jsonObject.getInt("hours");
                    Leader leader = new Leader(name, hours, country, badgeUrl);
                    leaders.add(leader);
                } else if (endpoint == SKILL_IQ) {
                    int score = jsonObject.getInt("score");
                    Leader leader = new Leader(name, country, badgeUrl, score);
                    leaders.add(leader);
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
        return leaders;
    }

}
