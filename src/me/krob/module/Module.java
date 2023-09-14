package me.krob.module;

import lombok.Getter;

@Getter
public class Module {
    private final String name;
    private final double weight;

    public Module(String name, int credits) {
        this.name = name;
        this.weight = (double) credits / 20;
    }
}
