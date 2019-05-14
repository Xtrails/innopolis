import java.io.Serializable;

public class SomeClass implements Worker, Serializable {

    @Override
    public void doWork() {System.out.println("doWork 1.0");}
}
