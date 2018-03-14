package com.sothatsit.cits2200.test;

import java.util.Objects;

public class Checks {

    public static class AssertFailure extends RuntimeException {
        public AssertFailure() {
            super();
        }

        public AssertFailure(String message) {
            super(message);
        }
    }

    public static void fail() {
        throw new AssertFailure();
    }

    public static void fail(String message) {
        throw new AssertFailure(message);
    }

    public static void assertTrue(boolean value) {
        if(value)
            return;

        fail();
    }

    public static void assertTrue(boolean value, String message) {
        if(value)
            return;

        fail(message);
    }

    public static void assertEquals(Object value1, Object value2) {
        assertTrue(Objects.equals(value1, value2), value1 + " does not equal " + value2);
    }

    public static void assertNonNull(Object value, String name) {
        assertTrue(value != null, name + " must be non-null");
    }

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
