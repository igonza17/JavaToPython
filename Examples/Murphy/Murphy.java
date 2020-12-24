public class Murphy {
    public static void main(String[] args) {
        System.out.println(Math.min(100, 13));
        System.out.println(Math.max(100, 13));

        boolean isBaseballFun = true;
        boolean isSchoolFun = false;
        System.out.println(isBaseballFun);
        System.out.println(isSchoolFun);

        int i = 2;

        if (i == 2) {
            System.out.println(Math.max(100, 13));
        } else if (i != 2) {
            System.out.println(Math.min(100, 13));
        } else {
            System.out.println("Something went wrong");
        }
    }
}
