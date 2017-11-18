import java.io.Writer;
import java.awt.Graphics;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

public interface World {
public void setCell(int col, int row, boolean alive);
public boolean getCell(int col, int row);
public void setBorders();
public int getWidth();
public int getHeight();
public int getGeneration();
public int getPopulation();
public int getMinX();
public int getMinY();
public int getMaxX();
public int getMaxY();
public World changeWidth(int w);
public World changeHeight(int h);
public String getCellInfo();
public void print(Writer w);
public void draw(Graphics g, int width, int height);
public void draw(Graphics g, int startX, int width, int height);
public World nextGeneration(int log2StepSize);
public World stepGeneration(int StepSize);
}