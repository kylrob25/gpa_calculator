package me.krob;

import me.krob.config.JsonConfigBuilder;
import me.krob.config.ModuleConfig;
import me.krob.grade.Grade;
import me.krob.module.Module;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application implements Runnable {

    private final ModuleConfig moduleConfig;

    public Application() {
        File configFile = new File("./modules.json");
        JsonConfigBuilder<ModuleConfig> configBuilder = new JsonConfigBuilder<>(configFile, ModuleConfig.class);

        configBuilder.makeParent();

        moduleConfig = configBuilder.loadFile();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Map<Module, Grade> modules = new HashMap<>();

        for (Module module : moduleConfig.getModules()) {
            System.out.printf("Enter the Final Grade for %s%n", module.getName());
            try {
                Grade grade = Grade.valueOf(scanner.next().toUpperCase());
                modules.put(module, grade);
            } catch (Throwable throwable) {
                System.exit(1);
            }
        }

        double totalGradePoints = 0;
        double totalModuleValue = 0;

        for (Map.Entry<Module, Grade> entry: modules.entrySet()) {
            Module module = entry.getKey();
            Grade grade = entry.getValue();

            totalGradePoints += (grade.getValue() * module.getValue());
            totalModuleValue += (module.getValue());
        }

        double points = totalGradePoints / totalModuleValue;
        double rounded = (double) Math.round(points * 100) / 100;

        System.out.printf("Your GPA is %.2f%n", rounded);
    }
}
