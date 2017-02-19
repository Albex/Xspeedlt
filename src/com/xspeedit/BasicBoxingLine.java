package com.xspeedit;


import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

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
        return producedBoxes.stream()
                .map(Box::toString)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.join ("/", producedBoxes());
    }

    private Box currentBox;

    private final int boxCapacity;

    private final Collection<Box> producedBoxes;

}
