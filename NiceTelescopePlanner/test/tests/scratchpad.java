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

/**
 *
 * @author victor
 */
public class scratchpad {
    public static void main(String[] args) throws ProtocolException, IOException {
        Location loc = new Location("India", 0.23741713814878865, 1.3514957062818092, 686);
        System.out.println("TZ Offset:" + loc.getTimeZoneOffset());
        System.out.println("TZ ID:" + loc.getTimeZoneID());
    }
}
