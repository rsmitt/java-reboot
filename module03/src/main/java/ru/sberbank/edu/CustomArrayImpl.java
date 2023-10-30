package ru.sberbank.edu;

import java.util.Arrays;
import java.util.Collection;

public class CustomArrayImpl<T> implements CustomArray {

    private T[] values = (T[]) new Object[0];



    @Override
    public int size() {
        return values.length;
    }

    @Override
    public boolean isEmpty() {
        return values.length == 0;
    }

    @Override
    public boolean add(Object item) {
        try {
            T[] temp = values;
            values = (T[]) new Object[temp.length + 1];
            System.arraycopy(temp, 0, values, 0, temp.length);
            values[values.length - 1] = (T) item;
            return true;
        }
        catch (ClassCastException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addAll(Object[] items) {
        return false;
    }

    @Override
    public boolean addAll(Collection items) {
        return false;
    }

    @Override
    public boolean addAll(int index, Object[] items) {
        return false;
    }

    @Override
    public Object get(int index) {
        return values[index];
    }

    @Override
    public Object set(int index, Object item) {
        return values[index] = (T)item;
    }

    @Override
    public void remove(int index) {

    }

    @Override
    public boolean remove(Object item) {
        return false;
    }

    @Override
    public boolean contains(Object item) {
        return false;
    }

    @Override
    public int indexOf(Object item) {
        return 0;
    }

    @Override
    public void ensureCapacity(int newElementsCount) {

    }

    @Override
    public int getCapacity() {
        return 0;
    }

    @Override
    public void reverse() {

    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }
}
