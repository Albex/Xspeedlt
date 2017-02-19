package com.xspeedit;


import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * First simple algorithm described in the README file.
 */
public class BasicBoxingLine {

    public BasicBoxingLine() {
        boxCapacity = 10;
        producedBoxes = new LinkedHashSet<>();
    }

    public int boxesProducedCount() {
        return producedBoxes.size();
    }

    public boolean addElements(int... elements) {
        Box box = new Box(boxCapacity);
        for (int element : elements) {
            if (element > 9 || element < 1) {
                throw new IllegalArgumentException(String.format("at least one element is not of correct size: %s",
                        element));
            }
            boolean added = box.add(element);
            if (! added) {
                producedBoxes.add(box);
                box = new Box(boxCapacity);
                box.add(element);
            }
        }
        producedBoxes.add(box);

        return true;
    }

    public List<String> producedBoxes() {
        return producedBoxes.stream()
                .map(Box::toString)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.join ("/", producedBoxes());
    }

    private final int boxCapacity;

    private final Collection<Box> producedBoxes;

}
