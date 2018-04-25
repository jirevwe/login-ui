package com.rtukpe.fixtrs.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import javax.annotation.Nullable;

public class Fixture {
    @SerializedName("id")
    public int id;

    @SerializedName("competitionId")
    public int competitionId;

    @SerializedName("date")
    public Date date;

    @SerializedName("status")
    public String status;

    @SerializedName("matchday")
    public int matchday;

    @SerializedName("homeTeamName")
    public String homeTeamName;

    @SerializedName("homeTeamId")
    public int homeTeamId;

    @SerializedName("awayTeamName")
    public String awayTeamName;

    @SerializedName("awayTeamId")
    public int awayTeamId;

    @SerializedName("result")
    public Result result;

    public class Result {
        @SerializedName("goalsHomeTeam")
        public int goalsHomeTeam = -1;

        @SerializedName("goalsAwayTeam")
        public int goalsAwayTeam = -1;

        @Nullable
        HalfTime halfTime;
    }

    public class HalfTime {
        @SerializedName("goalsHomeTeam")
        public int goalsHomeTeam = -1;

        @SerializedName("goalsAwayTeam")
        public int goalsAwayTeam = -1;
    }
}
