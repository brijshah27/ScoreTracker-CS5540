package com.example.brij.myapplication.model;

/**
 * Created by aquib on 8/6/17.
 */

public class ScheduleModel {
    public String gameName;
    public String homeTeam;
    public String awayTeam;
    public String gameLocation;
    public String gameDate;

    public ScheduleModel(String gameName, String homeTeam, String awayTeam, String gameLocation, String gameDate) {
        this.gameName = gameName;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.gameLocation = gameLocation;
        this.gameDate = gameDate;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
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

    public String getGameLocation() {
        return gameLocation;
    }

    public void setGameLocation(String gameLocation) {
        this.gameLocation = gameLocation;
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }
}
