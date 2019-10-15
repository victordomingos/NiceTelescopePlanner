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
package core;

import jparsec.astronomy.VisualLimit;
import jparsec.ephem.Ephem;
import jparsec.ephem.EphemerisElement;
import jparsec.ephem.RiseSetTransit;
import static jparsec.ephem.RiseSetTransit.ALWAYS_BELOW_HORIZON;
import static jparsec.ephem.RiseSetTransit.CIRCUMPOLAR;
import static jparsec.ephem.RiseSetTransit.NO_RISE_SET_TRANSIT;
import jparsec.ephem.Target;
import jparsec.ephem.planets.EphemElement;
import jparsec.io.ConsoleReport;
import static jparsec.math.Constant.RAD_TO_DEG;
import jparsec.observer.ObserverElement;
import jparsec.time.AstroDate;
import jparsec.time.TimeElement;
import jparsec.time.TimeElement.SCALE;
import jparsec.time.TimeScale;
import jparsec.util.JPARSECException;

/**
 * The SpaceObject class represents a celestial body being observed from Earth
 *
 * @throws JPARSECException
 */
public class SpaceObject {

    private final String name;
    private final String kind;
    private final ObserverElement observer;
    private final TimeElement startTimeEl;
    private final TimeElement endTimeEl;

    private EphemerisElement ephemerisEl;
    private EphemElement ephemEl;
    private EphemElement riseEl;

    private double[] rises;
    private double[] transits;
    private double[] sets;

    private double ra;                  // right ascension
    private double dec;                 // declination
    private String constellation;

    private double distance;
    private double aparentMagnitude;
    private double angularDiameter;

    /**
     * The default constructor for the SpaceObject class
     *
     * @param name The main designation of the object
     * @param observer An ObserverElement instance containing user location
     * details
     * @param startTimeEl A TimeElement instance containing the start time of
     * the observation session
     * @param endTimeEl A TimeElement instance containing the end time of the
     * observation sesison
     * @param category The kind of object (planet, moon, star, comet...)
     * @throws JPARSECException
     */
    public SpaceObject(String name, ObserverElement observer,
            TimeElement startTimeEl, TimeElement endTimeEl, String category)
            throws JPARSECException {

        this.name = name;
        this.startTimeEl = startTimeEl;
        this.endTimeEl = endTimeEl;
        this.observer = observer;
        this.kind = category.substring(0, 1).toUpperCase() 
                + category.substring(1);

        ephemerisEl = new EphemerisElement(
                Target.getID(this.name),
                EphemerisElement.COORDINATES_TYPE.APPARENT,
                EphemerisElement.EQUINOX_OF_DATE,
                EphemerisElement.TOPOCENTRIC,
                EphemerisElement.REDUCTION_METHOD.IAU_2006,
                EphemerisElement.FRAME.ICRF,
                EphemerisElement.ALGORITHM.MOSHIER);

        switch (category.toLowerCase()) {
            case "moon":
                ephemerisEl.algorithm = EphemerisElement.ALGORITHM.NATURAL_SATELLITE;
                break;
            case "planet":
                ephemerisEl.algorithm = EphemerisElement.ALGORITHM.MOSHIER;
                break;
            case "star":
                ephemerisEl.algorithm = EphemerisElement.ALGORITHM.STAR;
                break;
            case "comet":
                ephemerisEl.algorithm = EphemerisElement.ALGORITHM.ORBIT;
                break;
            default:
                ephemerisEl.algorithm = EphemerisElement.ALGORITHM.MOSHIER;

        }
        ephemerisEl.optimizeForSpeed();

        ephemEl = Ephem.getEphemeris(this.startTimeEl, this.observer,
                ephemerisEl, true);

        riseEl = RiseSetTransit.obtainNextRiseSetTransit(startTimeEl,
                observer, ephemerisEl, ephemEl,
                RiseSetTransit.TWILIGHT.TWILIGHT_ASTRONOMICAL);
        this.angularDiameter = riseEl.angularRadius * 2;
        this.aparentMagnitude = riseEl.magnitude;
        this.rises = riseEl.rise;
        this.sets = riseEl.set;
        this.transits = riseEl.transit;
        this.constellation = riseEl.constellation;
        this.distance = riseEl.distance;

        System.out.println(name + " rises:");
        for (double rise : rises) {
            System.out.println("    " + rise + "  -  " + new AstroDate(rise).toStringTZ());
        }
        System.out.println(name + " transits:");
        for (double transit : transits) {
            System.out.println("    " + transit + "  -  " + new AstroDate(transit).toStringTZ());
        }
        System.out.println(name + " sets:");
        for (double set : sets) {
            System.out.println("    " + set + "  -  " + new AstroDate(set).toStringTZ());
            if(set==ALWAYS_BELOW_HORIZON){
                System.out.println("ALWAYS_BELOW_HORIZON");
            } else if (set == CIRCUMPOLAR){
                System.out.println("CIRCUMPOLAR");
            } else if (set == NO_RISE_SET_TRANSIT){
                System.out.println("NO_RISE_SET_TRANSIT");
            } else{
                System.out.println("ELSE ---- ?");
            }
           
        }
        
        

        /*
                
        System.out.println("\n=======> RISE"
                + "\n  Alt: " + rise.elevation * RAD_TO_DEG
                + "\n  Az: " + rise.azimuth * RAD_TO_DEG
                + "\n Constellation: " + rise.constellation
                + "\n Magnitude: " + rise.magnitude
                + "\n Phase: " + rise.phase * 100 + "%"
                + "\n Phase Angle: " + rise.phaseAngle * RAD_TO_DEG + "˚"
        );
         */
    }

    /**
     * Check if this object is above the horizon line
     *
     * @return
     * @throws JPARSECException
     */
    public boolean isAboveHorizon() throws JPARSECException {
        return (riseEl.elevation * RAD_TO_DEG > 0);
    }

    /**
     * Determine if target will be above horizon during the specified interval
     *
     * @return
     */
    public boolean willBeAboveHorizon() throws JPARSECException {
       
        double jdUT_start = TimeScale.getJD(startTimeEl, observer, ephemerisEl, 
                SCALE.UNIVERSAL_TIME_UT1);
        double jdUT_end = TimeScale.getJD(endTimeEl, observer, ephemerisEl, 
                SCALE.UNIVERSAL_TIME_UT1);
        
        System.out.println(name + " - Sets:     " + sets.length);
        System.out.println(name + " - Rises:    " + sets.length);
        System.out.println(name + " - Transits: " + sets.length);
        
        if(sets[0]==RiseSetTransit.ALWAYS_BELOW_HORIZON){
            System.out.println(name + ": Never up in the horizon.");
            return false;
        }
               
        
        //Is the first rise between start and end?
        // Or is the last set between start and end?
        for (double rise : rises) {
            if (jdUT_start < rise  &&  rise < jdUT_end) {
                System.out.println("\n\n" + name + ": Is the first rise between start and end? " 
                        + (jdUT_start < rises[0]));
                System.out.println(name +": Is the last rise between start and end?  "
                        + (jdUT_end < rises[rises.length - 1]));
                return true;
            }
        }
        
        
        for (double set : sets) {
            if (jdUT_start < set  &&  set < jdUT_end ) {
                System.out.println(name + ": Is the first set between start and end? " 
                        + (jdUT_start < transits[0]));
                System.out.println(name + ": Is the last set between start and end?  "
                        + (jdUT_end < sets[sets.length - 1]));
                return true;
            }
        }
        
        for (double transit : transits) {
            if (jdUT_start < transit  &&  transit < jdUT_end ) {
                System.out.println(name + ": Is the first transit between start and end? " 
                        + (jdUT_start < transits[0]));
                System.out.println(name + ": Is the last transit between start and end?  "
                        + (jdUT_end < transits[transits.length - 1]));
                return true;
            }
        }        
        
        System.out.println("\n" + name + ": wont be up on the horizon.\n\n\n");
        return false;
    }

    /**
     * Determine visibility at a given time according to estimated limiting magnitude
     *
     * @return
     */
    public boolean isVisibleNakedEye(TimeElement timeEl) {
        boolean visible = false;
        try {
            //===========================
            //        
            //        System.out.println("\n" + this.name + "\n\n==> Visible to Naked Eye? \t" 
            //                + VisualLimit.isVisibleToNakedEye(timeEl, observer, ephemerisEl, ephemEl));
            //        System.out.println("==> Above horizon? \t\t"  + (rise.elevation*RAD_TO_DEG > 0));
            //============================

            // check for visibility according to limiting magnification
            visible = VisualLimit.isVisibleToNakedEye(timeEl, observer,
                    ephemerisEl, ephemEl);

        } catch (JPARSECException ex) {
            System.out.println(ex);
        }
        return visible;
    }

    public void showTargetDetails() throws JPARSECException {
        ConsoleReport.basicEphemReportToConsole(ephemEl);
        System.out.println(name + ": Ang. Diameter:  " + this.angularDiameter);
        System.out.println(name + ": App. Magnitude: " + this.aparentMagnitude);
        System.out.println(name + ": RA: " + this.ra);
        System.out.println(name + ": Dec: " + this.dec);
        System.out.println(name + ": Distance: " + this.distance);
    }


    public String getName() {
        return name;
    }

    public String getKind() {
        return kind;
    }

    public double[] getRises() {
        return rises;
    }

    public double[] getSets() {
        return sets;
    }

    public String getConstellation() {
        return constellation;
    }

}
