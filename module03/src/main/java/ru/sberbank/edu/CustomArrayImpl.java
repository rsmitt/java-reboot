package ru.sberbank.edu;

import java.util.Collection;

public class CustomArrayImpl<T> implements CustomArray<T> {

    private int capacity;

    private int size;
    private T[] objects;

    public CustomArrayImpl(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.objects = (T[]) new Object[capacity];
    }

    public CustomArrayImpl() {
        this(10);
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
        if (item == null) {
            return false;
        } else {
            if (size == capacity) {
                capacity *= 2;
                T[] help_array = (T[]) new Object[size];
                for (int i = 0; i < size; i++) {
                    help_array[i] = objects[i];
                }
                objects = (T[]) new Object[capacity];
                for (int i = 0; i < help_array.length; i++) {
                    objects[i] = help_array[i];
                }
                objects[help_array.length] = item;
            } else {
                objects[size] = item;
            }
            size += 1;
            return true;
        }
    }

    @Override
    public boolean addAll(T[] items) {
        if (items.length == 0) {
            return false;
        } else {
            if (items.length > capacity - size) {
                capacity = Math.max(2 * capacity, size + items.length);
                T[] help_array = (T[]) new Object[size];
                for (int i = 0; i < size; i++) {
                    help_array[i] = objects[i];
                }
                objects = (T[]) new Object[capacity];
                for (int i = 0; i < help_array.length; i++) {
                    objects[i] = help_array[i];
                }
                for (int i = 0; i < items.length; i++) {
                    objects[size + i] = items[i];
                }
            } else {
                for (int i = 0; i < items.length; i++) {
                    objects[size + i] = items[i];
                }
            }
            size += items.length;
            return true;
        }
    }

    @Override
    public boolean addAll(Collection<T> items) {
        if (items.isEmpty()) {
            return false;
        } else {
            addAll((T[]) items.toArray());
            return true;
        }
    }

    @Override
    public boolean addAll(int index, T[] items) {
        if (index >= size || index < 0) {
            return false;
        } else {
            T[] array_before_index = (T[]) new Object[index];
            for (int i = 0; i < index; i++) {
                array_before_index[i] = objects[i];
            }
            T[] array_from_index = (T[]) new Object[size - index];
            for (int i = 0; i < size - index; i++) {
                array_from_index[i] = objects[index + i];
            }
            if (items.length > capacity - size) {
                capacity = Math.max(2 * capacity, size + items.length);
                objects = (T[]) new Object[capacity];
                for (int i = 0; i < array_before_index.length; i++) {
                    objects[i] = array_before_index[i];
                }
                for (int i = 0; i < items.length; i++) {
                    objects[index + i] = items[i];
                }
                for (int i = 0; i < array_from_index.length; i++) {
                    objects[array_before_index.length + items.length + i] = array_from_index[i];
                }
            } else {
                for (int i = 0; i < array_before_index.length; i++) {
                    objects[i] = array_before_index[i];
                }
                for (int i = 0; i < items.length; i++) {
                    objects[index + i] = items[i];
                }
                for (int i = 0; i < array_from_index.length; i++) {
                    objects[array_before_index.length + items.length + i] = array_from_index[i];
                }
            }
            size += items.length;
            return  true;
        }
    }

    @Override
    public T get(int index) {
        return objects[index];
    }

    @Override
    public T set(int index, T item) {
        if (size == capacity) {
            capacity *= 2;
            T[] array_before_index = (T[]) new Object[index];
            for (int i = 0; i < index; i++) {
                array_before_index[i] = objects[i];
            }
            T[] array_from_index = (T[]) new Object[size - index];
            for (int i = index; i < size; i++) {
                array_from_index[i - index] = objects[i];
            }
            objects = (T[]) new Object[capacity];
            for (int i = 0; i < array_before_index.length; i++) {
                objects[i] = array_before_index[i];
            }
            objects[index] = item;
            for (int i = 0; i < array_from_index.length; i++) {
                objects[index + 1 + i] = array_from_index[i];
            }
        } else {
            T[] array_before_index = (T[]) new Object[index];
            for (int i = 0; i < index; i++) {
                array_before_index[i] = objects[i];
            }
            T[] array_from_index = (T[]) new Object[size - index];
            for (int i = index; i < size; i++) {
                array_from_index[i - index] = objects[i];
            }
            for (int i = 0; i < array_before_index.length; i++) {
                objects[i] = array_before_index[i];
            }
            objects[index] = item;
            for (int i = 0; i < array_from_index.length; i++) {
                objects[index + 1 + i] = array_from_index[i];
            }
        }
        size += 1;
        return objects[index];
    }

    @Override
    public void remove(int index) {
        T[] array_before_index = (T[]) new Object[index];
        for (int i = 0; i < index; i++) {
            array_before_index[i] = objects[i];
        }
        T[] array_after_index = (T[]) new Object[size - index - 1];
        for (int i = index + 1; i < size; i++) {
            array_after_index[i - index - 1] = objects[i];
        }
        objects = (T[]) new Object[capacity];
        for (int i = 0; i < array_before_index.length; i++) {
            objects[i] = array_before_index[i];
        }
        for (int i = 0; i < array_after_index.length; i++) {
            objects[index + i] = array_after_index[i];
        }
        size -= 1;
    }

    @Override
    public boolean remove(T item) {
        if (!contains(item)) {
            return false;
        } else {
            remove(indexOf(item));
            return true;
        }
    }

    @Override
    public boolean contains(T item) {
        for (T object: objects) {
            if (object.equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T item) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (objects[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public void ensureCapacity(int newElementsCount) {
        if (newElementsCount > capacity) {
            capacity = newElementsCount;
            T[] resized_array = (T[]) new Object[capacity];
            for (int i = 0; i < size; i++) {
                resized_array[i] = objects[i];
            }
            objects = resized_array;
        }
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void reverse() {
        T[] reversed_array = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            reversed_array[i] = objects[size - 1 - i];
        }
        for (int i = size; i < capacity; i++) {
            reversed_array[i] = null;
        }
        objects = reversed_array;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        } else {
            StringBuilder output_string = new StringBuilder();
            for (int i = 0; i < size; i++) {
                if (i == 0) {
                    output_string.append("[");
                    output_string.append(objects[i].toString());
                } else if (i < size - 1) {
                    output_string.append(", ");
                    output_string.append(objects[i].toString());
                } else {
                    output_string.append(", ");
                    output_string.append(objects[i].toString());
                    output_string.append("]");
                }
            }
            return output_string.toString();
        }
    }

    @Override
    public Object[] toArray() {
        T[] array = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            array[i] = objects[i];
        }
        return array;
    }
}
