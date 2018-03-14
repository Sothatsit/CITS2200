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
        Checks.assertTrue(lock.open(INITAL_LOCK_COMBO));
        Checks.assertTrue(!lock.open(123));

        Checks.assertTrue(lock.changeCombo(INITAL_LOCK_COMBO, 123));
        Checks.assertTrue(!lock.open(INITAL_LOCK_COMBO));
        Checks.assertTrue(lock.open(123));

        Checks.assertTrue(lock.changeCombo(123, 999));
        Checks.assertTrue(lock.open(999));
        Checks.assertTrue(!lock.open(456));
    }
}
