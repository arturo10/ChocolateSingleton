package mx.iteso;

/**
 * Created by Arturo Hernandez on 22/10/2016.
 */
public class ChocolateBoiler {

    private volatile  boolean empty;
    private volatile  boolean boiled;
    private static volatile ChocolateBoiler uniqueInstance = null;

    private ChocolateBoiler() {
        empty = true;
        boiled = false;
    }

    //First approach
    public static ChocolateBoiler getInstanceSyncronized(){
        if(uniqueInstance== null){
            synchronized (ChocolateBoiler.class){
                if(uniqueInstance == null){
                    uniqueInstance = new ChocolateBoiler();
                }
            }
        }
        return uniqueInstance;
    }

    //Second approach
    private static class InstanceHolder {
        private static final ChocolateBoiler instance = new ChocolateBoiler();
    }

    public static ChocolateBoiler getInstanceOnDemand() {
        return InstanceHolder.instance;
    }


    public synchronized  void fill() {
        if (isEmpty()) {
            empty = false;
            boiled = false;
            // fi ll the boiler with a milk/chocolate mixture
        }
    }
    public synchronized  void drain() {
        if (!isEmpty() && isBoiled()) {
            // drain the boiled milk and chocolate
            empty = true;
        }
    }
    public synchronized  void boil() {
        if (!isEmpty() && !isBoiled()) {
            // bring the contents to a boil
            boiled = true;
        }
    }
    public boolean isEmpty() {
        return empty;
    }
    public boolean isBoiled() {
        return boiled;
    }
}
