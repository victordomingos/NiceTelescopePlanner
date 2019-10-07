/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Constants;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Some approximate Limiting Magnitude values (meaning the brightness of the
 * faintest visible star) for some kinds of equipment and for some sky objects,
 * rouded up to fit into Integer values. This Hashmap feeds a combobox in the
 * session setup panel.
 */
public enum LIM_MAGNITUDE {
    CommonPortableTelescopes(15),
    Binoculars(10),
    ExtremeNakedEyeLimit(8),
    NakedEyeLimit(7),
    Betelgeuse(0),
    Sirius(-2),
    JupiterMars(-3),
    Venus(-5);

    private final int limit;

    LIM_MAGNITUDE(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }
    
    
   
    // Build immutable map of String name to enum pairs. Any Map impl can be used.
    // Adapted from https://stackoverflow.com/a/37841094
    private static final Map<String, LIM_MAGNITUDE> ENUM_MAP;
    
    static {
        Map<String,LIM_MAGNITUDE> map = new ConcurrentHashMap<>();
        for (LIM_MAGNITUDE lim : LIM_MAGNITUDE.values()) {
            map.put(lim.name(), lim);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }
    
    
    public static LIM_MAGNITUDE get (String name) {
        return ENUM_MAP.get(name);
    }

        
    public static boolean contains(String comparisonText) {
        for (LIM_MAGNITUDE lim : LIM_MAGNITUDE.values()) {
            if (lim.name().equals(comparisonText)) {
                return true;
            }
        }
        return false;
    }

   

}
