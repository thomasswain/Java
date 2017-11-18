
import java.io.Reader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.net.URL;
import java.net.URLConnection; 
import java.io.IOException;

class StatsLife {


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





public static void main(String[] args) throws Exception {


if (args.length==1) {

try{
List<Pattern> patternlist = PatternLoader.loadFromDisk(args[0]);
if (patternlist.isEmpty()) {System.out.println ("Input file contains no valid Pattern Strings"); System.exit(0);}

int listlength = patternlist.size();
Stats [] statsArray = new Stats [listlength];

int [] MaximumPopulation = new int [listlength];
double [] MaximumGrowthRate = new double [listlength];
double [] MaximumDeathRate = new double [listlength];
int [] MaximumLoopStart = new int [listlength];
int [] MaximumLoopLength = new int [listlength];

int index=0;
for(Pattern p: patternlist) {

String name = p.getName();
System.out.println ("Analysing "+name);
statsArray[index] = new Stats(p);

index=index+1;}

int MaxStartOfList = 0;
String MaxStartPattern = "name";

int MaxCycleOfList = 0;
String MaxCyclePattern = "name";

int MaxPopOfList = 0;
String MaxPopPattern = "name";

double MaxGrowthOfList = 0.0;
String MaxGrowthPattern = "name";

double MaxDeathOfList = 0.0;
String MaxDeathPattern = "name";

int MaxLoopStartOfList = 0;
String MaxLoopStartPattern = "name";

int MaxLoopLengthOfList = 0;
String MaxLoopLengthPattern = "name";

for (int k=0; k<statsArray.length; k++) {

if (statsArray[k].getMaximumPopulation() > MaxPopOfList ) { MaxPopOfList = statsArray[k].getMaximumPopulation(); MaxPopPattern = statsArray[k].getPatternName();}
if (statsArray[k].getMaximumGrowthRate() >= MaxGrowthOfList ) { MaxGrowthOfList = statsArray[k].getMaximumGrowthRate(); MaxGrowthPattern = statsArray[k].getPatternName();}
if (statsArray[k].getMaximumDeathRate() >= MaxDeathOfList ) { MaxDeathOfList = statsArray[k].getMaximumDeathRate(); MaxDeathPattern = statsArray[k].getPatternName();}
if (statsArray[k].getLoopStart() >= MaxStartOfList ) { MaxStartOfList = statsArray[k].getLoopStart(); MaxStartPattern = statsArray[k].getPatternName();}
if (statsArray[k].getCycleLength() >= MaxCycleOfList ) { MaxCycleOfList = statsArray[k].getCycleLength(); MaxCyclePattern = statsArray[k].getPatternName();}

}
System.out.println(" ");
System.out.println(String.format("Longest Start: %s (%d)", MaxStartPattern, MaxStartOfList));
System.out.println(String.format("Longest Cycle: %s (%d)", MaxCyclePattern, MaxCycleOfList));
System.out.println(String.format("Highest Growth Rate: %s (%f)", MaxGrowthPattern , MaxGrowthOfList ));
System.out.println(String.format("Highest Death Rate: %s (%f)", MaxDeathPattern , MaxDeathOfList ));
System.out.println(String.format("Maximum Population: %s (%d)", MaxPopPattern , MaxPopOfList ));
}


catch (IOException error) {System.out.println("Filename format not recognised - please correct.");}}

else {
int patternnumber = Integer.parseInt(args[1]);
List<Pattern> patternlist = PatternLoader.loadFromDisk(args[0]);
Pattern d = patternlist.get(patternnumber);
boolean[][] world = new boolean[d.getHeight()][d.getWidth()];
d.initialise(world);
play(world);
}




}


}
