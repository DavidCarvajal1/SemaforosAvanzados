import java.util.concurrent.Semaphore;

public class CarniceriaYCharcuteria implements Runnable{
    public static Semaphore semaforoCarniceria = new Semaphore(4);
    public static Semaphore semaforoCharcuteria = new Semaphore(2);

    private boolean atendidoCarniceria=false;
    private boolean atendidoCharcuteria=false;

    @Override
    public void run() {
        while(!atendidoCarniceria||!atendidoCharcuteria){//Mientras que el cliente no haya sido atendido tanto en la charcuteria comom en la carniceria
            if(!atendidoCarniceria&&semaforoCarniceria.availablePermits()>0){

            }
        }
    }
}
