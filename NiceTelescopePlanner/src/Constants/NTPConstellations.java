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

import static Constants.Constants.CONSTELATIONS_FULL_LIST_WITH_ABBREVS;
import java.util.ArrayList;



public class NTPConstellations {

    private ArrayList<String> abbrevs;
    private ArrayList<String> latinNames;
    
    public NTPConstellations() {
        abbrevs = new ArrayList<>();
        latinNames = new ArrayList<>();
        
        for (String c : CONSTELATIONS_FULL_LIST_WITH_ABBREVS) {
            //System.out.println("A:" + c.split(" - ")[0]);
            abbrevs.add(c.split(" - ")[0]);
            latinNames.add(c.split(" - ")[1]);
            //System.out.println("B:" + c.split(" - ")[1]);
        }
    }

    public ArrayList<String> getAllAbbrevs() {
        return abbrevs;
    }

    public ArrayList<String> getAllLatinNames() {
        return latinNames;
    }

    public String getAbbrev(String latinName) {
        return abbrevs.get(latinNames.indexOf(latinName));
    }

    public String getLatinName(String abbrev) {
        return latinNames.get(abbrevs.indexOf(abbrev));
    }
    
    

}
