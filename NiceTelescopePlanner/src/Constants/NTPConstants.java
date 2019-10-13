/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Constants;

/**
 *
 * @author victor
 */
public class NTPConstants {

    public static final String[] OUR_TOP_LIST_DEEPSPACE = {
        "NGC104", "NGC224", "NGC253", "NGC598", "NGC869", "NGC884",
        "NGC1976", "NGC2070", "NGC3293", "NGC3372", "NGC4594", "NGC5128",
        "NGC5139", "NGC5236", "NGC6121", "NGC6205", "NGC6405", "NGC6514",
        "NGC6533", "NGC6611", "NGC6618", "NGC6656", "NGC6720", "NGC6853",
        "NGC7089",};

    // Currently, location height or altitude is currently preset to 
    // the median elevation of human inhabited places.
    // Source: https://www.pnas.org/content/95/24/14009
    public static final String DEFAULT_LOCATION_HEIGHT = "194";

    public static final int DEFAULT_HTTP_CONNECTION_TIMEOUT = 5000;

    
    public static final String[] NTPPlanets = {"Moon", "Mars", "Venus", "Saturn",  "Jupiter", 
                            "Mercury", "Neptune", "Uranus", "Pluto"};
    
    
    public static final String[] NTPMoons = {
            "Ganymede", "Io", "Europa",             // Jupiter moons
            "Amalthea", "Himalia", "Callisto",      // Jupiter moons
            "Phobos", "Deimos",                     // Mars moons
            "Triton",                               // Neptunus moon
            "Janus",                                // Saturn moon
            // Avoiding a JPARSEC error with Saturn and Uranus moons (why?...)
            //"Titania", "Umbriel", "Ariel", "Oberon",  // Uranus moons
            //"Titan", "Iapetus", "Dione", "Rhea", 
            //"Thetis", "Mimas", "Hyperion", "Enceladus", 
        };
    
    public static final String[] CONSTELATIONS_FULL_LIST_WITH_ABBREVS = {
        "And - Andromeda",       "Ant - Antlia",          "Aps - Apus",
        "Aqr - Aquarius",        "Aql - Aquila",          "Ara - Ara",
        "Ari - Aries",           "Aur - Auriga",          "Boo - Bootes",
        "Cae - Caelum",          "Cam - Camelopardalis",  "Cnc - Cancer",
        "CVn - Canes Venatici",  "CMa - Canis Major",     "CMi - Canis Minor",
        "Cap - Capricornus",     "Car - Carina",          "Cas - Cassiopeia",
        "Cen - Centaurus",       "Cep - Cepheus",         "Cet - Cetus",
        "Cha - Chamaeleon",      "Cir - Circinus",        "Col - Columba",
        "Com - Coma Berenices",  "CrA - Corona Australis","CrB - Corona Borealis",
        "Crv - Corvus",          "Crt - Crater",          "Cru - Crux",
        "Cyg - Cygnus",          "Del - Delphinus",       "Dor - Dorado",
        "Dra - Draco",           "Equ - Equuleus",        "Eri - Eridanus",
        "For - Fornax",          "Gem - Gemini",          "Gru - Grus",
        "Her - Hercules",        "Hor - Horologium",      "Hya - Hydra",
        "Hyi - Hydrus",          "Ind - Indus",           "Lac - Lacerta", 
        "Leo - Leo",             "LMi - Leo Minor",       "Lep - Lepus", 
        "Lib - Libra",           "Lup - Lupus",           "Lyn - Lynx", 
        "Lyr - Lyra",            "Men - Mensa",           "Mic - Microscopium", 
        "Mon - Monoceros",       "Mus - Musca",           "Nor - Norma", 
        "Oct - Octans",          "Oph - Ophiuchus",       "Ori - Orion", 
        "Pav - Pavo",            "Peg - Pegasus",         "Per - Perseus", 
        "Phe - Phoenix",         "Pic - Pictor",          "Psc - Pisces", 
        "PsA - Piscis Austrinus","Pup - Puppis",          "Pyx - Pyxis", 
        "Ret - Reticulum",       "Sge - Sagitta",         "Sgr - Sagittarius", 
        "Sco - Scorpius",        "Scl - Sculptor",        "Sct - Scutum", 
        "Ser - Serpens",         "Se1 - Serpens Caput",   "Se2 - Serpens Cauda", 
        "Sex - Sextans",         "Tau - Taurus",          "Tel - Telescopium", 
        "Tri - Triangulum",      "TrA - Triangulum Australe", "Tuc - Tucana", 
        "UMa - Ursa Major",      "UMi - Ursa Minor",      "Vel - Vela", 
        "Vir - Virgo",           "Vol - Volans",          "Vul - Vulpecula"};
}
