package ru.sberbank.edu.impl;

import ru.sberbank.edu.CustomArray;

import java.util.Arrays;
import java.util.Collection;

public class CustomArrayImpl<T> implements CustomArray<T> {

    private static final int INITIAL_CAPACITY = 15;
    private static final double LIMIT_FOR_EXPANSION = 0.8;
    private static final double GROW_COEFFICIENT = 1.5;
    private int capacity;
    private int index = 0;
    private Object[] data;

    public CustomArrayImpl(int initialCapacity) {
        if (initialCapacity >= 0) {
            this.data = new Object[initialCapacity];
            this.capacity = initialCapacity;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    public CustomArrayImpl() {
        this.data = new Object[INITIAL_CAPACITY];
        this.capacity = INITIAL_CAPACITY;
    }

    @Override
    public int size() {
        return this.index;
    }

    @Override
    public boolean isEmpty() {
        return this.index == 0;
    }

    @Override
    public boolean add(T item) {
        this.data[index] = item;
        index++;
        if (index >= (int) (capacity * LIMIT_FOR_EXPANSION)) {
            grow();
        }
        return true;
    }

    @Override
    public boolean addAll(T[] items) {
        if (items == null) {
            throw new IllegalArgumentException();
        }
        for (T el : items) {
            add(el);
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<T> items) {
        if (items == null) {
            throw new IllegalArgumentException();
        }
        for (T el : items) {
            add(el);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, T[] items) {
        if (items == null) {
            throw new IllegalArgumentException();
        }
        if (index < 0 || this.index - 1 < index || index + items.length < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Object[] result = new Object[this.index + items.length];
        int j = 0;
        for (int i = 0; i < index; i++) {
            result[i] = this.data[i];
            j++;
        }
        for (int i = 0; i < items.length; i++) {
            result[j] = items[i];
            j++;
        }
        for (int i = index; i <= this.index - 1; ++i) {
            result[j] = this.data[i];
            j++;
        }
        this.index = this.index + items.length;
        this.data = result;

        return true;
    }

    @Override
    public T get(int index) {
        if (this.index > index) {
            return (T) data[index];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public T set(int index, T item) {
        if (this.index > index) {
            Object old = this.data[index];
            this.data[index] = item;
            return (T) old;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public void remove(int index) {
        if (this.index > index) {
            for (int i = index; i < this.index; i++) {
                this.data[i] = this.data[i + 1];
            }
            this.index--;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public boolean remove(T item) {
        for (int i = 0; i < index; i++) {
            if (this.data[i].equals(item)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(T item) {
        for (int i = 0; i < index; i++) {
            if (this.data[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T item) {
        for (int i = 0; i < index; i++) {
            if (this.data[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void ensureCapacity(int newElementsCount) {
        if (newElementsCount > this.capacity) {
            Object[] newData = new Object[newElementsCount];
            for (int i = 0; i < index; i++) {
                newData[i] = this.data[i];
            }
            capacity = newElementsCount;
            this.data = newData;
        }
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public void reverse() {
        Object temp;
        for (int i = 0; i < this.index / 2; i++) {
            temp = this.data[i];
            this.data[i] = this.data[index - i - 1];
            this.data[index - i - 1] = temp;
        }
    }

    @Override
    public String toString() {
        if (index > 0) {
            StringBuilder builder = new StringBuilder();
            builder.append("[ ");
            for (int i = 0; i < index; i++) {
                builder.append(this.data[i]);
                builder.append(" ");
            }
            builder.append("]");
            return builder.toString();
        } else {
            return "[ ]";
        }
    }

    @Override
    public Object[] toArray() {
        Object[] newObject = new Object[index];
        for (int i = 0; i < index; i++) {
            newObject[i] = this.data[i];
        }
        return newObject;
    }

    private void grow() {
        int newSize = (int) (capacity * GROW_COEFFICIENT);
        Object[] newData = new Object[newSize];
        for (int i = 0; i < index; i++) {
            newData[i] = this.data[i];
        }
        capacity = newSize;
        this.data = newData;
    }

}
