package com.xspeedit;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoxTest {

    @Test()
    public void test_box_is_empty() {
        Box box = new Box(10);

        assertTrue(box.isEmpty());
    }

    @Test()
    public void test_box_can_add_and_contain_an_element() {
        Box box = new Box(10);

        box.add(5);
        box.add(2);

        assertEquals(asList(5, 2), box.getContents());
        assertEquals("52", box.toString());
    }

    @Test()
    public void test_box_can_add_less_than_capacity() {
        Box box = new Box(10);

        box.add(4);
        box.add(3);

        assertEquals(3, box.leftSpace());
    }

    @Test
    public void test_box_remove_biggest() {
        Box box = new Box(10);
        box.add(2);
        box.add(3);

        int biggest = box.removeBiggest();

        assertEquals(3, biggest);
        assertEquals(asList(2), box.getContents());
        assertEquals("2", box.toString());
    }

    @Test
    public void test_box_cant_add_more_than_capacity() {
        Box box = new Box(10);

        boolean added = box.add(11);

        assertFalse(added);
    }

    @Test
    public void test_box_cant_contain_more_than_capacity() {
        Box box = new Box(10);
        box.add(4);

        boolean added = box.add(7);

        assertFalse(added);
    }

}