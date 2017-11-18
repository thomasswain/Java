package computerscience.java.examples;

class PrimitiveIntLeftShift {
public static void main(String[] args) {
int i;
//create a variable called i
i = 0x55<<7;
//update i with the value 55 in hexadecimal and use left shift 7 to get result 10880.
//Note, the arrows don't shift the hexadecimal digits - the number is converted to binary and then the shift acts on that.


System.out.println(i); //print out the current value of i
}
}

