/* *****************************************************************************
 *  Name: Wester J. Aldarondo Torres
 *  NetID: wester.aldarondo@upr.edu
 *  Precept: P00
 *
 *  Partner Name: N/A
 *  Partner NetID: N/A
 *  Partner Precept: N/A
 *
 *  Description:  This is a template file for GuitarString.java. It lists the
 *                constructors and methods you need, along with descriptions
 *                of what they're supposed to do.
 *
 *                Note: it won't compile until you fill in the constructors
 *                and methods (or at least commment out the ones whose return
 *                type is non-void).
 *
 **************************************************************************** */

public class GuitarString {
    // YOUR INSTANCE VARIABLES HERE
    private int n; // holds the size of the Ringbuffer array
    private RingBuffer buffer; // Creates RingBuffer array

    // creates a guitar string of the specified frequency,
    // using sampling rate of 44,100
    public GuitarString(double frequency) {
        // YOUR CODE HERE
        n = (int) Math.ceil(44100 / frequency);
        buffer = new RingBuffer(n);
        for (int i = 0; i < n; i++) {
            buffer.enqueue(0.0);
        }

    }

    // creates a guitar string whose size and initial values are given by
    // the specified array
    public GuitarString(double[] init) {
        // YOUR CODE HERE
        n = init.length;
        buffer = new RingBuffer(n);
        for (int i = 0; i < n; i++) {
            buffer.enqueue(init[i]);
        }
    }

    //
    // returns the number of samples in the ring buffer
    public int length() {
        // YOUR CODE HERE
        return buffer.size();
    }

    // plucks the guitar string (by replacing the buffer with white noise)
    public void pluck() {
        // YOUR CODE HERE
        for (int i = 0; i < n; i++) {
            if (buffer.isFull())
                buffer.dequeue();
            buffer.enqueue(StdRandom.uniform(-0.5, 0.5));
        }
    }

    // advances the Karplus-Strong simulation one time step
    public void tic() {
        // YOUR CODE HERE
        double firstnum = buffer.dequeue();
        double secondnum = buffer.peek();
        buffer.enqueue(((firstnum + secondnum) / 2) * 0.996);
    }

    // returns the current sample
    public double sample() {
        // YOUR CODE HERE
        return buffer.peek();
    }


    // tests and calls every constructor and instance method in this class
    public static void main(String[] args) {
        // YOUR CODE HERE
        GuitarString nye = new GuitarString(10000);
        nye.pluck(); // Fills array with random numbers between -0.5 and 0.5
        System.out.println(nye.length()); // Shows number of samples in array
        // (should be 5)
        nye.tic(); // Deletes first number and adds changes the last number to
        // to the average of the first two numbers multiplied by the
        // energy decay factor
        System.out.println(nye.sample());
    }

}
