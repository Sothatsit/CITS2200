package com.sothatsit.cits2200.other;

import static com.sothatsit.cits2200.util.Checks.*;

public class LockTest {

    private static final int INITAL_LOCK_COMBO = 111;

    public static void main(String[] args) {
        testLockInt();
        testLockString();
    }

    public static void testLockInt() {
        Lock lock = new LockInt(INITAL_LOCK_COMBO);

        testLock(lock);

        System.out.println("LockInt passed");
    }

    public static void testLockString() {
        String comboString = Integer.toString(INITAL_LOCK_COMBO);
        Lock lock = new LockString(comboString);

        testLock(lock);

        System.out.println("LockString passed");
    }

    public static void testLock(Lock lock) {
        assertTrue(lock.open(INITAL_LOCK_COMBO));
        assertTrue(!lock.open(123));

        assertTrue(lock.changeCombo(INITAL_LOCK_COMBO, 123));
        assertTrue(!lock.open(INITAL_LOCK_COMBO));
        assertTrue(lock.open(123));

        assertTrue(lock.changeCombo(123, 999));
        assertTrue(lock.open(999));
        assertTrue(!lock.open(456));
    }
}
