
package com.mycompany.ejemplo2proyecto1.reports;

import com.mycompany.ejemplo2proyecto1.models.Usuario;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author leonel
 */
public class ReporteHTML {
    
    public static boolean generarReporteUsuarios(Usuario[] usuarios, String ruta){
        if(usuarios == null || ruta == null || ruta.trim().isEmpty()){
            return false;
        }
        
        StringBuilder html = new StringBuilder();
        
        html.append("<!DOCTYPE html>\n");
        html.append("<html lang= ");
        html.append("<html lang=\"es\">\n");
        html.append("<head>\n");
        html.append("  <meta charset=\"UTF-8\">\n");
        html.append("  <title>Reporte de usuarios</title>\n");
        html.append("</head>\n");
        html.append("<body>\n");
        html.append("  <h1>Reporte de usuarios</h1>\n");

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        html.append("  <p>Generado: ").append(formato.format(new Date())).append("</p>\n");

        html.append("  <table border=\"1\" cellpadding=\"6\" cellspacing=\"0\">\n");
        html.append("    <tr><th>Codigo</th><th>Usuario</th><th>Rol</th></tr>\n");

        int total = 0;
        for (int i = 0; i < usuarios.length; i++) {
            Usuario u = usuarios[i];
            if (u == null) {
                continue;
            }
            html.append("    <tr>");
            html.append("<td>").append(u.getCodigo()).append("</td>");
            html.append("<td>").append(escapar(u.getUsuario())).append("</td>");
            html.append("<td>").append(escapar(u.getRol())).append("</td>");
            html.append("</tr>\n");
            total++;
        }

        html.append("  </table>\n");
        html.append("  <p>Total de usuarios: ").append(total).append("</p>\n");
        html.append("</body>\n");
        html.append("</html>\n");

        try (FileWriter escritor = new FileWriter(ruta)) {
            escritor.write(html.toString());
            return true;
        } catch (IOException e) {
            System.out.println("Error al escribir el reporte: " + e.getMessage());
            return false;
        }
    }

    private static String escapar(String texto) {
        if (texto == null) {
            return "";
        }
        return texto.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}

