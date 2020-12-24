public class Example2 {
    public static void main(String[] args) {
        int a = 5;
        int b = 6;

        if (a > b) {
            System.out.println("A is greater than B");
        } else {
            System.out.println("B is greater than A");
        }

        if (a == 6) {
            System.out.println("A is Equal to 6");
        } else if (a == 5) {
            System.out.println("A is Equal to 5");
        }

        for (int i = 0; i < 10; i = i + 2) {
            System.out.println(i);
        }
    }
}