package Libreria;

import javax.swing.JLabel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Eventos 
{
    public int indice;
    public Libro buscarLibro()
    {
        String [] opciones = {"Codigo","Nombre"};
  
        String  opcion = (String) JOptionPane.showInputDialog(
                            null, 
                            "Selecciona por cual caracteristica deseas buscar el Libro", 
                            "Busqueda", 
                            JOptionPane.INFORMATION_MESSAGE, 
                            null, 
                            opciones, opciones[0]);

        ConjuntoLibros lista = new ConjuntoLibros();
        lista.cargarLibros();  
        Libro libro;                  
        String aux;
        switch(opcion)
        {
            case "Codigo":
            aux = JOptionPane.showInputDialog(null, "Ingresa el codigo del Libro que buscas");
            for(int i=0 ; i<lista.total(); i++)
            {
                libro = lista.obtenerLibro(i);
                if(libro.dimeCodigo() == (Integer.parseInt(aux)))
                {
                    indice=i;
                    return libro;
                }
            }
            break;

            case "Nombre":
            aux = JOptionPane.showInputDialog(null, "Ingresa el nombre del Libro que buscas");
            for(int i=0 ; i<lista.total(); i++)
            {
                libro = lista.obtenerLibro(i);
                if(aux.equalsIgnoreCase(libro.dimeNombre()))
                {
                    return libro;
                }
            }
            break;
        }
        return null;
    }

    public void mostrarLibro(Libro libro)
    {
        MarcoLibro mostrar = new MarcoLibro(libro, indice);
    }
}

class MarcoLibro extends JFrame
{
    private JLabel [] label;
    private JPanel [] panel;
    private JTextArea sinopsis;
    private JButton[] boton;

    public MarcoLibro(Libro libro, int indice)
    {
        setTitle("Libro");
        setSize(600,450);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        Lamina(libro,indice);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private void Lamina(Libro libro, int indice)
    {
        
            label = new JLabel[7];
            panel = new JPanel[4];
            boton = new JButton[3];
            sinopsis = new JTextArea();
            String [] etiquetas = new String[6];

            

            boton[0] = new JButton("Borrar Libro");
            boton[1] = new JButton(new ImageIcon("src/Archivos/Flecha.png"));
            boton[2] = new JButton(new ImageIcon("src/Archivos/Flecha2.png"));

            etiquetas[0] = "Codigo: " +String.valueOf(libro.dimeCodigo());
            etiquetas[1] = libro.dimeNombre();
            etiquetas[2] = "Autor: " +libro.dimeAutor();
            etiquetas[3] = "Sinopsis: " +libro.dimeSinopsis();
            etiquetas[4] = "Páginas #: " +String.valueOf(libro.dimeNumeroPaginas());
            etiquetas[5] = "Calificacion: " +String.valueOf(libro.dimeCalificacion());
    
            for(int i=0 ; i<4 ; i++)
            {
                panel[i] = new JPanel();
            }
            for(int i=0 ; i<6 ; i++)
            {
                label[i] = new JLabel(etiquetas[i]);
            }
            for(int i=0; i<6; i++)
            {
                label[i].setFont(new Font("Arial",0,20));
            }
            label[6] = new JLabel();
            label[6].setIcon(libro.dimePortada());
    
            panel[0].setPreferredSize(new Dimension((550),(50)));
            panel[0].setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
            //panel[0].setBackground(Color.LIGHT_GRAY);
            label[1].setFont(new Font("Arial",0,30));
            panel[0].add(label[1]);
    
            panel[1].setPreferredSize(new Dimension((250),(200)));
            panel[1].setLayout(new FlowLayout());
            //panel[1].setBackground(Color.LIGHT_GRAY);
            panel[1].add(label[6]);
    
            panel[2].setPreferredSize(new Dimension((250),(200)));
            panel[2].setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
            //panel[2].setBackground(Color.LIGHT_GRAY);
            
            for(int i=0; i<6; i++)
            {
                if(i!=1)
                {
                    if(i!=3)
                    {
                        label[i].setPreferredSize(new Dimension((240),(23)));
                        panel[2].add(label[i]);
                    }
                    
                }  
            }

            panel[3].setPreferredSize(new Dimension((550),(100)));
            panel[3].setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
            //panel[3].setBackground(Color.LIGHT_GRAY);
            sinopsis.append(etiquetas[3]);
            sinopsis.setFont(new Font("Arial",0,20));
            sinopsis.setLineWrap(true);
            sinopsis.setWrapStyleWord(true);
            sinopsis.setEditable(false);
            sinopsis.setBackground(null);
            sinopsis.setPreferredSize(new Dimension((550),(100)));
            panel[3].add(sinopsis);

            for(int i=0;i<3;i++)
            {
                boton[i].addActionListener(new EventoRegistrar(indice));
            }
           
            add(panel[0]);
            add(panel[1]);
            add(panel[2]);
            add(panel[3]);
            add(boton[2]);
            add(boton[0]);
            add(boton[1]);
    }

    private class EventoRegistrar implements ActionListener
    {
        private int indice;

        public EventoRegistrar(int indice)
        {
            this.indice = indice;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == boton[0])
            {
                int eliminar = JOptionPane.showConfirmDialog(null, "Seguro que quieres borrar el Libro?", "Borrar", JOptionPane.YES_NO_OPTION);
                
                if(eliminar==0)
                {
                    dispose();  
                    ConjuntoLibros libros = new ConjuntoLibros();
                    libros.cargarLibros();
                    libros.eliminarLibro(indice);
                    Libro libro;
                    libro = libros.obtenerLibro(indice);
                    MarcoLibro marco = new MarcoLibro(libro, indice);
                }
                
            }

            if(e.getSource() == boton[1])
            {
                dispose();  
                ConjuntoLibros libros = new ConjuntoLibros();
                libros.cargarLibros();
                Libro libro;
                try 
                {
                    libro = libros.obtenerLibro(indice+1);
                    MarcoLibro marco = new MarcoLibro(libro, indice+1);
                } 
                catch (Exception error) 
                {
                    error.printStackTrace();
                    libro = libros.obtenerLibro(indice-1);
                    MarcoLibro marco = new MarcoLibro(libro, indice-1);
                }
                
            }

            if(e.getSource() == boton[2])
            {
                dispose();  
                ConjuntoLibros libros = new ConjuntoLibros();
                libros.cargarLibros();
                Libro libro;
                try 
                {
                    libro = libros.obtenerLibro(indice-1);
                    MarcoLibro marco = new MarcoLibro(libro, indice-1);
                } 
                catch (Exception error) 
                {
                    error.printStackTrace();
                    libro = libros.obtenerLibro(indice+1);
                    MarcoLibro marco = new MarcoLibro(libro, indice+1);
                }
                
            }
        }
    }
}

