/* *****************************************************************************
 *  Name:    Joshua Santiago
 *  NetID:   joshua.santiago14@upr.edu
 *  Precept: P00
 *
 *  Description:  Write a program that reads election data and prints the
 *  number of electorial votes.
 *
 **************************************************************************** */

public class Election {
    public static int majorityOf(int n) {
        int min = (int) (n * 0.5) + 1;
        return min;
    }

    public static void main(String[] args) {
        String frstCan = StdIn.readString();
        String secCan = StdIn.readString();
        int firElect = 0;
        int secElect = 0;
        int minWon = 0;
        int TotalVotes = 0;
        // read the data from standard input
        while (!StdIn.isEmpty()) {
            String region = StdIn.readString();
            int elecotral = StdIn.readInt();
            int fVotes = StdIn.readInt();
            int sVotes = StdIn.readInt();
            TotalVotes += elecotral;
            if (fVotes > sVotes) {
                firElect += elecotral;
            }
            if (sVotes > fVotes) {
                secElect += elecotral;
            }
        }
        minWon = majorityOf(TotalVotes);
        System.out.printf("%-10s   %-5d\n", frstCan, firElect);
        System.out.printf("%-10s   %-5d\n", secCan, secElect);
        System.out.printf("%d needed to win\n", minWon);


    }
}
