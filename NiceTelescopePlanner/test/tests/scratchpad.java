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
import jparsec.ephem.EphemerisElement;
import jparsec.ephem.Target;
import jparsec.observer.ObserverElement;
import jparsec.time.TimeElement;
import jparsec.util.JPARSECException;

/**
 *
 * @author victor
 */
public class scratchpad {

    public static void main(String[] args) throws ProtocolException, IOException {
        Location loc = null;
        TimeElement timeEl = null;
        EphemerisElement ephemEl = null;
        
        loc = new Location("India", 0.23741713814878865, 1.3514957062818092, 686);
        
        ObserverElement observer = new ObserverElement(loc.getName(), 
                loc.getLatitudeRad(), loc.getLongitudeRad(), loc.getHeight(), 
                loc.getTimeZoneOffset());
        
        try {
            timeEl = new TimeElement("2019-10-10 00:00:00");
        } catch (JPARSECException ex) {
            System.out.println(ex);
        }
        
        
        System.out.println("Available targets: ");
        int i = 0;
        for (String name : Target.getNames() ){
            System.out.print(name + " \t\t\t");
            i++;
            if(i%5 == 0) {
                System.out.println("");
            }
        }
        
        System.out.println("Total targets: " + Target.getNames().length);
        
        //ephemEl = new EphemerisElement()
        
        
        
        
        System.out.println("LOCATION: " + loc);
        System.out.println("");
        System.out.println("OBS-ELEMENT: " + observer);
        System.out.println("");
        System.out.println("TIME-ELEMENT: " + timeEl);
        System.out.println("");
        System.out.println("EPHEMERIS-ELEMENT: " + ephemEl);
        
        
        
        
        
        
    }
}
