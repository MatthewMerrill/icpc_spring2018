import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Lyft {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Map<String, Integer> elevators = new HashMap<>();
        elevators.put("A", 0);
        elevators.put("B", 0);

        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            String instruction = in.next();
            String target = in.next();

            int parameter;
            if (instruction.charAt(3) == 'I') {
                parameter = in.nextInt();
            } else {
                parameter = elevators.get(in.next());
            }

            int oldValue = elevators.get(target);
            instruction = instruction.substring(0, 3);
            switch (instruction) {
                case "set":
                    elevators.put(target, wrap(parameter));
                    break;
                case "add":
                    elevators.put(target, wrap(oldValue + parameter));
                    break;
                case "sub":
                    elevators.put(target, wrap(oldValue - parameter));
                    break;
                case "mul":
                    elevators.put(target, wrap(oldValue * parameter));
                    break;
            }
        }

        int a = elevators.get("A");
        int b = elevators.get("B");
        for (int floor = 4; floor >= 0; floor--) {
            System.out.print(a == floor? '#' : '|');
            System.out.print(' ');
            System.out.print(b == floor? '#' : '|');
            System.out.println();
        }
    }

    private static Integer wrap(int i) {
        return Math.floorMod(i, 5);
    }
}
