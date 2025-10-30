package Backend.Nodos;

import Backend.Objetos.Libro;
import java.util.List;
import java.util.ArrayList;

public class NodoB {
    
    private Libro[] libros;
    private int[] fechas;
    private int t;
    private NodoB[] C;
    private int n;
    private boolean hoja;
    
    public NodoB(int _t, boolean _hoja){
        t = _t;
        hoja = _hoja;
        libros = new Libro[2*t -1];
        fechas = new int[2* t - 1];
        C = new NodoB[2*t];
        n = 0;
    }

    public void imprimir() {
        int i;
        for (i = 0; i < n; i++) {
            if (!hoja) {
                C[i].imprimir();
            }
            libros[i].toString();
        }
        if (!hoja) {
            C[i].imprimir();
        }
    }

    public int findKey(int fecha) {
        int idX = 0;
        while (idX < n && fechas[idX] < fecha) {
            ++idX;
        }
        return idX;
    }

    public void showRange(int low, int high) {
        int i;
        for (i = 0; i < n; i++) {
            if (!hoja) {
                C[i].showRange(low, high);
            }
            if (fechas[i] >= low && fechas[i] <= high) {
                System.out.print(fechas[i] + " ");
            }
        }
        if (!hoja) {
            C[i].showRange(low, high);
        }
    }

    public void showRangeDetailed(int low, int high) {
        int i;
        for (i = 0; i < n; i++) {
            if (!hoja) {
                C[i].showRangeDetailed(low, high);
            }
            if (fechas[i] >= low && fechas[i] <= high) {
                libros[i].toString();
            }
        }
        if (!hoja) {
            C[i].showRangeDetailed(low, high);
        }
    }

    public void insertNonFull(Libro libro) {
        int fechaInt = libro.conversionInt();
        int i = n - 1;

        if (hoja) {
            while (i >= 0 && fechas[i] > fechaInt) {
                libros[i + 1] = libros[i];
                fechas[i + 1] = fechas[i];
                i--;
            }
            libros[i + 1] = libro;
            fechas[i + 1] = fechaInt;
            n = n + 1;
        } else {
            while (i >= 0 && fechas[i] > fechaInt) {
                i--;
            }
            if (C[i + 1].n == 2 * t - 1) {
                splitChild(i + 1, C[i + 1]);
                if (fechas[i + 1] < fechaInt) {
                    i++;
                }
            }
            C[i + 1].insertNonFull(libro);
        }
    }

    public void splitChild(int i, NodoB y) {
        NodoB z = new NodoB(y.t, y.hoja);
        z.n = t - 1;

        for (int j = 0; j < t - 1; j++) {
            z.libros[j] = y.libros[j + t];
            z.fechas[j] = y.fechas[j + t];
        }

        if (!y.hoja) {
            for (int j = 0; j < t; j++)
                z.C[j] = y.C[j + t];
        }

        y.n = t - 1;

        for (int j = n; j >= i + 1; j--)
            C[j + 1] = C[j];

        C[i + 1] = z;

        for (int j = n - 1; j >= i; j--) {
            libros[j + 1] = libros[j];
            fechas[j + 1] = fechas[j];
        }

        libros[i] = y.libros[t - 1];
        fechas[i] = y.fechas[t - 1];
        n = n + 1;
    }

    // Método mejorado para eliminar por año, ISBN y estado
    public boolean eliminarLibro(int año, String ISBN, String estado) {
        List<Integer> indices = new ArrayList<>();
        
        // Buscar todos los libros que coincidan con el año
        for (int i = 0; i < n; i++) {
            if (fechas[i] == año) {
                indices.add(i);
            }
        }
        
        // Buscar el libro específico que coincida con ISBN y estado
        for (int idx : indices) {
            Libro libro = libros[idx];
            if (libro.getISBN().equals(ISBN) && libro.getEstado().equals(estado)) {
                // Encontrado, proceder con la eliminación
                removeFromNode(idx);
                return true;
            }
        }
        
        // Si no se encontró en este nodo, buscar en los hijos
        if (!hoja) {
            for (int i = 0; i <= n; i++) {
                if (C[i].eliminarLibro(año, ISBN, estado)) {
                    // Después de eliminar, verificar si el nodo hijo necesita rebalanceo
                    if (C[i].n < t - 1) {
                        fill(i);
                    }
                    return true;
                }
            }
        }
        
        return false;
    }

    private void removeFromNode(int idx) {
        if (hoja) {
            removeFromLeaf(idx);
        } else {
            removeFromNonLeaf(idx);
        }
    }

    public void remove(int fecha) {
        int idx = findKey(fecha);

        if (idx < n && fechas[idx] == fecha) {
            if (hoja)
                removeFromLeaf(idx);
            else
                removeFromNonLeaf(idx);
        } else {
            if (hoja) {
                System.out.println("La fecha " + fecha + " no existe en el árbol");
                return;
            }

            boolean flag = (idx == n);
            if (C[idx].n < t)
                fill(idx);

            if (flag && idx > n)
                C[idx - 1].remove(fecha);
            else
                C[idx].remove(fecha);
        }
    }

    private void removeFromLeaf(int idx) {
        for (int i = idx + 1; i < n; ++i) {
            libros[i - 1] = libros[i];
            fechas[i - 1] = fechas[i];
        }
        libros[n - 1] = null; // Limpiar referencia
        n--;
    }

    private void removeFromNonLeaf(int idx) {
        int fecha = fechas[idx];
        Libro libro = libros[idx];

        if (C[idx].n >= t) {
            int predFecha = getPredecessor(idx);
            Libro predLibro = findLibroByFecha(C[idx], predFecha);
            fechas[idx] = predFecha;
            libros[idx] = predLibro;
            C[idx].remove(predFecha);
        } else if (C[idx + 1].n >= t) {
            int succFecha = getSuccessor(idx);
            Libro succLibro = findLibroByFecha(C[idx + 1], succFecha);
            fechas[idx] = succFecha;
            libros[idx] = succLibro;
            C[idx + 1].remove(succFecha);
        } else {
            merge(idx);
            C[idx].remove(fecha);
        }
    }

    private Libro findLibroByFecha(NodoB nodo, int fecha) {
        // Buscar recursivamente el libro asociado a una fecha
        if (nodo.hoja) {
            for (int i = 0; i < nodo.n; i++) {
                if (nodo.fechas[i] == fecha) {
                    return nodo.libros[i];
                }
            }
        } else {
            int idx = nodo.findKey(fecha);
            if (idx < nodo.n && nodo.fechas[idx] == fecha) {
                return nodo.libros[idx];
            } else {
                return findLibroByFecha(nodo.C[idx], fecha);
            }
        }
        return null;
    }

    private int getPredecessor(int idx) {
        NodoB cur = C[idx];
        while (!cur.hoja)
            cur = cur.C[cur.n];
        return cur.fechas[cur.n - 1];
    }

    private int getSuccessor(int idx) {
        NodoB cur = C[idx + 1];
        while (!cur.hoja)
            cur = cur.C[0];
        return cur.fechas[0];
    }

    private void fill(int idx) {
        if (idx != 0 && C[idx - 1].n >= t)
            borrowFromPrev(idx);
        else if (idx != n && C[idx + 1].n >= t)
            borrowFromNext(idx);
        else {
            if (idx != n)
                merge(idx);
            else
                merge(idx - 1);
        }
    }

    private void borrowFromPrev(int idx) {
        NodoB child = C[idx];
        NodoB sibling = C[idx - 1];

        for (int i = child.n - 1; i >= 0; --i) {
            child.libros[i + 1] = child.libros[i];
            child.fechas[i + 1] = child.fechas[i];
        }

        if (!child.hoja) {
            for (int i = child.n; i >= 0; --i)
                child.C[i + 1] = child.C[i];
        }

        child.libros[0] = libros[idx - 1];
        child.fechas[0] = fechas[idx - 1];

        if (!child.hoja)
            child.C[0] = sibling.C[sibling.n];

        libros[idx - 1] = sibling.libros[sibling.n - 1];
        fechas[idx - 1] = sibling.fechas[sibling.n - 1];

        child.n += 1;
        sibling.n -= 1;
    }

    private void borrowFromNext(int idx) {
        NodoB child = C[idx];
        NodoB sibling = C[idx + 1];

        child.libros[child.n] = libros[idx];
        child.fechas[child.n] = fechas[idx];

        if (!child.hoja)
            child.C[child.n + 1] = sibling.C[0];

        libros[idx] = sibling.libros[0];
        fechas[idx] = sibling.fechas[0];

        for (int i = 1; i < sibling.n; ++i) {
            sibling.libros[i - 1] = sibling.libros[i];
            sibling.fechas[i - 1] = sibling.fechas[i];
        }

        if (!sibling.hoja) {
            for (int i = 1; i <= sibling.n; ++i)
                sibling.C[i - 1] = sibling.C[i];
        }

        child.n += 1;
        sibling.n -= 1;
    }

    private void merge(int idx) {
        NodoB child = C[idx];
        NodoB sibling = C[idx + 1];

        child.libros[t - 1] = libros[idx];
        child.fechas[t - 1] = fechas[idx];

        for (int i = 0; i < sibling.n; ++i) {
            child.libros[i + t] = sibling.libros[i];
            child.fechas[i + t] = sibling.fechas[i];
        }

        if (!child.hoja) {
            for (int i = 0; i <= sibling.n; ++i)
                child.C[i + t] = sibling.C[i];
        }

        for (int i = idx + 1; i < n; ++i) {
            libros[i - 1] = libros[i];
            fechas[i - 1] = fechas[i];
        }

        for (int i = idx + 2; i <= n; ++i)
            C[i - 1] = C[i];

        child.n += sibling.n + 1;
        n--;
    }
    
    public Libro[] getLibros() {
        return libros;
    }

    public int[] getFechas() {
        return fechas;
    }

    public int getT() {
        return t;
    }

    public NodoB[] getC() {
        return C;
    }

    public int getN() {
        return n;
    }

    public boolean isHoja() {
        return hoja;
    }
    
    
}
