import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest {

    private static DynamicArray dynamicArray;

    @BeforeEach
    private void setup() {
        dynamicArray = new DynamicArray(Integer.class);
    }

    @Test
    public void givenNewArray_WhenIsEmtpyCalled_ThenReturnsTrue() {
        assertTrue(dynamicArray.isEmpty());
    }

    @Test
    public void givenNewArrayAndValuePushed_WhenIsEmtpyCalled_ThenReturnsFalse() {
        dynamicArray.push(1);
        assertFalse(dynamicArray.isEmpty());
    }

    @Test
    public void givenNewArray_WhenLengthCalled_ThenReturnsZero() {
        assertEquals(0, dynamicArray.length());
    }

    @Test
    public void givenNewArrayAndNValuesPushed_WhenLengthCalled_ThenReturnsN() {
        int n = 10;
        for (int i = 0; i < n; i++) {
            dynamicArray.push(1);
        }
        assertEquals(n, dynamicArray.length());
    }

    @Test
    public void givenArrayWithValues_WhenPopCalled_ReturnsLastValue() {
        Integer n = 1;
        dynamicArray.push(n);
        assertEquals(n, dynamicArray.pop());
    }

    @Test
    public void givenTParameter_WhenConstructorCalled_CreatesTArray() {
        dynamicArray = new DynamicArray(String.class);
        dynamicArray.push("Test");
        assertFalse(dynamicArray.isEmpty());
    }

    @Test
    public void givenTArray_WhenConstructorCalled_CreatesTArray() {
        Integer[] values = {0,1,2,3};

        dynamicArray = new DynamicArray(values);

        assertFalse(dynamicArray.isEmpty());
    }

    @Test
    public void givenArrayWithNValuesPoppedNTimes_WhenIsEmptyCalled_ReturnsTrue() {
        Integer[] values = {0,1,2,3};

        dynamicArray = new DynamicArray(values);

        for (int i = 0; i < values.length; i++) {
            dynamicArray.pop();
        }

        assertTrue(dynamicArray.isEmpty());
    }

    @Test
    public void givenNValues_WhenPushCalledNTimes_DynamicallyResizesCapacityToFit() {
        int n = 20; //default capacity is 16;
        for (Integer i = 0; i < n; i++) {
            dynamicArray.push(i);
        }
        assertEquals(n, dynamicArray.length());
    }

    @Test
    public void givenArrayWithNValues_WhenGetICalled_ReturnValueAtI() {
        Integer[] values = {0,1,2,3};
        dynamicArray = new DynamicArray(values);
        assertEquals(values[1], dynamicArray.get(1));
    }

    @Test
    public void givenArrayWithValues_WhenSetICalled_SetsValueToNewVal() {
        Integer[] values = {0,1,2,3};
        Integer newVal = 15;
        int i = 1;
        dynamicArray = new DynamicArray(values);
        dynamicArray.set(newVal, i);
        assertEquals(newVal, dynamicArray.get(i));
    }

    @Test
    public void givenArrayWithValueN_WhenFindIndexCalled_ReturnsIndexOfN() {
        Integer[] values = {0,1,2,3};
        dynamicArray = new DynamicArray(values);
        assertEquals(1, dynamicArray.findIndex(1));
    }

    @Test
    public void givenArrayWithoutValueN_WhenFindIndexCalled_ReturnsMinusOne() {
        Integer[] values = {0,1,2,3};
        dynamicArray = new DynamicArray(values);
        assertEquals(-1, dynamicArray.findIndex(10));
    }

    @Test
    public void givenNArray_WhenRemoveAtCalled_RemovesValueFromArray() {
        Integer[] values = {0,1,2,3,4,5,6,7,8,9};
        dynamicArray = new DynamicArray(values);
        dynamicArray.removeAt(2);
        assertEquals(-1, dynamicArray.findIndex(2));
    }

    @Test
    public void givenNArray_WhenRemoveAtCalled_ReturnsRemovedValue() {
        Integer[] values = {0,1,2,3,4,5,6,7,8,9};
        dynamicArray = new DynamicArray(values);
        Object returnVal = dynamicArray.removeAt(3);
        assertEquals(values[3], returnVal);
    }

    @Test
    public void givenNArray_whenShiftCalled_RemovesFirstValue() {
        Integer[] values = {0,1,2,3,4,5,6,7,8,9};
        dynamicArray = new DynamicArray(values);
        dynamicArray.shift();
        assertNotEquals(values[0], dynamicArray.get(0));
    }

    @Test
    public void givenNArray_whenUnShiftCalled_PrependsValueToArray() {
        Integer[] values = {0,1,2,3,4,5,6,7,8,9};
        dynamicArray = new DynamicArray(values);

        Integer newVal = 10;

        dynamicArray.unshift(newVal);
        assertNotEquals(newVal, dynamicArray.get(0));
    }

    @Test
    public void givenIntegerArrayAndNotIntegerValue_WhenPushCalled() throws ArrayStoreException {
        dynamicArray.push(1);
        assertThrows(ArrayStoreException.class, () -> {
            String notInt = "Test";
            dynamicArray.push(notInt);
        });
    }

    @Test
    public void givenEmptyArray_whenPopCalled() throws EmptyArrayException {
        assertThrows(EmptyArrayException.class, () -> {
            dynamicArray.pop();
        });
    }

    @Test
    public void givenEmptyArray_whenSetCalled() throws EmptyArrayException {
        assertThrows(EmptyArrayException.class, () -> {
            dynamicArray.set(1, 1);
        });
    }

    @Test
    public void givenEmptyArray_whenGetCalled() throws EmptyArrayException {
        assertThrows(EmptyArrayException.class, () -> {
            dynamicArray.get(0);
        });
    }

    @Test
    public void givenEmptyArray_whenFindIndexCalled() throws EmptyArrayException {
        assertThrows(EmptyArrayException.class, () -> {
            dynamicArray.findIndex(0);
        });
    }

    @Test
    public void givenEmptyArray_whenRemoveAtCalled() throws EmptyArrayException {
        assertThrows(EmptyArrayException.class, () -> {
            dynamicArray.removeAt(0);
        });
    }

    @Test
    public void givenEmptyArray_whenShiftCalled() throws EmptyArrayException {
        assertThrows(EmptyArrayException.class, () -> {
            dynamicArray.shift();
        });
    }


    @Test
    public void givenArrayOfSizeI_whenRemoveAtCalledOnIPlusOne() throws IllegalArgumentException {
        dynamicArray.push(1);
        assertThrows(IllegalArgumentException.class, () -> {
            dynamicArray.removeAt(10);
        });
    }

}