package com.xspeedit;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

/**
 * Created by alexc on 19/02/2017.
 */
public class BoxTest {

    @Test()
    public void test_box_can_add_and_contain_an_element() {
        Box box = new Box(10);

        box.add(5);
        box.add(2);

        assertEquals(asList(5, 2), box.getContents());
    }

    @Test()
    public void test_box_can_add_less_than_capacity() {
        Box box = new Box(10);

        box.add(4);
        box.add(3);

        assertEquals(3, box.leftSpace());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_box_cant_add_more_than_capacity() {
        Box box = new Box(10);

        box.add(11);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_box_cant_contain_more_than_capacity() {
        Box box = new Box(10);
        box.add(4);

        box.add(7);
    }

}