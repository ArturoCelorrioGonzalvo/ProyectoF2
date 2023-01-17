
/**
 * Write a description of class Principal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Principal{

    public static void main(String [] args){
        Grafo memoria=null, trabajo=null;
        ListaAristas subgrafo=null;
        int seleccion=0;
        while(seleccion!=17){
            System.out.println("1. Iniciar un nuevo grafo");
            System.out.println("2. Añadir una nueva arista al grafo");
            System.out.println("3. Quitar una arista del grafo");
            System.out.println("4. Visualizar el grafo");
            System.out.println("5. Comprobar si existe una arista entre 2 nodos concretos");
            System.out.println("6. Modificar el peso de una arista");
            System.out.println("7. Calcular el número de bucles del grafo");
            System.out.println("8. Calcular el grado de un nodo");
            System.out.println("9. Calcular el número de nodos terminales");
            System.out.println("10. Fusionar los grafos de memoria y trabajo");
            System.out.println("11. Intercambiar los grafos de trabajo y memoria");
            System.out.println("12. Subgrafo generado con las aristas que salen de un nodo concreto");
            System.out.println("13. Calcular la lista de nodos alcanzables desde un nodo concreto, su peso acumulado y la longitud de la trayectoria");
            System.out.println("14. Guardar el grafo trabajo");
            System.out.println("15. Crear un nuevo grafo a partir de un archivo");
            System.out.println("16. Crear un nuevo archivo a partir del grafo memoria");
            System.out.println("17. Salir del programa");
            do{
                seleccion=Teclado.leerEntero("Elige un número del 1 al 17 para "+
                    "seleccionar lo que quieras hacer \n");
            }while(seleccion<1||seleccion>17);

            switch(seleccion){
                case 1:
                    if(trabajo!=null){
                        char seg;
                        do seg = Teclado.leerCaracter("El grafo trabajo ya está"+
                            " inicializado, ¿quieres volver a crearlo?");
                        while(seg!='s'&&seg!='S'&&seg!='n'&&seg!='N');
                        if(seg=='s'||seg=='S'){
                            memoria=new Grafo(trabajo);
                            trabajo=null;
                        }
                        String nombreArchivoEntrada = Teclado.leerCadena("¿Nombre del archivo?");
                        trabajo = new Grafo(nombreArchivoEntrada);
                    }else{
                        int numMax;
                        do numMax=Teclado.leerEntero("¿Número máximo de aristas? (<1000)");
                        while(numMax<1||numMax>1000);
                        trabajo=new Grafo(numMax);
                    }
                    break;
                case 2:
                    if(trabajo!=null){
                        trabajo.anadeArista();
                        trabajo.verificaTerminales();
                    }
                    else System.out.println("El grafo de trabajo no está iniciado, inícialo y vuelve a intentarlo");
                    break;
                case 3:
                    if(trabajo!=null){
                        trabajo.quitaArista();
                        trabajo.verificaTerminales();
                    }
                    else System.out.println("El grafo de trabajo no está iniciado, inícialo y vuelve a intentarlo");
                    break;
                case 4: 
                    if(trabajo!=null){
                        trabajo.escribeGrafo();
                    }else System.out.println("El grafo de trabajo no está iniciado, inícialo y vuelve a intentarlo");
                    break;
                case 5:
                    if(trabajo!=null){
                        int n1, n2;
                        do n1=Teclado.leerEntero("¿Nodo inicial?");
                        while(n1<1);
                        do n2=Teclado.leerEntero("¿Nodo final?");
                        while(n2<1);
                        double peso = trabajo.existeAristaEntre(n1,n2);
                        if(peso!=-1)System.out.println(peso);
                        else System.out.println("No existe una arista entre esos nodos");
                    }else System.out.println("El grafo de trabajo no está iniciado, inícialo y vuelve a intentarlo");
                    break;
                case 6:
                    if(trabajo!=null){
                        int n1, n2;
                        do n1=Teclado.leerEntero("¿Nodo inicial?");
                        while(n1<1);
                        do n2=Teclado.leerEntero("¿Nodo final?");
                        while(n2<1);
                        trabajo.modificarPesoArista(new Arista(n1,n2,0.0));
                        
                    }else System.out.println("El grafo de trabajo no está iniciado, inícialo y vuelve a intentarlo");
                    break;
                case 7:
                    if(trabajo!=null){
                        System.out.println(trabajo.verNumBucles());
                    }else System.out.println("El grafo de trabajo no está iniciado");
                    break;
                case 8:
                    if(trabajo!=null){
                        int nodo;
                        do nodo=Teclado.leerEntero("¿Nodo?");
                        while(nodo<1);
                        int grado = trabajo.gradoNodo(nodo);
                        System.out.println("El grado del nodo "+nodo+" es "+grado);
                    }else System.out.println("El grafo de trabajo no está iniciado, inícialo y vuelve a intentarlo");
                    break;
                case 9:
                    if(trabajo!=null){
                        System.out.println("Hay "+trabajo.numNodosTerminales()+" nodos terminales");                        
                    }else System.out.println("El grafo de trabajo no está iniciado, inícialo y vuelve a intentarlo");
                    break;
                case 10:
                    if(trabajo!=null){
                        trabajo.fusiona(memoria);
                        trabajo.verificaTerminales();
                    }else System.out.println("El grafo de trabajo no está iniciado, inícialo y vuelve a intentarlo");
                    break;
                case 11:
                    if(trabajo!=null && memoria!=null){
                        Grafo apoyo = new Grafo (memoria);
                        memoria = new Grafo (trabajo);
                        trabajo = new Grafo (apoyo);
                        trabajo.verificaTerminales();
                    }else System.out.println("El grafo de trabajo y el de memoria no están iniciados, inícialos y vuelve a intentarlo");
                    break;
                case 12:
                    if(trabajo!=null){
                        int nodo;
                        do nodo=Teclado.leerEntero("¿Nodo?");
                        while(nodo<1);
                        subgrafo = trabajo.subgrafo(nodo);
                    }else System.out.println("El grafo de trabajo no está iniciado, inícialo y vuelve a intentarlo");
                    break;
                case 13:
                    if(trabajo != null){
                        int nodo;
                        do nodo=Teclado.leerEntero("¿Nodo?");
                        while(nodo<1);
                        trabajo.alcanzablesDesde(nodo).escribirLista();
                    }else System.out.println("El grafo de trabajo no está iniciado, inícialo y vuelve a intentarlo");
                    break;
                case 14:
                    if(trabajo!=null){
                        memoria = new Grafo(trabajo);
                    }else System.out.println("El grafo de trabajo no está iniciado, inícialo y vuelve a intentarlo");
                    break;
                case 15:
                    String nombreArchivoEntrada = Teclado.leerCadena("¿Nombre del archivo?");
                    trabajo = new Grafo(nombreArchivoEntrada);
                    break;
                case 16:
                    if(memoria!=null){
                        String nombreArchivoSalida = Teclado.leerCadena("¿Nombre del archivo?");
                        memoria.generaFichero(nombreArchivoSalida);
                    }else System.out.println("El grafo de memoria no está iniciado, inícialo y vuelve a intentarlo");
                    break;
                case 17:
                    System.out.println("Has salido del programa");
                    break;
            }
            System.out.println();
        }
    }
}


