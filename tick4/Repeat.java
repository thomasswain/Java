public class Repeat {
public static void main(String[] args) {
System.out.println(parseAndRep(args));
}

public static String parseAndRep(String[] args) {

String inputString = args[0];
String outputString = inputString;

try{
int times = Integer.parseInt(args[1]);
for (int i=1; i<times; i++)
outputString = outputString + " " + inputString;}

catch (NumberFormatException error) { 
return ("Error: second argument is not a positive integer");}

catch (ArrayIndexOutOfBoundsException error) {
return ("Error: insufficient arguments");}

return outputString;

}
}
