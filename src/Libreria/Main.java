package Libreria;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import java.net.URISyntaxException;
import java.net.URL;


public class Main 
{
 public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException 
 {
     //MarcoRegistro m = new MarcoRegistro();  
     

     VentanaMarco interfaz = new VentanaMarco();

     int x=500;
     int y=500;

     

     String titulo = "Coleccion de Libros";

     String ruta_logo = "Archivos/Logo.jpg";
     String ruta_musica ="src/Archivos/Musica.wav";

     interfaz.configure_ventana(x, y, titulo, ruta_logo);
     
     int cantidad_boton = 3;
     int cantidad_label = 1;

     String [] etiquetas_boton = new String [cantidad_boton];
     String [] etiquetas_label = new String [cantidad_label+1];

     etiquetas_boton[0]="Registrar Libro";
     etiquetas_boton[1]="Buscar Libro";
     etiquetas_boton[2]="Mostrar Libros";


     

     etiquetas_label[0]=titulo;

     etiquetas_label[1] = "Bienvenido a la colección de Libros";

     interfaz.inicie_componentes(cantidad_boton, cantidad_label, etiquetas_boton, etiquetas_label);


     interfaz.configure_boton(x, y,cantidad_boton,etiquetas_boton);

     interfaz.configure_label(x, y, cantidad_label, etiquetas_label);

     interfaz.configure_lamina(x, y, ruta_musica);
      
 }   
}
