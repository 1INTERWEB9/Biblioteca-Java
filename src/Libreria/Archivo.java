package Libreria;

import javax.swing.ImageIcon;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.*;

//import java.io.FileReader;
//import java.util.ArrayList;
//import java.net.URISyntaxException;
//import java.net.URL;

public class Archivo 
{
    private String nombre;
    
    public Archivo(String nombre)
    {
        this.nombre = nombre;
    }

    private File obtenerArchivo()
    {
        try
        {
            URL url = getClass().getClassLoader().getResource("Archivos/"+nombre);
            return new File(url.toURI());
        }
        catch (URISyntaxException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> leerArchivo()
    {
        ArrayList<String> datos = null;

        try
        {
            File archivo = obtenerArchivo();

            if(archivo.exists())
            {
                datos = new ArrayList<>();
                BufferedReader buffer = new BufferedReader(new FileReader(archivo));
                String linea;

                while((linea = buffer.readLine()) != null)
                {
                    //Verificamos que se esta leyendo el archivo
                    //System.out.println(linea);
                    datos.add(linea);
                }
                buffer.close();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "No encontramos el archivo de texto");
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Se produjo un ERROR");
        }

        return datos;
    }

    public boolean registrar(String linea)
    {
        File archivo = obtenerArchivo();

        try
        {
            if(archivo.exists())
            {
                FileWriter escribir = new FileWriter(archivo, true);
                BufferedWriter buffer = new BufferedWriter(escribir);
                PrintWriter imprimir = new PrintWriter(buffer);

                imprimir.println(linea);
                imprimir.flush();
                imprimir.close();
                
                return true;
            }
        }
        catch (Exception error)
        {
            error.printStackTrace();
        }
        return false;
    }

    public boolean borrar()
    {
        File archivo = obtenerArchivo();
        archivo.delete();
        try 
        {
            return archivo.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

class DarLista 
{
    public ArrayList<Libro> obtener()
    {
        ArrayList<Libro> libros = null;
        Archivo archivo = new Archivo("Libros.txt");
        ArrayList<String> lineas = archivo.leerArchivo();

        if(lineas != null)
        {
            libros = new ArrayList<>();

            for(int i=0 ; i< lineas.size() ; i++)
            {
                String linea = lineas.get(i);
                StringTokenizer separador = new StringTokenizer(linea, ";");
                int codigo = Integer.parseInt(separador.nextToken());
                String nombre = separador.nextToken();
                String autor = separador.nextToken();
                String sinopsis = separador.nextToken();
                int numeroPaginas = Integer.parseInt(separador.nextToken());
                int calificacion = Integer.parseInt(separador.nextToken());
                Icon portada = new ImageIcon(separador.nextToken());
                libros.add(new Libro(codigo, nombre, autor, sinopsis, numeroPaginas, calificacion, portada));
            }
        }
        return libros;
    }

    public boolean registrarLibro(Libro libro)
    {
        Archivo archivo = new Archivo("Libros.txt");
        return archivo.registrar(libro.dimeCodigo()+";"
        +libro.dimeNombre()+";"
        +libro.dimeAutor()+";"
        +libro.dimeSinopsis()
        +";"+libro.dimeNumeroPaginas()+";"
        +libro.dimeCalificacion()+";"
        +libro.dimePortada());
    }

    public boolean borrarTodo()
    {
        Archivo archivo = new Archivo("Libros.txt");
        return archivo.borrar();
    }


}