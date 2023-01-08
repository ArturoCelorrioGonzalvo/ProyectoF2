package listas;

import java.util.*;
/**
 * Clase para utilizar los métodos de la clase ListaDatos.
 * Implementa un programa para gestionar el contenido de
 * esa estructura.
 * 
 * @author Alberto Ciriano 
 * @version abril 2018
 */



public class GestionLDatos
{
    
    public static void muestraMenu(){
       System.out.println("===========================================");
       System.out.println("            MENU DE OPERACIONES            ");
       System.out.println("===========================================");
       System.out.println("0  -- Finalizar");
       System.out.println("1  -- Ver datos de la lista");
       System.out.println("2  -- Añadir dato al final");
       System.out.println("3  -- Añadir dato al principio");
       System.out.println("4  -- Añadir dato en posición K");
       System.out.println("5  -- Añadir dato en lista ordenada");
       System.out.println("6  -- Quitar dato del principio");
       System.out.println("7  -- Quitar dato del final");
       System.out.println("8  -- Quitar dato de posición K");
       System.out.println("9  -- Quitar un dato determinado");
       System.out.println("10 -- Quitar todos los iguales a uno dado");
       System.out.println("11 -- Búsqueda lineal en lista no ordenada");
       System.out.println("12 -- Búsqueda lineal exhaustiva");
       System.out.println("13 -- Búsqueda lineal en lista ordenada");
       System.out.println("14 -- Búsqueda dicotómica en lista ordenada");
       System.out.println("15 -- Fusión de listas ordenadas");
       System.out.println("16 -- Ordenación por Inserción directa");
       System.out.println("17 -- Ordenación por Selección directa");
       System.out.println("18 -- Ordenación por Intercambio directo");
       System.out.println("===========================================");
    }
    
    
    public static void main (String [] argumentos) {
                
        ListaDatos lista ;

        int operacion , clave;
        int max , posicion , cont ;
        TpDato dato ;
        boolean hecho , existe ;
        
        do {
            //solicitamos el tamaño
            max = Teclado.leerEntero("Escriba el tamaño máximo para la lista: ");
        } while ( max <= 0 );
        
        System.out.println ();
        System.out.println ();
        
        //creación del vector
        lista = new ListaDatos(max) ;
                    
        do {
            muestraMenu();
            operacion = Teclado.leerEntero("Seleccione una operación [0-18]: ");
            System.out.println("=======================");
            switch (operacion) {
                case 1: 
                    lista.muestraLista();
                    break;
                case 2:
                    if (!lista.listaLLena()){
                        dato = TpDato.leerDato();
                        lista.anadeAlFinal(dato);
                    }
                    else
                        System.out.println("La lista estaba llena");
                    break;
                case 3:
                    if (!lista.listaLLena()){
                        dato = TpDato.leerDato();
                        lista.anadeAlPrincipio(dato);
                    }
                    else
                        System.out.println("La lista estaba llena");
                    break;                    
                case 4:
                    if (!lista.listaLLena()){
                        posicion = Teclado.leerEntero("Introducir la posición: ");
                        dato = TpDato.leerDato();
                        lista.anadeEnPosicion(dato,posicion);
                    }
                    else
                        System.out.println("La lista estaba llena");
                    break;   
                case 5:
                    if (!lista.listaLLena()){
                        dato = TpDato.leerDato();
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
                    clave = Teclado.leerEntero("Introduce valor clave válido: ");
                    dato = new TpDato(clave,"");
                    lista.quitarPrimeraAparicion(dato);
                    break;
                case 10:
                    clave = Teclado.leerEntero("Introduce valor clave válido: ");
                    dato = new TpDato(clave,"");
                    lista.quitarTodosIguales(dato);
                    break;
                case 11:
                    clave = Teclado.leerEntero("Introduce valor clave válido: ");
                    dato = new TpDato(clave,"");
                    existe = lista.existeDato(dato);
                    if (existe)                        
                        System.out.println("Existe el dato buscado");
                    else
                        System.out.println("No existe el dato buscado");
                    break;
                case 12:
                    clave = Teclado.leerEntero("Introduce valor clave válido: ");
                    dato = new TpDato(clave,"");
                    cont = lista.numeroApariciones(dato);
                    System.out.println("Existen "+cont+" apariciones del dato");
                    break;
                case 13:
                    clave = Teclado.leerEntero("Introduce valor clave válido: ");
                    dato = new TpDato(clave,"");
                    existe = lista.existeDatoOrdenado(dato);
                    if (existe)                        
                        System.out.println("Existe el dato buscado");
                    else
                        System.out.println("No existe el dato buscado");
                    break;
                case 14:
                    clave = Teclado.leerEntero("Introduce valor clave válido: ");
                    dato = new TpDato(clave,"");
                    posicion = lista.posicionDeDatoOrdenado(dato);
                    if (posicion>=1 && posicion<=lista.numeroDatos())                        
                        System.out.println("Existe el dato en  "+posicion);
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
                default:
            }
      
            System.out.println ();
            System.out.println ("===================");
        } while (operacion != 0);

    }
}
