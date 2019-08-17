/* *****************************************************************************
 * Name:    Joshua Santiago
 * NetID:   joshua.santiago14@upr.edu
 * Precept: P00
 *
 * Description: Write a program that produces pseudo-random bits by simulating
 * a linear-feedback shift register, and then use it to implement a simple
 * form of encryption for digital pictures.
 **************************************************************************** */

import java.awt.Color;

public class PhotoMagic {
    // returns a transformed copy of the specified picture, using the specified
    // lfsr.
    public static Picture transform(Picture picture, LFSR lfsr) {
        // read the picture specified by command-line argument
        int width = picture.width();
        int height = picture.height();

        Picture newPic = new Picture(width, height);

        for (int col = 0; col < width; col++) {
            for (int row = 0; row < height; row++) {

                // color value of current pixel
                Color color = picture.get(col, row);

                int r = color.getRed();  // r
                int g = color.getGreen();  // g
                int b = color.getBlue();  // b
                int rGen = lfsr.generate(8);
                int xorR = r ^ rGen;
                int gGen = lfsr.generate(8);
                int xorG = g ^ gGen;
                int genB = lfsr.generate(8);

                int xorB = b ^ genB;

                newPic.set(col, row, new Color(xorR, xorG, xorB));

            }
        }

        return newPic;
    }

    // takes the name of an image file and a description of an lfsr as command
    // -line arguments;
    // displays a copy of the image that is "encrypted" using the LFSR.
    public static void main(String[] args) {
        String name = args[0];
        Picture picture = new Picture(name);
        String password = args[1];
        int tapPosition = Integer.parseInt(args[2]);

        LFSR lfsr = new LFSR(password, tapPosition);
        Picture pic = transform(picture, lfsr);

        pic.show();

    }
}
