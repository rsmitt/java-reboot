package ru.sberbank.edu;

import java.util.Collection;
import java.util.Arrays;

public class CustomArrayImpl<T> implements CustomArray<T> {

    private static final int DEF_CAPACITY = 10;
    private T[] array;
    private int size;

    public CustomArrayImpl() {
        this.array = (T[]) new Object[DEF_CAPACITY];
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean add(T item) {
        ensureCapacity(size + 1);
        array[size++] = item;
        return true;
    }

    @Override
    public boolean addAll(T[] items) {
        if (items == null)
            throw new IllegalArgumentException();
        ensureCapacity(size + items.length);
        System.arraycopy(items, 0, array, size, items.length);
        size += items.length;
        return true;
    }

    @Override
    public boolean addAll(Collection<T> items) {
        if (items == null)
            throw new IllegalArgumentException();
        return addAll((T[]) items.toArray());
    }

    @Override
    public boolean addAll(int index, T[] items) {
        if (index > size || index < 0)
            throw new ArrayIndexOutOfBoundsException();
        if (items == null)
            throw new IllegalArgumentException();
        ensureCapacity(size + items.length);
        System.arraycopy(array, index, array, index + items.length, size - index);
        System.arraycopy(items, 0, array, index, items.length);
        size += items.length;
        return true;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0)
            throw new ArrayIndexOutOfBoundsException();
        return array[index];
    }

    @Override
    public T set(int index, T item) {
        if (index >= size || index < 0)
            throw new ArrayIndexOutOfBoundsException();
        T old = array[index];
        array[index] = item;
        return old;
    }

    @Override
    public void remove(int index) {
        if (index >= size || index < 0)
            throw new ArrayIndexOutOfBoundsException();
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(array, index + 1, array, index, numMoved);
        array[--size] = null; // let GC do its work
    }

    @Override
    public boolean remove(T item) {
        if (item == null) {
            for (int index = 0; index < size; index++)
                if (array[index] == null) {
                    remove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (item.equals(array[index])) {
                    remove(index);
                    return true;
                }
        }
        return false;
    }

    @Override
    public boolean contains(T item) {
        return indexOf(item) >= 0;
    }

    @Override
    public int indexOf(T item) {
        if (item == null) {
            for (int i = 0; i < size; i++)
                if (array[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (item.equals(array[i]))
                    return i;
        }
        return -1;
    }


    @Override
    public void ensureCapacity(int newElementsCount) {
        if (newElementsCount > array.length) {
            int newCapacity = Math.max(newElementsCount, array.length * 2);
            array = Arrays.copyOf(array, newCapacity);
        }
    }

    @Override
    public int getCapacity() {
        return array.length;
    }

    @Override
    public void reverse() {
        for (int i = 0, mid = size >> 1, j = size - 1; i < mid; i++, j--) {
            T temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(array, size));
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

}