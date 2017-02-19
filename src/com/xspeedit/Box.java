package com.xspeedit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Box {

    public Box(int capacity) {
        this.contents = new ArrayList<>();
        this.capacity = capacity;
    }

    public void add(int elementSize) {
        if (elementSize + contentSize > capacity) {
            throw new IllegalArgumentException(String.format("element too big: space left in the box %s, element size %s",
                    capacity - contents.size(), elementSize));
        }

        contents.add(elementSize);
        contentSize += elementSize;
    }

    public int leftSpace() {
        return capacity - contentSize;
    }

    public Collection<Integer> getContents() {
        return contents;
    }

    private int contentSize;

    private final int capacity;

    private final List<Integer> contents;
}
