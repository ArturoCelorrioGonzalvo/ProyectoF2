package listas;

/**
 * clase para utilizar los métodos de la clase ListaEnteros
 * 
 * @author Alberto Ciriano 
 * @version ABR18
 */

import java.util.*;

public class GestionLEnteros
{

    public static void muestraMenu(){
        System.out.println("===========================================");
        System.out.println("            MENU DE OPERACIONES            ");
        System.out.println("===========================================");
        System.out.println("0  -- Finalizar");
        System.out.println("1  -- Ver datos de la tabla");
        System.out.println("2  -- Añadir dato al final");
        System.out.println("3  -- Añadir dato al principio");
        System.out.println("4  -- Añadir dato en posición K");
        System.out.println("5  -- Añadir dato en vector ordenado");
        System.out.println("6  -- Quitar dato del principio");
        System.out.println("7  -- Quitar dato del final");
        System.out.println("8  -- Quitar dato de posición K");
        System.out.println("9  -- Quitar un dato determinado");
        System.out.println("10 -- Quitar todos los iguales a uno dado");
        System.out.println("11 -- Búsqueda lineal en vector no ordenado");
        System.out.println("12 -- Búsqueda lineal exhaustiva");
        System.out.println("13 -- Búsqueda lineal en vector odenado");
        System.out.println("14 -- Búsqueda dicotómica en vector odenado");
        System.out.println("15 -- Fusión de vectores ordenados");
        System.out.println("16 -- Ordenación por Inserción directa");
        System.out.println("17 -- Ordenación por Selección directa");
        System.out.println("18 -- Ordenación por Intercambio directo");
        System.out.println("===========================================");
    }
    

    public static void main(String [] argumentos) {

        ListaEnteros lista = null;

        int operacion ;
        int max , dato , posicion , cont ;
        boolean hecho , existe ;

        do {
            //solicitamos el tamaño
            max = Teclado.leerEntero("Escriba el tamaño máximo para la lista: ");
        } while ( max <= 0 );

        System.out.println ();
        System.out.println ();

        //creamos la lista
        lista = new ListaEnteros(max) ;

        do {
            muestraMenu();
            operacion = Teclado.leerEntero ("Seleccione una operación [0-18]: " );
            System.out.println("===================");
            switch (operacion) {
                case 1: 
                    lista.muestraLista();
                    break;
                case 2:
                    if (!lista.listaLLena()){
                        dato = Teclado.leerEntero("Introducir dato a añadir: ");
                        lista.anadeAlFinal(dato);
                    }
                    else
                        System.out.println("La lista estaba llena");
                    break;
                case 3:
                    if (!lista.listaLLena()){
                        dato = Teclado.leerEntero("Introducir dato a añadir: ");
                        lista.anadeAlPrincipio(dato);
                    }
                    else
                        System.out.println("La lista estaba llena");
                    break;                    
                case 4:
                    if (!lista.listaLLena()){
                        posicion = Teclado.leerEntero("Introducir la posición: ");
                        dato = Teclado.leerEntero("Introducir dato a añadir: ");
                        lista.anadeEnPosicion(dato,posicion);
                    }
                    else
                        System.out.println("La lista estaba llena");
                    break;   
                case 5:
                    if (!lista.listaLLena()){
                        dato = Teclado.leerEntero("Introducir dato a añadir: ");
                        lista.anadeOrdenado(dato);        
                    }
                    else
                        System.out.println("La lista estaba llena");
                    break;
                case 6:
                    if (!lista.listaVacia())
                        lista.quitarDelPrincipio();                       
                    else
                        System.out.println("La lista estaba vacía");
                    break;
                case 7:
                    if (!lista.listaVacia())
                        lista.quitarDelFinal(); 
                    else
                        System.out.println("La lista estaba vacía");
                    break;
                case 8:
                    if (! lista.listaVacia()){
                        posicion = Teclado.leerEntero("Introducir la posición: ");
                        lista.quitarDePosicion(posicion);
                    }
                    else
                        System.out.println("La lista estaba vacía");
                    break; 
                case 9:
                    dato = Teclado.leerEntero("Introducir dato a quitar: ");
                    lista.quitarPrimeraAparicion(dato);
                    break;
                case 10:
                    dato = Teclado.leerEntero("Introducir dato a quitar: ");
                    lista.quitarTodosIguales(dato);
                    break;
                case 11:
                    dato = Teclado.leerEntero("Introducir dato a buscar: ");
                    existe = lista.existeDato(dato);
                    if (existe)                        
                        System.out.println("Existe el dato buscado");
                    else
                        System.out.println("No existe el dato buscado");
                    break;
                case 12:
                    dato = Teclado.leerEntero("Introducir dato a buscar: ");
                    cont = lista.numeroApariciones(dato);
                    System.out.println("Existen " + cont + " apariciones del dato");
                    break;
                case 13:
                    dato = Teclado.leerEntero("Introducir dato a buscar: ");
                    existe = lista.existeDatoOrdenado(dato);
                    if (existe)                        
                        System.out.println("Existe el dato buscado");
                    else
                        System.out.println("No existe el dato buscado");
                    break;
                case 14:
                    dato = Teclado.leerEntero("Introducir dato a buscar: ");
                    posicion = lista.posicionDeDatoOrdenado(dato);
                    if (posicion >= 1 && posicion <= lista.numeroDatos())                        
                        System.out.println("Existe el dato buscado en  " + posicion);
                    else
                        System.out.println("No existe el dato buscado");
                    break;
                case 15:
                    break;
                case 16:
                    lista.insercionDirecta();
                    break;
                case 17:
                    lista.seleccionDirecta();
                    break;
                case 18:
                    lista.intercambioDirecto();
                    break;
            }

            System.out.println ();
            System.out.println ("===================");
        } while (operacion != 0);
        
        System.out.println ("FIN DEL PROGRAMA");
        System.out.println ("===================");
    }
}
