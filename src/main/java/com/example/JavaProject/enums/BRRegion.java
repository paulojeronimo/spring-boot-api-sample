package com.example.JavaProject.enums;

import java.util.stream.Stream;

public enum BRRegion implements Region {
    N(1, "Norte"),
    NE(2, "Nordeste"),
    SE(3, "Sudeste"),
    S(4, "Sul"),
    CO(5, "Centro-Oeste");

    private final int id;
    private final String name;

    private BRRegion(final int id, final String name) {
        this.id = id;
        this.name = name;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Stream<BRRegion> stream() {
        return Stream.of(BRRegion.values());
    }
}
