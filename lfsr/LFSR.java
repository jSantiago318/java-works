/* *****************************************************************************
 * Name:    Joshua Santiago
 * NetID:   joshua.santiago14@upr.edu
 * Precept: P00
 *
 * Description: Write a program that produces pseudo-random bits by simulating
 * a linear-feedback shift register, and then use it to implement a simple
 * form of encryption for digital pictures.
 **************************************************************************** */

public class LFSR {
    private final int n;           // number of bits in the LFSR
    private int[] reg;       // reg[i] = ith bit of LFSR, reg[1] is rightmost bit
    private final int tapPosition; // tap position


    // creates an LFSR with the specified seed and tap
    public LFSR(String seed, int tap) {
        n = seed.length();
        reg = new int[n];
        tapPosition = n - tap;

        for (int i = 0; i < n; i++) {
            if (seed.charAt(i) == '1') {
                reg[i] = 1;
            }
            else {
                reg[i] = 0;
            }
        }

    }

    // returns the number of bits in the LFSR.
    public int length() {
        return reg.length;
    }

    // returns bit i as 0 or 1.
    public int bitAt(int b) {


        return reg[n - b];

    }

    // returns a string representation of this LFSR
    public String toString() {
        String arr = "";
        for (int i = 0; i < n; i++) {
            if (reg[i] == 1) {
                arr += "1";
            }
            else {
                arr += "0";
            }
        }
        return arr;
    }

    // simulates one step of this LFSR and return the new bit as 0 or 1
    public int step() {
        int x = reg[0];
        int y = reg[tapPosition];

        int xor = x ^ y;


        for (int i = 1; i < n; i++) {

            reg[i - 1] = reg[i];
        }
        reg[n - 1] = xor;


        return xor;

    }

    // simulates k steps of this LFSR and return the k bits as a k-bit integer
    public int generate(int k) {
        int base = 0;
        for (int i = 0; i < k; i++) {
            int step = step();
            base = (base * 2) + step;
        }
        return base;
    }

    // tests this class by directly calling all instance methods
    public static void main(String[] args) {

        LFSR lfsr2 = new LFSR("01101000010", 9);
        StdOut.println(lfsr2.length());
        StdOut.println(lfsr2.bitAt(9));
        StdOut.println(lfsr2);

        int a = lfsr2.generate(5);
        StdOut.println(lfsr2 + " " + a);
        for (int i = 0; i < 10; i++) {
            int r = lfsr2.step();
            StdOut.println(lfsr2 + " " + r);
        }
    }

}

