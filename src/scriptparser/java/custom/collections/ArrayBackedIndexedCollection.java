package scriptparser.java.custom.collections;

import java.util.Arrays;

public class ArrayBackedIndexedCollection {

    private static final int DEFAULT_CAPACITY = 16;

    private int size;
    private int capacity;
    private Object[] elements;

    /**
     * Default constructor. Sets capacity of array to @DEFAULT_CAPACITY.
     */
    public ArrayBackedIndexedCollection() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Initializes array with provided @initialCapacity
     * If the capacity is less than 1, it throws IllegalArgumentException
     * @param initialCapacity capacity of @elements array
     */
    public ArrayBackedIndexedCollection(int initialCapacity) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("Inital array capacity has to be at least 1!");
        }
        this.capacity = initialCapacity;
        this.elements = new Object[capacity];
        this.size = 0;
    }

    /**
     * Checks if an array has no elements
     * @return true if array is empty, false otherwise
     */
    public boolean isEmpty() {
       return size == 0;
    }

    /**
     * Gets number of elements in the array
     * @return number of elements in the array
     */
    public int size() {
        return size;
    }

    /**
     * Adds an element in the array.
     * If the array is full, it will be expanded with a double capacity.
     * Null values will throw an IllegalArgumentException
     * @param value object to add to an array
     */
    public void add(Object value) {
        insert(value, size);
    }

    /**
     * Gets an element at index @index. If the index is less than 0 or more than size of elements in array -1, it throws
     * an IndexOutOfBoundsException
     * @param index index of element in array to get
     * @return object at position @index in the array
     */
    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Cannot get an element with index " + index + "! Valid indices are [0," +
                    (size-1)+"].");
        }
        return elements[index];
    }

    /**
     * Removes an element at index @index. Other elements after the @index are shifted down, so that there is no empty
     * space inside the array.
     * If the index is less than 0 or more than size of elements in array -1, it throws an IndexOutOfBoundsException.
     * @param index index of element in array which is to be removed
     * @throws IndexOutOfBoundsException
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Cannot remove an element with index " + index + "! Valid indices are [0," +
                    (size-1)+"].");
        }
        for (int i = index; i < size-1; i++) {
            elements[i] = elements[i+1];
        }
        elements[size-1] = null;
        size--;
    }

    /**
     * Inserts an object @value at position @position in the array. Elements after the object will be shifted to the
     * higher positions. If the array is full, it will be expanded with a double capacity.
     * Null @value will throw an IllegalArgumentException, and positions less than 0 or more than @size will throw
     * IndexOutOfBoundsException
     * @param value object to insert into an array
     * @param position index to which to insert an object
     * @throws IndexOutOfBoundsException
     * @throws IllegalArgumentException
     */
    public void insert(Object value, int position) {
        if (value == null) {
            throw new IllegalArgumentException("Cannot add null objects to the array!");
        }
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException("Cannot inert an element with to the position " + position + "! Valid "+
                    "positions are [0," + (size)+"].");
        }
        if (size == capacity) {
            doubleArray();
        }
        for (int i = size; i > position; i--) {
            elements[i] = elements[i-1];
        }

        this.elements[position] = value;
        size++;
    }

    /**
     * Reallocate array to double size.
     */
    private void doubleArray() {
        Object[] doubledCapacityArray = new Object[capacity*2];
        doubledCapacityArray = Arrays.copyOf(elements, doubledCapacityArray.length);
        this.elements = doubledCapacityArray;
    }

    /**
     *
     * @param value
     * @return
     */
    public int indexOf(Object value) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    /**
     *
     * @param value
     * @return
     */
    public boolean contains(Object value) {
        return indexOf(value) != -1;
    }

    /**
     *
     */
    public void clear() {
       for (int i = 0; i < size; i++) {
           elements[i] = null;
       }
        size = 0;
    }


}
