enum Day
{
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    public boolean isWeekend() {
        return this == MONDAY || this == SUNDAY;
    }
}
enum Planet {
    MERCURY(3.303e+23, 2.4397e6),
    EARTH  (5.976e+24, 6.37814e6);
    private final double mass;
    private final double radius;
    static final double G = 6.67300E-11;
    Planet(double mass, double radius) {
        this.mass   = mass;
        this.radius = radius;
    }
    double surfaceGravity() { return G * mass / (radius * radius); }
}

class enums {
    public static void main(String[] args) {
        Day today = Day.SATURDAY;
        System.out.println(today + " weekend? " + today.isWeekend()); // SATURDAY weekend?


        for (Day d : Day.values()) {
            if (!d.isWeekend()) System.out.println("Workday: " + d);
        }
        System.out.printf("Earth gravity: %.2f%n", Planet.EARTH.surfaceGravity());
    }
}