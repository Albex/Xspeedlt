package com.xspeedit;

import java.util.List;

public interface BoxingLine {

    int boxesProducedCount();

    void addElements(Integer... elements);

    void close();

    List<String> producedBoxes();

}
