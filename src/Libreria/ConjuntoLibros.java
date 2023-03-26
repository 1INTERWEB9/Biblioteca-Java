package Libreria;

import java.util.*;

public class ConjuntoLibros 
{
    private ArrayList<Libro> libros;

    public void agregarLibro(Libro libro)
    {
        libros.add(libro);
    }

    public void eliminarLibro(int indice)
    {
        libros.remove(indice);
        guardar();
    }

    public int total()
    {
        return libros.size();
    }

    public Libro obtenerLibro(int indice)
    {
        return libros.get(indice);
    }

    public void cargarLibros()
    {
        DarLista array = new DarLista();
        libros = array.obtener();
    }

    public void imprimir()
    {
        for(int i=0 ; i<libros.size() ; i++)
        {
            System.out.println(libros.get(i).dimeNombre() + " " + libros.get(i).dimeSinopsis());
        }
    }

    public void guardar()
    {
        DarLista array = new DarLista();
        array.borrarTodo();
        for(int i=0; i<libros.size() ; i++)
        {
            array.registrarLibro(libros.get(i));
        }
    }
}
