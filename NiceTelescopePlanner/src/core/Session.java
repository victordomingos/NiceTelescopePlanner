/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import Constants.NTPConstants;
import static Constants.NTPConstants.NTPMoons;
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

    /**
     * Assemble an ArrayList of SpaceObjects targets, filtering out targets less
     * bright than the selected limiting magnitude as well as those that wil be
     * below the horizon line, and so on…
     */
    public Session(Location loc, LocalDateTime datetime_start,
            LocalDateTime datetime_end, int limMag, String constellation, String kind) {

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
        System.out.println("       " + startTimeEl
                + "  -  A: " + astrodtStart.toStringTZ());

        System.out.println("END: " + datetime_end + " - "
                + endTimeEl.toString());
        System.out.println("       " + endTimeEl + "  -  A: " + astrodtEnd.toStringTZ());

//        System.out.println("COORDS: LAT " + latitude 
//                + " / LON " + longitude 
//                + " / HEIGHT " + height);
        System.out.println("TIMEZONE: " + timezone);
        //System.out.println("LIM.MAGNITUDE: " + limMag);
        System.out.println("\n\n");
        // ===============================================
        if(kind.equalsIgnoreCase("All kinds") || kind.equalsIgnoreCase("Planet")){
            for (String planet : NTPPlanets) {
                try {
                    SpaceObject p = new SpaceObject(planet, observer, startTimeEl,
                            endTimeEl, "planet");
                    if ((p.getAparentMag() < limMag) && p.willBeAboveHorizon()) {
                        if (constellation.equalsIgnoreCase("All constellations")
                                || p.getConstell().equals(constellation)) {
                            planets.add(p);
                        }
                    }
                } catch (JPARSECException e) {
                    System.out.println(e);
                }
            }
            targets.addAll(planets);
        }

        if(kind.equalsIgnoreCase("All kinds") || kind.equalsIgnoreCase("Moon")){
            for (String moon : NTPMoons) {
                try {
                    SpaceObject m = new SpaceObject(moon, observer, startTimeEl,
                            endTimeEl, "moon");
                    if ((m.getAparentMag() < limMag) && m.willBeAboveHorizon()) {
                        if (constellation.equalsIgnoreCase("All constellations")
                                || m.getConstell().equals(constellation)) {
                            moons.add(m);
                        }
                    }
                } catch (JPARSECException e) {
                    System.out.println(e);
                }
            }
            targets.addAll(moons);
        }


        int visible = 0;
        // TODO - check this for more times during the session?
        for (SpaceObject target : targets) {
            if (target.isVisibleNakedEye(startTimeEl)) {
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
