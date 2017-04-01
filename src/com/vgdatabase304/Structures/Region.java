package com.vgdatabase304.Structures;

import com.vgdatabase304.Exceptions.InstanceNotFoundException;
import sun.security.jca.GetInstance;

public enum Region {
    NTSC,PAL;

    public static Region getRegionFromString(String region) throws InstanceNotFoundException {
        switch (region) {
            case "NTSC":
                return NTSC;
            case "PAL":
                return PAL;
            default:
                throw new InstanceNotFoundException();
        }
    }
}
