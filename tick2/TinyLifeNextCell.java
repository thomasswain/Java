package computerscience.java.tick2; 


class TinyLifeNextCell

{


public static boolean getCell(long world, int col, int row) {
if (row>7 |row<0 | col<0 | col>7) { return false ;} else {return (PackedLong.get (world, (8*row + col))) ;} 
}



public static void print(long world) {
System.out.println("-");
for (int row = 0; row < 8; row++) {
for (int col = 0; col < 8; col++) {
System.out.print(getCell(world, col, row) ? "#" : "_");
}
System.out.println();}
}


// Below is a test for count class
public static void main(String [] args) {
System.out.println (computeCell(0x20272000027202L,1,0));
}


public static long setCell(long world, int col, int row, boolean value) {
if (row>7 |row<0 | col<0 | col>7) { return world ;} else {return (PackedLong.set (world, (8*row + col), value));} 
}



public static int count(long world, int col, int row) {

int sum1=0;
int sum2=0;
int sum3=0;

for (int i=row-1; i<=row+1; i++){if (getCell(world,(col-1),i)){sum1+=1;} else{sum1=sum1;};}
for (int j=row-1; j<=row+1; j++){if (j==row) continue; if (getCell(world,(col),j)){sum2+=1;} else{sum2=sum2;};}
for (int k=row-1; k<=row+1; k++){if (getCell(world,(col+1),k)){sum3+=1;} else{sum3=sum3;};}

if (row>7 |row<0 | col<0 | col>7) { return 0 ;} else
{return (sum1+sum2+sum3);}

}

public static boolean computeCell(long world,int col,int row) {
boolean liveCell = getCell(world, col, row);
int neighbours = count(world, col, row);
boolean nextCell = false;

if (neighbours < 2) {nextCell = false;}
if (neighbours > 3) {nextCell = false;}
if (neighbours == 2 & liveCell | neighbours == 3) {nextCell = true;}

return nextCell;
}





}
