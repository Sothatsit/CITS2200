package com.sothatsit.cits2200.data.tree;

import CITS2200.Iterator;
import CITS2200.OutOfBounds;
import static com.sothatsit.cits2200.util.Checks.*;

public class BinTreeTest {

    public static void main(String[] args) {
        testBinTree();
    }

    public static void testBinTree() {
        //         "a"
        //      /       \
        //    "b"       "c"
        //   /  \       /
        // "d"  "e"   "f"
        //   \
        //   "g"

        BinTree<String> leaf = new BinTree<>();
        BinTree<String> root;
        {
            BinTree<String> g = new BinTree<>("g", leaf, leaf);

            BinTree<String> d = new BinTree<>("d", leaf, g);
            BinTree<String> e = new BinTree<>("e", leaf, leaf);
            BinTree<String> f = new BinTree<>("f", leaf, leaf);

            BinTree<String> b = new BinTree<>("b", d, e);
            BinTree<String> c = new BinTree<>("c", f, leaf);

            root = new BinTree<>("a", b, c);
        }

        //
        // Iterator test
        //

        String[] expected = {
                "a",
                "b", "c",
                "d", "e", "f",
                "g"
        };

        Iterator<String> iterator = root.iterator();

        for (String expectedNext : expected) {
            assertTrue(iterator.hasNext());

            String next = iterator.next();
            assertEquals(expectedNext, next);
        }

        assertTrue(!iterator.hasNext());
        assertThrows(iterator::next, OutOfBounds.class);

        //
        // Equality test
        //

        BinTree<String> rootCopy;
        {
            BinTree<String> g = new BinTree<>("g", leaf, leaf);

            BinTree<String> d = new BinTree<>("d", leaf, g);
            BinTree<String> e = new BinTree<>("e", leaf, leaf);
            BinTree<String> f = new BinTree<>("f", leaf, leaf);

            BinTree<String> b = new BinTree<>("b", d, e);
            BinTree<String> c = new BinTree<>("c", f, leaf);

            rootCopy = new BinTree<>("a", b, c);
        }

        assertTrue(root.equals(rootCopy));
        assertTrue(root.getLeft().equals(rootCopy.getLeft()));

        BinTree<String> rootNoG;
        {
            BinTree<String> d = new BinTree<>("d", leaf, leaf);
            BinTree<String> e = new BinTree<>("e", leaf, leaf);
            BinTree<String> f = new BinTree<>("f", leaf, leaf);

            BinTree<String> b = new BinTree<>("b", d, e);
            BinTree<String> c = new BinTree<>("c", f, leaf);

            rootNoG = new BinTree<>("a", b, c);
        }

        assertTrue(!root.equals(rootNoG));
        assertTrue(!root.equals(null));
        assertTrue(!root.equals("apples"));
        assertTrue(!root.equals(root.getLeft()));

        System.out.println("BinTree passed");
    }
}
