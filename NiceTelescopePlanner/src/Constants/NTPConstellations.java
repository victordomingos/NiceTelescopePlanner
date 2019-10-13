/*
 * Copyright (C) 2019 Victor Domingos
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
package Constants;

import static Constants.NTPConstants.CONSTELATIONS_FULL_LIST_WITH_ABBREVS;
import java.util.ArrayList;

/**
 * This class provides a list of the constellations and lookup methods
 *
 * The methods getAllAbbrevs() and getAllLatinNames() return ArrayList<String>
 * objects. There are also two methods, getAbbrev() and getLatinName() for
 * key-value and value-key retrieval
 *
 * @author victordomingos
 */
public class NTPConstellations {

    private ArrayList<String> abbrevs;
    private ArrayList<String> latinNames;

    /**
     * The constructor for the class NTPConstellations
     *
     */
    public NTPConstellations() {
        abbrevs = new ArrayList<>();
        latinNames = new ArrayList<>();

        for (String c : CONSTELATIONS_FULL_LIST_WITH_ABBREVS) {
            abbrevs.add(c.split(" - ")[0]);
            latinNames.add(c.split(" - ")[1]);
        }
    }

    /**
     * Get an ArrayList containing all constellation abbreviatures as strings.
     *
     * @return
     */
    public ArrayList<String> getAllAbbrevs() {
        return abbrevs;
    }

    /**
     * Get an ArrayList containing all constellation names in Latin as strings.
     *
     * @return
     */
    public ArrayList<String> getAllLatinNames() {
        return latinNames;
    }

    /**
     * Get the constellation abbreviation for a Latin name.
     *
     * @param latinName
     * @return
     */
    public String getAbbrev(String latinName) {
        return abbrevs.get(latinNames.indexOf(latinName));
    }

    /**
     * Get the constellation name in Latin according to the provided
     * abbreviation.
     *
     * @param abbrev
     * @return
     */
    public String getLatinName(String abbrev) {
        return latinNames.get(abbrevs.indexOf(abbrev));
    }
}
