package ru.sberbank.edu;

import java.util.*;

public class CustomArrayImpl<T> implements CustomArray<T> {

    ArrayList<T> massiv = new ArrayList<T>();




    /**
     * Get size of ArrayList.
     */
    @Override
    public int size() {
        return this.massiv.size();
    }




    /**
     * Checks if ArrayList is Empty or not.
     */
    @Override
    public boolean isEmpty() {
        return this.massiv.isEmpty();
    }




    /**
     * Add single item.
     *
     * @param item
     */
    @Override
    public boolean add(T item) {
        int temp = this.massiv.size();
        this.massiv.add(item);
        return (this.massiv.size()) > temp;
    }




    /**
     * Add all items.
     *
     * @param items
     * @throws IllegalArgumentException if parameter items is null
     */
    @Override
    public boolean addAll(T[] items) {
        int temp = this.massiv.size();
        this.massiv.addAll(List.of(items));
        return (this.massiv.size()) > temp;
    }




    /**
     * Add all items.
     *
     * @param items
     * @throws IllegalArgumentException if parameter items is null
     */
    @Override
    public boolean addAll(Collection<T> items) {
        int temp = this.massiv.size();
        this.massiv.addAll(items);
        return (this.massiv.size()) > temp;
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
    public boolean addAll(int index, T[] items) {
        int temp = this.massiv.size();
        this.massiv.addAll(index, List.of(items));
        return (this.massiv.size()) > temp;
    }




    /**
     * Get item by index.
     *
     * @param index - index
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     * @return
     */
    @Override
    public T get(int index) {
        return this.massiv.get(index);
    }




    /**
     * Set item by index.
     *
     * @param index - index
     * @param item
     * @return old value
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     */
    @Override
    public T set(int index, T item) {
        return this.massiv.set(index, item);
    }




    /**
     * Remove item by index.
     *
     * @param index - index
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     */
    @Override
    public void remove(int index) {
        this.massiv.remove(index);
    }




    /**
     * Remove item by value. Remove first item occurrence.
     *
     * @param item - item
     * @return true if item was removed
     */
    @Override
    public boolean remove(T item) {
        int temp = this.massiv.size();
        this.massiv.remove(item);
        return (this.massiv.size()) < temp;
    }




    /**
     * Checks if item exists.
     *
     * @param item - item
     * @return true or false
     */
    @Override
    public boolean contains(T item) {
        return this.massiv.contains(item);
    }




    /**
     * Index of item.
     *
     * @param item - item
     * @return index of element or -1 of list doesn't contain element
     */
    @Override
    public int indexOf(T item) {
        return indexOf(item);
    }




    /**
     * Grow current capacity to store new elements if needed.
     *
     * @param newElementsCount - new elements count
     */
    @Override
    public void ensureCapacity(int newElementsCount) {
        this.massiv.ensureCapacity(newElementsCount);
    }




    /**
     * Get current capacity.
     */
    @Override
    public int getCapacity() {
        this.massiv.trimToSize();
        return ((this.massiv.size() * 3/2) + 1);
    }




    /**
     * Reverse list.
     */
    @Override
    public void reverse() {
        for (int i = 0; i < this.massiv.size() / 2; i++) {
            T tmp = this.massiv.get(i);
            this.massiv.set(i, this.massiv.get(this.massiv.size() - i - 1));
            this.massiv.set(this.massiv.size() - i - 1, tmp);
        }
    }




    /**
     * Get content in format '[ element1 element2 ... elenentN ] or [ ] if empty.
     */
    @Override
    public String toString() {
        return massiv.toString();
    }




    /**
     * Get copy of current array.
     */
    @Override
    public Object[] toArray() {
        return this.massiv.toArray().clone();
    }
}