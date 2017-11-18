package computerscience.java.tick2; 


class TinyLifePrintWorld 

{


public static boolean getCell(long world, int col, int row) {
if (row>7 | col>7) { return false ;} else {return (PackedLong.get (world, (8*row + col))) ;} 
}

public static void print(long world) {
System.out.println("-");
for (int row = 0; row < 8; row++) {
for (int col = 0; col < 8; col++) {
System.out.print(getCell(world, col, row) ? "#" : "_");
}
System.out.println();}
}

public static void main(String [] args) {
print(0x20A0600000000000L);
print(setCell(0x20A0600000000000L,0,0,true));
}

public static long setCell(long world, int col, int row, boolean value) {
if (row>7 | col>7) { return world ;} else {return (PackedLong.set (world, (8*row + col), value));} 
}

public static int count(long world, int col, int row) {

int sum=0;
for (int i=row-1; i<=row+1; i++){


for (int j=col-1; j<=col+1; j++){

 if (getCell(world,i,j)) {sum+=sum;} else {sum=sum;};






}



}

return sum;

}


}
