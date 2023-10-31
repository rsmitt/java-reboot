package ru.sberbank.edu;

import java.util.Collection;

public class CustomArrayImpl<T> implements CustomArray {

    private Object[] values = new Object[0];



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
            Object[] temp = values;
            values = new Object[temp.length + 1];
            System.arraycopy(temp, 0, values, 0, temp.length);
            values[values.length - 1] = item;
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
        try {
            Object[] temp = values;
            values = new Object[temp.length - 1];
            System.arraycopy(temp, 0, values, 0, index);
            System.arraycopy(temp, index + 1, values, index, temp.length - index - 1);
        }
        catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean remove(Object item) {
        return false;
    }

    @Override
    public boolean contains(Object item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(Object item) {
        int index = -1;
        if (item == null) {
            for (int i = 0; i <= values.length - 1;) {
                if(values[i] == null) {
                    index = i;
                    break;
                }
                i++;
            }
        } else {
            for (int i = 0; i <= values.length - 1;) {
                if(values[i] != null && values[i].equals(item)) {
                    index = i;
                    break;
                }
                i++;
            }
        }
        return index;
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
        try {
            Object[] temp = values;
            values = new Object[temp.length];
            int j = 0;
            for (int i = temp.length - 1; i >= 0; i--) {
                values[j] = temp[i];
                j++;
            }
        }
        catch (ClassCastException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public Object[] toArray() {
        return values;
    }
}
