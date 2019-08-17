/* *****************************************************************************
 *  Name:   Joshua Santiago
 *  NetID:  joshua.santiago14@upr.edu
 *  Precept:    P00
 *
 *
 *  Description:  Plays  guitar strings
 *                when the user types the certain keys in the keyboard,
 *                respectively in the standard drawing window.
 *
 **************************************************************************** */

public class GuitarHero {
    // keys to play
    private static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";


    public static void main(String[] args) {

        // crate an array to hold each guitar string
        GuitarString[] guitarString = new GuitarString[keyboard.length()];

        // compute the frequency of each key on the keyboard
        for (int i = 0; i < keyboard.length(); i++) {
            double freq = 440.0 * Math.pow(2.0, (i - 24.0) / 12.0);
            guitarString[i] = new GuitarString(freq);
        }
        // the main input loop
        while (true) {

            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {

                // the user types this character
                char key = StdDraw.nextKeyTyped();

                // pluck the corresponding string
                if (keyboard.contains(String.valueOf(key))) {
                    guitarString[keyboard.indexOf(key)].pluck();
                }

            }

            // compute the superposition of the samples
            double sample = 0.0;
            for (int i = 0; i < keyboard.length(); i++) {
                sample += +guitarString[i].sample();
                guitarString[i].tic();
            }

            // send the result to standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step

        }
    }

}
