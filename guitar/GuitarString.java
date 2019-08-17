/* *****************************************************************************
 *  Name:   Joshua Santiago
 *  NetID:  josuhua.santiago14@upr.edu
 *  Precept: P00
 *
 *
 *  Description:  The program consists in using a ring buffer to store white
 *  noise. Then the values within an array will represent the frecuency of a
 *  guitar cord.
 *
 **************************************************************************** */

public class GuitarString {
    // YOUR INSTANCE VARIABLES HERE
    // length of arr
    private final int length;
    // buffer
    private final RingBuffer ringBuffer;

    // creates a guitar string of the specified frequency,
    // using sampling rate of 44,100
    public GuitarString(double frequency) {
        // YOUR CODE HERE
        length = (int) Math.ceil(44100 / frequency);
        ringBuffer = new RingBuffer(length);
        while (!ringBuffer.isFull()) {
            ringBuffer.enqueue(0);
        }
    }

    // creates a guitar string whose size and initial values are given by
    // the specified array
    public GuitarString(double[] init) {
        // YOUR CODE HERE
        length = init.length;

        ringBuffer = new RingBuffer(length);
        for (int i = 0; i < length; i++) {
            ringBuffer.enqueue(init[i]);
        }

    }

    // returns the number of samples in the ring buffer
    public int length() {
        // YOUR CODE HERE
        return length;
    }

    // plucks the guitar string (by replacing the buffer with white noise)
    public void pluck() {
        // YOUR CODE HERE

        while (!ringBuffer.isEmpty()) {
            ringBuffer.dequeue();
        }
        for (int i = 0; i < length; i++) {
            ringBuffer.enqueue(StdRandom.uniform(-0.5, 0.5));

        }

    }

    // advances the Karplus-Strong simulation one time step
    public void tic() {
        // YOUR CODE HERE
        double frst = ringBuffer.dequeue();
        double sec = sample();
        double enq = (frst + sec) / 2.0;
        ringBuffer.enqueue(enq * 0.996);
        // System.out.println((enq * 0.996));

    }

    // returns the current sample
    public double sample() {

        return ringBuffer.peek();
    }


    // tests and calls every constructor and instance method in this class
    public static void main(String[] args) {

        // create two guitar strings, for concert A and concert C
        double[] samples = { 0.2, 0.4, 0.5, 0.3, -0.2, 0.4, 0.3, 0.0, -0.1, -0.3 };
        GuitarString testString = new GuitarString(samples);
        int m = 25; // 25 tics
        for (int i = 0; i < m; i++) {
            double sample = testString.sample();
            StdOut.printf("%6d %8.4f\n", i, sample);
            testString.tic();
        }
        testString.pluck();
        System.out.println(testString.length());

    }

}
