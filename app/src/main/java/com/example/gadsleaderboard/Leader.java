package com.example.gadsleaderboard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Leader {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("hours")
    @Expose
    private int hours;

    @SerializedName("score")
    @Expose
    private int score;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("bagdeUrl")
    @Expose
    private String badgeUrl;

    public Leader(String name, int hours, String country, String badgeUrl) {
        this.name = name;
        this.hours = hours;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public Leader(String name, String country, String badgeUrl, int score) {
        this.name = name;
        this.country = country;
        this.badgeUrl = badgeUrl;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

}
