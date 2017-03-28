package com.vgdatabase304.Structures;
import com.vgdatabase304.Utils.*;
import com.vgdatabase304.Exceptions.*;

public enum ESRBRating {
    EC ("EC", "Early Childhood"),
    E ("E", "Everyone"),
    ET ("E10+", "Everyone 10+"),
    T ("T", "Teen"),
    M ("M", "Mature"),
    AO ("AO", "Adults Only");

    private final String shortName;
    private final String ratingName;
    ESRBRating(String shortName, String ratingName) {
        this.shortName = shortName;
        this.ratingName = ratingName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getRatingName() {
        return ratingName;
    }

    public static ESRBRating getRatingFromString(String rating) throws InstanceNotFoundException {
        switch (rating) {
            case "EC":
            case "Early Childhood":
                return EC;
            case "E":
            case "Everyone":
                return E;
            case "ET10+":
            case "Everyone 10+":
                return ET;
            case "T":
            case "Teen":
                return T;
            case "M":
            case "Mature":
                return M;
            case "AO":
            case "Adults Only":
                return AO;
            default:
                throw new InstanceNotFoundException("ESRB Rating not found");
        }

    }
}
