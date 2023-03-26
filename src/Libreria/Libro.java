package Libreria;

import javax.swing.*;

public class Libro 
{
    //Atributos del Libro
    private int codigo;
    private String nombre;
    private String autor;
    private String sinopsis;
    private int numeroPaginas;
    private int calificacion;
    private Icon portada;


    //Constructor
    public Libro(int codigo, String nombre, String autor, String sinopsis, int numeroPaginas, int calificacion, Icon portada)
    {
        this.codigo = codigo;
        this.nombre = nombre;
        this.autor = autor;
        this.sinopsis = sinopsis;
        this.numeroPaginas = numeroPaginas;
        this.calificacion = calificacion;
        this.portada = portada;

        
    }


    //Metodos setter

    public int dimeCodigo()
    {
        return codigo;
    }

    public String dimeNombre()
    {
        return nombre;
    }

    public String dimeAutor()
    {
        return autor;
    }

    public String dimeSinopsis()
    {
        return sinopsis;
    }

    public int dimeNumeroPaginas()
    {
        return numeroPaginas;
    }

    public int dimeCalificacion()
    {
        return calificacion;
    }

    public Icon dimePortada()
    {
        return portada;
    }

    //Metodos getter

    public void darCodigo(int codigo)
    {
        this.codigo = codigo;
    }

    public void darNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void darAutor(String autor)
    {
        this.autor = autor;
    }

    public void darSinopsis(String sinopsis)
    {
        this.sinopsis = sinopsis;
    }

    public void darNumeroPaginas(int numeroPaginas)
    {
        this.numeroPaginas = numeroPaginas;
    }

    public void darCalificacion(int calificacion)
    {
        this.calificacion = calificacion;
    }

    public void darPortada(Icon portada)
    {
        this.portada = portada;
    }
}

