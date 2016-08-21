package scriptparser.java.custom.collections;

public class ObjectStack {

    private ArrayBackedIndexedCollection collection;

    public ObjectStack() {
        this.collection = new ArrayBackedIndexedCollection();
    }

    public boolean isEmpty() {
        return collection.isEmpty();
    }

    public int size() {
        return collection.size();
    }

    public void push(Object value) {
        collection.add(value);
    }

    public Object pop() {
        Object value = peek();
        collection.remove(size()-1);
        return value;
    }

    public Object peek() {
        if (isEmpty()) {
            throw new EmptyStackException("Stack is empty, cannot pop value!");
        }
        return collection.get(size()-1);
    }

    public void clear() {
        collection.clear();
    }

}
