/**
 * En esta clase se desarrollan distintas alternativas para
 * resolver problemas básicos de tratamiento de ficheros de
 * datos binarios (ficheros secuenciales). En concreto, se 
 * implementan soluciones para:
 *  - construcción (escritura) de ficheros binarios
 *  - inspección (lectura) de ficheros binarios
 *  - versiones anteriores y posteriores a JAVA7
 *  - distintos tipos de datos primitivos
 *  - origen de datos desde teclado y fichero de texto
 * 
 * @author Alberto Ciriano 
 * @version DIC-2021
 */

import java.io.*;
import java.util.*;

public class Ejercicio07009 {    

    /**
     * Método creaFicheroEnteros1 --> método que genera un
     * fichero de datos ENTEROS que se leen desde teclado.
     * Versión para maquinas java versión 7 o posterior que
     * simplifica la gestion de las excepciones y garantiza
     * el cierre del fichero.
     *
     * @param nombre nombre del fichero que se creará
     */
    public static void creaFicheroEnteros1(String nombre){

        int dato;

        try (DataOutputStream salida = new DataOutputStream(
                new BufferedOutputStream(
                    new FileOutputStream(nombre)))) {

            char opc = Teclado.leerCaracter("Meter números?");

            while (opc =='s' || opc=='S') {
                dato = Teclado.leerEntero("Número: ");
                salida.writeInt(dato);
                opc = Teclado.leerCaracter("Más números? ");
            } 
            //el cierre no es obligatorio segun documentación
            //pero si es MUY RECOMENDABLE como costumbre
            salida.close();
        }
        //la captura de FileNotFoundException no sería
        //imprescindible si no se necesitara detectar la
        //situación de no poder acceder al fichero       
        catch (FileNotFoundException fnfe){
            System.out.printf("%s\n",fnfe.getMessage());
        }
        catch (IOException ioe){
            System.out.printf("%s\n",ioe.getMessage());
        }

    }
    

    /**
     * Método creaFicheroEnteros2 --> este método resuelve
     * el mismo problema que el método anterior. Esta versión
     * es para maquinas java anteriores a java7 y expande la
     * gestión de las excepciones para intentar garantizar 
     * que el fichero se cierre.
     *
     * @param nombre nombre del fichero que se creará
     */
    public static void creaFicheroEnteros2(String nombre){
        
        int dato;
        DataOutputStream salida = null;

        try{
            salida = new DataOutputStream(
                new BufferedOutputStream(
                    new FileOutputStream(nombre)));

            char opc = Teclado.leerCaracter("Meter números?");

            while(opc =='s' || opc=='S'){
                dato = Teclado.leerEntero("Número: ");
                salida.writeInt(dato);
                opc = Teclado.leerCaracter("Más números? ");
            }          
        }
        catch (FileNotFoundException fnfe){
            System.out.printf("%s\n",fnfe.getMessage());
        }
        catch (IOException ioe){
            System.out.printf("%s\n",ioe.getMessage());
        }
        finally{
            if (salida != null) {
                try {
                    salida.close();
                }
                catch (IOException ioe) {
                    System.out.printf("%s\n",ioe.getMessage());
                }
            }
        }        
    }
    

    /**
     * Method creaFicheroEnteros3 --> este método realiza la
     * misma tarea que los dos métodos anteriores. Es una 
     * versión SIMPLIFICADA, funciona para máquinas java
     * anteriores a java7 y puede generar el no cierre del
     * fichero si hubiera algun problema en escritura, aunque
     * cuando se trabaja en LOCAL ES MUY POCO PROBABLE
     */
    public static void creaFicheroEnteros3(String nombre){
        int dato;
        DataOutputStream salida = null;

        try{
            salida = new DataOutputStream(
                new BufferedOutputStream(
                    new FileOutputStream(nombre)));

            char opc = Teclado.leerCaracter("Meter números?");

            while(opc == 's' || opc == 'S'){
                dato = Teclado.leerEntero("Número: ");
                salida.writeInt(dato);
                opc = Teclado.leerCaracter("Más números? ");
            }   
            salida.close();
        }
        catch (IOException ioe){
            System.out.printf("%s\n",ioe.getMessage());
        }
    }

    
    /**
     * Método creaFicheroReales --> método que genera un
     * fichero de datos REALES que se leen desde teclado.
     * Versión para maquinas java versión 7 o posterior que 
     * simplifica la gestion de las excepciones y garantiza
     * el cierre del fichero.
     *
     * @param nombre nombre del fichero que se creará
     */
    public static void creaFicheroReales(String nombre){

        double dato;

        try (DataOutputStream salida = new DataOutputStream(
                new BufferedOutputStream(
                    new FileOutputStream(nombre)))) {

            char opc = Teclado.leerCaracter("Meter números?");

            while (opc =='s' || opc=='S') {
                dato = Teclado.leerReal("Número: ");
                salida.writeDouble(dato);
                opc = Teclado.leerCaracter("Más números? ");
            } 
            
            salida.close();
        }
        //mismos comentarios que en el método
        //creaFicheroEnteros1
        catch (FileNotFoundException fnfe){
            System.out.printf("%s\n",fnfe.getMessage());
        }
        catch (IOException ioe){
            System.out.printf("%s\n",ioe.getMessage());
        }
        
    }
    

    /**
     * Método creaFichRealesDesdeTexto --> este método crea
     * un fichero binario de datos reales procedentes de un
     * fichero de texto. Se supondrá que el fichero de origen
     * solo "contiene" valores reales separados por espacios
     * en blanco (uno o más).
     * Para el tratamiento del fichero de texto se utiliza uno
     * de los dos esquemas propuestos en la asignatura y para
     * el fichero binario se utiliza el esquema propuesto
     * para java 7 o posterior.
     *
     * @param nom1 nombre del fichero de texto de origen
     * @param nom2 nombre del fichero binario de destino
     */
    public static void creaFichRealesDesdeTexto(String nom1,
                                                String nom2){

        double dato;
        Scanner f = null;

        try {
            f = new Scanner(new File(nom1));
        }
        catch (FileNotFoundException fnfe){
            System.out.println(fnfe.getMessage());
        }

        if (f != null) {

            try(DataOutputStream salida= new DataOutputStream(
                    new BufferedOutputStream(
                        new FileOutputStream(nom2)))) {

                while (f.hasNextLine()) {
                    Scanner linea = new Scanner(f.nextLine());
                    linea.useLocale(Locale.ENGLISH);
                    //para que se entiendan con punto decimal
                    while (linea.hasNext()) {
                        dato = linea.nextDouble();
                        salida.writeDouble(dato);
                    } 
                }
                salida.close();
            }
            catch (FileNotFoundException fnfe){
                System.out.printf("%s\n",fnfe.getMessage());
            }
            catch (IOException ioe){
                System.out.printf("%s\n",ioe.getMessage());
            }
            f.close();
        }
    }


    /**
     * Método muestraFicheroEnteros1 --> método que muestra
     * por pantalla el contenido de un fichero de ENTEROS.
     * Versión para maquinas java versión 7 o posterior que 
     * simplifica la gestion de las excepciones y garantiza
     * el cierre del fichero.
     *
     * @param nombre nombre del fichero que se inspeccionará
     */
    public static void muestraFicheroEnteros1(String nombre){
        int dato;
        boolean eof = false;

        try (DataInputStream entrada = new DataInputStream(
                new BufferedInputStream(
                    new FileInputStream(nombre)))) {

            while(!eof){
                //hay que capturar EOFException ya que, en
                //caso de llegar al final del fichero en la
                //lectura, se lanzaría IOException sin que se
                //hubiera producido ningun error
                try{
                    dato=entrada.readInt();
                    System.out.println(dato);
                }
                catch(EOFException eofe){
                    eof = true;
                }
            } 
            //no es necesario el cierre del fichero en modo
            //java 7 o posterior pero si es recomendable
            entrada.close();
        }
        catch (FileNotFoundException fnfe){
            //el compilador NO SE QUEJA si no lo ponemos pero
            //sería necesario ponerlo si queremos hacer algun
            //tratamiento diferenciado
            System.out.println("el fichero no es accesible");
        }
        catch (IOException ioe){
            //esta, en cambio, hay que ponerla
            System.out.println("error E/S en lectura");
        }
    }
    

    /**
     * Método muestraficheroEnteros2 --> este método hace
     * la misma tarea que el anterior aunque también funciona
     * para máquinas java anteriores a java7. En esta versión
     * se hace un tratamiento completo de excepciones para 
     * intentar garantizar el cierre del archivo.
     *
     * @param nombre nombre del fichero que se inspeccionará
     */   
    public static void muestraficheroEnteros2 (String nombre){
        
        int dato;
        boolean eof = false;
        DataInputStream entrada = null;

        try{
            entrada = new DataInputStream(
                new BufferedInputStream(
                    new FileInputStream(nombre)));

            while(!eof){
                try{
                    dato=entrada.readInt();
                    System.out.println(dato);
                }
                catch(EOFException eofe){
                    eof = true;
                }
            }
        }
        catch (FileNotFoundException fnfe){
            System.out.println("el fichero no es accesible");
        }
        catch (IOException ioe){
            System.out.println("error de lectura en entrada");
        }
        finally{
            if (entrada != null) {
                try {
                    entrada.close();
                }
                catch (IOException ioe) {
                    System.out.println("error E/S en cierre");
                }
            }
        }        
    }
    
    
    /**
     * Método muestraficheroEnteros3 --> este método realiza
     * la misma tarea que los dos anteriores. Gestiona las 
     * excepciones de una manera más enrevesada de forma que,
     * aunque funciona, la convierte en una version MENOS
     * INTERESANTE que las anteriores. Funciona también para 
     * versiones de java anteriores a java 7.
     *
     * @param nombre nombre del fichero que se inspeccionará
     */
    public static void muestraficheroEnteros3(String nombre){
        int dato;
        boolean eof = false;

        try{
            DataInputStream entrada = new DataInputStream(
                    new BufferedInputStream(
                    new FileInputStream(nombre)));
            try{                     
                while(!eof){
                    dato=entrada.readInt();
                    System.out.println(dato);
                }                
            }
            catch (EOFException eofe){
                // es necesario CAPTURARLA AUNQUE NO SE HAGA 
                // NADA, ya que si la lectura llega al final 
                // del fichero, saltaria la IOException sin
                // haberse producido ningun error
            }
            catch (IOException ioe){
                System.out.println("error E/S en lectura");
            }
            finally{
                if (entrada != null) {
                    try {
                        entrada.close();
                    }
                    catch (IOException ioe) {
                        System.out.println("error en cierre");
                    }
                }
            }
        }
        catch (FileNotFoundException fnfe){
            System.out.println("el fichero no es accesible");
        }
    }

    
    /**
     * Método muestraFicheroReales --> método que muestra
     * por pantalla el contenido de un fichero de REALES.
     * Versión para maquinas java versión 7 o posterior que 
     * simplifica la gestion de las excepciones y garantiza
     * el cierre del fichero.
     *
     * @param nombre nombre del fichero que se inspeccionará
     */
    public static void muestraFicheroReales(String nombre){
        
        double dato;
        boolean eof = false;

        try (DataInputStream entrada = new DataInputStream(
                    new BufferedInputStream(
                    new FileInputStream(nombre)))) {

            while(!eof){
                try{
                    dato=entrada.readDouble();
                    System.out.println(dato);
                }
                catch(EOFException eofe){
                    eof = true;
                }
            } 

            entrada.close();
        }
        
        catch (FileNotFoundException fnfe){
            System.out.println("el fichero no es accesible");
        }
        catch (IOException ioe){
            System.out.println("error E/S en lectura");
        }
    }
    

    public static void main (String [] args){
        /*
         * instrucciones de prueba de los métodos anteriores
         */
    }
}
