/* *****************************************************************************
 *  Name:   Joshua Santiago
 *  NetID:  joshua.santiago14@upr.edu
 *  Precept: P00
 *
 *
 *  Description:  create a data type to model the ring buffer. Teh ring buffer
 *  will have a given length and the given data should keep looping inside the
 *  buffer.
 *
 **************************************************************************** */

public class RingBuffer {
    // YOUR INSTANCE VARIABLES HERE
    // capacity
    private final int initCapacity;
    // size
    private int initSize;
    // buffer array
    private double[] buffer;
    // privae Queue<Double> queueBuff;
    private int first;
    // last in arr
    private int last;

    // creates an empty ring buffer with the specified capacity
    public RingBuffer(int capacity) {
        // YOUR CODE HERE
        initCapacity = capacity;
        buffer = new double[initCapacity];

        for (int i = 0; i < initCapacity; i++) {
            buffer[i] = 0.0;
        }
        first = 0;
        last = 0;
        initSize = 0;
    }

    // return the capacity of this ring buffer
    public int capacity() {
        // YOUR CODE HERE
        return initCapacity;
    }

    // return number of items currently in this ring buffer
    public int size() {
        // YOUR CODE HERE
        return initSize;
    }

    // is this ring buffer empty (size equals zero)?
    public boolean isEmpty() {
        // YOUR CODE HERE
        return initSize == 0;

    }

    // is this ring buffer full (size equals capacity)?
    public boolean isFull() {
        // YOUR CODE HERE
        return size() == capacity();
    }

    // adds item x to the end of this ring buffer
    public void enqueue(double x) {
        // YOUR CODE HERE

        if (isFull()) {
            throw new RuntimeException("Array is full");
        }
        if (last == initCapacity) {
            buffer[0] = x;
            last = 0;
        }
        else {
            buffer[last] = x;
        }

        last++;
        initSize++;

    }

    // deletes and returns the item at the front of this ring buffer
    public double dequeue() {
        // YOUR CODE HERE
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        double frst = buffer[first];
        first++;
        if (first == capacity()) {
            first = 0;
        }
        initSize--;

        return frst;
    }

    // returns the item at the front of this ring buffer
    public double peek() {
        // YOUR CODE HERE
        if (isEmpty()) {
            throw new RuntimeException("Array is empty");
        }

        return buffer[first];

    }

    // tests and calls every instance method in this class
    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        RingBuffer buffer = new RingBuffer(n);
        for (int i = 1; i <= n; i++) {
            buffer.enqueue(i);
        }
        System.out.println(buffer.peek());
        double t = buffer.dequeue();
        buffer.enqueue(t);
        StdOut.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) {
            double x = buffer.dequeue();
            double y = buffer.dequeue();
            buffer.enqueue(x + y);
        }
        StdOut.println(buffer.size());
        StdOut.println(buffer.capacity());
        StdOut.println(buffer.isEmpty());
        StdOut.println(buffer.isFull());
        StdOut.println(buffer.peek());
    }

}
