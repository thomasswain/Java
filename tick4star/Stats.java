public class Stats {

private String PatternName; //done
private int Cycles = 50;
private double [] GrowthRate = new double[Cycles];
private double MaximumGrowthRate = 0.0;
private double [] DeathRate = new double[Cycles];
private double MaximumDeathRate = 0.0;
private int LoopStart =0;
private int LoopEnd =0;
private int CycleLength = 0;
private int [] Population = new int[Cycles];
private int MaximumPopulation = 0; //done






public String getPatternName(){
return PatternName;
}

public double getMaximumGrowthRate(){
return MaximumGrowthRate;
}

public double getMaximumDeathRate(){
return MaximumDeathRate;
}

public int getLoopStart(){
return LoopStart;
}

public int getLoopEnd(){
return LoopEnd;
}

public int getCycleLength(){
return CycleLength;
}

public int getMaximumPopulation(){
return MaximumPopulation;
}


public Stats(Pattern p) throws PatternFormatException, ArrayIndexOutOfBoundsException, NumberFormatException{


PatternName = p.getName();

boolean[][] loopworld = new boolean[p.getHeight()][p.getWidth()];
p.initialise(loopworld);



//LoopStart = StartLoop(loopworld, p.getHeight(), p.getWidth());
//LoopEnd = EndLoop(loopworld);
//CycleLength = LoopEnd+1 - LoopStart;


boolean[][] world = new boolean[p.getHeight()][p.getWidth()];
p.initialise(world);

for (int i=0; i<Cycles; i++){

int aliveCount = 0;

for (int row = 0; row < world.length; row++) {
for (int col = 0; col < world[row].length; col++) {

if (StatsLife.getCell(world, col, row)) {aliveCount++;}


}
}

Population[i] = aliveCount;
world = StatsLife.nextGeneration(world);

}


for(int j=0; j<Cycles; j++) {
if (Population[j] > MaximumPopulation) { MaximumPopulation = Population[j]; }
}






for(int k=0; k<Cycles; k++) {
	if(k==(Cycles-1) || (double)Population[k] == 0.0)  {GrowthRate[k]=0.0;} 

else {if((double)Population[k+1]-(double)Population[k]<0.0) {GrowthRate[k]=0.0;} else

	{GrowthRate[k] = ((double)Population[k+1]-(double)Population[k])/(double)Population[k];}}

}


for(int l=0; l<Cycles; l++) {
if (GrowthRate[l] > MaximumGrowthRate ) { MaximumGrowthRate = GrowthRate[l]; }
}




for(int e=0; e<Cycles; e++) {
	if(e==(Cycles-1) || (double)Population[e] == 0.0)  {DeathRate[e]=0.0;} 

else {if((double)Population[e]-(double)Population[e+1]<0.0) {DeathRate[e]=0.0;} else

	{DeathRate[e] = ((double)Population[e]-(double)Population[e+1])/(double)Population[e];}}

}


for(int f=0; f<Cycles; f++) {
if (DeathRate[f] > MaximumDeathRate ) { MaximumDeathRate = DeathRate[f]; }
}

boolean[][] loopworld1 = new boolean[p.getHeight()][p.getWidth()];
boolean[][] loopworld2 = new boolean[p.getHeight()][p.getWidth()];


p.initialise(loopworld1);

outerloop:

for (int g=0; g<Cycles; g++) {

loopworld2 = StatsLife.nextGeneration(loopworld1);

for (int h=g+1; h<Cycles+g; h++) {

if (match(loopworld1, loopworld2)) {LoopStart=g; LoopEnd=h-1; break outerloop;} else {loopworld2 = StatsLife.nextGeneration(loopworld2);}


}

loopworld1 = StatsLife.nextGeneration(loopworld1);

LoopStart = -1;
LoopEnd = -1;

}


if (LoopStart== -1) {CycleLength=0;} else{
CycleLength = LoopEnd + 1 - LoopStart;}


}

public static boolean match(boolean[][] world1, boolean[][] world2) {

boolean matches = true;


for (int r = 0; r < world1.length; r++) {
for (int c = 0; c < world1[r].length; c++) {

if (world1[r][c] != world2[r][c]) {matches = false; return matches;}

}
}

return matches;

}




}