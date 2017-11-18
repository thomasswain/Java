import java.awt.Color;
import java.awt.Graphics;
import java.io.Writer;

public class HashWorld
implements World {
    private int a;
    private a b;
    private int c;

    public HashWorld(int n, int n2) {
        int n3 = n2 > n ? n2 : n;
        this.c = 0;
        while (1 << this.c < n3) {
            ++this.c;
        }
        this.a = 0;
        this.b = c.a((int)this.c, (int)0);
    }

    public void draw(Graphics graphics, int n, int n2) {
        graphics.setColor(Color.BLACK);
        this.b.a(graphics, 0, 0, n, n2);
    }

    public void print(Writer writer) {
    }

    public int getPopulation() {
        return this.b.f();
    }

    private HashWorld(int n, a a2) {
        this.a = n;
        this.b = a2;
        this.c = a2.e();
    }

    public int getGeneration() {
        return this.a;
    }

    public boolean getCell(int n, int n2) {
        return false;
    }

    public int getHeight() {
        return 1 << this.c;
    }

    public int getWidth() {
        return 1 << this.c;
    }

    public void setCell(int n, int n2, boolean bl) {
        this.b = this.b.a(n, n2, bl);
    }

    public World nextGeneration(int n) {
        a a2 = this.b.l();
        while (a2.a().f() != a2.a().d().d().f() || a2.b().f() != a2.b().c().c().f() || a2.c().f() != a2.c().b().b().f() || a2.d().f() != a2.d().a().a().f()) {
            a2 = a2.l();
        }
        return new HashWorld(this.a + (1 << n), a2.a(n));
    }
}