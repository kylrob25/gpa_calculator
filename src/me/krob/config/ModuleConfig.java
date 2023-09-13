package me.krob.config;

import lombok.Getter;
import me.krob.grade.Grade;
import me.krob.module.Module;

@Getter
public class ModuleConfig {
    private final Module[] modules = new Module[] {};
    private final Grade[] grades = new Grade[] {};
}
