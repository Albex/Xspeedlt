package com.xspeedit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    public int removeBiggest() {
        int biggestIndex = 0;
        for (int i = 1; i < contents.size(); i++) {
            if (contents.get(biggestIndex) < contents.get(i)) {
                biggestIndex = i;
            }
        }
        int biggest = contents.remove(biggestIndex);
        contentSize -= biggest;
        return biggest;
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
        StringBuilder builder = new StringBuilder();
        for (Integer content : contents) {
            builder.append(content);
        }
        return builder.toString();
    }

    private int contentSize;

    private final int capacity;

    private final List<Integer> contents;
}
