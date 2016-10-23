package mx.iteso;

import java.util.concurrent.Callable;

/**
 * Created by Arturo Hernandez on 22/10/2016.
 */
public class ChocolateBoilerCallable implements Callable<ChocolateBoiler> {
        public ChocolateBoiler call() throws Exception {
            return ChocolateBoiler.getInstanceSyncronized();
        }
}
