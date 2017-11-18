package computerscience.java.tick2star; 


public class PackedLong {

public static boolean get(long packed, int position) {
long check = (packed>>position)%2 ;
return (check == 1);
}

public static long set(long packed, int position, boolean value) {
if (value) { 
	if ((packed>>position)%2==1) {packed = packed;} 
	else { long result = 1; for (int i=1; i<=position; i++){ result *= 2;} packed = packed + result   ;};}
else {
	if ((packed>>position)%2==0) {packed = packed;} 

	else { long result = 1; for (int i=1; i<=position; i++){ result *= 2;} packed = packed - result   ;};}
return packed;
}

}
