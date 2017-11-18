class TestPacked {

public static long set(long packed, int position, boolean value){
if (value){packed = packed | (1L<<position);}
else {packed = packed & ~(1L<<position);}
return packed;
}

public static void main (String[] args) {

long input = Long.parseLong(args[0]);
System.out.println("Input in binary is "+Long.toString(input,2));
int bit = Integer.parseInt(args[1]);
boolean bitSet;
if (Integer.parseInt(args[2])==1) {bitSet=true;} else {bitSet = false;}
System.out.println("After setting bit "+bit+" to "+bitSet+" result is "+Long.toString((set(input, bit, bitSet)),2));
}


}