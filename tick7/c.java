import java.lang.ref.SoftReference;
import java.util.WeakHashMap;

class c {
    private static WeakHashMap<a, SoftReference<a>> a = new WeakHashMap();

class a extends d{

 private a(int n, int n2) {
        super(n, n2);
    }

    private a(a a2, a a3, a a4, a a5) {
        super(a2, a3, a4, a5);
    }
}

    c() {
    }

    private static a a(a a2) {
        a a3;
        SoftReference<a> softReference = a.get(a2);
        if (softReference != null && (a3 = softReference.get()) != null) {
            return a3;
        }
        a.put(a2, new SoftReference<a>(a2));
        return a2;
    }

    static a a(a a2, a a3, a a4, a a5) {
        return c.a((a)((Object)new a(a2, a3, a4, a5)));
    }

    static a a(int n, int n2) {
        return c.a((a)((Object)new a(n, n2)));
    }

}