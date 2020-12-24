public class Example4 {
    public static void main(String[] args) {
        int[] intArray = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};

        for (int h = 0; h < 6; h++) {
            System.out.println(intArray[h]);
            if (h==2){
                continue;
            }
        }

        for (int i = 0; i < 3; i++) {
            System.out.println(cars[i]);
            if (i==1){
                break;
            }
        }

        try {
            System.out.println("Example 2 - The try statement");
        }
        finally
        {
            System.out.println("Example 3 - The finally statement");
        }

    }
}