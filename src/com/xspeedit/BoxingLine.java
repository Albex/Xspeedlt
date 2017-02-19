package com.xspeedit;

import java.util.List;

public interface BoxingLine {

    int boxesProducedCount();

    boolean addElements(int... elements);

    List<String> producedBoxes();

}
