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

import jparsec.ephem.Ephem;
import jparsec.ephem.EphemerisElement;
import jparsec.ephem.Target.TARGET;
import jparsec.ephem.planets.EphemElement;
import jparsec.io.ConsoleReport;
import jparsec.observer.City;
import jparsec.observer.CityElement;
import jparsec.observer.ObserverElement;
import jparsec.time.AstroDate;
import jparsec.time.TimeElement;
import jparsec.time.TimeScale;
import jparsec.util.JPARSECException;

/**
 *
 * @author victor
 */
public class simpletime {

    public static void main(String[] args) throws JPARSECException {
        AstroDate astro = new AstroDate(2006, AstroDate.JANUARY, 1, 0, 0, 0);
        TimeElement time = new TimeElement(astro, TimeElement.SCALE.BARYCENTRIC_DYNAMICAL_TIME);
        CityElement city = City.findCity("Madrid");
        ObserverElement observer = ObserverElement.parseCity(city);
        EphemerisElement eph = new EphemerisElement();
        eph.optimizeForSpeed();
        
        double TTminusUT1 = TimeScale.getTTminusUT1(time, observer);

        System.out.println("TT-UT1: " + TTminusUT1 + " s");

        double jd_TDB = astro.jd();
        double jd_LT = TimeScale.getJD(time, observer, eph, TimeElement.SCALE.LOCAL_TIME);

        //=====================================
        EphemerisElement eph2 = new EphemerisElement(TARGET.SATURN, EphemerisElement.COORDINATES_TYPE.APPARENT,
                EphemerisElement.EQUINOX_OF_DATE, EphemerisElement.TOPOCENTRIC, EphemerisElement.REDUCTION_METHOD.IAU_2006,
                EphemerisElement.FRAME.DYNAMICAL_EQUINOX_J2000, EphemerisElement.ALGORITHM.MOSHIER);
        eph2.optimizeForSpeed();
        
// Include rise/set/transit calculations
        boolean full_ephem = true;

// Calculate
        EphemElement ephem = Ephem.getEphemeris(time, observer, eph2, full_ephem);

// Report results
        ConsoleReport.fullEphemReportToConsole(ephem);
    }

}
