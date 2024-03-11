import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Matricula: S21017265 - Jesus Antonio Morales Cruz

public class Actividad_10 {
    JFrame frame;
    JButton[] startStopButtons = new JButton[3];
    JLabel[] timerLabels = new JLabel[3];
    TimerThread[] timerThreads = new TimerThread[3];

    public Actividad_10() {
        frame = new JFrame();
        frame.setLayout(null);
        frame.setSize(500, 200);

        // Crear tres temporizadores
        for (int i = 0; i < 3; i++) {
            timerLabels[i] = new JLabel("0");
            timerLabels[i].setBounds(50 + (i * 120), 40, 100, 30);
            frame.add(timerLabels[i]);

            startStopButtons[i] = new JButton("Iniciar/Detener");
            startStopButtons[i].setBounds(50 + (i * 120), 80, 120, 30);
            int finalI = i;
            startStopButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Iniciar o detener el temporizador al hacer clic en el botón
                    if (timerThreads[finalI] == null || !timerThreads[finalI].isRunning()) {
                        timerThreads[finalI] = new TimerThread(timerLabels[finalI]);
                        timerThreads[finalI].start();
                    } else {
                        timerThreads[finalI].stopTimer();
                    }
                }
            });
            frame.add(startStopButtons[i]);
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Actividad_10();
    }
}

// Clase para el temporizador
class TimerThread extends Thread {
    private boolean running;
    private final JLabel label;
    private int counter;

    public TimerThread(JLabel label) {
        this.label = label;
        this.running = true;
        this.counter = Integer.parseInt(label.getText());
    }

    @Override
    public void run() {
        while (running) {
            try { 
                sleep(1000); // Esperar 1 segundo
                counter++;   // Incrementar el contador
                label.setText(String.valueOf(counter)); // Actualizar la etiqueta con el nuevo valor
                
             } catch (InterruptedException e) { 
                 e.printStackTrace();
             }
        }
    }

    // Detener el temporizador
    public void stopTimer() {
        this.running = false;
    }

    // Verificar si el temporizador está en ejecución
    public boolean isRunning() {
        return this.running;
    }
}
