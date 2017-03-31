package com.vgdatabase304.Structures;



public class Ranking {

    private int personalTotal;
    private int overallTotal;
    private int rank;

    public Ranking(int personalTotal, int overallTotal, int rank) {
        this.personalTotal = personalTotal;
        this.overallTotal = overallTotal;
        this.rank = rank;
    }

    public int getPersonalTotal() {
        return personalTotal;
    }

    public void setPersonalTotal(int personalTotal) {
        this.personalTotal = personalTotal;
    }

    public int getOverallTotal() {
        return overallTotal;
    }

    public void setOverallTotal(int overallTotal) {
        this.overallTotal = overallTotal;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
