package com.xspeedit;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class OptimizedBoxingLineTest {

    @Test
    public void test_empty_line_produce_no_box() throws Exception {
        BoxingLine boxingLine = new OptimizedBoxingLine(10);

        assertEquals (0, boxingLine.boxesProducedCount());
    }

    @Test
    public void test_line_of_one_element_produce_one_box() throws Exception {
        BoxingLine boxingLine = new OptimizedBoxingLine(10);

        boxingLine.addElements (1);
        boxingLine.close();

        assertEquals (1, boxingLine.boxesProducedCount());
        assertEquals (asList("1"), boxingLine.producedBoxes());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_line_of_one_element_too_big() throws Exception {
        BoxingLine boxingLine = new OptimizedBoxingLine(10);

        boxingLine.addElements (10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_line_of_one_element_too_small() throws Exception {
        BoxingLine boxingLine = new OptimizedBoxingLine(10);

        boxingLine.addElements (0);
    }

    @Test
    public void test_beginning_of_example() throws Exception {
        BoxingLine boxingLine = new OptimizedBoxingLine(10);

        boxingLine.addElements (1, 6, 3, 8, 2);
        boxingLine.close();

        assertEquals(2, boxingLine.boxesProducedCount());
        assertEquals(asList("163", "82"), boxingLine.producedBoxes ());
        assertEquals("163/82", boxingLine.toString());
    }

    @Test
    public void test_example() throws Exception {
        BoxingLine boxingLine = new OptimizedBoxingLine(10);

        boxingLine.addElements (1, 6, 3, 8, 4);
        boxingLine.addElements (1, 6, 8, 9, 5, 2, 5, 7, 7, 3);
        boxingLine.close();

        assertEquals(8, boxingLine.boxesProducedCount());
        assertEquals(asList("163", "46", "19", "8", "55", "28", "73", "7"), boxingLine.producedBoxes ());
        assertEquals("163/46/19/8/55/28/73/7", boxingLine.toString());
    }

    @Test
    public void test_other_example() throws Exception {
        BoxingLine boxingLine = new OptimizedBoxingLine(10);

        boxingLine.addElements (1, 6, 3, 8, 4);
        boxingLine.addElements (1, 6, 8, 9, 5, 2, 5, 7, 7, 3, 8, 3);
        boxingLine.close();

        assertEquals(9, boxingLine.boxesProducedCount());
        assertEquals(asList("163", "46", "19", "8", "55", "28", "73", "73", "8"), boxingLine.producedBoxes ());
        assertEquals("163/46/19/8/55/28/73/73/8", boxingLine.toString());
    }

}