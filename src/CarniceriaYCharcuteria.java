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
                carniceria();
                atendidoCarniceria=true;
            }
            if(!atendidoCharcuteria&&semaforoCharcuteria.availablePermits()>0){
                charcuteria();
                atendidoCharcuteria=true;
            }
        }
    }

    /**
     * Se atendera a un cliente en 10 sec, ocupando al inicio a un trabajador de la charcuteria y dejandolo libre al final
     */
    public void charcuteria(){
        try {
            semaforoCharcuteria.acquire();//Ocupamos un hueco
            System.out.println("Se esta atendiendo a " + Thread.currentThread().getName() + " en la charcuteria");
            Thread.sleep(1000);
            System.out.println("Se termino de atender a "+Thread.currentThread().getName()+" en la charcuteria");
            semaforoCharcuteria.release();//Dejamos libre un hueco
        } catch (InterruptedException e) {
            System.out.println("Hilo interrumpido");
        }
    }

    /**
     * Se atendera a un cliente en 10 sec, ocupando al inicio a un trabajador de la charcuteria y dejandolo libre al final
     */
    public void carniceria(){
        try {
            semaforoCarniceria.acquire();//Ocupamos un hueco
            System.out.println("Se esta atendiendo a " + Thread.currentThread().getName() + " en la carniceria");
            Thread.sleep(1000);
            System.out.println("Se termino de atender a "+Thread.currentThread().getName()+" en la charcuteria");
            semaforoCarniceria.release();//Dejamos libre un hueco
        } catch (InterruptedException e) {
            System.out.println("Hilo interrumpido");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread th=new Thread(new CarniceriaYCharcuteria());//Creamos el objeto dentro del bucle para que la variable atendido no sea comun
            th.setName("cliente "+(1+i));
            th.start();
        }
    }
}
