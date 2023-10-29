package ru.sberbank.edu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class CustomArrayImpl<T> implements CustomArray<T> {
    private static final int DEFAULT_CAPACITY = 4;
    private static final Object[] EMPTY_DATA = {};
    private static final Object[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = {};
    private Object[] elementData;
    private int size;

    /**
     * initiate with default size and empty data
     */
    public CustomArrayImpl(){
        elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
    }
    /**
     * initiate with custom size and empty data
     */
    public CustomArrayImpl(int initialCapacity){
        if(initialCapacity > 0) {
            elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            elementData = EMPTY_DATA;
        } else {
            throw new IllegalArgumentException("Size can't be less than zero");
        }
    }
    /**
     * initiate with custom data
     */
    public CustomArrayImpl(Collection<? extends T> collection){
        Object[] temp = collection.toArray();
        if(temp.length == 0) {
            elementData = EMPTY_DATA;
        } else {
            size = temp.length;
            if (collection.getClass() == ArrayList.class) {
                elementData = temp;
            } else {
                elementData = Arrays.copyOf(temp, size, Object[].class);
            }
        }
    }

    /**
     * @return size this collection
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * @return true if this list contains no elements
     */
    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    /**
     * Add single item.
     * @param item - item to add
     * @return - true if item has been added
     */
    @Override
    public boolean add(T item) {
        if(size == elementData.length) {
            ensureCapacity(size+1);
        }
        elementData[size] = item;
        size++;
        return true;
    }


    /**
     * Add all items.
     * @param items - items to add
     * @return true if operation has benn success
     */
    @Override
    public boolean addAll(T[] items) {
        if (items.length == 0) {
            throw new IllegalArgumentException("Receiving data is empty");
        }
        for (T item: items) {
            add(item);
        }
        return true;
    }

    /**
     * Add all items.
     * @param items - collection to add
     * @throws IllegalArgumentException if parameter items is null
     */
    public boolean addAll(Collection<T> items) {
        if (items.size() == 0) {
            throw new IllegalArgumentException("Receiving data is empty");
        }
        //Переданные элементы коллекции собираем в массив
        Object[] temp = items.toArray();
        //Вычисляем кол-во элементов в переданной коллекции
        int tempLength = temp.length;
        //Проверяем, что места в текущем массиве достаточно для добавление элементов переданной коллекции
        if(tempLength > (elementData.length - size)) {
            //Если недостаточно - то увеличиваем размер массива для хранения элементов на длинну переданной коллекции
            ensureCapacity(size + tempLength);
        }
        //Добавляем в конец массива переданные элементы коллекции
        System.arraycopy(temp, 0, elementData, size, tempLength);
        //Актуализируем кол-во элементов массива
        size = size + tempLength;
        return false;
    }

    /**
     * Add items to current place in array.
     * @param index - index
     * @param items - items for insert
     * @return
     */
    @Override
    public boolean addAll(int index, T[] items) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        int lengthItems = items.length;
        if(lengthItems == 0) {
            return false;
        }
        //Увеличиваем массив на длинну добавляемой коллекции
        if (lengthItems > elementData.length - size) {
            ensureCapacity(size + lengthItems);
        }
        //Находим позицию, с которой будет начинаться "вторая" часть изначального массива
        int posMoved = size - index;
        if (posMoved > 0) {
            //Переносим "вторую" часть на вычесленную выше позицию
            System.arraycopy(elementData, index,
                    elementData, index + lengthItems,
                    posMoved);
        }
        //Заполняем промежуток между "первой" частью изначального массива и "второй" после сдвига, добавляя переданный массив
        System.arraycopy(items, 0, elementData, index, lengthItems);
        //Актуализируем размер массива с учетом добавления массива
        size += lengthItems;
        return true;
    }

    /**
     * Get item by index.
     * @param index - index of element
     * @return element of this index
     * Добавил простой эксепшн
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) elementData[index];
    }

    /**
     * Set item by index.
     * @param index - index
     * @param item - element to set
     * @throws ArrayIndexOutOfBoundsException if index is out of bounds
     * @return old value
     */
    @Override
    public T set(int index, T item) {
        Objects.checkIndex(index, size);
        T oldValue = (T) elementData[index];
        elementData[index] = item;
        return oldValue;
    }

    /**
     * Remove item by index.
     * @param index - index
     * @throws IndexOutOfBoundsException if index is out of bounds
     */
    @Override
    public void remove(int index) {
        //Проверка корректности индекса
        Objects.checkIndex(index, size);
        //Копируем дату
        final Object[] tempData = elementData;
        //Сохраняем значенте удаляемого элемента
        T oldValue = (T) tempData[index];
        //Уменьшаем размер с учетом удаления элемента
        int newSize = size - 1;
        //Новый размер - больше индекса
        if (newSize > index) {
            //Копируем в временный массив все что правее удаляемого эл-та на его место
            System.arraycopy(tempData, index + 1, tempData, index, newSize - index);
        }
        //Актуализируем размер
        size = newSize;
        //Зануляем последний элемент (т.к. он дубль предпоследнего изза копирования).
        tempData[size] = null;
    }

    /**
     * Remove item by value. Remove first item occurrence.
     * @param item - item
     * @return true if item was removed
     */
    @Override
    public boolean remove(T item) {
        int itemIndex = indexOf(item);
        if(itemIndex == -1) {
            return false;
        }
        remove(itemIndex);
        return true;
    }

    /**
     * Checks if item exists.
     * @param item - item
     * @return true or false
     */
    @Override
    public boolean contains(T item) {
        return indexOf(item) != -1;
    }

    /**
     * Index of item.
     * @param item - item
     * @return index of element or -1 of list doesn't contain element
     */
    @Override
    public int indexOf(T item) {
        Object[] tempData = elementData;
        //Если передали null, то ищем первый null и отдаем его индекс
        if (item == null) {
            for (int i = 0; i < size; i++) {
                if (tempData[i] == null) {
                    return i;
                }
            }
        } else {//Иначе, итерируемся по элементам и если нашли совпадение - возвращаем индекс
            for (int i = 0; i < size; i++) {
                if (item.equals(tempData[i])) {
                    return i;
                }
            }
        }
        //Н енашли совпадений
        return -1;
    }

    /**
     * Grow current capacity to store new elements if needed.
     * @param newElementsCount - new elements count
     */
    @Override
    public void ensureCapacity(int newElementsCount) {
        int oldCapacity = elementData.length;
        if (oldCapacity > 0 || elementData != DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA) {
            int newCapacity = (int) Math.round(size*1.5);
            elementData = Arrays.copyOf(elementData, newCapacity);
        } else {
            elementData = new Object[Math.max(DEFAULT_CAPACITY, newElementsCount)];
        }
    }

    /**
     * Get current capacity.
     * @return current capacity
     */
    @Override
    public int getCapacity() {
        return elementData.length;
    }

    /**
     * Reverse list.
     */
    @Override
    public void reverse() {
        for (int i = 0; i < (size / 2); i++) {
            Object temp = elementData[i];
            elementData[i] = elementData[size - i - 1];
            elementData[size - i - 1] = temp;
        }
    }

    /**
     * Get copy of current array.
     * @return copy of current array
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    /**
     * Get content in format '[ element1 element2 ... elenentN ] or [ ] if empty.
     * @return [ element1 element2 ... elenentN ]
     */
    @Override
    public String toString() {
        return Arrays.toString(elementData);
    }
}
