/*
 * Copyright (C) 2019 EFA
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
package gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Get an image from a file scaling it first
 *
 * The dimensions of the resulting image are obtained after the current size of
 * the label.
 *
 */
public class ImageLoader {

    private static Image img;

    public static ImageIcon getScaledSpaceImage(String objectName, JLabel label) {
        String path = Paths.get("src/images/" + objectName + ".jpg")
                .toAbsolutePath().toString();
        System.out.println(path);
        BufferedImage buffImg;

        try {
            buffImg = ImageIO.read(new File(path));
            img = buffImg.getScaledInstance(label.getWidth(), label.getHeight(),
                    Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (IOException e) {
            System.out.println("Don't panic! It's ok. " + e);
        }

        return null;
    }
}
