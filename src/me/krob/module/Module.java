package me.krob.module;

import lombok.Getter;

@Getter
public class Module {
    private final String name;
    private final double credits;

    public Module(String name, int credits) {
        this.name = name;
        this.credits = credits;
    }

    public double getWeight() {
        return credits / 20.0;
    }
}
