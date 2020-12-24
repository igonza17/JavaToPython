public class Ivan {
    public static void main(String[] args) {
        System.out.println("Forloop function test");
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
        }

        System.out.println("Try and Catch function test");
        try {
            int[] myNumbers = {1, 2, 3};
            System.out.println(myNumbers[10]);
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }

        System.out.println("Break function test");
        for (int i = 0; i < 10; i++) {
            if (i == 4) {
                break;
            }
            System.out.println(i);
        }

        System.out.println("Continue function test");
        for (int i = 0; i < 10; i++) {
            if (i == 4) {
                continue;
            }
            System.out.println(i);
        }


    }
}