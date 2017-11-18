import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import uk.ac.cam.acr31.life.hash.a;
import uk.ac.cam.acr31.life.hash.b;
import uk.ac.cam.acr31.life.hash.c;

abstract class d
extends b {
    private a a = null;
    private int b;
    private static final int c = 1879;
    private static final int d = 3758;
    private static final int e = 30064;
    private static final int f = 60128;

    d(a a2, a a3, a a4, a a5) {
        super(a2, a3, a4, a5);
    }

    d(int n, int n2) {
        super(n, n2);
    }

    @Override
    a k() {
        if (this.f() == 0) {
            return c.a(this.e() - 1, 0);
        }
        return c.a(this.c().b(), this.d().a(), this.c().d(), this.d().c());
    }

    @Override
    a h() {
        if (this.f() == 0) {
            return c.a(this.e() - 1, 0);
        }
        return c.a(this.a().c(), this.a().d(), this.c().a(), this.c().b());
    }

    @Override
    a g() {
        if (this.f() == 0) {
            return c.a(this.e() - 1, 0);
        }
        return c.a(this.a().d(), this.b().c(), this.c().b(), this.d().a());
    }

    @Override
    a i() {
        if (this.f() == 0) {
            return c.a(this.e() - 1, 0);
        }
        return c.a(this.b().c(), this.b().d(), this.d().a(), this.d().b());
    }

    @Override
    a j() {
        if (this.f() == 0) {
            return c.a(this.e() - 1, 0);
        }
        return c.a(this.a().b(), this.b().a(), this.a().d(), this.b().c());
    }

    @Override
    a l() {
        if (this.f() == 0) {
            return c.a(this.e() + 1, 0);
        }
        a a2 = c.a(this.e() - 1, 0);
        a a3 = c.a(a2, a2, a2, this.a());
        a a4 = c.a(a2, a2, this.b(), a2);
        a a5 = c.a(a2, this.c(), a2, a2);
        a a6 = c.a(this.d(), a2, a2, a2);
        return c.a(a3, a4, a5, a6);
    }

    @Override
    a a(int n, int n2, boolean bl) {
        int n3;
        if (!bl && this.f() == 0) {
            return this;
        }
        if (this.e() == 0) {
            return c.a(0, bl ? 1 : 0);
        }
        int n4 = 1 << this.e() - 1;
        a a2 = this.a();
        a a3 = this.b();
        a a4 = this.c();
        a a5 = this.d();
        a a6 = c.a(this.e() - 1, 0);
        if (a2 == null) {
            a2 = a6;
        }
        if (a3 == null) {
            a3 = a6;
        }
        if (a4 == null) {
            a4 = a6;
        }
        if (a5 == null) {
            a5 = a6;
        }
        int n5 = n < n4 ? n : n - n4;
        int n6 = n3 = n2 < n4 ? n2 : n2 - n4;
        if (n < n4 && n2 < n4) {
            a2 = a2.a(n5, n3, bl);
        } else if (n < n4 && n2 >= n4) {
            a4 = a4.a(n5, n3, bl);
        } else if (n >= n4 && n2 < n4) {
            a3 = a3.a(n5, n3, bl);
        } else {
            a5 = a5.a(n5, n3, bl);
        }
        return c.a(a2, a3, a4, a5);
    }

    @Override
    public a a(int n) {
        if (this.a == null || this.b != n) {
            this.a = this.c(n);
            this.b = n;
        }
        return this.a;
    }

    private a c(int n) {
        if (this.f() == 0) {
            this.a = this.a();
            return this.a;
        }
        if (this.e() == 2) {
            this.a = this.m();
            return this.a;
        }
        if (n + 3 > this.e()) {
            this.a = this.d(n);
            return this.a;
        }
        this.a = this.e(n);
        return this.a;
    }

    private a d(int n) {
        a a2 = this.a().a(n);
        a a3 = this.j().a(n);
        a a4 = this.b().a(n);
        a a5 = this.h().a(n);
        a a6 = this.g().a(n);
        a a7 = this.i().a(n);
        a a8 = this.c().a(n);
        a a9 = this.k().a(n);
        a a10 = this.d().a(n);
        a a11 = c.a(a2, a3, a5, a6).a(n);
        a a12 = c.a(a3, a4, a6, a7).a(n);
        a a13 = c.a(a5, a6, a8, a9).a(n);
        a a14 = c.a(a6, a7, a9, a10).a(n);
        return c.a(a11, a12, a13, a14);
    }

    private a e(int n) {
        a a2 = this.a().g();
        a a3 = this.j().g();
        a a4 = this.b().g();
        a a5 = this.h().g();
        a a6 = this.g().g();
        a a7 = this.i().g();
        a a8 = this.c().g();
        a a9 = this.k().g();
        a a10 = this.d().g();
        a a11 = c.a(a2, a3, a5, a6).a(n);
        a a12 = c.a(a3, a4, a6, a7).a(n);
        a a13 = c.a(a5, a6, a8, a9).a(n);
        a a14 = c.a(a6, a7, a9, a10).a(n);
        return c.a(a11, a12, a13, a14);
    }

    private a m() {
        int n = this.f();
        int n2 = Integer.bitCount(n & 1879);
        int n3 = Integer.bitCount(n & 3758);
        int n4 = Integer.bitCount(n & 30064);
        int n5 = Integer.bitCount(n & 60128);
        return c.a(1, (n2 == 3 || (n & 32) != 0 && n2 == 2 ? 1 : 0) | (n3 == 3 || (n & 64) != 0 && n3 == 2 ? 2 : 0) | (n4 == 3 || (n & 512) != 0 && n4 == 2 ? 16 : 0) | (n5 == 3 || (n & 1024) != 0 && n5 == 2 ? 32 : 0));
    }

    @Override
    public void a(Graphics graphics, int n, int n2, int n3, int n4) {
        int n5 = this.f();
        if (n5 == 0) {
            return;
        }
        Shape shape = graphics.getClip();
        if (shape != null && !shape.intersects(new Rectangle2D.Float(n, n2, n3, n4))) {
            return;
        }
        if (n3 == 1 || n4 == 1) {
            graphics.fillRect(n, n2, 1, 1);
        }
        int n6 = n3 / 2;
        int n7 = n4 / 2;
        int n8 = n3 - n6;
        int n9 = n4 - n7;
        if (this.e() == 1) {
            if ((n5 & 1) != 0) {
                graphics.fillRect(n, n2, n6, n7);
            }
            if ((n5 & 2) != 0) {
                graphics.fillRect(n + n6, n2, n8, n7);
            }
            if ((n5 & 16) != 0) {
                graphics.fillRect(n, n2 + n7, n6, n9);
            }
            if ((n5 & 32) != 0) {
                graphics.fillRect(n + n6, n2 + n7, n8, n9);
            }
        } else {
            this.a().a(graphics, n, n2, n6, n7);
            this.b().a(graphics, n + n6, n2, n8, n7);
            this.c().a(graphics, n, n2 + n7, n6, n9);
            this.d().a(graphics, n + n6, n2 + n7, n8, n9);
        }
    }
}