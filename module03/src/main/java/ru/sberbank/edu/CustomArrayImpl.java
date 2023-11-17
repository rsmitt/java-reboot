package ru.sberbank.edu;

import java.lang.reflect.Field;
import java.util.*;

public class CustomArrayImpl<T> implements CustomArray<T> {

    ArrayList<Object> arrayList = new ArrayList<>();

    /**
     * Return actual size of array
     */
    @Override
    public int size() {
        return this.arrayList.size();
    }

    /**
     * Is Array empty?
     */
    @Override
    public boolean isEmpty() {
        return this.arrayList.isEmpty();
    }

    /**
     * Add single item.
     */
    @Override
    public boolean add(Object item) {
        this.arrayList.add(item);
        return true;
    }

    /**
     * Add all items.
     *
     * @throws IllegalArgumentException if parameter items is null
     */
    @Override
    public boolean addAll(Object[] items) {
        if (items != null) {
            this.arrayList.addAll(List.of(items));
            return true;
        } else {
            throw new IllegalArgumentException("Array is empty");
        }
    }

    /**
     * Add all items.
     *
     * @throws IllegalArgumentException if parameter items is null
     */
    @Override
    public boolean addAll(Collection<T> items) {
        if (items != null) {
            this.arrayList.addAll(items);
            return true;
        } else {
            throw new IllegalArgumentException("Collection is empty");
        }
    }

    /**
     * Add items to current place in array.
     *
     * @param index - index
     * @param items - items for insert
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     * @throws IllegalArgumentException       if parameter items is null
     */
    @Override
    public boolean addAll(int index, Object[] items) {
        if (items == null) {
            throw new IllegalArgumentException("Array is empty");
        } else if (index > arrayList.size()) {
            throw new ArrayIndexOutOfBoundsException("Index is not reachable");
        } else {
            this.arrayList.addAll(List.of(index, Arrays.stream(items).toArray()));
            return true;
        }

    }

    /**
     * Get item by index.
     *
     * @param index - index
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     */
    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        if (arrayList.size() < index) {
            throw new ArrayIndexOutOfBoundsException("Index is not reachable");
        } else {
            return (T) arrayList.get(index);
        }
    }

    /**
     * Set item by index.
     *
     * @param index - index
     * @return old value
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     */
    @SuppressWarnings("unchecked")
    @Override
    public T set(int index, Object item) {
        if (arrayList.size() < index) {
            throw new ArrayIndexOutOfBoundsException("Index is not reachable");
        } else {
            Object localElement = arrayList.get(index);
            arrayList.set(index, item);
            return (T) localElement;
        }
    }

    /**
     * Remove item by index.
     *
     * @param index - index
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     */
    @Override
    public void remove(int index) {
        if (arrayList.size() < index) {
            throw new ArrayIndexOutOfBoundsException("Index is not reachable");
        } else {
            arrayList.remove(index);
        }
    }

    /**
     * Remove item by value. Remove first item occurrence.
     *
     * @param item - item
     * @return true if item was removed
     */
    @Override
    public boolean remove(Object item) {
        if (item == null) {
            throw new NullPointerException("Empty object");
        } else {
            var index = arrayList.indexOf(item);
            if (index == -1) {
                return false;
            } else {
                arrayList.remove(index);
                return true;
            }
        }
    }

    /**
     * Checks if item exists.
     *
     * @param item - item
     * @return true or false
     */
    @Override
    public boolean contains(Object item) {
        if (item == null) {
            throw new NullPointerException("Empty object");
        } else {
            var index = arrayList.indexOf(item);
            return index != -1;
        }
    }

    /**
     * Index of item.
     *
     * @param item - item
     * @return index of element or -1 of list doesn't contain element
     */
    @SuppressWarnings("UnnecessaryLocalVariable")
    @Override
    public int indexOf(Object item) {
        if (item == null) {
            throw new NullPointerException("Empty object");
        } else {
            var index = arrayList.indexOf(item);
            return index;
        }
    }

    /**
     * Grow current capacity to store new elements if needed.
     *
     * @param newElementsCount - new elements count
     */
    @Override
    public void ensureCapacity(int newElementsCount) {
        if (arrayList.size() < newElementsCount)
            arrayList.ensureCapacity(newElementsCount);
    }

    /**
     * Get current capacity.
     */
    @Override
    public int getCapacity() {
        arrayList.trimToSize();
        return arrayList.size();
    }

    /**
     * Reverse list.
     */
    @Override
    public void reverse() {
        Collections.reverse(arrayList);
    }

    /**
     * Get copy of current array.
     */
    @Override
    public Object[] toArray() {
        return arrayList.toArray();
    }

}
