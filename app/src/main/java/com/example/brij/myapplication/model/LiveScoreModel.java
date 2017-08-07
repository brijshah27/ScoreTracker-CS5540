package com.example.brij.myapplication.model;

/**
 * Created by Brij on 8/6/17.
 */

public class LiveScoreModel {

    private String teamName1;
    private String score1;
    private String teamName2;
    private String score2;
    private String teamName3;
    private String score3;
    private String teamName4;
    private String score4;

    public LiveScoreModel(String teamName1, String score1, String teamName2, String score2, String teamName3, String score3, String teamName4, String score4) {
        this.teamName1 = teamName1;
        this.score1 = score1;
        this.teamName2 = teamName2;
        this.score2 = score2;
        this.teamName3 = teamName3;
        this.score3 = score3;
        this.teamName4 = teamName4;
        this.score4 = score4;
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

    public String getTeamName3() {
        return teamName3;
    }

    public void setTeamName3(String teamName3) {
        this.teamName3 = teamName3;
    }

    public String getScore3() {
        return score3;
    }

    public void setScore3(String score3) {
        this.score3 = score3;
    }

    public String getTeamName4() {
        return teamName4;
    }

    public void setTeamName4(String teamName4) {
        this.teamName4 = teamName4;
    }

    public String getScore4() {
        return score4;
    }

    public void setScore4(String score4) {
        this.score4 = score4;
    }
}


