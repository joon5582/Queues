/* *****************************************************************************
 *  Name: Junwoo Lee
 *  Date: 6/3/2020
 *  Description: https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php
 *  I got this code from someone else.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {

        RandomizedQueue<String> strs = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);

        for (int i = 0; i < k && !StdIn.isEmpty(); i++) {
            strs.enqueue(StdIn.readString());
        }
        for (int i = k + 1; !StdIn.isEmpty(); i++) {
            String a = StdIn.readString();
            int j = StdRandom.uniform(1, i + 1);
            if (j <= k) {
                strs.dequeue();
                strs.enqueue(a);
            }
        }


        for (int i = 0; i < k; i++) {
            StdOut.println(strs.dequeue());
        }
    }


}

