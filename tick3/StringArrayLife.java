 


class StringArrayLife {


public static boolean getCell(boolean[][] world, int col, int row) {
if (row < 0 || row > world.length - 1) return false;
if (col < 0 || col > world[row].length - 1) return false;
return world[row][col];
}




public static void print(boolean[][] world) {
System.out.println("-");

for (int row = 0; row < world.length; row++) {
for (int col = 0; col < world[row].length; col++) {
System.out.print(getCell(world, col, row) ? "#" : "_");
}
System.out.println();}


// Below tests if "count" method works.
//for (int row = 0; row < world.length; row++) {
//for (int col = 0; col < world[row].length; col++) {
//System.out.print(count(world, col, row));}
//System.out.println();}


// Below tests if "computeCell" method works.
//for (int row = 0; row < world.length; row++) {
//for (int col = 0; col < world[row].length; col++) {
//System.out.print(computeCell(world, col, row) ? "#" : "_");}
//System.out.println();}

// Below tests if "nextGeneration" method works.
//for (int row = 0; row < world.length; row++) {
//for (int col = 0; col < world[row].length; col++) {
//System.out.print(getCell(nextGeneration(world), col, row) ? //"#" : "_");}
//System.out.println();}




}





public static void setCell(boolean[][] world, int col, int row, boolean value) {


if (row < 0 || row > world.length - 1) {return ;}
if (col < 0 || col > world[row].length - 1) {return ;}


world[row][col] = value; 

return ;
}



public static int count(boolean[][] world, int col, int row) {

int sum1=0;
int sum2=0;
int sum3=0;

if (row < 0 || row > world.length - 1) {return 0;}
if (col < 0 || col > world[row].length - 1) {return 0;}

for (int i=row-1; i<=row+1; i++){if (getCell(world,(col-1),i)){sum1+=1;} else{sum1=sum1;};}
for (int j=row-1; j<=row+1; j++){if (j==row) continue; if (getCell(world,(col),j)){sum2+=1;} else{sum2=sum2;};}
for (int k=row-1; k<=row+1; k++){if (getCell(world,(col+1),k)){sum3+=1;} else{sum3=sum3;};}




return (sum1+sum2+sum3);

}

public static boolean computeCell(boolean[][] world,int col,int row) {
boolean liveCell = getCell(world, col, row);
int neighbours = count(world, col, row);
boolean nextCell = false;


if (row < 0 || row > world.length - 1) {nextCell = false;}
if (col < 0 || col > world[row].length - 1) {nextCell = false;}

if (neighbours > 3) {nextCell = false;}
if (neighbours == 2 & liveCell | neighbours == 3) {nextCell = true;}

return nextCell;
}


//Below function had some issues. It didn't work when I had the first line defining worldNext as "boolean[][] worldNext = world;". nextGeneration would make some random new world. I think because of the referencing method with arrays, the definition to have worldNext = world at the beginning meant that the final line of the for loop changed world. So then computeCell acted on a different, updated world. Also note, no actual need to reference setCell. Can just use commented code and remove the 2 lines after that.



public static boolean[][] nextGeneration(boolean[][] world) {

boolean[][]worldNext=new boolean[world.length][world[0].length];


for (int row = 0; row < world.length; row++) {
for (int col = 0; col < world[row].length; col++) {


//worldNext[row][col] = computeCell(world,col,row);

boolean cellNext = computeCell (world,col,row);

setCell (worldNext,col,row,cellNext);

}
}



return worldNext;

}

public static void play(boolean[][] world) throws Exception {
int userResponse = 0;
while (userResponse != 'q') {
print(world);
userResponse = System.in.read();
world = nextGeneration(world);
}
}


//some long numbers to try and input:
//0x1824428181422418
//0x20A0600000000000
//0x20272000027202


public static void main(String[] args
) throws Exception {
String formatString = args[0];


//TODO: Determine the dimensions of the game board

String[] formatArray = formatString.split(":");


int width = Integer.parseInt(formatArray[2]);
int height = Integer.parseInt(formatArray[3]);
int startCol = Integer.parseInt(formatArray[4]);
int startRow = Integer.parseInt(formatArray[5]);

String[] cellStrings = (formatArray[6]).split(" ");

int gridWidth = ( (cellStrings[0]).toCharArray() ).length;
int gridHeight = cellStrings.length;


char[][] Grid = new char [gridHeight][gridWidth];

for (int i=0; i < gridHeight ; i++) {
Grid [i] = (cellStrings[i]).toCharArray(); }

int[][]intGrid = new int [gridHeight][gridWidth];


for (int j=0; j < gridHeight; j++) {
for (int k=0; k < gridWidth; k++){
intGrid [j][k] = Grid[j][k] - '0';
}}


boolean[][] world = new boolean[height][width];

for (int row = startRow; row < (gridHeight+startRow); row++) {
for (int col = startCol; col < (gridWidth+startCol); col++) {
world[row][col] = (intGrid[row - startRow][col-startCol]==1);
}}


//System.out.println(gridWidth);
//System.out.println(gridHeight);
//System.out.println(formatArray[6]);
//System.out.println(cellStrings[0]);
//System.out.println(cellStrings[1]);
//System.out.println(cellStrings[2]);


for (int a=0; a < gridHeight ; a++) {
for (int b=0; b < gridWidth ; b++){
System.out.print(Grid[a][b]);
}
System.out.println();}

play (world);
}


}
