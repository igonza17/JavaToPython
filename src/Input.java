import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Integer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Created by Marcus, work included: reading the input file and organizing the input into easy to translate words and assigning types to commands
//Modifications from David, work included: assigning types to variables, classes, functions and overall improvements to read variables, classes, functions

public class Input {
    public static int Nest=0;
    static String inputF = "";
    static boolean inClass = false;
    static ArrayList<Integer> classNestIn = new ArrayList<Integer>();
    static ArrayList<String> classIn=new ArrayList<String>();
    static boolean inMethod = false;
    static boolean method = false;
    static ArrayList<Integer> methodNestIn = new ArrayList<Integer>();
    static ArrayList<Word> Words=new ArrayList<Word>();
    static ArrayList<String> varKey=new ArrayList<String>();
    static ArrayList<String> funcKey=new ArrayList<String>();
    static ArrayList<String> classKey=new ArrayList<String>();

    static ArrayList<Word> Read(String InputFile) {

        int split = 0;
        Scanner input = null;
        try {
            input = new Scanner((new File(InputFile)));
            String regex = "[a-zA-Z0-9_]*.java";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(InputFile);
            int start = 0;
          while (matcher.find())
          {
           start = matcher.start();
          }
            InputFile = InputFile.substring(start);
            split = InputFile.indexOf(".");
            inputF = InputFile.substring(0, split);

           // System.out.println("INPUt: " + inputF);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<String> WordList=new ArrayList<>();
        String Word = "";
        String PrevChar="";
        String PrevNonWhiteSpace="";
        int Quote=0;

        while (input.hasNextLine()) {
            String CurrentLine=input.nextLine();
            CurrentLine=CurrentLine.trim();

            if(CurrentLine.equals("")){
                continue;
            }


            for (int i=0;i<CurrentLine.length();i++){
                String CurrentChar = Character.toString(CurrentLine.charAt(i));
                if (i>0){
                    PrevChar = Character.toString(CurrentLine.charAt(i-1));

                }


                if(CurrentChar.equals("\"") && !PrevChar.equals("\\")){
                    if (Quote==1){
                        Quote=0;
                    }
                    else {
                        Quote++;
                    }
                }

                if (CurrentChar.equals(";") && Quote==0){
                    Word = Word + CurrentChar;
                    WordList.add(Word);
                    Word = "";
                    continue;
                }

                if (CurrentChar.equals("{") && Quote==0 && !PrevNonWhiteSpace.equals("]")){
                    Word = Word + CurrentChar;
                    WordList.add(Word);
                    Word = "";
                    continue;
                }

                if (CurrentChar.equals("}") && Quote==0){
                    Word = Word + CurrentChar;
                    WordList.add(Word);
                    Word = "";
                    continue;
                }

                Word = Word + CurrentChar;

                if(!CurrentChar.equals(" ")){
                    PrevNonWhiteSpace=CurrentChar;
                }
            }
        }

        for (int i=0;i<WordList.size();i++){
            String CurrentLine=WordList.get(i);
            CurrentLine=CurrentLine.trim();

            if(CurrentLine.equals("")){
                continue;
            }

            ArrayList<String> CharList=new ArrayList<>();

            for (int j=0;j<CurrentLine.length();j++){
                CharList.add(Character.toString(CurrentLine.charAt(j)));
            }


            String Type = KeywordType(CurrentLine);



            if(Type.length() != 0)
            {

            StringBuilder str = new StringBuilder(CurrentLine);


            if(Type == "function")
            {
              modifyFunction(str);
            }
            else if (Type == "variable" )
            {

              modifyVariable(str);
            }
            else if(method)
            {
              modifyMethod(str);
              method = false;
            }


            Words.add(new Word(str.toString(),Type,Nest,CharList));
            NestCheck(CharList);

            if(inMethod)
            {
              if(methodNestIn.get(methodNestIn.size() - 1) == Nest)
              {
                methodNestIn.remove(methodNestIn.size() - 1);
                if(methodNestIn.size() > 0)
                {

                }
                else
                {
                  inMethod = false;
                }
              }
            }
            else if(inClass)
            {
              if(classNestIn.get(classNestIn.size() - 1) == Nest)
              {
                classNestIn.remove(classNestIn.size() - 1);
                if(classNestIn.size() > 0)
                {

                }
                else
                {
                  inClass = false;
                }
              }
            }

            //CharList.clear();
          }
        }

        return Words;
    }

    static String KeywordType(String Word){
        String KeywordType;
        String[] Keywords=new String[]{
                "System.out.print.+","if.+","\\{","\\}",
                "for.+","float.+","String\\[\\].+","int\\[\\].+",
                "while.+",
                "try.+", "catch.+", "break.+", "continue.+",
                "return.+", "this.+",
                "finally.+","else if.+","else.+",
                "Math.min.+","Math.max.+","Math.sqrt.+", ";", "public static void main.+",
        };

        String[] KeywordsClass=new String[]{
              "public class.+", "protected class.+",
              "private class.+","class.+"};

        String[] KeywordsFunc  = new String[]{
              "public int \\w+\\(.+", "protected int \\w+\\(.+",
              "private int \\w+\\(.+", "int \\w+\\(.+",
              "public String \\w+\\(.+", "protected String \\w+\\(.+",
              "private String \\w+\\(.+", "String \\w+\\(.+",
              "public double \\w+\\(.+" , "protected double \\w+\\(.+" ,
              "private double \\w+\\(.+" ,"double \\w+\\(.+",
              "public boolean \\w+\\(.+", "protected boolean \\w+\\(.+",
              "private boolean \\w+\\(.+", "boolean \\w+\\(.+",
              "public void \\w+\\(.+", "protected void \\w+\\(.+",
              "private void \\w+\\(.+", "void \\w+\\(.+",
              };
        String[] KeywordsVar  = new String[]{
        		    "int.+", "static int.+", "public int.+", "private int.+", "public static int.+", "private static int.+", "protected int.+",
                "String.+", "static String.+", "public String.+", "private String.+", "public static String.+", "private static String.+", "protected String.+",
                "double.+", "static double.+", "public double.+", "private double.+", "public static double.+", "private static double.+", "protected double.+",
                "Double.+", "static Double.+", "public Double.+", "private Double.+", "public static Double.+", "private static Double.+", "protected Double.+",
                "boolean.+", "static boolean.+", "public boolean.+", "private boolean.+", "public static boolean.+", "private static boolean.+", "protected boolean.+",
                "Boolean.+", "static Boolean.+", "public Boolean.+", "private Boolean.+", "public static Boolean.+", "private static Boolean.+", "protected Boolean.+",
              };



        for (int i=0;i<Keywords.length;i++){
            if(Word.matches(Keywords[i])){
                KeywordType=Keywords[i];
                if (Keywords[i].equals("public static void main.+")){
                    Nest--;
                }
                return KeywordType;
            }
        }

        for (int k=0;k<KeywordsFunc.length; k++)
        {
          if(Word.matches(KeywordsFunc[k])){
              addFunction(Word, funcKey);
              KeywordType=KeywordsFunc[k];
              return KeywordType;
            }

        }

      for (int j=0;j<KeywordsVar.length; j++)
      {
        if(Word.matches(KeywordsVar[j]))
        {
            KeywordType=KeywordsVar[j];
            addVariable(Word, varKey);
            return KeywordType;
          }

      }

      for (int l=0;l<KeywordsClass.length; l++)
      {
        if(Word.matches(KeywordsClass[l]))
        {
            addClass(Word, classKey);
            if(inClass)
            {
              KeywordType=KeywordsClass[l];
              return KeywordType;
            }
            else
            {
              return "";
            }
        }

      }

      if(checkFunc(Word))
      {
        KeywordType = "function";
        return KeywordType;
      }
      else if(checkVar(Word))
      {
        KeywordType = "variable";
        return KeywordType;
      }
      else if(checkClass(Word))
      {
        KeywordType = "class";
        return KeywordType;
      }

        return "NULL";
    }

    static void NestCheck(ArrayList<String> CharList){



        for (int i=0;i<CharList.size();i++){
            String Prev2Char="";

            if (i>1){
                Prev2Char=CharList.get(i-2);
                Prev2Char = Prev2Char + CharList.get(i-1);
            }

            String CurrentChar = CharList.get(i);

            if (CurrentChar.equals("{") && !Prev2Char.equals("\\\\")){
                Nest++;
            }

            if (CurrentChar.equals("}") && !Prev2Char.equals("\\\\")){
                if (Nest>0)
                Nest--;
            }
        }

    }

    static void addVariable(String word, ArrayList<String> var)
  {
    String wordCopy = word;
    String access = "";
    String name = "";

    if (wordCopy.startsWith("protected"))
    {
      access = " _";
      wordCopy=wordCopy.replaceFirst("protected \\w+ ","");
      wordCopy = wordCopy.substring(0, wordCopy.length()-1);
      name = wordCopy.split(" ")[0];
    }
    else if (wordCopy.startsWith("private"))
    {
      access = " __";
      wordCopy=wordCopy.replaceFirst("private \\w+ ","");
      wordCopy = wordCopy.substring(0, wordCopy.length()-1);
      name = wordCopy.split(" ")[0];
    }
    else if (wordCopy.startsWith("public"))
    {
      wordCopy=wordCopy.replaceFirst("public \\w+ ","");
      wordCopy = wordCopy.substring(0, wordCopy.length()-1);
      name = wordCopy.split(" ")[0];
    }
    else
    {
      wordCopy = wordCopy.replaceFirst("\\w+ ","");
      wordCopy = wordCopy.substring(0, wordCopy.length()-1);
      name = wordCopy.split(" ")[0];
    }

      name = name + access;
      var.add(name);
  }

  static void addFunction(String word, ArrayList<String> func)
  {
    String wordCopy = word;
    String access = "";
    String name = "";
    int split = 0;

    if (wordCopy.startsWith("protected"))
    {
      access = " _";
      wordCopy=wordCopy.replaceFirst("protected \\w+ ", "");
      split = wordCopy.indexOf("(");
      name = wordCopy.substring(0, split+1);
    }
    else if (wordCopy.startsWith("private"))
    {
      access = " __";
      wordCopy=wordCopy.replaceFirst("private \\w+ ","");
      split = wordCopy.indexOf("(");
      name = wordCopy.substring(0, split+1);
    }
    else if (wordCopy.startsWith("public"))
    {
      wordCopy=wordCopy.replaceFirst("public \\w+ ","");
      split = wordCopy.indexOf("(");
      name = wordCopy.substring(0, split+1);
    }
    else
    {
      split = wordCopy.indexOf("(");
      name = wordCopy.substring(0, split+1);

    }

    if(inClass)
    {
      method = true;
      inMethod = true;
      methodNestIn.add(Nest);
    }

    name = name + access;

    func.add(name);
  }

  static void addClass(String word, ArrayList<String> cla)
  {
    String wordCopy = word;
    String access = "";
    String name = "";
    int split = 0;

    if (wordCopy.startsWith("protected"))
    {
      access = " _";
      wordCopy=wordCopy.replaceFirst("protected class ", "");
      split = wordCopy.indexOf("{");
      name = wordCopy.substring(0, split);
    }
    else if (wordCopy.startsWith("private"))
    {
      access = " __";
      wordCopy=wordCopy.replaceFirst("private class ","");
      split = wordCopy.indexOf("{");
      name = wordCopy.substring(0, split);
    }
    else if (wordCopy.startsWith("public"))
    {
      wordCopy=wordCopy.replaceFirst("public class ","");
      split = wordCopy.indexOf("{");
      name = wordCopy.substring(0, split);
    }
    else
    {
      wordCopy=wordCopy.replaceFirst("class ","");
      split = wordCopy.indexOf("{");
      name = wordCopy.substring(0, split);

    }

    //name = name + access;
    if(!inputF.startsWith(name))
    {
      classNestIn.add(Nest);
      //classIn.add(name);
      inClass = true;
      cla.add(name);
    }


  }

  static boolean checkFunc(String word)
  {
    for(int i=0; i<funcKey.size(); i++)
    {
      if(word.startsWith(funcKey.get(i).split(" ")[0]))
      {
        Collections.swap(funcKey, i, funcKey.size()-1);
        return true;
      }
    }

    return false;
  }

  static boolean checkVar(String word)
  {
    for(int i=0; i<varKey.size(); i++)
    {
      if(word.startsWith(varKey.get(i).split(" ")[0]))
      {
        Collections.swap(varKey, i, varKey.size()-1);
        return true;
      }
    }

    return false;
  }

  static boolean checkClass(String word)
  {
    for(int i=0; i<classKey.size(); i++)
    {
      if(word.startsWith(classKey.get(i)))
      {
        return true;
      }
    }

    return false;
  }

  static void modifyVariable(StringBuilder str)
  {
    String s = varKey.get(varKey.size()-1);
    String [] sArr = s.split(" ");
    if(sArr.length>1)
    {
      String fS = sArr[1] + sArr[0];
      int split = str.toString().indexOf("=");
      str = str.replace(0, split, fS);
    }
  }

  static void modifyFunction(StringBuilder str)
  {
    String s = funcKey.get(funcKey.size()-1);
    int split = str.toString().indexOf("(");
    String [] sArr = s.split(" ");
    if(sArr.length>1)
    {
      String fS = sArr[1] + sArr[0];
      str = str.replace(0, split, fS);
    }

  }

  static void modifyMethod(StringBuilder str)
  {
      int split = 0;
      split = str.toString().indexOf("(");
      str = str.insert(split+1, "self, ");
  }




}
