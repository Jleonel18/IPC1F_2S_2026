package demo.controller;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import demo.model.Config;
import demo.model.Escena;
import demo.model.Jugador;
import demo.view.PanelEscena;
import demo.view.Reporte;

/** Arma todo y arranca los hilos. */
public class Main {

    private static Escena escena;
    private static Jugador jugador;
    private static Arbitro arbitro;
    private static HiloRender render;
    private static HiloDisparo disparo;
    private static HiloSpawn spawn;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                construir();
            }
        });
    }

    private static void construir() {

        Teclado teclado = new Teclado();

        escena  = new Escena();
        jugador = new Jugador(teclado, Config.PAUSA_JUGADOR_NORMAL);
        arbitro = new Arbitro(escena, jugador);

        PanelEscena panel = new PanelEscena(escena, jugador, arbitro);
        panel.setPreferredSize(new Dimension(Config.ANCHO, Config.ALTO));
        panel.addKeyListener(teclado);

        JButton btnReporte = new JButton("Generar reporte");
        btnReporte.setFocusable(false);   // si no, el boton se roba el foco del teclado
        btnReporte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarReporte();
            }
        });

        JPanel barra = new JPanel();
        barra.add(btnReporte);

        JFrame ventana = new JFrame("Demo de hilos - flechas para moverse");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new BorderLayout());
        ventana.add(panel, BorderLayout.CENTER);
        ventana.add(barra, BorderLayout.SOUTH);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

        panel.requestFocusInWindow();   // va DESPUES de setVisible

        // Arranque de hilos
        escena.agregar(jugador);
        jugador.start();

        render  = new HiloRender(panel);
        disparo = new HiloDisparo(escena, jugador, Config.CADENCIA_NORMAL);
        spawn   = new HiloSpawn(escena);
        arbitro.start();
        render.start();
        disparo.start();
        spawn.start();
    }

    private static void generarReporte() {
        try {
            String[] nombres  = { "Leonel", "Ana", "Marco", "Sofia", "Luis" };
            int[] puntajes    = { 320, 280, 195, 150, 90 };

            File html = Reporte.generarTop(nombres, puntajes);

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(html.toURI());
            } else {
                JOptionPane.showMessageDialog(null, "Generado en: " + html.getAbsolutePath());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    /** Apagado limpio: sin esto quedan hilos vivos y el proceso no muere. */
    public static void terminarPartida() {
        arbitro.detener();
        disparo.detener();
        spawn.detener();
        render.detener();
        escena.matarTodo();
        escena.limpiarMuertos();
    }
}
