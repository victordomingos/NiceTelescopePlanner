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

import core.Location;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jparsec.ephem.Ephem;
import jparsec.ephem.EphemerisElement;
import jparsec.ephem.RiseSetTransit;
import jparsec.ephem.Target;
import jparsec.ephem.Target.TARGET;
import jparsec.ephem.planets.EphemElement;
import jparsec.io.ConsoleReport;
import static jparsec.io.ConsoleReport.fullEphemReportToConsole;
import static jparsec.math.Constant.DEG_TO_RAD;
import static jparsec.math.Constant.RAD_TO_DEG;
import jparsec.observer.ObserverElement;
import jparsec.time.AstroDate;
import jparsec.time.TimeElement;
import jparsec.util.JPARSECException;

/**
 *
 * @author victor
 */
public class scratchpad {

    public static void main(String[] args) throws ProtocolException, IOException, JPARSECException {
        Location loc = null;
        TimeElement timeEl = null;
        EphemerisElement ephemerisEl = null;
        EphemElement ephemEl = null;
        AstroDate astrodt = null;
        
        loc = new Location("AVV", 42*DEG_TO_RAD , -8.5*DEG_TO_RAD, 194);
    System.out.println("=======> LOCATION: " + loc);

    
        ObserverElement observer = new ObserverElement(loc.getName(), 
                loc.getLatitudeRad(), loc.getLongitudeRad(), loc.getHeight(), 
                0);
    System.out.println("=======> OBS-ELEMENT: " + observer);

    

        astrodt = new AstroDate(2019, AstroDate.OCTOBER, 11, 23, 0, 0);
        timeEl = new TimeElement(astrodt, TimeElement.SCALE.LOCAL_TIME);

        //timeEl = new TimeElement("2019-10-10 00:00:00 UTC");
        
    System.out.println("=======> TIME-ELEMENT: " + timeEl);
    
    
        
        System.out.println("Available targets: -----------------------");
        int i = 0;
        for (String name : Target.getNames() ){
            System.out.print(name + ", ");
            i++;
            if(i%6 == 0) {
                System.out.println("");
            }
        }
        System.out.println("------------------------------------------");
        System.out.println("Total targets: " + Target.getNames().length);
        
        
//            ephemerisEl = new EphemerisElement(Target.getID("Saturn"),
//                    EphemerisElement.COORDINATES_TYPE.APPARENT,
//                    EphemerisElement.EQUINOX_J2000, true,
//                    EphemerisElement.REDUCTION_METHOD.IAU_2006,
//                    EphemerisElement.FRAME.ICRF);
        ephemerisEl = new EphemerisElement(TARGET.SATURN, 
                EphemerisElement.COORDINATES_TYPE.APPARENT, 
                EphemerisElement.EQUINOX_OF_DATE, EphemerisElement.TOPOCENTRIC, 
                EphemerisElement.REDUCTION_METHOD.IAU_2006, 
                EphemerisElement.FRAME.DYNAMICAL_EQUINOX_J2000, 
                EphemerisElement.ALGORITHM.MOSHIER);
        
        
        System.out.println("=======> EPHEMERIS-ELEMENT: " + ephemerisEl);
        
        ephemEl = Ephem.getEphemeris(timeEl, observer, ephemerisEl, true);
        System.out.println("=======> EPHEM-ELEMENT: " + ephemEl);
        
        //System.out.println("=======> LOCATION: " + loc);
        //System.out.println("");
        //System.out.println("=======> OBS-ELEMENT: " + observer);
        //System.out.println("");
        //System.out.println("=======> TIME-ELEMENT: " + timeEl);
        //System.out.println("");
        //System.out.println("=======> EPHEMERIS-ELEMENT: " + ephemerisEl);
        //System.out.println("");
        //System.out.println("=======> EPHEM-ELEMENT: " + ephemEl);
        
        System.out.println("===========================");
        
        EphemElement rise;
        rise = RiseSetTransit.obtainNextRiseSetTransit(timeEl, 
                observer, ephemerisEl, ephemEl,
                RiseSetTransit.TWILIGHT.TWILIGHT_ASTRONOMICAL);
        System.out.println("");
        System.out.println("=======> RISE"
                + "\n  Alt: " + rise.elevation * RAD_TO_DEG
                + "\n  Az: " + rise.azimuth * RAD_TO_DEG
                + "\n Constellation: " + rise.constellation
                + "\n Magnitude: " + rise.magnitude
                + "\n Phase: " + rise.phase * 100 + "%"
                + "\n Phase Angle: " + rise.phaseAngle * RAD_TO_DEG + "˚"
        );
        
        System.out.println(ConsoleReport.getFullEphemReport(ephemEl, false, false, 4));
        
        TimeElement time = new TimeElement(astro, TimeElement.SCALE.BARYCENTRIC_DYNAMICAL_TIME);
        EphemElement ephem = Ephem.getEphemeris(time, observer, eph, true);
        
        System.out.println();
        
        
        
        
        
    }
}
