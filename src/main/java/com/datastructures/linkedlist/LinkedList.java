package com.datastructures.linkedlist;

import java.util.Arrays;

/**
 * Реализация односвязного списка для хранения целых чисел
 */
public class LinkedList {
    private Node head;
    private int size;

    /**
     * Внутренний класс для представления узла списка
     */
    private static class Node {
        private Node next;
        private int value;

        public Node(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    /**
     * Добавляет элемент в конец списка
     * @param value значение для добавления
     */
    public void addLast(int value) {
        if (head == null) {
            this.head = new Node(value);
        } else {
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(new Node(value));
        }
        size++;
    }

    /**
     * Добавляет элемент в начало списка
     * @param value значение для добавления
     */
    public void addFirst(int value) {
        Node newNode = new Node(value);
        newNode.setNext(head);
        head = newNode;
        size++;
    }

    /**
     * Добавляет элемент по указанному индексу
     * @param index позиция для вставки
     * @param value значение для добавления
     * @throws IndexOutOfBoundsException если индекс невалиден
     */
    public void add(int index, int value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == 0) {
            addFirst(value);
            return;
        }

        if (index == size) {
            addLast(value);
            return;
        }

        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }

        Node newNode = new Node(value);
        newNode.setNext(current.getNext());
        current.setNext(newNode);
        size++;
    }

    /**
     * Получает элемент по индексу
     * @param index элемента
     * @return значение элемента
     * @throws IndexOutOfBoundsException если индекс невалиден
     */
    public int get(int index) {
        int currentIndex = 0;
        Node temp = head;
        while (temp != null) {
            if (currentIndex == index) {
                return temp.getValue();
            } else {
                temp = temp.getNext();
                currentIndex++;
            }
        }
        throw new IllegalArgumentException("Index out of bounds");
    }

    /**
     * Удаляет элемент по индексу
     * @param index индекс удаляемого элемента
     * @throws IndexOutOfBoundsException если индекс невалиден
     */
    public void remove(int index) {
        if (index == 0) {
            head = head.getNext();
            size--;
            return;
        }
        int currentIndex = 0;
        Node temp = head;
        while (temp != null) {
            if (currentIndex == index - 1) {
                temp.setNext(temp.getNext().getNext());
                size--;
                return;
            } else {
                temp = temp.getNext();
                currentIndex++;
            }
        }
    }

    /**
     * Проверяет, содержит ли список указанное значение
     * @param value искомое значение
     * @return true если значение найдено
     */
    public boolean contains(int value) {
        Node current = head;
        while (current != null) {
            if (current.getValue() == value) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Возвращает размер списка
     * @return количество элементов в списке
     */
    public int size() {
        return size;
    }

    /**
     * Проверяет, пуст ли список
     * @return true если список пуст
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Очищает список
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Возвращает индекс первого вхождения значения
     * @param value искомое значение
     * @return индекс элемента или -1 если не найден
     */
    public int indexOf(int value) {
        Node current = head;
        int index = 0;
        while (current != null) {
            if (current.getValue() == value) {
                return index;
            }
            current = current.getNext();
            index++;
        }
        return -1;
    }

    public String toString() {
        int[] result = new int[size];

        int index = 0;
        Node temp = head;
        while (temp != null) {
            result[index++] = temp.getValue();
            temp = temp.getNext();
        }
        return Arrays.toString(result);
    }
}
