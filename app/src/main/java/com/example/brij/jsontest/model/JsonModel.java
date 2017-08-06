package com.example.brij.jsontest.model;

/**
 * Created by Brij on 8/5/17.
 */

public class JsonModel {

    private String teamName1;
    private String score1;
    private String teamName2;
    private String score2;

    public String getTeamName2() {
        return teamName2;
    }

    public void setTeamName2(String teamName2) {
        this.teamName2 = teamName2;
    }

    public String getScore2() {
        return score2;
    }

    public void setScore2(String score2) {
        this.score2 = score2;
    }

    public JsonModel(String teamName1, String score1, String teamName2, String score2) {
        this.teamName1 = teamName1;
        this.score1 = score1;
        this.teamName2 = teamName2;

        this.score2 = score2;
    }

    public JsonModel(String teamName1, String score1) {
        this.teamName1 = teamName1;
        this.score1 = score1;
    }

    public String getTeamName1() {
        return teamName1;
    }

    public void setTeamName1(String teamName1) {
        this.teamName1 = teamName1;
    }

    public String getScore1() {
        return score1;
    }

    public void setScore1(String score1) {
        this.score1 = score1;
    }
}
