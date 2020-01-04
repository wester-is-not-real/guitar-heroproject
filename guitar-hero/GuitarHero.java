/* *****************************************************************************
 *  Name:    Wester J. Aldarondo Torres
 *  NetID:   wester.aldarondo@upr.edu
 *  Precept: P00
 *
 *  Partner Name:    N/A
 *  Partner NetID:   N/A
 *  Partner Precept: N/A
 *
 *  Description: Similar to GuitarHeroLite, but supports a total of 37 notes
 *                 on the chromatic scale from 110 Hz to 880 Hz.
 *
 **************************************************************************** */

public class GuitarHero {
    public static void main(String[] args) {
        // Create two guitar strings, for concert A and C
        GuitarString string;
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] samples = new GuitarString[keyboard.length()];
        // Initializes GuitarString array
        for (int i = 0; i < keyboard.length(); i++) {
            string = new GuitarString(440.0 * Math.pow(2, (i - 24.0) / 12));
            samples[i] = string;
        }
        // the main input loop
        while (true) {

            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {

                // the user types this character
                char key = StdDraw.nextKeyTyped();

                // pluck the corresponding string
                for (int i = 0; i < keyboard.length(); i++) {
                    if (key == keyboard.charAt(i)) {
                        int index = keyboard.indexOf(key);
                        samples[index].pluck();
                    }
                }

            }

            // Computes superposition of samples
            double sample = 0.0;
            for (int i = 0; i < keyboard.length(); i++) {
                sample = sample + samples[i].sample();
            }

            // send the result to standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            for (int i = 0; i < keyboard.length(); i++) {
                samples[i].tic();
            }
        }
    }
}
