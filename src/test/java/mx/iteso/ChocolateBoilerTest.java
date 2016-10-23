package mx.iteso;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Created by Arturo Hernandez on 22/10/2016.
 */
public class ChocolateBoilerTest {

    private ChocolateBoiler boiler;
    @Before
    public void setUp(){
        boiler = ChocolateBoiler.getInstanceSyncronized();
    }
    @Test
    public void fillTest(){
        boiler.fill();
        assertEquals(false, boiler.isEmpty());
    }
    @Test
    public void boilTest(){
        boiler.fill();
        boiler.boil();
        assertEquals(true, boiler.isBoiled());
    }


    @Test
    public void drainTest(){
        boiler.fill();
        boiler.boil();
        boiler.drain();
        assertEquals(true, boiler.isEmpty());
    }

    @Test
    public void multiThreadTest() throws Exception{
        int TOTALTHREADS = 10;
        ExecutorService ex = Executors.newFixedThreadPool(TOTALTHREADS);
        HashSet<ChocolateBoiler> chocolateBoilers = new HashSet<ChocolateBoiler>();
        HashSet<Callable<ChocolateBoiler>> boilerCallable = new HashSet<Callable<ChocolateBoiler>>();

        for(int i = 0; i < TOTALTHREADS; i++ )
            ex.submit(new ChocolateBoilerCallable());
        ex.invokeAll(boilerCallable);

        for(Callable<ChocolateBoiler> call : boilerCallable)
            chocolateBoilers.add(call.call());
        for(ChocolateBoiler chocolateBoiler : chocolateBoilers)
            assertEquals(ChocolateBoiler.getInstanceSyncronized().hashCode(),chocolateBoiler);
    }
}
