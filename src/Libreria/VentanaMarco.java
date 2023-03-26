package Libreria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class VentanaMarco 
{
    //Elementos escenciales     //Elementos de eventos      //Etiquetas              
    private JFrame ventana1;    private JButton boton[];    private JLabel label1;     
    private JPanel panel1;                                  private JLabel label[];
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panelA;
    Eventos eventos;

    public VentanaMarco()
    {
       eventos = new Eventos();
    }

    public Image logo_ventana(String ruta_logo)
    {
        //El icono debe tener dimensiones cuadradas, ej 200x200 / 400x400

        Image logo = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource(ruta_logo));
        return logo;
    }

    public Clip musica_fondo(String ruta_musica) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        File archivo_wav = new File(ruta_musica);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivo_wav);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        FloatControl volumen = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        double porcentaje = 0.2;
        float dB = (float) (Math.log(porcentaje)/ Math.log(10.0)*(20.0));
        volumen.setValue(dB);

        return clip;
    }

    public void configure_ventana(int x, int y, String titulo, String ruta_logo)
    {
        
        
        ventana1 = new JFrame();
        ventana1.setSize(x,y); 
        //(x) es el ancho de la ventana (y) el alto de la ventana
        ventana1.setLocationRelativeTo(null);
        ventana1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana1.setTitle(titulo);
        ventana1.setIconImage(logo_ventana(ruta_logo));
        ventana1.setResizable(false);
        ventana1.setVisible(false);
    }
    
    public void configure_lamina(int x, int y, String ruta_musica) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
    
            int x2 = (int) (x*0.15);    int x3 = (int) (x*0.2);     int x4 = (int) (x*0.1);
            int y2 = (int) (y*0.4);     int y3 = (int) (y*0.90);    int y4 = (int) (y*0.85);

            Clip musica = musica_fondo(ruta_musica);

            ventana1.setLayout(new FlowLayout());

            panelA.setPreferredSize(new Dimension((x),(y)));
            panelA.setLayout(new FlowLayout());

            panel1.setPreferredSize(new Dimension((x-x2),(y-y2)));
            //panel1.setBackground(Color.LIGHT_GRAY);
            panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));            
          
            panel2.setPreferredSize(new Dimension((x-x3),(y-y3)));
            //panel2.setBackground(Color.LIGHT_GRAY);
            panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

            panel3.setPreferredSize(new Dimension((x-x4),(y-y4)));
            //panel3.setBackground(Color.LIGHT_GRAY);
            panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));  




            panelA.add(panel3);
            panelA.add(panel2);
            panelA.add(panel1);
            ventana1.add(panelA);
            ventana1.setVisible(true);   

            musica.loop(musica.LOOP_CONTINUOUSLY);
            
    }


    public void inicie_componentes(int cantidad_boton, int cantidad_label, String  etiquetas_boton[], String etiquetas_label[])
    {
        panel1 = new JPanel();  panel2 = new JPanel();  panel3 = new JPanel(); panelA = new JPanel();
        
        boton = new JButton[cantidad_boton];

        for(int i=0 ; i<cantidad_boton ; i++)
        {
            boton[i] = new JButton(etiquetas_boton[i]);
        }

        label1 = new JLabel();
        label = new JLabel[cantidad_label];

        for(int i=0 ; i<cantidad_label ; i++)
        {
            label[i] = new JLabel(etiquetas_label[i+1]);
        }
    }

    public void configure_boton(int x, int y, int cantidad_boton, String etiquetas_boton[])
    {
        
        String fuente = "Arial";    int tf=0;

        for(int i=0 ; i<cantidad_boton ; i++)
        {
            if(etiquetas_boton[i].length()<=15)
            {
                tf=30;
                boton[i].setFont(new Font(fuente,0,tf));
            }

            if(etiquetas_boton[i].length()>15)
            {
                tf=25;
                boton[i].setFont(new Font(fuente,0,tf));
            }
        }

        for(int i=0 ; i<cantidad_boton ; i++)
        {
            boton[i].addActionListener(new Cofigure_eventos());
            panel1.add(boton[i]);
        }    
    }


    public void configure_label(int x, int y, int cantidad_label, String etiquetas_label[])
    {
        String fuente = "Arial";    int tf=0;

        label1.setText(etiquetas_label[0]);

        if(etiquetas_label[0].length()<15)
        {
            tf=45;
            label1.setFont(new Font(fuente,0,tf));
        }

        if(etiquetas_label[0].length()>15)
        {
            tf=40;
            label1.setFont(new Font(fuente,0,tf));
        } 

        if(x==500)
        {
            tf=35;
            label1.setFont(new Font(fuente,0,tf));
        }

        for(int i=0 ; i<cantidad_label ; i++)
        {
            if(etiquetas_label[i].length()<=15)
            {
                tf=25;
                label[i].setFont(new Font(fuente,0,tf));
            }

            if(etiquetas_label[i].length()>15)
            {
                tf=20;
                label[i].setFont(new Font(fuente,0,tf));
            } 
        }

        panel3.add(label1);
        
        for(int i=0 ; i<cantidad_label ; i++)
        {
            panel2.add(label[i]);
        }  

    }


    private class Cofigure_eventos implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {

            if(e.getSource() == boton[0])
            {
                Toolkit.getDefaultToolkit().beep();
                MarcoRegistro marco = new MarcoRegistro();
            }       

            if(e.getSource() == boton[1])
            {
                Toolkit.getDefaultToolkit().beep();
                ConjuntoLibros c = new ConjuntoLibros();
                c.cargarLibros();;
                Eventos evento = new Eventos();
                Libro libro = evento.buscarLibro();

                evento.mostrarLibro(libro);

            }

            if(e.getSource() == boton[2])
            {
                Toolkit.getDefaultToolkit().beep();
                ConjuntoLibros c = new ConjuntoLibros();
                c.cargarLibros();;
                Eventos evento = new Eventos();
                try 
                {
                    Libro libro = c.obtenerLibro(0);
                    evento.mostrarLibro(libro);
                } 
                catch (Exception error) 
                {
                    JOptionPane.showMessageDialog(null, "No hay libros registrados");
                }
                

                
            }
            
        } 
    }
}

class MarcoRegistro extends JFrame
{
    public MarcoRegistro()
    {
        setTitle("Registrar Libro");
        setSize(400,300);
        setLocationRelativeTo(null);

        LaminaRegistro lamina = new LaminaRegistro();
        add(lamina);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
}

class LaminaRegistro extends JPanel
{
    private JTextField [] texto;
    private JLabel [] label;
    private JButton registrar;

    public LaminaRegistro()
    {
        String [] etiqueta = new String[6];
        registrar = new JButton("Registrar Libro");

        texto = new JTextField[6]; label = new JLabel[6];
        setLayout(new FlowLayout());

        etiqueta[0]=("Codigo");
        etiqueta[1]=("Nombre");
        etiqueta[2]=("Autor");
        etiqueta[3]=("Sinopsis");
        etiqueta[4]=("Páginas #");
        etiqueta[5]=("Calificacion");

        for(int i=0 ; i<6 ; i++)
        {
            label[i] = new JLabel(etiqueta[i]);
            label[i].setFont(new Font("Arial",0,21));
            label[i].setPreferredSize(new Dimension(110,30));

        }

        for(int i=0 ; i<6 ; i++)
        {
            texto[i]=new JTextField();
            texto[i].setFont(new Font("Arial",0,20));
            texto[i].setPreferredSize(new Dimension(225,30));
        }

        for(int i=0 ; i<6 ; i++)
        {
            add(label[i]);
            add(texto[i]);
        }

        registrar.setPreferredSize(new Dimension(225,30));
        registrar.setFont(new Font("Arial",0,20));
        registrar.addActionListener(new EventoRegistrar());
        add(registrar);

    }

    private class EventoRegistrar implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JFileChooser escogerArchivo = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG & PNG","jpg","png");
            escogerArchivo.setFileFilter(filtro);

            String nombre="", autor="", sinopsis="",ruta="";
            int codigo=0,numeroPaginas=0,calificacion=0;
            int respuesta,contador=0;
            boolean correctoDato = false;

            if(e.getSource() == registrar)
            {
                for(int i=0; i<6 ; i++)
                {
                    switch (i)
                    {
                        case 0:
                        try
                        {
                            codigo = Integer.parseInt(texto[0].getText());
                            if(codigo>0)
                            {
                                contador++;  
                            }   
                            else
                            {
                                JOptionPane.showMessageDialog(null, "El codigo deber ser mayor a 0");  
                            }  
                        }
                        catch (Exception error)
                        {
                            JOptionPane.showMessageDialog(null, "En codigo has puesto caracteres cuando solo se permiten números");  
                        }
                        break;
    
                        case 1:
                        nombre = texto[1].getText();
                        if(nombre.length()>0)
                        {
                            contador++;      
                        }
                        else
                        {  
                            JOptionPane.showMessageDialog(null, "En nombre no escribiste nada ;-;");    
                        }
                        break;
    
                        case 2:
                        autor = texto[2].getText();
                        if(autor.length()>0)
                        {
                            contador++;      
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "En autor no escribiste nada ;-;");    
                        }
                        break;
    
                        case 3:
                        sinopsis = texto[3].getText();
                        if(sinopsis.length()>0)
                        {
                            contador++;     
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "En sinopsis no escribiste nada ;-;");    
                        }
                        break;
    
                        case 4:
                        try
                        {
                            numeroPaginas = Integer.parseInt(texto[4].getText());
                            if(numeroPaginas>0)
                            {
                                contador++;  
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "El número de páginas deber ser mayor a 0");  
                            }        
                        }
                        catch (Exception error)
                        {
                            JOptionPane.showMessageDialog(null, "En número de páginas has puesto caracteres cuando solo se permiten números"); 
                        }
                        break;
    
                        case 5:
                        try
                        {
                            calificacion = Integer.parseInt(texto[5].getText());
                            if(calificacion>=0)
                            {
                                contador++;  
                            }  
                            else
                            {
                                JOptionPane.showMessageDialog(null, "La calificación deber ser mayor o igual a 0");  
                            }      
                        }
                        catch (Exception error)
                        {
                            JOptionPane.showMessageDialog(null, "En calificación has puesto caracteres cuando solo se permiten números");  
                        }
                        break;
                    } 
                } 

                if(contador == 6)
                {
                    correctoDato = true;
                }
            
                if(correctoDato == false)
                {
                    JOptionPane.showMessageDialog(null, "El libro que deseas ingresar no cumple con los parametros\nRecuerda en codigo, número de páginas y calificacion debes de poner solo números\n(en caso de codigo y número de páginas solo números mayores a 0 y en caso de calificación números mayores o iguales a 0)\ny no puedes dejar campos vacios"); 
                }
                
                if(correctoDato == true)
                {
                    JOptionPane.showMessageDialog(null, "Selecciona la portada del libro\nRecuerda debe ser una imagen"); 
                    respuesta= escogerArchivo.showOpenDialog(null);
                    
                    if(respuesta == JFileChooser.APPROVE_OPTION)
                    {
                        ruta = escogerArchivo.getSelectedFile().getPath();
                        

                        Icon portada= new ImageIcon(ruta);
                        
                        
                        Libro libro = new Libro(codigo, nombre, autor, sinopsis, numeroPaginas, calificacion,portada);
                        
                        DarLista lista = new DarLista();

                        if(lista.registrarLibro(libro))
                        {
                            JOptionPane.showMessageDialog(null, "Libro registrado");
                            for(int i=0;i<6;i++)
                            {
                                texto[i].setText("");
                            }
                        }
                    }
                }
               
            }
            
            
        }
    }
}



