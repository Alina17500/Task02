package ru.vsu.cs.volobueva;

public class Main {

    public static void main(String[] args) throws LinkedList.LinkedListException {
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(52);
        list.addFirst(10);
        list.addElementByIndex(2, 21);
        list.addLast(30);
        list.addLast(15);
        list.addLast(52);
        list.addElementByIndex(6, 44);

        printList(list);

        list.removeFirst();
        list.removeLast();
        list.removeElementByIndex(1);

        printList(list);

        Integer maxElement = list.findQuantityOfElements(findMaxElement(list));
        printQuantityOfMaxElements(maxElement);
    }

    private static void printList(LinkedList<Integer> list) {
        int i = 0;
        for (Integer value : list) {
            System.out.print((i > 0 ? ", " : "") + value);
            i++;
        }
        System.out.println();
    }

    private static Integer findMaxElement(LinkedList<Integer> list) throws LinkedList.LinkedListException {
        Integer max = list.getFirst();

        for (int i = 0; i < list.getSize(); i++) {
            if (list.getElementByIndex(i) > max) {
                max = list.getElementByIndex(i);
            }
        }
        return max;
    }

    private static void printQuantityOfMaxElements(Integer max) {
        System.out.println("QuantityOfMaxElements in this list = " + max);
    }
}
