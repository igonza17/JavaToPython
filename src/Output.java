import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Framework modified by everyone
//Specific modules are Commented

public class Output {
    static ArrayList<String> Variables = new ArrayList<>();
    static int counter=0;
    static ArrayList<Word> WordsCopy=new ArrayList<Word>();

    static void Translate(ArrayList<Word> Words) throws IOException{


        BufferedWriter output = new BufferedWriter(new FileWriter("Output.py"));

        String Out="";


        for (int i=0;i<Words.size();i++) {
            WordsCopy.add(new Word((Words.get(i).RawInput),(Words.get(i).Type),(Words.get(i).Nest),(Words.get(i).CharList)));
        }


        while (counter<Words.size()){
            switch (Words.get(counter).Type){
              case "function":
                  Out=functionCommand(Words.get(counter).RawInput, Words.get(counter).Nest);
                  break;
              case "variable":
                Out=variableCommand(Words.get(counter).RawInput, Words.get(counter).Nest);
                break;
              case "class":
                Out=classConstructorCommand(Words.get(counter).RawInput, Words.get(counter).Nest);
                break;
                case "System.out.print.+":
                    Out=PrintCommand(Words.get(counter).RawInput,Words.get(counter).Nest,Words.get(counter).CharList);
                    break;
                case "class.+":
                case "public class.+":
                case "protected class.+":
                case "private class.+":
                    Out=ClassCommand(Words.get(counter).RawInput,Words.get(counter).Nest);
                    break;
                case "if.+":
                    Out=ifCommand(Words.get(counter).RawInput,Words.get(counter).Nest,Words.get(counter).CharList);
                    break;
                case "for.+":
                    Out=forCommand(Words.get(counter).RawInput,Words.get(counter).Nest,Words.get(counter).CharList);
                    break;
                case "float.+":
                    Out=floatCommand(Words.get(counter) .RawInput,Words.get(counter).Nest);
                    break;
                case"String\\[\\].+":
                    Out=stringarray(Words.get(counter) .RawInput,Words.get(counter).Nest,Words.get(counter).CharList);
                    break;
                case"int\\[\\].+":
                    Out=numberarray(Words.get(counter) .RawInput,Words.get(counter).Nest,Words.get(counter).CharList);
                    break;
                case "while.+":
                    Out=whileCommand(Words.get(counter).RawInput,Words.get(counter).Nest);
                    break;
                case "void \\w+\\(.+":
                case "public void \\w+\\(.+":
                case "protected void \\w+\\(.+":
                case "private void \\w+\\(.+":
                    Out=voidMethodCommand(Words.get(counter).RawInput,Words.get(counter).Nest);
                    break;
                case "int \\w+\\(.+":
                case "public int \\w+\\(.+":
                case "protected int \\w+\\(.+":
                case "private int \\w+\\(.+":
                    Out=intMethodCommand(Words.get(counter).RawInput,Words.get(counter).Nest);
                    break;
                case "int.+":
                case "static int.+":
                case "public int.+":
                case "private int.+":
                case "public static int.+":
                case "private static int.+":
                case "protected int.+":
                    Out=intCommand(Words.get(counter).RawInput,Words.get(counter).Nest,Words.get(counter).CharList);
                    break;
                case "String \\w+\\(.+":
                case "public String \\w+\\(.+":
                case "protected String \\w+\\(.+":
                case "private String \\w+\\(.+":
                    Out=StringMethodCommand(Words.get(counter).RawInput,Words.get(counter).Nest);
                    break;
                case "String.+":
                case "static String.+":
                case "public String.+":
                case "private String.+":
                case "public static String.+":
                case "private static String.+":
                case "protected String.+":
                	Out=StringCommand(Words.get(counter).RawInput,Words.get(counter).Nest,Words.get(counter).CharList);
                    break;
                case "double \\w+\\(.+":
                case "public double \\w+\\(.+":
                case "protected double \\w+\\(.+":
                case "private double \\w+\\(.+":
                    Out=doubleMethodCommand(Words.get(counter).RawInput,Words.get(counter).Nest);
                    break;
                  //Accounts for multiple variations for initialization of double
                case "double.+":
                case "static double.+":
                case "public double.+":
                case "private double.+":
                case "public static double.+":
                case "private static double.+":
                case "protected double.+":
                //Accounts for multiple variations for initializations of Double
                case "Double.+":
                case "static Double.+":
                case "public Double.+":
                case "private Double.+":
                case "public static Double.+":
                case "private static Double.+":
                case "protected Double.+":
                    Out=doubleCommand(Words.get(counter).RawInput,Words.get(counter).Nest,Words.get(counter).CharList);
                    break;
                case "boolean \\w+\\(.+":
                case "public boolean \\w+\\(.+":
                case "protected boolean \\w+\\(.+":
                case "private boolean \\w+\\(.+":
                    Out=booleanMethodCommand(Words.get(counter).RawInput,Words.get(counter).Nest);
                    break;
                  //Accounts for multiple variations for initializations of boolean
                case "boolean.+":
                case "static boolean.+":
                case "public boolean.+":
                case "private boolean.+":
                case "public static boolean.+":
                case "private static boolean.+":
                case "protected boolean.+":
                //Accounts for multiple variations for initializations of Boolean
                case "Boolean.+":
                case "static Boolean.+":
                case "public Boolean.+":
                case "private Boolean.+":
                case "public static Boolean.+":
                case "private static Boolean.+":
                case "protected Boolean.+":
                    Out=booleanCommand(Words.get(counter).RawInput,Words.get(counter).Nest,Words.get(counter).CharList);
                    break;
                case "return.+":
                    Out=returnCommand(Words.get(counter).RawInput,Words.get(counter).Nest);
                    break;
                case "this.+":
                    Out=thisCommand(Words.get(counter).RawInput,Words.get(counter).Nest);
                    break;
                case "try.+":
                    Out=tryCommand(Words.get(counter).RawInput,Words.get(counter).Nest,Words.get(counter).CharList);
                    break;
                case "catch.+":
                    Out=catchCommand(Words.get(counter).RawInput,Words.get(counter).Nest,Words.get(counter).CharList);
                    break;
                case "break.+":
                    Out=breakCommand(Words.get(counter).RawInput,Words.get(counter).Nest,Words.get(counter).CharList);
                    break;
                case "continue.+":
                    Out=continueCommand(Words.get(counter).RawInput,Words.get(counter).Nest,Words.get(counter).CharList);
                    break;
                case "finally.+":
                    Out=finallyCommand(Words.get(counter).RawInput,Words.get(counter).Nest,Words.get(counter).CharList);
                    break;
                case"else.+":
                    Out=elseCommand(Words.get(counter).RawInput,Words.get(counter).Nest,Words.get(counter).CharList);
                    break;
                case"else if.+":
                    Out=elseifCommand(Words.get(counter).RawInput,Words.get(counter).Nest,Words.get(counter).CharList);
                    break;
                case "Math.min.+":
                    Out=minCommand(Words.get(counter).RawInput,Words.get(counter).Nest,Words.get(counter).CharList);
                    break;
                case "Math.max.+":
                    Out=maxCommand(Words.get(counter).RawInput,Words.get(counter).Nest,Words.get(counter).CharList);
                    break;
                case "Math.sqrt.+":
                    Out=sqrtCommand(Words.get(counter).RawInput,Words.get(counter).Nest,Words.get(counter).CharList);
                    break;
                case "public static void main.+":
                case ";":
                case "\\{":
                case "\\}":
                    Out="";
                    break;
            }

            Out = DataInterpreter(Out);
            output.append(Out);
            counter++;
        }

        output.close();

        Variables.clear();
        counter=0;
        WordsCopy.clear();

    }

    //Created by Marcus
    //Indents the translated word
    static String NestInsert(String Out, int Nest){
        for(int i=0;i<Nest;i++){
            Out=Out+"\t";
        }

        return Out;
    }

    //Created by Marcus
    //Extracts the String between parenthesis and outside quotation marks
    static String BetweenPara(ArrayList<String> CharList){
        String Word = "";

        int Para=0;
        int Quote=0;
        String PrevChar="";

        for (int i=0;i<CharList.size();i++){
            String CurrentChar = CharList.get(i);
            if (i>0){
                PrevChar = CharList.get(i-1);
            }

            if (CurrentChar.equals(")") && Quote==0){
                Para--;
            }

            if(CurrentChar.equals("\"") && !PrevChar.equals("\\")){
                if (Quote==1){
                    Quote=0;
                }
                else {
                    Quote++;
                }
            }

            if(Para>0){
                Word = Word + CurrentChar;
            }

            if (CurrentChar.equals("(")&& Quote==0){
                Para++;
            }
        }

        return Word;
    }

    //Created by Marcus
    //Additions from Murphy
    //Used to replace data used in for, if, print, etc
    static String DataInterpreter(String Word){
        //Replaces target string outside of " "
        Word = Word.replaceAll("(?=(([^\"]*\"){2})*[^\"]*$)Math.max", "max");
        Word = Word.replaceAll("(?=(([^\"]*\"){2})*[^\"]*$)Math.sqrt", "math.sqrt");
        Word = Word.replaceAll("(?=(([^\"]*\"){2})*[^\"]*$)Math.min", "min");

        return Word;
    }

    //Created by Marcus
    static String PrintCommand(String Word, int Nest,ArrayList<String> CharList){
        //Modification that allows translation of println and print
        String Mod1="";

        if (!Word.contains("System.out.println")){
            Mod1=",end = ''";
        }

        Word=BetweenPara(CharList);

        String Out="";

        Out=NestInsert(Out,Nest);

        Out=Out+"print("+Word+Mod1+")\n";

        //Replaces all + outside of " " with ,
        Out = Out.replaceAll("(?=(([^\"]*\"){2})*[^\"]*$)\\+", ",");

        return Out;
    }

    //Created by Marcus
    static String ifCommand(String Word, int Nest,ArrayList<String> CharList){
        Word=BetweenPara(CharList);

        String Out="";

        Out=NestInsert(Out,Nest);

        Out=Out+"if "+Word+":\n";

        return Out;
    }

    //Created by David
    static String ClassCommand(String Word, int Nest){
      String wordCopy = Word;
      String access = "";
      String name = "";
      int split = 0;

        if (wordCopy.startsWith("protected"))
          {
            access = "_";
            wordCopy=wordCopy.replaceFirst("protected class ", "");
            split = wordCopy.indexOf("{");
            name = wordCopy.substring(0, split);
          }
          else if (wordCopy.startsWith("private"))
          {
            access = "__";
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

          name = access + name;
          Word = "class " + name + "{";

        Word=Word.replaceFirst("\\{","");

        String Out="";

        Out=NestInsert(Out,Nest);

        Out=Out+Word+":\n";

        return Out;


    }


    //Created by David
    static String classConstructorCommand(String Word, int Nest){
        Word=Word.replaceFirst("\\{","");

        int split = 0;

        Word=Word.replaceAll("String", "");
        Word=Word.replaceAll("int", "");
        Word=Word.replaceAll("boolean", "");
        Word=Word.replaceAll("double", "");


        split = Word.indexOf("(");
        Word = Word.substring(split+1);
        Word = "def __init__(self, " + Word;

        String Out="";

        Out=NestInsert(Out,Nest);

        Out=Out+Word+":\n";

        return Out;

    }

    //Created by David
    static String whileCommand(String Word, int Nest){
        Word=Word.substring(Word.indexOf("(")+1);
        Word=Word.replaceFirst("\\) ","");
        Word=Word.replaceFirst("\\)\\{","");

        String Out="";

        Out=NestInsert(Out,Nest);

        Out=Out+"while "+Word+":\n";

        return Out;
    }

    //Created by David
    static String voidMethodCommand(String Word, int Nest){
      //Word=Word.substring(Word.indexOf("(")+1);
      Word=Word.replaceFirst("\\{","");
      Word=Word.replaceFirst("void ","def ");
      Word=Word.replaceAll("String", "");
      Word=Word.replaceAll("int", "");
      Word=Word.replaceAll("boolean", "");
      Word=Word.replaceAll("double", "");

      String Out="";

      Out=NestInsert(Out,Nest);

      Out=Out+Word+":\n";

      return Out;
    }

    //Created by David
    static String intMethodCommand(String Word, int Nest){
      Word=Word.replaceFirst("\\{","");
      Word=Word.replaceFirst("int ","def ");
      Word=Word.replaceAll("String", "");
      Word=Word.replaceAll("int", "");
      Word=Word.replaceAll("boolean", "");
      Word=Word.replaceAll("double", "");


      String Out="";

      Out=NestInsert(Out,Nest);

      Out=Out+Word+":\n";

      return Out;
    }

    //Created by David
    static String StringMethodCommand(String Word, int Nest){

      Word=Word.replaceFirst("\\{","");
      Word=Word.replaceFirst("String ","def ");
      Word=Word.replaceAll("String", "");
      Word=Word.replaceAll("int", "");
      Word=Word.replaceAll("boolean", "");
      Word=Word.replaceAll("double", "");

      String Out="";

      Out=NestInsert(Out,Nest);

      Out=Out+Word+":\n";

      return Out;
    }

    //Created by David
    static String doubleMethodCommand(String Word, int Nest){
      Word=Word.replaceFirst("\\{","");
      Word=Word.replaceFirst("double ","def ");
      Word=Word.replaceAll("String", "");
      Word=Word.replaceAll("int", "");
      Word=Word.replaceAll("boolean", "");
      Word=Word.replaceAll("double", "");

      String Out="";

      Out=NestInsert(Out,Nest);

      Out=Out+Word+":\n";

      return Out;
    }

    //Created by David
    static String booleanMethodCommand(String Word, int Nest){

      Word=Word.replaceFirst("\\{","");
      Word=Word.replaceFirst("boolean ","def ");
      Word=Word.replaceAll("String", "");
      Word=Word.replaceAll("int", "");
      Word=Word.replaceAll("boolean", "");
      Word=Word.replaceAll("double", "");

      String Out="";

      Out=NestInsert(Out,Nest);

      Out=Out+Word+":\n";

      return Out;
    }

    //Created by David
    static String variableCommand(String Word, int Nest){
    Word=Word.replaceFirst(";","");

    String Out="";

    Out=NestInsert(Out,Nest);

    Out=Out+Word+"\n";

    return Out;
    }

    //Created by David
    static String functionCommand(String Word, int Nest){

       Word=Word.replaceFirst(";","");
       Word=Word.replaceAll("String", "");
       Word=Word.replaceAll("int", "");
       Word=Word.replaceAll("boolean", "");
       Word=Word.replaceAll("double", "");


      String Out="";

      Out=NestInsert(Out,Nest);

      Out=Out+Word+"\n";

     return Out;
    }

    //Created by David
    static String returnCommand(String Word, int Nest){

        String Out="";

        Out=NestInsert(Out,Nest);

        Out=Out+Word+"\n";

        return Out;
    }

    //Created by David
    static String thisCommand(String Word, int Nest){
        //Word=Word.substring(Word.indexOf("(")+1);
        Word=Word.replaceFirst("this","self");
        //Word=Word.replaceFirst("\\)\\{","");

        String Out="";

        Out=NestInsert(Out,Nest);

        Out=Out+Word+"\n";

        return Out;
    }

    //Created by Ivan
    // Modified to accept start and end range
    // *example range(1,5)
    // Added support if iterators increase not by one but by 2 or more
    // Fixed for loop showing proper results when using >= or <=
    static String forCommand(String Word, int Nest,ArrayList<String> CharList){
        //Get all lines until first bracket is reached
        int bracketcheck=0;
        ArrayList<String> NextCharList;
        while(bracketcheck==0){
            counter++;
            NextCharList=WordsCopy.get(counter).CharList;
            if (NextCharList.contains("{")){
                bracketcheck=1;
            }
            CharList.add(" ");
            CharList.addAll(NextCharList);
        }
        //Makes string with everything after the word For keyword
        Word=BetweenPara(CharList);
        String Out="";
        Out=NestInsert(Out,Nest);

        //Check if range is exclusive or inclusive by finding ('>','<','<=','>=') operators with regex
        String word = Word;
        ArrayList<String> ar = new ArrayList<String>(3);
        String[] list = word.split("(?![=<>]).");

        for (int i = 0; i < list.length; i++) {
            ar.add(list[i]);
        }
        ar.removeAll(Arrays.asList("",null));
        String operator = ar.get(1);

        //Check if iterator is increasing more than one
        String word2 = Word;
        String lastChar = word2.substring(word2.length()-1);
        boolean isDigit = lastChar.matches("//d{1}");
        String it = "";
        if(isDigit == false){
            it = lastChar;
        }

        //Extracting the number at which the iterator must stop
        ArrayList<String> arr = new ArrayList<String>(2);
        String[] words = Word.split("(?!\\d+).");

        for (int i = 0; i < words.length; i++) {
            arr.add(words[i]);
        }
        arr.removeAll(Arrays.asList("",null));

        //Extracting the name used for the iterator
        ArrayList<String> arr2 = new ArrayList<String>(2);
        String[] words2 = Word.split("=[\\s\\S]*$");
        for (int i = 0; i < words2.length; i++) {
            arr2.add(words2[i]);
        }

        //Key variables needed for the python for loop
        String startRange = arr.get(0);//Number in which iterator must start at
        String endRange = arr.get(1);//Number in which iterator must stop
        String var = arr2.get(0);//Name used for the variable

        //Remove any remaining data types
        var = var.replace("int ","");
        var = var.replace("double ","");
        var = var.replace("float ","");

        //Check for "less than or greater than" or "less than equal to or greater than equal to"
        if (operator.contains(">=")  || operator.contains("<=")){
            String plusOne = "1";

            //Check for iterator increase by 2 or more
            if(!lastChar.contains("+")){
                //Final string that gets returned
                Out=Out+"for "+ var + " in range("+ startRange + ',' + endRange + "+" + plusOne + ',' + it + "):\n";
                return Out;
            }
            //Final string that gets returned
            Out=Out+"for "+ var + " in range("+ startRange + ',' + endRange + "+" + plusOne + "):\n";
            return Out;
        }

        //Check for iterator increase by 2 or more
        if(!lastChar.contains("+")){
            //Final string that gets returned
            Out=Out+"for "+ var + " in range("+ startRange + ',' + endRange + ',' + it + "):\n";
            return Out;
        }

        //Final string that gets returned
        Out=Out+"for "+ var + " in range("+ startRange + ',' + endRange + "):\n";
        return Out;
    }

    //Created by Ivan
    static String tryCommand(String Word, int Nest,ArrayList<String> CharList){
        //Pretty much this function only adds colon to the end of the keyword "try:"
        Word=BetweenPara(CharList);
        String Out="";
        Out=NestInsert(Out,Nest);
        Out=Out+"try"+":\n";
        return Out;
    }
    //Created by Ivan
    static String catchCommand(String Word, int Nest,ArrayList<String> CharList){
        //This function adds colon to the end of the keyword "except:"
        Word=BetweenPara(CharList);
        //If the catch has a specific exception type than we must specify example "except ArithmeticError:"
        //Only added 3 types of exceptions but there are many more
        if(Word.contains("ArithmeticException")){
            String Out="";
            Out=NestInsert(Out,Nest);

            Out=Out+"except ArithmeticError"+":\n";
            return Out;
        }
        else if(Word.contains("Exception")){
            String Out="";
            Out=NestInsert(Out,Nest);

            Out=Out+"except Exception"+":\n";
            return Out;

        }
        else if(Word.contains("ArrayIndexOutOfBoundsException")){
            String Out="";
            Out=NestInsert(Out,Nest);

            Out=Out+"except IndexError"+":\n";
            return Out;
        }
        //If no type of exception specified than we just output "except:"
        else {
            String Out = "";
            Out = NestInsert(Out, Nest);

            Out = Out + "except" + ":\n";
            return Out;
        }
    }
    //Created by Ivan
    static String breakCommand(String Word, int Nest,ArrayList<String> CharList){
        //Pretty much this function only removes ";" after "break;"
        Word=BetweenPara(CharList);
        String Out="";
        Out=NestInsert(Out,Nest);
        Out=Out+"break"+"\n";
        return Out;
    }
    //Created by Ivan
    static String continueCommand(String Word, int Nest,ArrayList<String> CharList){
        //Pretty much this function only removes ";" after "continue;"
        Word=BetweenPara(CharList);
        String Out="";
        Out=NestInsert(Out,Nest);
        Out=Out+"continue"+"\n";
        return Out;
    }

    //created by Murphy
    //Modified by Bernard
    static String booleanCommand(String Word, int Nest,ArrayList<String> CharList){
        Word=Word.substring(Word.indexOf("(")+1);
        Word=Word.replaceFirst("public ","");
    	Word=Word.replaceFirst("private ","");
    	Word=Word.replaceFirst("protected ","");
    	Word=Word.replaceFirst("static ","");
    	Word=Word.replaceFirst("Boolean ","");
        Word=Word.replaceFirst("boolean ","");
        Word=Word.replaceFirst(";","");
        Word=Word.replaceFirst("true","True");
        Word=Word.replaceFirst("false","False");

        String Out="";

        Out=NestInsert(Out,Nest);

        Variables.add(Word);
        Out=Out+""+Word+"\n";

        return Out;
    }
    //created by Murphy
    static String minCommand(String Word, int Nest,ArrayList<String> CharList){
        Word=BetweenPara(CharList);

        String Out="";

        Out=NestInsert(Out,Nest);

        Out=Out+"min("+Word+")\n";

        return Out;
    }
    //Changes made by Murphy
    static String maxCommand(String Word, int Nest,ArrayList<String> CharList){
        Word=BetweenPara(CharList);

        String Out="";

        Out=NestInsert(Out,Nest);

        Out=Out+"max("+Word+")\n";

        return Out;
    }
    //Changes made by Murphy
    static String sqrtCommand(String Word, int Nest,ArrayList<String> CharList){
        Word=BetweenPara(CharList);

        String Out="";

        Out=NestInsert(Out,Nest);

        Out=Out+"math.sqrt("+Word+")\n";

        return Out;
    }

    //Created by Bernard
    //Modified by Ivan
    static String intCommand(String Word, int Nest, ArrayList<String> CharList){

        if (!Word.contains("="))
        {
            Word=Word.replaceFirst("(?=(([^\"]*\"){2})*[^\"]*$);", " = 0;");
        }

        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(Word);
        if(matcher.find() == false){
            Word=Word.replaceFirst("int ","");
            Word=Word.replaceFirst(";"," = None");
            String Out="";
            Out=NestInsert(Out,Nest);
            Variables.add(Word);
            Out=Out+Word+"\n";
            return Out;
        }
        Word=Word.substring(Word.indexOf("(")+1);
    	Word=Word.replaceFirst("public ","");
    	Word=Word.replaceFirst("private ","");
    	Word=Word.replaceFirst("protected ","");
    	Word=Word.replaceFirst("static ","");
    	Word=Word.replaceFirst("int ","");
        Word=Word.replaceFirst(";","");
        String Out="";
        Out=NestInsert(Out,Nest);
        Variables.add(Word);
        Out=Out+Word+"\n";
        return Out;
    }

  //String Command created by Bernard
    static String StringCommand(String Word, int Nest, ArrayList<String> CharList){

        if (!Word.contains("="))
        {
            Word=Word.replaceFirst("(?=(([^\"]*\"){2})*[^\"]*$);", " = 0;");
        }

    	Word=Word.substring(Word.indexOf("(")+1);
    	Word=Word.replaceFirst("public ","");
    	Word=Word.replaceFirst("private ","");
    	Word=Word.replaceFirst("protected ","");
    	Word=Word.replaceFirst("static ","");
    	Word=Word.replaceFirst("String ","");
    	Word=Word.replaceFirst(";","");
        String Out="";
        Out=NestInsert(Out,Nest);
        Variables.add(Word);
        Out=Out+Word+"\n";
        return Out;
    }

  //Double Command created by Bernard
    static String doubleCommand(String Word, int Nest, ArrayList<String> CharList){

        if (!Word.contains("="))
        {
            Word=Word.replaceFirst("(?=(([^\"]*\"){2})*[^\"]*$);", " = 0;");
        }

    	Word=Word.substring(Word.indexOf("(")+1);
    	Word=Word.replaceFirst("public ","");
    	Word=Word.replaceFirst("private ","");
    	Word=Word.replaceFirst("protected ","");
    	Word=Word.replaceFirst("static ","");
    	Word=Word.replaceFirst("Double ","");
    	Word=Word.replaceFirst("double ","");
    	Word=Word.replaceFirst(";","");
        String Out="";
        Out=NestInsert(Out,Nest);
        Variables.add(Word);
        Out=Out+Word+"\n";

        return Out;
    }




    //Created by Kwasi
    static String finallyCommand(String Word, int Nest, ArrayList<String> CharList){
        Word=BetweenPara(CharList);
        String Out="";
        Out=NestInsert(Out,Nest);
        Out=Out+"finally"+":\n"+Word;
        return Out;
    }

    //Created by Kwasi
    static String elseifCommand(String Word, int Nest,ArrayList<String> CharList){

        Word=BetweenPara(CharList);
        String Out="";
        Out=NestInsert(Out,Nest);
        Out=Out+"elif "+Word+":\n";
        return Out;
    }

    //Created by Kwasi
    static String elseCommand(String Word, int Nest,ArrayList<String> CharList){

        Word=BetweenPara(CharList);

        String Out="";

        Out=NestInsert(Out,Nest);

        Out=Out+"else"+Word+":\n";

        return Out;
    }

    //Created by Kwasi
    static String floatCommand(String Word, int Nest){

        if (!Word.contains("="))
        {
            Word=Word.replaceFirst("(?=(([^\"]*\"){2})*[^\"]*$);", " = 0;");
        }

        Word=Word.substring(Word.indexOf("(")+1);
        Word=Word.replaceFirst("float ","");
        Word=Word.replaceFirst(";","");
        Word=Word.replaceFirst("f","");

        String Out="";

        Out=NestInsert(Out,Nest);

        Variables.add(Word);

        Out=Out+""+Word+"\n";

        return Out;
    }

    //Created by Kwasi
    static String numberarray(String Word, int Nest,ArrayList<String> CharList){
        int bracketcheck=0;
        ArrayList<String> NextCharList;
        if(Word.contains("{"))
        {
            Word = "";
            while(bracketcheck==0){
                counter++;
                NextCharList=WordsCopy.get(counter).CharList;
                if (NextCharList.contains(";")){
                    bracketcheck=1;
                }
                CharList.add("");
                CharList.addAll(NextCharList);
            }
            for(int i =0; i < CharList.size(); i++){
                Word = Word + CharList.get(i);
                System.out.print(Word);
            }
        }
        Word=Word.substring(Word.indexOf("(")+1);
        Word=Word.replaceFirst("int","");
        Word=Word.replaceFirst("\\{","[");
        Word=Word.replaceFirst("}","]");
        Word=Word.replaceFirst("\\[","");
        Word=Word.replaceFirst("] ","");
        Word=Word.replaceFirst(";","");
        Word=Word.replaceFirst("new int\\[]","");
        //Word = Word.replaceAll("\\[]","");

        String Out="";
        Out=NestInsert(Out,Nest);
        Out=Out+""+Word+"\n";
        return Out;
    }
    //Created by Kwasi
    static String stringarray(String Word, int Nest,ArrayList<String> CharList){
        int bracketcheck=0;
        ArrayList<String> NextCharList;
        if(Word.contains("{")) {
            Word = "";
            while (bracketcheck == 0) {
                counter++;
                NextCharList = WordsCopy.get(counter).CharList;
                if (NextCharList.contains(";")) {
                    bracketcheck = 1;
                }
                CharList.add("");
                CharList.addAll(NextCharList);
            }
            for (int i = 0; i < CharList.size(); i++) {
                Word = Word + CharList.get(i);
                System.out.print(Word);
            }
        }
        Word=Word.substring(Word.indexOf("(")+1);
        Word=Word.replaceFirst("String","");
        Word=Word.replaceFirst("\\{","[");
        Word=Word.replaceFirst("}","]");
        Word=Word.replaceFirst("\\[","");
        Word=Word.replaceFirst("] ","");
        Word=Word.replaceFirst(";","");
        Word=Word.replaceFirst("new","");
        Word=Word.replaceFirst("string","");

        String Out="";
        Out=NestInsert(Out,Nest);
        Out=Out+""+Word+"\n";
        return Out;
    }


}
