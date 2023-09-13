package me.krob.module;

import lombok.Getter;

@Getter
public class Module {
    private final String name;
    private final double value;

    public Module(String name, int credits) {
        this.name = name;
        this.value = (double) credits / 20;
    }
}
