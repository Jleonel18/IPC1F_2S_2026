package demo.model;

/**
 * El arreglo compartido. Es el UNICO punto donde se tocan todos los hilos,
 * y por eso es el unico que necesita synchronized.
 *
 * Usa vector (arreglo nativo) que crece al doble, sin ArrayList.
 */
public class Escena {

    private Movil[] moviles = new Movil[10];
    private int cantidad = 0;

    public synchronized int getCantidad() {
        return cantidad;
    }

    public synchronized void agregar(Movil m) {
        if (cantidad == moviles.length) {
            Movil[] nuevo = new Movil[moviles.length * 2];
            for (int i = 0; i < cantidad; i++) {
                nuevo[i] = moviles[i];
            }
            moviles = nuevo;
        }
        moviles[cantidad] = m;
        cantidad++;
    }

    public synchronized void eliminar(int indice) {
        if (indice < 0 || indice >= cantidad) {
            return;
        }
        for (int i = indice; i < cantidad - 1; i++) {
            moviles[i] = moviles[i + 1];   // corre todo un lugar a la izquierda
        }
        moviles[cantidad - 1] = null;      // suelta la ultima referencia
        cantidad--;
    }

    /**
     * Copia defensiva. Quien la recibe puede recorrerla tranquilo aunque
     * otro hilo este borrando elementos al mismo tiempo.
     */
    public synchronized Movil[] instantanea() {
        Movil[] copia = new Movil[cantidad];
        for (int i = 0; i < cantidad; i++) {
            copia[i] = moviles[i];
        }
        return copia;
    }

    public synchronized void limpiarMuertos() {
        int i = 0;
        while (i < cantidad) {
            if (!moviles[i].estaVivo()) {
                eliminar(i);   // OJO: no incrementar i, ya se corrio otro a esta posicion
            } else {
                i++;
            }
        }
    }

    public synchronized void matarTodo() {
        for (int i = 0; i < cantidad; i++) {
            moviles[i].matar();
        }
    }
}
