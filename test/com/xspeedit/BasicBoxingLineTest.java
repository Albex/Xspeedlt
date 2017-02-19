package com.xspeedit;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class BasicBoxingLineTest {

    @Test
    public void test_empty_line_produce_no_box() throws Exception {
        BoxingLine boxingLine = new BasicBoxingLine();

        assertEquals (0, boxingLine.boxesProducedCount());
    }

    @Test
    public void test_line_of_one_element_produce_one_box() throws Exception {
        BoxingLine boxingLine = new BasicBoxingLine();

        boxingLine.addElements (1);

        assertEquals (1, boxingLine.boxesProducedCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_line_of_one_element_too_big() throws Exception {
        BoxingLine boxingLine = new BasicBoxingLine();

        boxingLine.addElements (10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_line_of_one_element_too_small() throws Exception {
        BoxingLine boxingLine = new BasicBoxingLine();

        boxingLine.addElements (0);
    }

    @Test
    public void test_line_of_example() throws Exception {
        BoxingLine boxingLine = new BasicBoxingLine();

        boxingLine.addElements (1, 6, 3, 8, 4, 1, 6, 8, 9, 5, 2, 5, 7, 7, 3);

        assertEquals(10, boxingLine.boxesProducedCount());
        assertEquals(asList("163", "8", "41", "6", "8", "9", "52", "5", "7", "73"), boxingLine.producedBoxes ());
        assertEquals("163/8/41/6/8/9/52/5/7/73", boxingLine.toString());
    }

}