/*
 * Copyright (C) 2019 victor domingos 
 * https://no-title.victordomingos.com
 * https://github.com/victordomingos
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

import java.util.ArrayList;
import java.util.Comparator;
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

    private ArrayList<Double> rises = new ArrayList<>();
    private ArrayList<Double> transits = new ArrayList<>();
    private ArrayList<Double> sets = new ArrayList<>();

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

        switch (this.kind.toLowerCase()) {
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

        cleanRiseSetTransitListFromArray(riseEl.rise, rises);
        cleanRiseSetTransitListFromArray(riseEl.set, sets);
        cleanRiseSetTransitListFromArray(riseEl.transit, transits);

        this.constellation = riseEl.constellation;
        this.distance = riseEl.distance;

        ///showRisesSetsTransits(); // DEBUG

    }
    
    /**
     * Convert each array[double](rise, transit, set) to an ArrayList[Double]
     * but excluding any duplicates and invalid values, like 
     * ALWAYS_BELOW_HORIZON, CIRCUMPOLAR or NO_RISE_SET_TRANSIT.
     *
     * @param source an array of doubles for rise/set/transit from
     * EphemElelement
     * @param destination an ArrayList of Doubles without invalid values
     */
    private void cleanRiseSetTransitListFromArray(double[] source,
            ArrayList<Double> destination) {
        for (double d : source) {
            if (d != ALWAYS_BELOW_HORIZON
                    && d != CIRCUMPOLAR
                    && d != NO_RISE_SET_TRANSIT) {
                // DEBUG: check what hapens with Polaris and Sigma Octans 
                // - will they return CIRCUMPOLAR or what?
                if (!destination.contains((Double) d)){
                    destination.add((Double) d);
                }
            }
        }

    }

    public void showRisesSetsTransits() throws JPARSECException {
        System.out.println(name + " rises:");
        for (double rise : rises) {
            System.out.println("    " + rise + "  -  " + new AstroDate(rise).toStringTZ());
        }

        System.out.println(name + " transits:");
        for (double transit : transits) {
            System.out.println("    " + transit + "  -  " + new AstroDate(transit).toStringTZ());
        }

        System.out.println(name + " sets:");
        for (Double s : sets) {
            System.out.println("    " + s + "  -  " + new AstroDate(s).toStringTZ());
        }
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
     * This methods starts by getting a list of session hours and then iterates
     * on them. If it finds a time when the object is sbove the horizon, it
     * returns true.
     *
     * @return
     */
    public boolean willBeAboveHorizon() throws JPARSECException {
        for (TimeElement t : getHourlyTimeElements()) {
            ephemEl = Ephem.getEphemeris(t, this.observer, ephemerisEl, true);
            //get object elevation for specified instant
            if (ephemEl.elevation * RAD_TO_DEG > 0) { 
                return true;
            }
        }
        return false;
    }

    /**
     * Generate an ArrayList<TimeElement> for each hour within the session i.e.,
     * between startTimeEl and endTimeEl.
     *
     * @return
     */
    public ArrayList<TimeElement> getHourlyTimeElements() {
        ArrayList<TimeElement> time_interval = new ArrayList<>();

        // we are not taking minutes and seconds into account 
        // during the session configuration process...
        int m = 00;
        double s = 00;

        int y_start = startTimeEl.astroDate.getYear();
        int M_start = startTimeEl.astroDate.getMonth();
        int d_start = startTimeEl.astroDate.getDay();
        int h_start = startTimeEl.astroDate.getHour();

        int y_end = endTimeEl.astroDate.getYear();
        int M_end = endTimeEl.astroDate.getMonth();
        int d_end = endTimeEl.astroDate.getDay();
        int h_end = endTimeEl.astroDate.getHour();

        if (d_start == d_end) {
            for (int h = h_start; h <= h_end; h++) {
                //add all hours within the session
                AstroDate a = new AstroDate(y_start, M_start, d_start, h, m, s);
                time_interval.add(new TimeElement(a, SCALE.LOCAL_TIME));
            }
        } else {
            // our date selector only allows for a second day, so:
            for (int h = h_start; h < 24; h++) {
                //add hours for first day, aftwer session start
                AstroDate a = new AstroDate(y_start, M_start, d_start, h, m, s);
                time_interval.add(new TimeElement(a, SCALE.LOCAL_TIME));
            }
            for (int h = 0; h <= h_end; h++) {
                //add hours for second day, until session end
                AstroDate a = new AstroDate(y_end, M_end, d_end, h, m, s);
                time_interval.add(new TimeElement(a, SCALE.LOCAL_TIME));
            }
        }
        return time_interval;
    }


    /**
     * Determine visibility at a given time according to estimated limiting
     * magnitude
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

        System.out.println("\n=======> RISE"
                + "\n  Alt: " + riseEl.elevation * RAD_TO_DEG
                + "\n  Az: " + riseEl.azimuth * RAD_TO_DEG
                + "\n Constellation: " + riseEl.constellation
                + "\n Magnitude: " + riseEl.magnitude
                + "\n Phase: " + riseEl.phase * 100 + "%"
                + "\n Phase Angle: " + riseEl.phaseAngle * RAD_TO_DEG + "˚"
        );
    }

    public String getName() {
        return name;
    }

    public String getKind() {
        return kind;
    }

    public ArrayList<Double> getRises() {
        return rises;
    }

    public ArrayList<Double> getSets() {
        return sets;
    }

    public String getConstell() {
        return constellation;
    }

    public double getAparentMag() {
        return aparentMagnitude;
    }

    public double getAngularDiameter() {
        return angularDiameter;
    }

    public ArrayList<Double> getTransits() {
        return transits;
    }

}
