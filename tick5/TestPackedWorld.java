
import java.io.Writer;
import java.lang.Math;
import java.awt.Graphics;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.awt.Color;

public class TestPackedWorld implements World {
private int generation;
private int width;
private int height;
private long cells;


public TestPackedWorld() {
width = 8;
height = 8;
generation = 0;
cells = 0;
}
protected TestPackedWorld(TestPackedWorld prev) {
width = prev.width;
height = prev.height;
generation = prev.generation + 1;
cells = 0;
}

public boolean getCell( int col, int row) { 
if (row < 0 || row > height - 1) return false;
if (col < 0 || col > width - 1) return false;
boolean check = PackedLong.get(cells,(col+8*row));
return check;
}

public void setCell(int col, int row, boolean alive){
if (row < 0 || row > height - 1) {return ;}
if (col < 0 || col > width - 1) {return ;}
cells = PackedLong.set(cells, (col+8*row), alive);
}

public int countNeighbours( int col, int row) {
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


public boolean computeCell ( int col, int row) {
boolean liveCell = this.getCell(col,row);
int neighbours = countNeighbours(col, row);
boolean nextCell=false;
if (row < 0 || row > height - 1) {nextCell = false;}
if (col < 0 || col > width - 1) {nextCell = false;}
if (neighbours > 3) {nextCell = false;}
if (neighbours == 2 & liveCell | neighbours == 3) {nextCell = true;}
return nextCell;
}

public int getWidth() { 
return width;
}


public int getHeight() { 
return height;
}

public int getGeneration() { 
return generation;
}

public int getPopulation() { return 0; }

public void print(Writer w) { 
PrintWriter pw = new PrintWriter(w);
pw.println("-");
for (int row = 0; row < height; row++) {
for (int col = 0; col < width; col++) {
pw.print(this.getCell(col,row) ? "#" : "_");
}
pw.println();
}
pw.flush();
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


private TestPackedWorld nextGeneration() {
TestPackedWorld nextWorld = new TestPackedWorld(this);
int h = nextWorld.getHeight();
int w = nextWorld.getWidth();
for (int row = 0; row < h; row++) {
for (int col = 0; col < w; col++) {
nextWorld.setCell(col, row, computeCell(col, row));
}
}
return nextWorld;
}

public World nextGeneration(int log2StepSize) { 
TestPackedWorld world = this;
int StepSize = 1<<log2StepSize;
for (int i=0; i<StepSize; i++) {world = world.nextGeneration();}
return world;
}


}