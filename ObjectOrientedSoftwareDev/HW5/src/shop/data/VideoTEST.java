package shop.data;

import junit.framework.TestCase;

import junit.framework.Assert;
import junit.framework.TestCase;

public class VideoTEST extends TestCase {
    public VideoTEST(String name) {
        super(name);
    }
    public void testHashCode() {
        assertEquals
                (-875826552,
                        new VideoObj("None", 2009, "Zebra").hashCode());
        assertEquals
                (-1391078111,
                        new VideoObj("Blah", 1954, "Cante").hashCode());
    }
    public void testMe() {
    }
}