import java.io.Writer;
import java.lang.Math;
import java.awt.Graphics;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.awt.Color;

public abstract class WorldImpl implements World {

protected int width;
protected int height;
protected int generation;
private int minY=0;
private int minX=0;
private int maxY=0;
private int maxX=0;
private String cellInfo;
private StringBuilder cellBuilder;

protected WorldImpl(int w, int h) {
this.width = w;
this.height = h;
this.generation = 0;
}

protected WorldImpl(WorldImpl prev) {
this.width = prev.width;
this.height = prev.height;
this.generation = prev.generation + 1;
} 

public int getWidth() { return this.width; }

public int getHeight() { return this.height; }

public int getGeneration() { return this.generation; }

public int getPopulation() { return 0; }

public int getMinX() {return this.minX;}
public int getMinY() {return this.minY;}
public int getMaxX() {return this.maxX;}
public int getMaxY() {return this.maxY;}
public String getCellInfo() {return this.cellInfo;}

public void setBorders() {
minY=0;
minX=0;
maxY=0;
maxX=0;

outerloop1:
for(int r=0; r<height; r++){
for(int c=0; c<width; c++){
if (getCell(c,r)){minY=r; break outerloop1;}
}
}

outerloop2:
for(int r=height; r>=0; r--){
for(int c=0; c<width; c++){
if (getCell(c,r)){maxY=r; break outerloop2; }
}
}

outerloop3:
for(int c=0; c<width; c++){
for(int r=0; r<height; r++){
if (getCell(c,r)){minX=c; break outerloop3;}
}
}

outerloop4:
for(int c=width; c>=0; c--){
for(int r=0; r<height; r++){
if (getCell(c,r)){maxX=c; break outerloop4; }
}
}

cellBuilder = new StringBuilder();
for(int r=minY; r<maxY+1; r++){
for(int c=minX; c<maxX+1; c++){
String add = getCell(c,r) ? "1" : "0";
cellBuilder.append(add);
if(r!=maxY && c==maxX){cellBuilder.append(" ");}
}
}
cellInfo = cellBuilder.toString();
}

protected String getCellAsString(int col,int row) {
return getCell(col,row) ? "#" : "_";
}

protected Color getCellAsColour(int col,int row) {
return getCell(col,row) ? Color.BLACK : Color.WHITE;
}	


public void draw(Graphics g,int width, int height) {
int worldWidth = getWidth();
int worldHeight = getHeight();
double colScale = (double)width/(double)worldWidth;
double rowScale = (double)height/(double)worldHeight;
for(int col=0; col<worldWidth; ++col) {
for(int row=0; row<worldHeight; ++row) {
int colPos = (int)(col*colScale);
int rowPos = (int)(row*rowScale);
int nextCol = (int)((col+1)*colScale);
int nextRow = (int)((row+1)*rowScale);
if (g.hitClip(colPos,rowPos,nextCol-colPos,nextRow-rowPos)) {
g.setColor(getCellAsColour(col, row));
g.fillRect(colPos,rowPos,nextCol-colPos,nextRow-rowPos);
}
} 
} 
}


public void draw(Graphics g, int startX, int width, int height) {
int worldWidth = getWidth();
int worldHeight = getHeight();
double colScale = (double)width/(double)worldWidth;
double rowScale = (double)height/(double)worldHeight;
for(int col=0; col<worldWidth; ++col) {
for(int row=0; row<worldHeight; ++row) {
int colPos = (int)(col*colScale);
int rowPos = (int)(row*rowScale);
int nextCol = (int)((col+1)*colScale);
int nextRow = (int)((row+1)*rowScale);
if (g.hitClip(colPos,rowPos,nextCol-colPos,nextRow-rowPos)) {
g.setColor(getCellAsColour(col, row));
g.fillRect(startX + colPos,rowPos,nextCol-colPos,nextRow-rowPos);
}
} 
} 
}


public World nextGeneration(int log2StepSize) {
WorldImpl world = this;
int StepSize = 1<<log2StepSize;
for (int i=0; i<StepSize; i++) {world = world.nextGeneration();}
return world;
}

public World stepGeneration(int StepSize) {
WorldImpl world = this;
for (int i=0; i<StepSize; i++) {world = world.nextGeneration();}
return world;
}

public void print(Writer w) {
PrintWriter pw = new PrintWriter(w);
pw.println("-");
for (int row = 0; row < height; row++) {
for (int col = 0; col < width; col++) {
pw.print(getCellAsString(col,row));
}
pw.println();
}
pw.flush();
}


protected int countNeighbours(int col, int row) {
int sum1=0;
int sum2=0;
int sum3=0;
if (row < 0 || row > height - 1) {return 0;}
if (col < 0 || col > width- 1) {return 0;}
//Have used this.getCell(a,b) below instead of cells[b][a]) because at row or col = 0 it is trying to access outside the array and gives ArrayIndexOutOfBounds Exception!
for (int i=row-1; i<=row+1; i++){if (this.getCell(col-1,i)){sum1+=1;} else{sum1=sum1;};}
for (int j=row-1; j<=row+1; j++){if (j==row) continue; if (this.getCell(col,j)){sum2+=1;} else{sum2=sum2;};}
for (int k=row-1; k<=row+1; k++){if (this.getCell(col+1,k)){sum3+=1;} else{sum3=sum3;};}
return (sum1+sum2+sum3);
}


protected boolean computeCell(int col, int row) {
boolean liveCell = getCell(col,row);
int neighbours = countNeighbours(col, row);
boolean nextCell=false;
if (row < 0 || row > height - 1) {nextCell = false;}
if (col < 0 || col > width - 1) {nextCell = false;}
if (neighbours > 3) {nextCell = false;}
if (neighbours == 2 & liveCell | neighbours == 3) {nextCell = true;}
return nextCell;
}

public abstract boolean getCell(int col,int row);

public abstract void setCell(int col, int row, boolean alive);

protected abstract WorldImpl nextGeneration();

public abstract WorldImpl changeWidth(int w);

public abstract WorldImpl changeHeight(int h);

}