/* *****************************************************************************
 *  Name: Junwoo Lee
 *  Date: 6/3/2020
 *  Description: https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php
 *  I have done all the coding by myself
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] s;
    private int size;


    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        s = (Item[]) new Object[1];
    }


    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("argument is null");
        if (size == s.length) resize(2 * s.length);
        s[size++] = item;

    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++)
            copy[i] = s[i];
        s = copy;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int r = StdRandom.uniform(size());
        Item temp = s[r];
        s[r] = s[size - 1];
        s[size - 1] = null;
        size--;
        if (size > 0 && size == s.length / 4) resize(s.length / 2);
        return temp;

    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        int r = StdRandom.uniform(size());
        return s[r];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueiterator();

    }

    private class RandomizedQueueiterator implements Iterator<Item> {

        private int[] index;
        private int n;

        private RandomizedQueueiterator() {
            n = 0;
            index = new int[size];
            for (int i = 0; i < size; i++) {
                index[i] = i;
            }
            StdRandom.shuffle(index);
        }

        public boolean hasNext() {
            return n < size;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return s[index[n++]];


        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> ranque = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-"))
                StdOut.print(ranque.dequeue());
            else if (s.equals("?"))
                StdOut.print(ranque.sample());
            else
                ranque.enqueue(s);

        }

    }

}
