class MakePattern {

public static void main(String[] args) throws Exception {

try{

Pattern p = new Pattern(args[0]);

System.out.print(p.getName());
}

catch (PatternFormatException e) {System.out.print("Error - Too many or too little inputs have been provided.");}

catch (ArrayIndexOutOfBoundsException e) {System.out.print("Error - No input provided.");}

catch (NumberFormatException e) {System.out.print("Error - One or more of the input fields contains non-numerical values.");}

}
}