import javax.swing.SwingUtilities;

import controlador.ControladorEntrada;
import vista.VentanaPrincipal;

public class Main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(()->{
            VentanaPrincipal vista = new VentanaPrincipal();
            ControladorEntrada controlador = new ControladorEntrada(vista);
            controlador.iniciarGaritas();
            vista.setVisible(true);
        });
    }
}
