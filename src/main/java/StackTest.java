import CITS2200.Overflow;
import CITS2200.Underflow;

public class StackTest {

    public static void main(String[] args) {
        testStackBlock();
        testStackLinked();
    }

    public static void testStackBlock() {
        StackBlock stack = new StackBlock(5);

        Checks.assertTrue(stack.isEmpty());

        stack.push(3);

        Checks.assertTrue(!stack.isEmpty());
        Checks.assertTrue(!stack.isFull());
        Checks.assertEquals(stack.examine(), 3);

        stack.push(2);
        stack.push(1);

        Checks.assertTrue(!stack.isEmpty());
        Checks.assertTrue(!stack.isFull());
        Checks.assertEquals(stack.examine(), 1);

        stack.pop();

        Checks.assertTrue(!stack.isEmpty());
        Checks.assertTrue(!stack.isFull());
        Checks.assertEquals(stack.examine(), 2);

        stack.push(1);
        stack.push(3);
        stack.push(4);

        Checks.assertTrue(!stack.isEmpty());
        Checks.assertTrue(stack.isFull());
        Checks.assertEquals(stack.examine(), 4);

        try {
            stack.push(7);
            Checks.fail();
        } catch (Overflow ignored) {}

        Checks.assertTrue(!stack.isEmpty());
        Checks.assertTrue(stack.isFull());
        Checks.assertEquals(stack.examine(), 4);

        for(int i=0; i < 5; ++i) {
            stack.pop();
        }

        Checks.assertTrue(stack.isEmpty());

        try {
            stack.pop();
            Checks.fail();
        } catch (Underflow ignored) {}

        try {
            stack.examine();
            Checks.fail();
        } catch (Underflow ignored) {}

        System.out.println("StackBlock passed");
    }

    public static void testStackLinked() {
        StackLinked stack = new StackLinked();

        Checks.assertTrue(stack.isEmpty());

        stack.push(3);

        Checks.assertTrue(!stack.isEmpty());
        Checks.assertTrue(!stack.isFull());
        Checks.assertEquals(stack.examine(), 3);

        stack.push(2);
        stack.push(1);

        Checks.assertTrue(!stack.isEmpty());
        Checks.assertTrue(!stack.isFull());
        Checks.assertEquals(stack.examine(), 1);

        stack.pop();

        Checks.assertTrue(!stack.isEmpty());
        Checks.assertTrue(!stack.isFull());
        Checks.assertEquals(stack.examine(), 2);

        stack.push(1);
        stack.push(3);
        stack.push(4);

        Checks.assertTrue(!stack.isEmpty());
        Checks.assertTrue(!stack.isFull());
        Checks.assertEquals(stack.examine(), 4);

        stack.push(7);

        Checks.assertTrue(!stack.isEmpty());
        Checks.assertTrue(!stack.isFull());
        Checks.assertEquals(stack.examine(), 7);

        for(int i=0; i < 6; ++i) {
            stack.pop();
        }

        Checks.assertTrue(stack.isEmpty());

        try {
            stack.pop();
            Checks.fail();
        } catch (Underflow ignored) {}

        try {
            stack.examine();
            Checks.fail();
        } catch (Underflow ignored) {}

        System.out.println("StackLinked passed");
    }
}
