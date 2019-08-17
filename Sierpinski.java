/* *****************************************************************************
 *  Name:    Joshua Santiago
 *  NetID:   joshua.santiago14@upr.edu
 *  Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */

public class Sierpinski {
    //  Height of an equilateral triangle whose sides are of the specified length.
    public static double height(double length) {
        double height = (length * Math.sqrt(3)) / 2;
        return height;
    }

    //  Draws a filled equilateral triangle whose bottom vertex is (x, y)
    //  of the specified side length.
    public static void filledTriangle(double x, double y, double length) {

        double[] aX = { x, (x + length / 2), (x - length / 2) };
        double[] aY = { y, (y + height(length)), (y + height(length)) };
        StdDraw.filledPolygon(aX, aY);
    }

    //  Draws a Sierpinski triangle of order n, such that the largest filled
    //  triangle has bottom vertex (x, y) and sides of the specified length.
    public static void sierpinski(int n, double x, double y, double length) {
        // base case
        if (n == 0)
            return;
        // reductive step
        filledTriangle(x, y, length);

        sierpinski(n - 1, x, y + height(length), length / 2); // top triangle
        sierpinski(n - 1, x + length / 2, y, length / 2); // right triangle
        sierpinski(n - 1, x - length / 2, y, length / 2); // left triangle
        
    }


    // Tests each of the API methods by directly calling them.
    public static void main(String[] args) {
        // Scales polygon by the factor 2.
        int loop = Integer.parseInt(args[0]);

        double[] x = { 0, 1, 0.5 };
        double[] y = { 0, 0, height(1) };
        double initX = 0.5;
        double initY = 0;
        double length = 0.5;
        StdDraw.polygon(x, y);

        sierpinski(loop, initX, initY, length);

    }
}
