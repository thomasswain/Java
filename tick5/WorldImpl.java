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


public World nextGeneration(int log2StepSize) {
WorldImpl world = this;
int StepSize = 1<<log2StepSize;
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

}