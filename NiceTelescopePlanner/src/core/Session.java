/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import static Constants.NTPConstants.NTP_MOONS;
import static Constants.NTPConstants.NTP_PLANETS;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import jparsec.observer.ObserverElement;
import jparsec.time.AstroDate;
import jparsec.time.TimeElement;
import jparsec.util.JPARSECException;

/**
 *
 * @author victor
 */
public class Session {

//    private int session_id;
//    private int location_id;
//    private Date date;
//    private int bookmarked_targets = 0;
//    private int seen_targets = 0;
//    private String description = "";
//    private String notes = "";
    private int limMag; 
    private String constellation;
    private String onlyIncludeKind;
    private Boolean showBookmarked = true;
    private Boolean showAlreadySeen = true;

    private ObserverElement observer;
    private TimeElement startTimeEl, endTimeEl;
    Double timezone;

//    private ArrayList<SpaceObject> planets = new ArrayList<>();
//    private ArrayList<SpaceObject> moons = new ArrayList<>();
//    private ArrayList<SpaceObject> comets = new ArrayList<>();
//    private ArrayList<SpaceObject> stars = new ArrayList<>();
    private ArrayList<SpaceObject> targets = new ArrayList<>();

    /**
     * Assemble an ArrayList of SpaceObjects targets, filtering out targets less
     * bright than the selected limiting magnitude as well as those that wil be
     * below the horizon line, and so on…
     *
     * @param loc Location
     * @param datetime_start (LocalDateTime)
     * @param datetime_end (LocalDateTime)
     * @param limMag Limiting magnitude (int)
     * @param constellation
     * @param onlyIncludeKind
     */
    public Session(Location loc, LocalDateTime datetime_start,
            LocalDateTime datetime_end, int limMag, String constellation, 
            String onlyIncludeKind) {
        
        initializeSession(loc, datetime_start, datetime_end, limMag, 
                constellation, onlyIncludeKind);
        

        // DEBUG =========================================
//        System.out.println("START: " + datetime_start + " - "
//                + startTimeEl.toString());
//        System.out.println("       " + startTimeEl
//                + "  -  A: " + astrodtStart.toStringTZ());
//
//        System.out.println("END: " + datetime_end + " - "
//                + endTimeEl.toString());
//        System.out.println("       " + endTimeEl + "  -  A: " + astrodtEnd.toStringTZ());
//        System.out.println("COORDS: LAT " + latitude 
//                + " / LON " + longitude 
//                + " / HEIGHT " + height);
//        System.out.println("TIMEZONE: " + timezone);
        //System.out.println("LIM.MAGNITUDE: " + limMag);
//        System.out.println("\n\n");
        // ===============================================
        
        Instant start = Instant.now(); //DEBUG
        if (onlyIncludeKind.equalsIgnoreCase("All kinds") || onlyIncludeKind.equalsIgnoreCase("Planet")) {
            addTargets(NTP_PLANETS, "planet");
        }
        Instant end = Instant.now(); //DEBUG
        System.out.println("Planets: " + Duration.between(start, end)); //DEBUG
        

        
        start = Instant.now(); //DEBUG
        if (onlyIncludeKind.equalsIgnoreCase("All kinds") || onlyIncludeKind.equalsIgnoreCase("Moon")) {
            addTargets(NTP_MOONS, "moon");
        }
        end = Instant.now(); //DEBUG
        System.out.println("Moons: " + Duration.between(start, end)); //DEBUG
        

        
//        
//        int visible = 0;
//        // TODO - check this for more times during the session?
//        for (SpaceObject target : targets) {
//            if (target.isVisibleNakedEye(startTimeEl)) {
//                System.out.println(target.getName());
//                visible++;
//                //target.showTargetDetails();
//            }
//        }
//        System.out.println("\nNumber of targets above horizon: " + targets.size()
//                + " (" + visible + " visible at naked eye).");
    }

    private void initializeSession(Location loc, LocalDateTime datetime_start,
            LocalDateTime datetime_end, int limMag, String constellation, 
            String onlyIncludeKind) {
        
        this.limMag = limMag;
        this.constellation = constellation;
        this.onlyIncludeKind = onlyIncludeKind;

        // Gather and convert session configuration parameters --------------
        observer = new ObserverElement(loc.getName(),
                loc.getLongitudeRad(), loc.getLatitudeRad(),
                loc.getHeight(), loc.getTimezone());

        timezone = loc.getTimeZoneOffset();

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
        
    }

    private void addTargets(String[] targetList, String kind){
        for (String planet : targetList) {
                try {
                    SpaceObject o = new SpaceObject(planet, observer, startTimeEl,
                            endTimeEl, kind);
                    if ((o.getAparentMag() < limMag) && o.willBeAboveHorizon()) {
                        if (constellation.equalsIgnoreCase("All constellations")
                                || o.getConstell().equals(constellation)) {
                            targets.add(o);
                        }
                    }
                } catch (JPARSECException e) {
                    System.out.println("addTargets: " + planet + " (" + kind + ")\n" + e );
                }
            }
    }
    
    
    public ArrayList<SpaceObject> getTargets() {
        return targets;
    }

    public SpaceObject getTarget(String name) {
        for (SpaceObject target : targets) {
            if (target.getName().equals(name)) {
                return target;
            }
        }
        return null;
    }

    public ObserverElement getObserver() {
        return this.observer;
    }

    public Boolean getShowBookmarked() {
        return showBookmarked;
    }

    public void setShowBookmarked(Boolean showBookmarked) {
        this.showBookmarked = showBookmarked;
    }

    public Boolean getShowAlreadySeen() {
        return showAlreadySeen;
    }

    public void setShowAlreadySeen(Boolean showAlreadySeen) {
        this.showAlreadySeen = showAlreadySeen;
    }
}
