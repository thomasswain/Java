package computerscience.java.tick2; 


class TinyLifegetCell {


public static boolean getCell(long world, int col, int row) {
if (row>7 | col>7) { return false ;} else {return (PackedLong.get (world, (8*row + col))) ;} 
}


public static void main(String [] args) {
System.out.println(getCell (0x20272000027202L,1,3));
}


}
