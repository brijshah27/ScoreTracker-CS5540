package com.example.brij.myapplication.model;

/**
 * Created by Brij on 6/21/17.
 */

public class NBAData {



    //NBA Data
    private String homeTeam;
    private String awayTeam;
    private String homeTeamCity;
    private String awayTeamCity;
    private String homeScore;
    private String awayScore;
    private String location;
    private String gameDate;


    public NBAData(String homeTeam, String awayTeam, String homeTeamCity, String awayTeamCity, String homeScore, String awayScore, String location, String gameDate) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamCity = homeTeamCity;
        this.awayTeamCity = awayTeamCity;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.location = location;
        this.gameDate = gameDate;


    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getHomeTeamCity() {
        return homeTeamCity;
    }

    public void setHomeTeamCity(String homeTeamCity) {
        this.homeTeamCity = homeTeamCity;
    }

    public String getAwayTeamCity() {
        return awayTeamCity;
    }

    public void setAwayTeamCity(String awayTeamCity) {
        this.awayTeamCity = awayTeamCity;
    }

    public String getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(String homeScore) {
        this.homeScore = homeScore;
    }

    public String getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(String awayScore) {
        this.awayScore = awayScore;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
