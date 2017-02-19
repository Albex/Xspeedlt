package com.xspeedit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * This algorithm tries to optimized the boxes without knowing all the elements but the nearest.
 * The buffer is limited as in real life.
 * This algorithm tries to ship big elements asap with maximized boxes
 */
public class OptimizedBoxingLine implements BoxingLine {

    public OptimizedBoxingLine(int boxCapacity) {
        this.boxCapacity = boxCapacity;
        this.producedBoxes = new LinkedHashSet<>();
        this.currentBox = new Box(boxCapacity);
    }

    @Override
    public int boxesProducedCount() {
        return producedBoxes.size();
    }

    @Override
    public void addElements(Integer... elements) {
        for (int element : elements) {
            if (element > 9 || element < 1) {
                throw new IllegalArgumentException(String.format("at least one element is not of correct size: %s",
                        element));
            }

            boolean added = currentBox.add(element);
            if (! added) {
                //waiting element is the biggest so must try to ship it asap
                if (waitingElement != null && waitingElement + element == boxCapacity) {
                    Box box = new Box(boxCapacity);
                    box.add(waitingElement);
                    box.add(element);
                    producedBoxes.add(box);
                    waitingElement = null;
                    continue;
                }

                //biggestInBox is the the biggest if waiting does not exist or cannot be shipped,
                //try to ship biggestInBox
                int biggestInBox = currentBox.removeBiggest();
                if (biggestInBox + element == boxCapacity) {
                    Box box = new Box(boxCapacity);
                    box.add(biggestInBox);
                    box.add(element);
                    producedBoxes.add(box);
                    continue;
                }

                //currentBox with biggestInBox had leftSpace, but try with element
                //element is hence bigger than biggestInBox, so ship it asap
                if (currentBox.leftSpace() == element) {
                    currentBox.add(element);
                    producedBoxes.add(currentBox);
                    currentBox = new Box(boxCapacity);
                    currentBox.add(biggestInBox);
                    continue;
                }

                //all combinations tried, hence one box must be shipped with the biggest
                if (waitingElement != null) {
                    Box box = new Box(boxCapacity);
                    box.add(waitingElement);
                    producedBoxes.add(box);
                }
                currentBox.add(min(biggestInBox, element));
                waitingElement = max(biggestInBox, element);
            } else if (currentBox.leftSpace() == 0) {
                producedBoxes.add(currentBox);
                currentBox = new Box(boxCapacity);
            }
        }
    }

    @Override
    public void close() {
        if (! currentBox.isEmpty ()) {
            producedBoxes.add(currentBox);
        }
    }

    @Override
    public List<String> producedBoxes() {
        List<String> res = new ArrayList<>();
        for (Box producedBox : producedBoxes) {
            res.add(producedBox.toString());
        }
        return res;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String box : producedBoxes()) {
            if (! builder.toString().equals("")) {
                builder.append("/");
            }
            builder.append(box);
        }
        return builder.toString();
    }

    private Box currentBox;

    private Integer waitingElement;

    private final int boxCapacity;

    private final Collection<Box> producedBoxes;
}
