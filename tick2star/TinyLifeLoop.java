package computerscience.java.tick2star; 


class TinyLifeLoop

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

if (row>7 |row<0 | col<0 | col>7) {nextCell = false;}
if (neighbours < 2) {nextCell = false;}
if (neighbours > 3) {nextCell = false;}
if (neighbours == 2 & liveCell | neighbours == 3) {nextCell = true;}

return nextCell;
}



public static long nextGeneration(long world) {
long nextGen = world;

for (int row = 0; row < 8; row++) {
for (int col = 0; col < 8; col++) {


boolean cellNext = computeCell(world, col, row); nextGen = setCell(nextGen, col, row, cellNext);
}
}

return nextGen;

}

public static void play(long world) throws Exception {
int userResponse = 0;
while (userResponse != 'q') {
print(world);
userResponse = System.in.read();
world = nextGeneration(world);
}
}

public static int findLoop(long world) {
long [] Array = new long [100];
Array[0]=world;
for (int i=1; i<=99; i++) {Array[i]=nextGeneration(Array[i-1]);}
int loop=1;
for (int j=1; j<=99; j++) {if (Array[j]==Array[0]) {break;} else {loop+=1;}}
return loop-1;
}

// Below is a test for Tinylife
public static void main(String[] args) throws Exception {
if (findLoop(Long.decode(args[0])) == 99) {System.out.println("NO LOOP FOUND");} else {
System.out.println(0 + " to " + (findLoop(Long.decode(args[0]))));}
}
}
