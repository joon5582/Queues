/* *****************************************************************************
 *  Name: Junwoo Lee
 *  Date: 6/3/2020
 *  Description: https://coursera.cs.princeton.edu/algs4/assignments/queues/specification.php
 *  I have done all the coding by myself
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {

    private Node first = null, last = null;
    private int size = 0;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }


    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;

    }

    // return the number of items on the deque
    public int size() {

        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("argument is null");
        if (!isEmpty()) {
            Node a = new Node();
            a.item = item;
            a.prev = null;
            a.next = first;
            first.prev = a;
            first = a;
        }
        else {
            Node a = new Node();
            a.item = item;
            a.next = null;
            a.prev = null;
            first = a;
            last = a;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("argument is null");
        if (!isEmpty()) {
            Node a = new Node();
            a.item = item;
            a.next = null;
            a.prev = last;
            last.next = a;
            last = a;

        }
        else {
            Node a = new Node();
            a.item = item;
            a.next = null;
            a.prev = null;
            first = a;
            last = a;
        }
        size++;

    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        Item a;
        a = first.item;
        first = first.next;
        if (first == null) {
            last = null;
        }
        else first.prev = null;
        size--;
        return a;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("queue is empty");
        }
        Item a;
        a = last.item;
        if (size() == 1) {
            first = null;
            last = null;
        }
        else {
            last = last.prev;
            last.next = null;

        }
        size--;
        return a;


    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();

    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (current == null) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-"))
                StdOut.print(deque.removeLast());
            else
                deque.addFirst(s);

        }

    }


}
