package com.xspeedit;


import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * First simple algorithm described in the README file.
 */
public class BasicBoxingLine implements BoxingLine {

    public BasicBoxingLine(int boxCapacity) {
        this.boxCapacity = boxCapacity;
        this.producedBoxes = new LinkedHashSet<>();
        this.currentBox = new Box(this.boxCapacity);
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
                producedBoxes.add(currentBox);
                currentBox = new Box(boxCapacity);
                currentBox.add(element);
            }
        }
    }

    @Override
    public void close() {
        if (! currentBox.isEmpty()) {
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

    private final int boxCapacity;

    private final Collection<Box> producedBoxes;

}
