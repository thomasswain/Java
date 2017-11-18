
abstract class b extends a {
    private final int a;
    private final int b;
    private final a c;
    private final a d;
    private final a e;
    private final a f;

    protected b(a a2, a a3, a a4, a a5) {
        this.c = a2;
        this.d = a3;
        this.e = a4;
        this.f = a5;
        if (a2.e() != a3.e() || a2.e() != a4.e() || a2.e() != a5.e()) {
            throw new Error("Unbalanced child nodes! " + a2.e() + " " + a3.e() + " " + a4.e() + " " + a5.e());
        }
        this.a = a2.e() + 1;
        this.b = this.a <= 2 ? a2.f() | a3.f() << 1 * this.a | a4.f() << 4 * this.a | a5.f() << 5 * this.a : (this.a == 3 ? Integer.bitCount(a2.f()) + Integer.bitCount(a3.f()) + Integer.bitCount(a4.f()) + Integer.bitCount(a5.f()) : a2.f() + a3.f() + a4.f() + a5.f());
    }

    protected b(int n, int n2) {
        this.a = n;
        this.b = n2;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
    }

    a b(int n) {
        switch (n) {
            case 0: {
                return this.a();
            }
            case 1: {
                return this.b();
            }
            case 2: {
                return this.c();
            }
            case 3: {
                return this.d();
            }
        }
        return null;
    }

    @Override
    a a() {
        return this.c == null ? c.a((int)(this.a - 1), (int)(this.b & 1)) : this.c;
    }

    @Override
    a b() {
        return this.d == null ? c.a((int)(this.a - 1), (int)(this.b >> 1 & 1)) : this.d;
    }

    @Override
    a c() {
        return this.e == null ? c.a((int)(this.a - 1), (int)(this.b >> 4 & 1)) : this.e;
    }

    @Override
    a d() {
        return this.f == null ? c.a((int)(this.a - 1), (int)(this.b >> 5 & 1)) : this.f;
    }

    @Override
    int e() {
        return this.a;
    }

    @Override
    int f() {
        return this.b;
    }

    public int hashCode() {
        int n = 31;
        int n2 = 1;
        n2 = 31 * n2 + this.a;
        n2 = 31 * n2 + this.b;
        if (this.b != 0) {
            n2 = 31 * n2 + (this.e == null ? 0 : System.identityHashCode(this.e));
            n2 = 31 * n2 + (this.f == null ? 0 : System.identityHashCode(this.f));
            n2 = 31 * n2 + (this.c == null ? 0 : System.identityHashCode(this.c));
            n2 = 31 * n2 + (this.d == null ? 0 : System.identityHashCode(this.d));
        }
        return n2;
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null) {
            return false;
        }
        b b2 = (b)object;
        if (this.a != b2.a) {
            return false;
        }
        if (this.b != b2.b) {
            return false;
        }
        if (this.b == 0) {
            return true;
        }
        if (this.e != b2.e) {
            return false;
        }
        if (this.c != b2.c) {
            return false;
        }
        if (this.f != b2.f) {
            return false;
        }
        if (this.d != b2.d) {
            return false;
        }
        return true;
    }
}