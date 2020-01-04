/* *****************************************************************************
 *  Name: Wester J. Aldarondo Torres
 *  NetID: wester.aldarondo@upr.edu
 *  Precept: P00
 *
 *  Partner Name: N/A
 *  Partner NetID: N/A
 *  Partner Precept: N/A
 *
 *  Description:  This is a template file for RingBuffer.java. It lists the
 *                constructors and methods you need, along with descriptions
 *                of what they're supposed to do.
 *
 *                Note: it won't compile until you fill in the constructors
 *                and methods (or at least commment out the ones whose return
 *                type is non-void).
 *
 **************************************************************************** */

public class RingBuffer {
    // YOUR INSTANCE VARIABLES HERE
    private int samp; // Represents the Ring buffer capacity
    private int first; // Represents first value in the RingBuffer
    private int last; // Represents last value in the RingBuffer
    private int size; // Represents the number of samples in RingBuffer
    private double[] samples; // Array to hold the RingBuffer values
    // first and last should never be greater than or equal than capacity

    // creates an empty ring buffer with the specified capacity
    public RingBuffer(int capacity) {
        // YOUR CODE HERE
        samp = capacity;
        samples = new double[samp];
        first = 0;
        last = 0;
        size = 0;
    }

    // return the capacity of this ring buffer
    public int capacity() {
        // YOUR CODE HERE
        return samp;
    }

    // return number of items currently in this ring buffer
    public int size() {
        // YOUR CODE HERE
        return size;
    }

    // is this ring buffer empty (size equals zero)?
    public boolean isEmpty() {
        // YOUR CODE HERE
        return size == 0;
    }

    // is this ring buffer full (size equals capacity)?
    public boolean isFull() {
        // YOUR CODE HERE
        return size == samp;
    }

    // adds item x to the end of this ring buffer
    public void enqueue(double x) {
        // YOUR CODE HERE
        if (isFull())
            throw new RuntimeException("Array is full");
        samples[last] = x;
        if (last < samp - 1)
            last++;
        else
            last = 0;
        size++;
    }

    // deletes and returns the item at the front of this ring buffer
    public double dequeue() {
        // YOUR CODE HERE
        if (isEmpty())
            throw new RuntimeException("Array is empty");
        double num = samples[first];
        samples[first] = 0.0;
        if (first < samp - 1)
            first++;
        else
            first = 0;

        size--;
        return num;
    }

    // returns the item at the front of this ring buffer
    public double peek() {
        // YOUR CODE HERE
        if (isEmpty())
            throw new RuntimeException("Array is empty");
        return samples[first];
    }

    // For testing
    public String toString() {
        String print = "";
        for (int i = 0; i < capacity(); i++) {
            print = print + samples[i] + ", ";
        }
        return print;
    }

    // tests and calls every instance method in this class
    public static void main(String[] args) {
        // YOUR CODE HERE
        RingBuffer yes = new RingBuffer(4); // Creates array with size 4
        System.out.println(yes.capacity());
        System.out.println(yes.size());
        System.out.println(yes.isEmpty());
        System.out.println(yes.isFull());
        yes.enqueue(0.5); // Adds 0.5 to the position 0 of the array
        yes.enqueue(0.6); // Adds 0.6 to the position 1 of the array
        System.out.println(yes.dequeue()); // Deletes and returns 0.5 because it
        // is the first element
        System.out.println(yes.peek()); // Prints 0.6 because it is now the first
        // element
    }

}
