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
import java.io.IOException;
import java.net.ProtocolException;
import static jparsec.astronomy.Constellation.CONSTELLATION_NAMES;
import jparsec.ephem.Ephem;
import jparsec.ephem.EphemerisElement;
import jparsec.ephem.RiseSetTransit;
import jparsec.ephem.Target;
import jparsec.ephem.planets.EphemElement;
import jparsec.io.ConsoleReport;
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
        NTPConstellations constels = new NTPConstellations();

        Location loc = new Location("AVV", 42 * DEG_TO_RAD, -8.5 * DEG_TO_RAD, 194);
        TimeElement timeEl = new TimeElement("2019-10-11 00:00:00 UTC ");

        ObserverElement observer = new ObserverElement(loc.getName(),
                loc.getLongitudeRad(), loc.getLatitudeRad(),
                loc.getHeight(), loc.getTimezone());

        showPlanet("Mars", observer, timeEl);
        showPlanet("Venus", observer, timeEl);
        showPlanet("Saturn", observer, timeEl);
        showPlanet("Moon", observer, timeEl);
        showPlanet("Jupiter", observer, timeEl);
        showPlanet("Mercury", observer, timeEl);
        
        
        
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
        
        
        
        
        System.out.println("JPARSEC: Available constellations: -----------------------");
        for (String name : CONSTELLATION_NAMES) {
            System.out.println(name);
            System.out.println(constels.getAbbrev(name));
        }
        System.out.println("------------------------------------------");
        System.out.println("Total constellations: " + CONSTELLATION_NAMES.length);

        
    }

    
    
    
    public static void listAllTargets(int objectsPerLine) {
        System.out.println("Available targets: -----------------------");
        int i = 0;
        for (String name : Target.getNames()) {
            System.out.print(name + ", ");
            i++;
            if (i % objectsPerLine == 0) {
                System.out.println("");
            }
        }
        System.out.println("------------------------------------------");
        System.out.println("Total targets: " + Target.getNames().length);
    }

    public static void showPlanet(String planetName, ObserverElement observer,
            TimeElement timeEl) throws JPARSECException {

        EphemerisElement ephemerisEl = new EphemerisElement(Target.getID(planetName),
                EphemerisElement.COORDINATES_TYPE.APPARENT,
                EphemerisElement.EQUINOX_OF_DATE,
                EphemerisElement.TOPOCENTRIC,
                EphemerisElement.REDUCTION_METHOD.IAU_2006,
                EphemerisElement.FRAME.ICRF,
                EphemerisElement.ALGORITHM.MOSHIER);

        ephemerisEl.optimizeForSpeed();

        EphemElement ephemEl = Ephem.getEphemeris(timeEl, observer,
                ephemerisEl, true);

        EphemElement rise = RiseSetTransit.obtainNextRiseSetTransit(timeEl,
                observer, ephemerisEl, ephemEl,
                RiseSetTransit.TWILIGHT.TWILIGHT_ASTRONOMICAL);

        System.out.println("\n=======> RISE"
                + "\n  Alt: " + rise.elevation * RAD_TO_DEG
                + "\n  Az: " + rise.azimuth * RAD_TO_DEG
                + "\n Constellation: " + rise.constellation
                + "\n Magnitude: " + rise.magnitude
                + "\n Phase: " + rise.phase * 100 + "%"
                + "\n Phase Angle: " + rise.phaseAngle * RAD_TO_DEG + "˚"
        );
        ConsoleReport.basicEphemReportToConsole(ephemEl);

    }

}
