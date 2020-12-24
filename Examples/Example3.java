public class Example3 {
    int a = 0;
    static int b = 1;
    public int c = 2;
    private int d = 3;
    public static int e = 4;
    private static int f = 5;
    protected int g = 6;

    public static void main(String[] args) {

        try {
            int[] myNumbers = {1, 2, 3};
            System.out.println(myNumbers[10]);
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }
}