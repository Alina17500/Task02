package ru.vsu.cs.volobueva;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T> {

    public static class LinkedListException extends Exception {
        public LinkedListException(String errorReport) {
            super(errorReport);
        }
    }

    private class LinkedListNode {
        public T element;
        public LinkedListNode nextElement;

        public LinkedListNode(T element, LinkedListNode nextElement) {
            this.element = element;
            this.nextElement = nextElement;
        }

        public LinkedListNode(T element) {
            this(element, null);
        }
    }

    private LinkedListNode head = null;
    private LinkedListNode tail = null;
    private int size = 0;

    public int getSize() {
        return size;
    }

    public void addFirst(T element) {
        head = new LinkedListNode(element, head);

        if (size == 0) {
            tail = head;
        }
        size++;
    }

    public void addLast(T element) {
        if (size != 0) {
            tail.nextElement = new LinkedListNode(element);
            tail = tail.nextElement;
        } else {
            head = tail = new LinkedListNode(element);
        }

        size++;
    }

    public void addElementByIndex(int index, T element) {
        if (index == 0) {
            addFirst(element);
        } else if (index == size) {
            addLast(element);
        } else {
            LinkedListNode previous = getComponent(index - 1);
            previous.nextElement = new LinkedListNode(element, previous);
            size++;
        }
    }

    private void isEmptyList() throws LinkedListException {
        if (size == 0) {
            throw new LinkedListException("This list is empty!");
        }
    }

    public void removeFirst() throws LinkedListException {
        isEmptyList();

        head = head.nextElement;

        if (size == 1) {
            tail = null;
        }
        size--;
    }

    public void removeLast() throws LinkedListException {
        isEmptyList();

        tail = getComponent(size - 2);
        tail.nextElement = null;

        if (size == 1) {
            head = tail = null;
        }
        size--;
    }

    public void removeElementByIndex(int index) throws LinkedListException {
        isEmptyList();

        if (index < 0 || index > size) {
            throw new LinkedListException("The index is incorrect: out of list bounds.");
        }

        if (index == 0) {
            removeFirst();
        } else if (index == size) {
            removeLast();
        } else {
            LinkedListNode previous = getComponent(index - 1);
            previous.nextElement = previous.nextElement.nextElement;
            size--;
        }
    }

    public T getFirst() throws LinkedListException {
        isEmptyList();
        return head.element;
    }

    public T getLast() throws LinkedListException {
        isEmptyList();
        return tail.element;
    }

    public T getElementByIndex(int index) throws LinkedListException {
        isEmptyList();
        return getComponent(index).element;
    }

    public LinkedListNode getComponent(int index) {
        LinkedListNode current = head;

        for (int i = 0; i < index; i++) {
            current = current.nextElement;
        }

        return current;
    }

    public int findQuantityOfElements(T value) throws LinkedListException {
        int count = 0;

        for (int i = 0; i < size; i++) {
            if (getElementByIndex(i) == value) {
                count++;
            }
        }

        return count;
    }

    @Override
    public Iterator<T> iterator() {
        class LinkedListIterator implements Iterator<T> {
            LinkedListNode current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T value = current.element;
                current = current.nextElement;
                return value;
            }
        }

        return new LinkedListIterator();
    }
}