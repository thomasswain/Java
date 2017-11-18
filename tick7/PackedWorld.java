public class PackedWorld extends WorldImpl {

private long cells;

public PackedWorld() {
super(8,8);
cells = 0;
}

protected PackedWorld(PackedWorld prev) {
super(prev);
this.cells = 0;
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

protected PackedWorld nextGeneration() {
PackedWorld world = new PackedWorld(this);
int h = world.getHeight();
int w = world.getWidth();
for (int row = 0; row < h; row++) {
for (int col = 0; col < w; col++) {
world.setCell(col, row, computeCell(col, row));
}
}
return world;
}


}