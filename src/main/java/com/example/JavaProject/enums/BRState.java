package com.example.JavaProject.enums;

import java.util.stream.Stream;

public enum BRState implements State {
    RO(11, "Rondônia", BRRegion.N),
    AC(12, "Acre", BRRegion.N),
    AM(13, "Amazonas", BRRegion.N),
    RR(14, "Roraima", BRRegion.N),
    PA(15, "Pará", BRRegion.N),
    AP(16, "Amapá", BRRegion.N),
    TO(17, "Tocantins", BRRegion.N),
    MA(21, "Maranhão", BRRegion.NE),
    PI(22, "Piauí", BRRegion.NE),
    CE(23, "Ceará", BRRegion.NE),
    RN(24, "Rio Grande do Norte", BRRegion.NE),
    PB(25, "Paraíba", BRRegion.NE),
    PE(26, "Pernambuco", BRRegion.NE),
    AL(27, "Alagoas", BRRegion.NE),
    SE(28, "Sergipe", BRRegion.NE),
    BA(29, "Bahia", BRRegion.NE),
    MG(31, "Minas Gerais", BRRegion.SE),
    ES(32, "Espírito Santo", BRRegion.SE),
    RJ(33, "Rio de Janeiro", BRRegion.SE),
    SP(35, "São Paulo", BRRegion.SE),
    PR(41, "Paraná", BRRegion.S),
    SC(42, "Santa Catarina", BRRegion.S),
    RS(43, "Rio Grande do Sul", BRRegion.S),
    MS(50, "Mato Grosso do Sul", BRRegion.CO),
    MT(51, "Mato Grosso", BRRegion.CO),
    GO(52, "Goiás", BRRegion.CO),
    DF(53, "Distrito Federal", BRRegion.CO);

    private final int id;
    private final String name;
    private final BRRegion region;

    private BRState(final int id, final String name, final BRRegion region) {
        this.id = id;
        this.name = name;
        this.region = region;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BRRegion getRegion() {
        return region;
    }

    public static Stream<BRState> stream() {
        return Stream.of(BRState.values());
    }
}
