import java.lang.reflect.Array;
import java.util.Arrays;

public class DynamicArray<T> {
    private int length = 0;
    private int capacity = 0;
    private T[] arr;

    DynamicArray(Class<T> classType) {
        capacity = 16;
        arr = (T[]) Array.newInstance(classType, capacity);
    }

    DynamicArray(int capacity) throws NegativeArraySizeException {
        if (capacity <= 0) throw new NegativeArraySizeException("Array must have a positive capacity");
        this.capacity = capacity;
        arr = (T[]) Array.newInstance(Integer.class, this.capacity);
    }

    DynamicArray(Class<T> classType, int capacity) throws NegativeArraySizeException {
        if (capacity <= 0) throw new NegativeArraySizeException("Array must have a positive capacity");
        this.capacity = capacity;
        arr = (T[]) Array.newInstance(classType, this.capacity);
    }

    DynamicArray(T[] arr) {
        length = arr.length;
        capacity = arr.length;
        this.arr = arr;
    }

    public boolean isEmpty() {
        if (length == 0) return true;
        return false;
    }

    public int length() {
        return length;
    }

    public void push(T val) {
        if (!val.getClass().equals(arr.getClass().getComponentType())) throw new ArrayStoreException("Type incorect this is a " + arr.getClass().getComponentType() + " array.");
        if (length == capacity) {
            capacity *=2;
            T[] newArr = (T[]) Array.newInstance(arr.getClass().getComponentType(), capacity);
            for (int i = 0; i < length; i++) {
                newArr[i] = arr[i];
            }
            arr = newArr;
        }
        arr[length] = val;
        length++;
    }

    public T pop() throws EmptyArrayException {
        if (isEmpty()) throw new EmptyArrayException();
        length--;
        return arr[length];
    }

    public T get(int i) throws EmptyArrayException {
        if (isEmpty()) throw new EmptyArrayException();
        return arr[i];
    }

    public void set(T val,int i) throws EmptyArrayException {
        if (isEmpty()) throw new EmptyArrayException();
        arr[i] = val;
    }

    public int findIndex(T val) throws EmptyArrayException {
        if (isEmpty()) throw new EmptyArrayException();
        boolean found = false;
        int i = 0;
        while (i < length) {
            if (arr[i].equals(val)) {
                found = true;
                break;
            }
            i++;
        }
        if (!found) i = -1;
        return i;
    }

    public T removeAt(int index) {
        if (isEmpty()) throw new EmptyArrayException();
        if (index > length) throw new IllegalArgumentException("Cannot remove at index larger than array");
        if (index == length) return this.pop();


        T returnVal = arr[index];

        T[] newArr = (T[]) Array.newInstance(arr.getClass().getComponentType(), length - 1);

        int i = 0;
        int j = 0;


        while (i < arr.length) {

            if (i == index) {
                i++;
                continue;
            };

            newArr[j] = arr[i];
            i++;
            j++;

        }

        arr = newArr;
        length--;
        return returnVal;
    }

    public T shift() throws EmptyArrayException {
        return removeAt(0);
    }

    public void unshift(T val) {
        if (isEmpty()) {
            push(val);
            return;
        }

        length++;

        T[] newArr = (T[]) Array.newInstance(arr.getClass().getComponentType(), length);

        newArr[0] = val;

        for (int i = 1; i < length; i++) {
            newArr[i] = arr[i - 1];
        }
    }
}

