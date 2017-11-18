public class AnonTest2 {

private int counter;

public class A { 
private A() {} 
public void print() { System.out.println("A: "+counter); }
}

public AnonTest2() {
counter = 0;
}

public void incrementCounter() {
counter++;
}

public A getA() {
A instance1 = new A();
return instance1;
}

public A getSpecialA() {
A instance2 = new A() {
public void print() { System.out.println("Special: "+counter); }
};
return instance2;
}

}