package me.krob;

import me.krob.config.JsonConfigBuilder;
import me.krob.grade.Grade;
import me.krob.module.Module;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// TODO: Move to GUI system
public class Application implements Runnable {

    private final Module[] modules;

    public Application() {
        JsonConfigBuilder<Module[]> configBuilder =
                new JsonConfigBuilder<>(Module[].class, "./modules.json");

        configBuilder.makeParent();
        modules = configBuilder.loadFile();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        Map<Module, Grade> modules = new HashMap<>();

        for (Module module : this.modules) {
            while (!modules.containsKey(module)) {
                System.out.printf("Enter the final grade for %s%n", module.getName());
                try {
                    Grade grade = Grade.valueOf(scanner.next().toUpperCase());
                    modules.put(module, grade);
                } catch (Throwable throwable) {
                    System.out.println("Please enter a valid letter grade.");
                }
            }
        }

        double gpa = getGradePointAverage(modules);
        double rounded = (double) Math.round(gpa * 100) / 100;

        System.out.printf("Your GPA is %.2f%n", rounded);
    }

    private double getGradePointAverage(Map<Module, Grade> modules) {
        double totalGradePoints = 0;
        double totalModuleWeight = 0;

        for (Map.Entry<Module, Grade> entry: modules.entrySet()) {
            Module module = entry.getKey();
            Grade grade = entry.getValue();

            totalGradePoints += (grade.getValue() * module.getWeight());
            totalModuleWeight += (module.getWeight());
        }

        return totalGradePoints / totalModuleWeight;
    }
}
