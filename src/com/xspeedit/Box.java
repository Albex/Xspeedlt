package com.xspeedit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Box {

    public Box(int capacity) {
        this.contents = new ArrayList<>();
        this.capacity = capacity;
    }

    public boolean add(int elementSize) {
        if (elementSize + contentSize > capacity) {
            return false;
        }

        contents.add(elementSize);
        contentSize += elementSize;
        return true;
    }

    public int leftSpace() {
        return capacity - contentSize;
    }

    public Collection<Integer> getContents() {
        return contents;
    }

    public boolean isEmpty() {
        return contents.isEmpty();
    }

    @Override
    public String toString () {
        return contents.stream()
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    private int contentSize;

    private final int capacity;

    private final List<Integer> contents;
}
