import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Actividad11 extends JFrame {
    private JProgressBar[] barras = new JProgressBar[3];
    private JButton botonIniciar = new JButton("Iniciar");
    private JTextArea areaDeTexto = new JTextArea();
    private AtomicInteger posicion = new AtomicInteger(1);
    private final int limit = 3;

    public Actividad11() {
        setLayout(new BorderLayout());
        JPanel panelDeBarras = new JPanel();
        for (int i = 0; i < limit; i++) {
            barras[i] = new JProgressBar(0, 100);
            barras[i].setStringPainted(true);
            panelDeBarras.add(new JLabel("Hilo " + (i + 1)));
            panelDeBarras.add(barras[i]);
        }
        add(panelDeBarras, BorderLayout.CENTER);
        add(botonIniciar, BorderLayout.NORTH);
        add(new JScrollPane(areaDeTexto), BorderLayout.SOUTH);

        botonIniciar.addActionListener(e -> iniciarCarrera());

        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void iniciarCarrera() {
        areaDeTexto.setText("");
        posicion.set(1);
        for (int i = 0; i < limit; i++) {
            final int indice = i;
            barras[i].setValue(0);
            new Thread(() -> {
                IntStream.rangeClosed(1, 100).forEach(j -> {
                    SwingUtilities.invokeLater(() -> barras[indice].setValue(j));
                    try {
                        Thread.sleep((int) (Math.random() * 100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                areaDeTexto.append("Hilo " + (indice + 1) + " terminó en la posición " + posicion.getAndIncrement() + "\n");
            }).start();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Actividad11().setVisible(true));
    }
}
