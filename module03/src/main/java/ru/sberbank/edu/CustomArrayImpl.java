package ru.sberbank.edu;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class CustomArrayImpl<T> implements CustomArray{

    private int size;
    private Object[] objectsArray;

    CustomArrayImpl(){
        this(10);
    }
    CustomArrayImpl(int capacity){
        objectsArray = new Object[capacity];
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
    public boolean add(Object item) {
        if (size == objectsArray.length){
            objectsArray = Arrays.copyOf(objectsArray, size * 2);;
        }
        objectsArray[size] = item;
        ++size;
        return true;
    }

    @Override
    public boolean addAll(Object[] items) {
        for(int i = 0; i < items.length; i++){
            add(items[i]);
        }
        return true;
    }

    @Override
    public boolean addAll(Collection items) {
        Iterator<T> iterator = items.iterator();
        while (iterator.hasNext()){
            add(iterator.next());
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Object[] items) {
        if (index < 0){
            throw new IndexOutOfBoundsException();
        }

        for(int i = index; i< items.length; i++){
            add(items[i]);
        }
        return true;
    }

    @Override
    public Object get(int index) {
        return (T)objectsArray[index];
    }

    @Override
    public Object set(int index, Object item) {
        if (index < 0){
            throw new IndexOutOfBoundsException();
        }
        return (T)(objectsArray[index] = item);
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index > size()){
            throw new IndexOutOfBoundsException();
        }
        System.arraycopy(objectsArray,index + 1, objectsArray, index, size - index - 1);
    }

    @Override
    public boolean remove(Object item) {
        for(int i = 0; i < size; i++){
            if(item.equals(objectsArray[i])){
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Object item) {
        for(int i = 0; i < size; i++){
            if(item.equals(objectsArray[i])){
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Object item) {
        for(int i = 0; i < size; i++){
            if(item.equals(objectsArray[i])){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void ensureCapacity(int newElementsCount) {
        objectsArray = Arrays.copyOf(objectsArray, newElementsCount + size);
    }

    @Override
    public int getCapacity() {
        return objectsArray.length;
    }

    @Override
    public void reverse() {
        Object[] reverse = new Object[size];
        for (int i = 0; i < size(); i++){
            reverse[size - 1 - i ] = objectsArray[i];
        }
        System.arraycopy(reverse,0,objectsArray,0,size());
    }

    @Override
    public Object[] toArray() {
        return objectsArray;
    }
}
