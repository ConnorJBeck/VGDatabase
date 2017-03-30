package com.vgdatabase304.Structures;

public class Ranking {

    private int personalTotal;
    private int overallTotal;

    public Ranking(int personalTotal, int overallTotal) {
        this.personalTotal = personalTotal;
        this.overallTotal = overallTotal;
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
}
