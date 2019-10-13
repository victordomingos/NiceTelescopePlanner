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

import java.time.LocalDateTime;
import java.util.Date;
import jparsec.astronomy.VisualLimit;
import jparsec.ephem.Ephem;
import jparsec.ephem.EphemerisElement;
import jparsec.ephem.RiseSetTransit;
import jparsec.ephem.Target;
import jparsec.ephem.planets.EphemElement;
import jparsec.io.ConsoleReport;
import static jparsec.math.Constant.RAD_TO_DEG;
import jparsec.observer.ObserverElement;
import jparsec.time.TimeElement;
import jparsec.util.JPARSECException;

/**
 * The SpaceObject class represents a celestial body being observed from Earth
 *
 * @throws JPARSECException
 */
public class SpaceObject {

    private final String name;
    private final ObserverElement observer;
    private final TimeElement timeEl;

    private EphemerisElement ephemerisEl;
    private EphemElement ephemEl;
    private EphemElement riseEl;

    private double rise;
    private double transit;
    private double set;
    
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
     * @param timeEl A TimeElement instance containing the time of the
     * observation
     * @param category The kind of object (planet, moon, star, comet...)
     * @throws JPARSECException
     */
    public SpaceObject(String name, ObserverElement observer,
            TimeElement timeEl, String category) throws JPARSECException {

        this.name = name;
        this.timeEl = timeEl;
        this.observer = observer;
        

        ephemerisEl = new EphemerisElement(
                Target.getID(this.name),
                EphemerisElement.COORDINATES_TYPE.APPARENT,
                EphemerisElement.EQUINOX_OF_DATE,
                EphemerisElement.TOPOCENTRIC,
                EphemerisElement.REDUCTION_METHOD.IAU_2006,
                EphemerisElement.FRAME.ICRF,
                EphemerisElement.ALGORITHM.MOSHIER);

        switch (category) {
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

        ephemEl = Ephem.getEphemeris(this.timeEl, this.observer,
                ephemerisEl, true);

        riseEl = RiseSetTransit.obtainNextRiseSetTransit(timeEl,
                observer, ephemerisEl, ephemEl,
                RiseSetTransit.TWILIGHT.TWILIGHT_ASTRONOMICAL);
        this.angularDiameter = riseEl.angularRadius * 2;
        this.aparentMagnitude = riseEl.magnitude;
        this.rise = riseEl.rise[0];
        this.set = riseEl.set[0];
        this.transit = riseEl.transit[0];
        this.constellation = riseEl.constellation;
        this.distance = riseEl.distance;
        
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
     * Determine visibility according to estimated limiting magnitude
     *
     * @return
     */
    public boolean isVisibleNakedEye() {
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
        System.out.println("Ang. Diameter:  " + this.angularDiameter);
        System.out.println("App. Magnitude: " + this.aparentMagnitude);
        System.out.println("RA: " + this.ra);
        System.out.println("Dec: " + this.dec);
        System.out.println("Distance: " + this.distance);
    }

    public boolean isVisible(LocalDateTime ObsStart, LocalDateTime ObsEnd) {
        return false;
        // if obsStart > rise or ObsEnd < set: return true.
    }

    public String getName() {
        return name;
    }

}
