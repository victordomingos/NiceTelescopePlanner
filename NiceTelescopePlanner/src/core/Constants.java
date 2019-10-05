/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.HashMap;

/**
 *
 * @author victor
 */
public class Constants {

    public static final String[] OUR_TOP_LIST_DEEPSPACE = {
        "NGC104", "NGC224", "NGC253", "NGC598", "NGC869", "NGC884",
        "NGC1976", "NGC2070", "NGC3293", "NGC3372", "NGC4594", "NGC5128",
        "NGC5139", "NGC5236", "NGC6121", "NGC6205", "NGC6405", "NGC6514",
        "NGC6533", "NGC6611", "NGC6618", "NGC6656", "NGC6720", "NGC6853",
        "NGC7089",};

    // Currently, location height or altitude is currently preset to 
    // the median elevation of human inhabited places.
    // Source: https://www.pnas.org/content/95/24/14009
    public static final String DEFAULT_LOCATION_HEIGHT = "194";

    public static final int DEFAULT_HTTP_CONNECTION_TIMEOUT = 3000;

    // Some approximate Limiting Magnitude values (meaning the brightness of the 
    // faintest visible star) for some kinds of equipment and for some sky 
    // objects, rouded up to fit into Integer values. This Hashmap feeds a
    // combobox in the session setup panel.
    public static HashMap<String, Integer> LIM_MAGNITUDE = new HashMap<String, Integer>();

    public Constants() {
        LIM_MAGNITUDE.put("24\" Telescope (30min stacked images)", 22);
        LIM_MAGNITUDE.put("Common portable telescopes", 15);
        LIM_MAGNITUDE.put("7x50 Binoculars", 10);
        LIM_MAGNITUDE.put("Extreme naked eye limit", 8);
        LIM_MAGNITUDE.put("Average naked eye limit", 7);
        LIM_MAGNITUDE.put("Betelgeuse", 0);
        LIM_MAGNITUDE.put("Sirius", 2);
        LIM_MAGNITUDE.put("Jupiter/Mars", 3);
        LIM_MAGNITUDE.put("Venus", 5);
    }
}
