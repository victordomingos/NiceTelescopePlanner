/*
 * Copyright (C) 2019 victor
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package tests;

import Constants.NTPConstants;
import static Constants.NTPConstants.NTPPlanets;
import Constants.NTPConstellations;
import core.Location;
import core.SpaceObject;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import static jparsec.astronomy.Constellation.CONSTELLATION_NAMES;
import jparsec.astronomy.VisualLimit;
import jparsec.ephem.Ephem;
import jparsec.ephem.EphemerisElement;
import jparsec.ephem.RiseSetTransit;
import jparsec.ephem.Target;
import jparsec.ephem.Target.TARGET;
import jparsec.ephem.planets.EphemElement;
import jparsec.ephem.planets.PlanetEphem;

import static jparsec.math.Constant.DEG_TO_RAD;
import static jparsec.math.Constant.RAD_TO_DEG;
import jparsec.observer.ObserverElement;
import jparsec.time.TimeElement;
import jparsec.util.JPARSECException;

/**
 *
 * @author victor
 */
public class scratchpad {

    public static void main(String[] args) throws ProtocolException, IOException, JPARSECException {

        Location loc = new Location("Braga", 41.6 * DEG_TO_RAD, -8.4 * DEG_TO_RAD, 194);
        TimeElement timeEl = new TimeElement("2019-10-12 21:55:00 UTC ");

        ObserverElement observer = new ObserverElement(loc.getName(),
                loc.getLongitudeRad(), loc.getLatitudeRad(),
                loc.getHeight(), loc.getTimezone());

        
        // Testing retrieval of data about planets: ==================
        ArrayList<SpaceObject> targets = new ArrayList<>();
        System.out.println("PLANETS ======================");
        for (String planet : NTPPlanets) {
           targets.add(new SpaceObject(planet, observer, timeEl, "planet"));
        }
        
        //showConstellations();
        //listAllTargets(3);
        
        // Testing retrieval of data about natural satellites: ==================
        System.out.println("MOONS ======================");
        for (String moon : NTPConstants.NTPMoons) {
           targets.add(new SpaceObject(moon, observer, timeEl, "moon"));
        }
        
        
        System.out.println("DETAILS: ======================");
        for (SpaceObject target : targets) {
            target.showTargetDetails();
        }
        System.out.println("Number of targets: " + targets.size());
        
        
    }

    
    
    /**
     * List all targets from JPARSEC
     * 
     * @param objectsPerLine 
     */
    public static void listAllTargets(int objectsPerLine) {
        System.out.println("Available targets: -----------------------");
        int i = 0;
        for (String name : Target.getNames()) {
            System.out.print(name.trim() + "\t\t\t");
            i++;
            if (i % objectsPerLine == 0) {
                System.out.println("");
            }
        }
        System.out.println("------------------------------------------");
        System.out.println("Total targets: " + Target.getNames().length);
    }

    public static void showPlanet(String planetName, ObserverElement observer,
            TimeElement timeEl, String category) throws JPARSECException {
        
              
        EphemerisElement ephemerisEl = new EphemerisElement(Target.getID(planetName),
                EphemerisElement.COORDINATES_TYPE.APPARENT,
                EphemerisElement.EQUINOX_OF_DATE,
                EphemerisElement.TOPOCENTRIC,
                EphemerisElement.REDUCTION_METHOD.IAU_2006,
                EphemerisElement.FRAME.ICRF,
                EphemerisElement.ALGORITHM.MOSHIER);

        switch(category){
            case "moon":
                ephemerisEl.algorithm = EphemerisElement.ALGORITHM.NATURAL_SATELLITE;
                break;
            case "planet":
                ephemerisEl.algorithm = EphemerisElement.ALGORITHM.MOSHIER;
            case "star":
                ephemerisEl.algorithm = EphemerisElement.ALGORITHM.STAR;
            case "comet":
                ephemerisEl.algorithm = EphemerisElement.ALGORITHM.ORBIT;
            default:
                ephemerisEl.algorithm = EphemerisElement.ALGORITHM.MOSHIER;
                
        }

        ephemerisEl.optimizeForSpeed();

        EphemElement ephemEl = Ephem.getEphemeris(timeEl, observer,
                ephemerisEl, true);

        EphemElement rise = RiseSetTransit.obtainNextRiseSetTransit(timeEl,
                observer, ephemerisEl, ephemEl,
                RiseSetTransit.TWILIGHT.TWILIGHT_ASTRONOMICAL);
        
        System.out.println(
                "Alt: " + rise.elevation * RAD_TO_DEG
                //+ "\n  Az: " + rise.azimuth * RAD_TO_DEG
        //        + "\n Constellation: " + rise.constellation
                + "\n Magnitude: " + rise.magnitude
        //        + "\n Phase: " + rise.phase * 100 + "%"
        //        + "\n Phase Angle: " + rise.phaseAngle * RAD_TO_DEG + "˚"
        );
        //ConsoleReport.basicEphemReportToConsole(ephemEl);

    }
    
    /**
     * Constellation names - latin name to abbreviation, and vice versa =========
     * 
     **/
    private static void showConstellations(){
        

        NTPConstellations constels = new NTPConstellations();        
        System.out.println("Available constellations: -----------------------");
        for (String abbrev : constels.getAllAbbrevs()) {
            System.out.println(abbrev + " - " + constels.getLatinName(abbrev));
        }

        System.out.println("------------------------------------------");
        System.out.println("Total constellations: " + CONSTELLATION_NAMES.length);

        // From abbrev to latin name:
        System.out.print("Ser:");
        System.out.println(constels.getLatinName("Ser"));

        //From latin name to abbrev:
        System.out.print("Serpens:");
        System.out.println(constels.getAbbrev("Serpens"));
        
        /*
        System.out.println("JPARSEC: Available constellations: -----------------------");
        for (String name : CONSTELLATION_NAMES) {
            System.out.println(name);
            // Making sure the names in our list match those from JPARSEC:
            System.out.println(constels.getAbbrev(name)); 
        }
        System.out.println("------------------------------------------");
        System.out.println("Total constellations: " + CONSTELLATION_NAMES.length);
        */
    }

}
