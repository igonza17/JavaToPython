public class Example5{
  public static void main (String [] args) {
    class Student{
      String name;
      int age;

      Student(String name, int age){
        this.name = name;
        this.age = age;
      }

      String getName()
      {
        return name;
      }

      int getAge()
      {
        return age;
      }

      void setName(String name)
      {
        String n = name;
        this.name = n;
      }

      void setAge(int age)
      {
        int a = age;
        this.age = age;
      }


    }

  }
}