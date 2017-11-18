public class ArrayWorld extends WorldImpl {

private boolean[][] cells;


public ArrayWorld(int w, int h) {
super(w,h);
cells = new boolean [h][w];
}

public ArrayWorld(ArrayWorld prev){
super(prev);
this.cells = new boolean[prev.height][prev.width];
}

public boolean getCell( int col, int row) { 
if (row < 0 || row > cells.length - 1) return false;
if (col < 0 || col > cells[row].length - 1) return false;
return cells[row][col];
}

public void setCell(int col, int row, boolean alive) { 
if (row < 0 || row > cells.length - 1) {return ;}
if (col < 0 || col > cells[row].length - 1) {return ;}
cells[row][col] = alive;
return; 
}

protected ArrayWorld nextGeneration() {
ArrayWorld world = new ArrayWorld(this);
int h = world.getHeight();
int w = world.getWidth();
for (int row = 0; row < w; row++) {
for (int col = 0; col < h; col++) {
world.setCell(col, row, computeCell(col, row));
}
}
return world;
}

public ArrayWorld changeWidth(int w) {
ArrayWorld world = new ArrayWorld(w,this.height);
for(int r=0; r<this.height; r++) {
for(int c=0; c<w; c++) {
if(c>this.width-1) {world.setCell(c,r,false);}
else {world.setCell(c,r,this.getCell(c,r));}
}
}
return world;
}

public ArrayWorld changeHeight(int h) {
ArrayWorld world = new ArrayWorld(this.width,h);
for(int r=0; r<h; r++) {
for(int c=0; c<this.width; c++) {
if(r>this.height-1) {world.setCell(c,r,false);}
else {world.setCell(c,r,this.getCell(c,r));}
}
}
return world;
}


}