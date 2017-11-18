




public class FibonacciCache {
//TODO: Test your program with values other than 20 as given here

public static long[] fib = new long[0];


public static void store() {

if (fib.length == 1) {fib[0] = 1; return;}

if (fib.length > 1) {fib[0] = 1; fib[1] = 1;

for (int i=2; i<fib.length; i++) fib[i] = fib[i-1] + fib[i-2] ; return;}
}


public static void main(String[] args) {

store();

for (int i=0; i<fib.length; i++) {
System.out.println(fib[i]);}
System.out.println(get(-1));
}


public static void reset() {

for (int i=0; i<fib.length; i++) fib[i] = 0;

return;
}



public static long get(int i) {

if (i<0 | i>= fib.length) {return -1;} else {return fib[i];}


//TODO: return the value of the element in fib found at index i
// e.g. "get(3)" should return the fourth element of fib
//
//Note: your code should check that i is within the bounds of fib
// and if it is outside this range return the literal "-1L"
}



}
