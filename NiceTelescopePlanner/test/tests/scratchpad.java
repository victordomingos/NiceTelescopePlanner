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

import Constants.NTPConstellations;
import core.Location;
import core.SpaceObject;
import java.io.IOException;
import java.net.ProtocolException;
import static jparsec.astronomy.Constellation.CONSTELLATION_NAMES;
import jparsec.ephem.Ephem;
import jparsec.observer.LocationElement;
import jparsec.ephem.EphemerisElement;
import jparsec.ephem.Functions;

import jparsec.ephem.RiseSetTransit;
import jparsec.ephem.Target;
import jparsec.ephem.planets.EphemElement;
import jparsec.ephem.stars.StarElement;
import jparsec.ephem.stars.StarEphem;
import jparsec.io.ConsoleReport;
import jparsec.math.Constant;
import static jparsec.math.Constant.DEG_TO_RAD;
import static jparsec.math.Constant.RAD_TO_DEG;
import jparsec.observer.ObserverElement;
import jparsec.time.TimeElement;
import jparsec.util.JPARSECException;
import jparsec.ephem.EphemerisElement.FRAME;

/**
 *
 * @author victor
 */
public class scratchpad {

    public static void main(String[] args) throws ProtocolException, IOException, JPARSECException {

        Location loc = new Location("Braga", 
                41.6 * DEG_TO_RAD,
                -8.4 * DEG_TO_RAD,
                194);
        TimeElement startTimeEl = new TimeElement("2019-10-14 22:00:00 UTC ");
        TimeElement endTimeEl = new TimeElement("2019-10-15 3:00:00 UTC ");  
        ObserverElement observer = new ObserverElement(loc.getName(),
                loc.getLongitudeRad(), loc.getLatitudeRad(),
                loc.getHeight(), loc.getTimezone());
        
        System.out.println(startTimeEl);
        System.out.println(endTimeEl);
        
        
        
        /*
        // Testing retrieval of data about planets: ==================
        System.out.println("\nPLANETS ====================== ");
        ArrayList<SpaceObject> planets = new ArrayList<>();
        for (String planet : NTPPlanets) {
            SpaceObject p = new SpaceObject(planet, observer, startTimeEl, 
                    endTimeEl, "planet");
            if(p.isAboveHorizon()) { 
                planets.add(p); 
                System.out.println(p.getName());
                p.showTargetDetails();
                System.out.println(".....");
            }
        }
        System.out.println(planets.size());
        
        //showConstellations();
        //listAllTargets(3);
        
        // Testing retrieval of data about natural satellites: ==================
        System.out.println("\nMOONS ====================== ");
        ArrayList<SpaceObject> moons = new ArrayList<>();
        for (String moon : NTPConstants.NTPMoons) {
            SpaceObject m = new SpaceObject(moon, observer, startTimeEl,
                    endTimeEl, "moon");
            if(m.isAboveHorizon()){
                moons.add(m); 
                System.out.println(m.getName());
            }
        }
        System.out.print(moons.size());
        
        System.out.println("\n\nDETAILS: ======================");
        ArrayList<SpaceObject> targets = new ArrayList<>();
        targets.addAll(planets);
        targets.addAll(moons);
        
        int visible = 0;
        for (SpaceObject target : targets) {
            if(target.isVisibleNakedEye(startTimeEl)) {
                System.out.println(target.getName());
                visible++;
                //target.showTargetDetails();
            }
        }
        System.out.println("Number of targets above horizon: " + targets.size()
                + " (" + visible + " visible at naked eye).");
        
        */
        
       
        // Star example from jparsec documentation:
        StarElement star = new StarElement("HD 6755", 
                Functions.parseRightAscension(1, 9, 42.3), 
                Functions.parseDeclination("61", 32, 49.5), 
                139, 
                0, 
                (float) (628.42 * 1.0E-3 * Constant.ARCSEC_TO_RAD),
                (float) (76.65 * 1.0E-3 * Constant.ARCSEC_TO_RAD), 
                -321.4f, 
                Constant.J2000, 
                FRAME.FK5);
        
        double uvw[] = StarEphem.getGalacticMotionUVW(star, true);
        System.out.println(ConsoleReport.doubleArrayReport(new String[] {"u = xxxx.x km/s", "v = xxxx.x km/s", "w = xxxx.x km/s"}, uvw));
        
//        
//        // Another star example from jparsec documentation
//        // Read BSC5 or SKYMASTER 2000 catalog
//        ReadFile rf = new ReadFile();
//        rf.setPath(StarEphem.PATH_TO_SkyMaster2000_JPARSEC_FILE);
//        rf.setFormat(ReadFile.FORMAT.BSC5);
//        rf.readFileOfStars();
//
//        // Choose a star.
//        int my_star = rf.searchByName("Alp UMi");
//        StarElement star2 = (StarElement) rf.READ_ELEMENTS.elementAt(my_star);
//
//        // Calc ephemeris.
//        StarEphemElement star_ephem = StarEphem.StarEphemeris(startTimeEl, observer, eph, star2, true);
//       

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
