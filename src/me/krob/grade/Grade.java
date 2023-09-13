package me.krob.grade;

public enum Grade {
    A1(4.0),
    A2(3.5),
    A3(3.0),
    B1(2.5),
    B2(2.0),
    C(1.5),
    D(1.0),
    E(0.5),
    NS(0.0);

    private final double value;

    Grade(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
