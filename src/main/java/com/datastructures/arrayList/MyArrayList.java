package com.datastructures.arrayList;

import java.util.Arrays;

public class MyArrayList {
    private static final int DEFAULT_CAPACITY = 10;
    private int[] array;
    private int size;

    public MyArrayList() {
        array = new int[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
        array = new int[capacity];
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > array.length) {
            int newCapacity = array.length * 3 / 2 + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean add(int element) {
        ensureCapacity(size + 1);
        array[size++] = element;
        return true;
    }

    public int get(int index) {
        rangeCheck(index);
        return array[index];
    }

    public int set(int index, int element) {
        rangeCheck(index);
        int oldValue = array[index];
        array[index] = element;
        return oldValue;
    }

    public int remove(int index) {
        rangeCheck(index);
        int removedValue = array[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(array, index + 1, array, index, numMoved);
        }
        size--;
        return removedValue;
    }

    public void clear() {
        size = 0;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}