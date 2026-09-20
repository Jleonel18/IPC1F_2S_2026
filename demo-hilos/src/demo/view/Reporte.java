package demo.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;

/**
 * Reporte con java.io puro: PNG de la grafica + HTML que la referencia.
 * Desde el navegador: Ctrl+P -> Guardar como PDF.
 *
 * EN LA PRACTICA, la imagen no se dibuja a mano: sale de JFreeChart con
 *
 *     JFreeChart grafica = ChartFactory.createBarChart(
 *             "Top de puntajes", "Piloto", "Puntos", dataset);
 *     BufferedImage imagen = grafica.createBufferedImage(640, 400);
 *
 * De ahi para abajo, todo lo demas de esta clase es identico.
 */
public class Reporte {

    public static File generarTop(String[] nombres, int[] puntajes) throws IOException {

        File carpeta = new File("reportes");
        carpeta.mkdirs();   // si no existe, el FileWriter truena

        BufferedImage imagen = dibujarBarras(nombres, puntajes, 640, 400);
        ImageIO.write(imagen, "png", new File(carpeta, "top.png"));

        File html = new File(carpeta, "top.html");
        PrintWriter out = new PrintWriter(new FileWriter(html));

        out.println("<html><head><meta charset='UTF-8'><title>Top de puntajes</title>");
        out.println("<style>");
        out.println("body{font-family:Arial,sans-serif;margin:40px;color:#222}");
        out.println("h1{border-bottom:2px solid #444;padding-bottom:8px}");
        out.println("table{border-collapse:collapse;width:100%;margin-top:20px}");
        out.println("th,td{border:1px solid #999;padding:8px;text-align:left}");
        out.println("th{background:#eee}");
        out.println("@media print{body{margin:0}}");
        out.println("</style></head><body>");

        out.println("<h1>Top de puntajes</h1>");

        // Ruta RELATIVA. Si ponen C:\Users\... solo funciona en su maquina.
        out.println("<img src='top.png' width='640'>");

        out.println("<table><tr><th>#</th><th>Piloto</th><th>Puntos</th></tr>");
        for (int i = 0; i < nombres.length; i++) {
            out.println("<tr><td>" + (i + 1) + "</td>"
                      + "<td>" + nombres[i] + "</td>"
                      + "<td>" + puntajes[i] + "</td></tr>");
        }
        out.println("</table>");

        out.println("</body></html>");
        out.close();   // SIN ESTO el archivo sale vacio o cortado a la mitad

        return html;
    }

    /** Solo para la demo. En la practica esto lo hace JFreeChart. */
    private static BufferedImage dibujarBarras(String[] nombres, int[] valores,
                                               int ancho, int alto) {
        BufferedImage img = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, ancho, alto);

        int maximo = 1;
        for (int v : valores) {
            if (v > maximo) maximo = v;
        }

        int margen = 50;
        int anchoBarra = (ancho - margen * 2) / Math.max(valores.length, 1) - 12;

        g.setFont(new Font("SansSerif", Font.BOLD, 14));
        g.setColor(Color.DARK_GRAY);
        g.drawString("Top de puntajes", margen, 28);

        for (int i = 0; i < valores.length; i++) {
            int altoBarra = (int) ((alto - 110) * (valores[i] / (double) maximo));
            int x = margen + i * (anchoBarra + 12);
            int y = alto - 50 - altoBarra;

            g.setColor(new Color(70, 130, 200));
            g.fillRect(x, y, anchoBarra, altoBarra);

            g.setColor(Color.BLACK);
            g.setFont(new Font("SansSerif", Font.PLAIN, 11));
            g.drawString(nombres[i], x, alto - 34);
            g.drawString(String.valueOf(valores[i]), x, y - 4);
        }

        g.setColor(Color.GRAY);
        g.drawLine(margen - 10, alto - 50, ancho - margen, alto - 50);

        g.dispose();
        return img;
    }
}
