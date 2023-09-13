import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Module, Grade> modules = new HashMap<>();

        for (Module module : Module.values()) {
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

    public enum Module {
        WEB_SITE_DEV("Web Site Development", 20),
        DATABASE_APPLICATIONS("Database Applications", 20),
        PROFESSIONAL_COMPUTING("Professional Computing Practice", 10),
        BUSINESS_INTELLIGENCE("Business Intelligence", 20),
        ADVANCED_MOBILE("Advanced Programming for Mobile Devices", 20),
        INFO_SEC("Information Security Management", 20),
        RESEARCH_METHODS("Research Methods in Computing", 10);

        private final String name;
        private final double value;

        Module(String name, int credits) {
            this.name = name;

            this.value = (double) credits / 20;
        }

        public String getName() {
            return name;
        }

        public double getValue() {
            return value;
        }
    }

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
}
