/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import Constants.NTPConstants;
import static Constants.NTPConstants.NTPPlanets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import jparsec.observer.ObserverElement;
import jparsec.time.AstroDate;
import jparsec.time.TimeElement;
import jparsec.util.JPARSECException;

/**
 *
 * @author victor
 */
public class Session {
    int session_id;
    int location_id;
    Date date;
    int bookmarked_targets = 0;
    int seen_targets = 0;
    String description = "";
    String notes = "";
    private ArrayList<SpaceObject> planets = new ArrayList<>();
    private ArrayList<SpaceObject> moons = new ArrayList<>();
    private ArrayList<SpaceObject> targets = new ArrayList<>();
    
    
    public Session(Location loc, LocalDateTime datetime_start, 
            LocalDateTime datetime_end, int limMagnitude){
        
        // Gather and convert session configuration parameters --------------
        ObserverElement observer = new ObserverElement(loc.getName(),
                loc.getLongitudeRad(), loc.getLatitudeRad(),
                loc.getHeight(), loc.getTimezone());
        
        TimeElement startTimeEl, endTimeEl;
        
        Double latitude = loc.getLatitudeRad();
        Double longitude = loc.getLongitudeRad();
        int height = loc.getHeight();
        Double timezone = loc.getTimeZoneOffset();
        
      
        AstroDate astrodtStart = new AstroDate(
                datetime_start.getYear(),
                datetime_start.getMonthValue(), 
                datetime_start.getDayOfMonth(), 
                datetime_start.getHour(), 
                datetime_start.getMinute(), 
                datetime_start.getSecond());
        startTimeEl = new TimeElement(astrodtStart, TimeElement.SCALE.UNIVERSAL_TIME_UT1);

        
        AstroDate astrodtEnd = new AstroDate(
                datetime_end.getYear(),
                datetime_end.getMonthValue(), 
                datetime_end.getDayOfMonth(), 
                datetime_end.getHour(), 
                datetime_end.getMinute(), 
                datetime_end.getSecond());
        endTimeEl = new TimeElement(astrodtEnd, TimeElement.SCALE.UNIVERSAL_TIME_UT1);

                
        // DEBUG =========================================
        
        System.out.println("START: " + datetime_start + " - " 
                + startTimeEl.toString());
        System.out.println(startTimeEl + "  -  A: " + astrodtStart.toStringTZ());

        System.out.println("END: " + datetime_end + " - " 
                + endTimeEl.toString());
        System.out.println(endTimeEl + "  -  A: " + astrodtEnd.toStringTZ());

        System.out.println("COORDS: LAT " + latitude 
                + " / LON " + longitude 
                + " / HEIGHT " + height);
        
        System.out.println("TIMEZONE: " + timezone);
        System.out.println("LIM.MAGNITUDE: " + limMagnitude);
        System.out.println("\n\n");
        // ===============================================
        
        
        // Assemble an ArrayList of SpaceObjects targets, one kind at a time -------
        for (String planet : NTPPlanets) {
            try {
                SpaceObject p = new SpaceObject(planet, observer, startTimeEl,
                        endTimeEl, "planet");
                System.out.println(p.getName() 
                        + " - Mag: " + p.getAparentMagnitude()
                        + " - Const: " + p.getConstellation());
                System.out.println("WITHIN LIMIT MAG:" + (p.getAparentMagnitude() < limMagnitude));
                if((p.getAparentMagnitude() < limMagnitude) 
                        && p.willBeAboveHorizon()) { 
                    planets.add(p); 
                    //p.showTargetDetails();
                    System.out.println(p.getName() + ": will be up on the horizon");
                }               
            }
            catch (JPARSECException e) {
                System.out.println(e);
            }
        }
        System.out.println("\n======" + planets.size() + " planets =======\n\n\n"); // DEBUG
        
        for (String moon : NTPConstants.NTPMoons) {
            
            try{
                SpaceObject m = new SpaceObject(moon, observer,  startTimeEl,
                        endTimeEl, "moon");
                System.out.println(m.getName() 
                        + " - Mag: " + m.getAparentMagnitude()
                        + " - Const: " + m.getConstellation());
                System.out.println("WITHIN LIMIT MAG:" + (m.getAparentMagnitude() < limMagnitude));
                if((m.getAparentMagnitude() < limMagnitude) 
                        && m.willBeAboveHorizon()){
                    moons.add(m); 
                    //m.showTargetDetails();
                    System.out.println(m.getName() + ": will be up on the horizon");
                }
            }
            catch (JPARSECException e) {
                System.out.println(e);
            }
        }
        System.out.println("\n======" + moons.size() + " moons =======\n\n\n"); // DEBUG
        
        
        targets.addAll(planets);
        targets.addAll(moons);
        
        int visible = 0;
        // TODO - check this for more times during the session?
        for (SpaceObject target : targets) {
            if(target.isVisibleNakedEye(startTimeEl)) {
                System.out.println(target.getName());
                visible++;
                //target.showTargetDetails();
            }
        }
        System.out.println("\nNumber of targets above horizon: " + targets.size()
                + " (" + visible + " visible at naked eye).");
        
        
               
        
    }

    public ArrayList<SpaceObject> getTargets() {
        return targets;
    }
}
