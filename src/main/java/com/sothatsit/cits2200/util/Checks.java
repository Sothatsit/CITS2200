package com.sothatsit.cits2200.util;

import java.lang.reflect.Array;
import java.util.Objects;

/**
 * A collection of assertions to guarantee parameter constraints or to guarantee conditions in tests.
 *
 * @author Paddy Lamont
 */
public class Checks {

    /**
     * An exception thrown when an assertion fails.
     */
    public static class AssertionFailure extends RuntimeException {

        /**
         * Construct an assertion failure exception without a message.
         */
        public AssertionFailure() {
            super();
        }

        /**
         * Construct an assertion failure with the reason {@param reason}.
         *
         * @param reason the reason for this assertion failure
         */
        public AssertionFailure(String reason) {
            super(reason);
        }
    }

    /**
     * Throw an empty assertion failure.
     */
    public static void fail() {
        throw new AssertionFailure();
    }

    /**
     * Throw an assertion failure with the reason {@param reason};
     *
     * @param reason the reason for this assertion failure
     */
    public static void fail(String reason) {
        throw new AssertionFailure(reason);
    }

    /**
     * Assert that {@param value} is true.
     *
     * @param value the value that you assert to be true
     */
    public static void assertTrue(boolean value) {
        if(value)
            return;

        fail();
    }

    /**
     * Assert that {@param value} is true, throwing an assertion failure
     * with the reason {@param reason} if the assertion fails.
     *
     * @param value  the value that you assert to be true
     * @param reason the reason if this assertion fails
     */
    public static void assertTrue(boolean value, String reason) {
        if(value)
            return;

        fail(reason);
    }

    /**
     * Assert that {@param value1} is equal to {@param value2}.
     *
     * @param value1 the first value
     * @param value2 the second value
     */
    public static void assertEquals(Object value1, Object value2) {
        assertEquals("", value1, value2);
    }

    /**
     * Assert that {@param value1} is equal to {@param value2}.
     *
     * @param prefix a prefix to place before the error message
     * @param value1 the first value
     * @param value2 the second value
     */
    public static void assertEquals(String prefix, Object value1, Object value2) {
        if(value1 != null && value2 != null && value1.getClass().isArray() && value2.getClass().isArray()) {
            assertArraysEqual(prefix, value1, value2);
            return;
        }

        assertTrue(Objects.equals(value1, value2), prefix + value1 + " does not equal " + value2);
    }


    /**
     * Assert that the contents of {@param array1} are equal to the contents of {@param array2}.
     *
     * @param array1 the first array
     * @param array2 the second array
     */
    public static void assertArraysEqual(Object array1, Object array2) {
        assertArraysEqual("", array1, array2);
    }

    /**
     * Assert that the contents of {@param array1} are equal to the contents of {@param array2}.
     *
     * @param prefix a prefix to place before the error message
     * @param array1 the first array
     * @param array2 the second array
     */
    public static void assertArraysEqual(String prefix, Object array1, Object array2) {
        assertTrue(array1.getClass().isArray(), "array1 must be an array");
        assertTrue(array2.getClass().isArray(), "array2 must be an array");

        int length1 = Array.getLength(array1);
        int length2 = Array.getLength(array2);

        if(length1 != length2)
            fail("array1 is not the same length as array2");

        for(int index = 0; index < length1; ++index) {
            Object value1 = Array.get(array1, index);
            Object value2 = Array.get(array2, index);

            assertEquals(prefix + "at index " + index + ": ", value1, value2);
        }
    }

    /**
     * Assert that {@param value} is non-null.
     *
     * @param value the value that you assert to not be null
     * @param name  the name of the value that must be non-null
     */
    public static void assertNonNull(Object value, String name) {
        assertTrue(value != null, name + " must be non-null");
    }

    /**
     * Assert that running {@param runnable} throws an exception of type {@param exceptionClass}.
     *
     * @param runnable       the runnable that you assert will throw the exception
     * @param exceptionClass the or a super class of the exception you assert will be thrown
     */
    public static void assertThrows(Runnable runnable, Class<? extends Exception> exceptionClass) {
        String exceptionName = exceptionClass.getSimpleName();

        try {
            runnable.run();
        } catch (Exception thrown) {
            Class<? extends Exception> thrownClass = thrown.getClass();
            String thrownName = thrownClass.getSimpleName();

            assertTrue(
                exceptionClass.isAssignableFrom(thrownClass),
                thrownName + "  was thrown, expected " + exceptionName
            );

            return;
        }

        fail("no exception was thrown, expected " + exceptionName);
    }
}
