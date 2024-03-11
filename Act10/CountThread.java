import javax.swing.JLabel;

public class CountThread extends Thread {

    private volatile boolean running = false;
    private int count = 0;
    private JLabel label; // Agrega una referencia a la etiqueta asociada

    public CountThread(JLabel label) {
        this.label = label;
    }

    public void run() {
        while (running) {
            try {
                Thread.sleep(1000);
                count++;
                label.setText(Integer.toString(count)); // Actualiza la etiqueta con el nuevo valor del contador
            } catch (InterruptedException e) {
                System.err.println("El hilo fue interrumpido.");
                e.printStackTrace();
                running = false;
            }
        }
    }

    public void startCounting() {
        running = true;
        this.start();
    }

    public void stopCounting() {
        running = false;
    }

    public int getCount() {
        return count;
    }
}
