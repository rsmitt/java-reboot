package ru.sberbank.edu;

import java.util.Collection;

public class CustomArrayImpl<T> implements CustomArray {

    private static final int DEFAULT_CAPACITY = 10;
    private int size = 0;
    private Object[] values;

    public CustomArrayImpl(int initialCapacity) {
        if (initialCapacity > 0) {
            this.values = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.values = new Object[0];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }

    public CustomArrayImpl() {
        this.values = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return values.length == 0;
    }

    @Override
    public boolean add(Object item) {
        try {
            if ((getCapacity() - size()) == 0 ) {
                if (getCapacity() == 0) {
                    ensureCapacity(getCapacity() + 1);
                } else
                    ensureCapacity(getCapacity() * 2);
            }
            values[size()] = item;
            size++;
            return true;
        }
        catch (ClassCastException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addAll(Object[] items) {
        try {
            if ((getCapacity() - size()) < items.length ) {
                ensureCapacity((getCapacity() * 2) + items.length);
            }
            Object[] temp = values;
            System.arraycopy(temp, 0, values, 0, temp.length);
            System.arraycopy(items, 0, values, size(), items.length);
            size += items.length;
            return true;
        }
        catch (ClassCastException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addAll(Collection items) {
        Object[] arrayItem = items.toArray();
        return addAll(arrayItem);
    }

    @Override
    public boolean addAll(int index, Object[] items) {
        try {
            if ((getCapacity() - size()) < items.length ) {
                ensureCapacity((getCapacity() * 2) + items.length);
            }
            Object[] temp = new Object[size()];
            System.arraycopy(values, 0, temp, 0, size());
            System.arraycopy(temp, 0, values, 0, index);
            System.arraycopy(items, 0, values, index, items.length);
            System.arraycopy(temp, index, values, index + items.length, size() - index);
            size += items.length;
            return true;
        }
        catch (ClassCastException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public Object get(int index) {
        return values[index];
    }

    @Override
    public Object set(int index, Object item) {
        return values[index] = item;
    }

    @Override
    public void remove(int index) {
        try {
            Object[] temp = values;
            values = new Object[temp.length];
            System.arraycopy(temp, 0, values, 0, index);
            System.arraycopy(temp, index + 1, values, index, temp.length - index - 1);
            size--;
        }
        catch (ClassCastException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean remove(Object item) {
        int index = indexOf(item);
        if (index != -1) {
            remove(index);
            return true;
        } else
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
        Object[] temp = values;
        values = new Object[temp.length + newElementsCount];
        System.arraycopy(temp, 0, values, 0, temp.length);
    }

    @Override
    public int getCapacity() {
        return values.length;
    }

    @Override
    public void reverse() {
        try {
            Object[] temp = toArray();
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
        Object[] val = new Object[size()];
        for (int i = 0; i < size(); i++) {
            val[i]=values[i];
        }
        return val;
    }
}
