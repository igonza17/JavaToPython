public class Kwasi {
    public static void main(String args[]) {
        double n1 = -1.0;
        double n2 = 4.5;
        double n3 = -5.3;
        double largest;


        float a = 2.356f;
        float x = -125.563f;
        float r = -101.23f;
        float w = a + x + r;

        int[] intArray = new int[]{1,2,3,4,5,6,7,8,9,10};
        String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};


        if (n1 >= n2) {


            if (n1 >= n3) {
                largest = n1;
            }

            else {
                largest = n3;
            }
        } else {


            if (n2 >= n3) {
                largest = n2;
            }

            else {
                largest = n3;
            }
        }

        System.out.println("Example 1 - Largest Number: " + largest);

        System.out.println("Example 4 Float addition: "+ w);
        System.out.println("Example 5 & 6 Arrays int and string");


        for (int h = 0; h < 6; h++) {
            System.out.println(intArray[h]);
        }

        for (int i = 0; i < 3; i++) {
            System.out.println(cars[i]);
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