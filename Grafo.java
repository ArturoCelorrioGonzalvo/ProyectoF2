import java.util.*;
import java.io.*;

/**
 * Write a description of class Grafo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Grafo{
    private ListaAristas lista;
    private int numNodos;
    private ListaNumeros nodosTerminales;

    public Grafo(int n){
        this.lista=new ListaAristas(n);
        this.numNodos=0;
        this.nodosTerminales = new ListaNumeros ();
    }
    
    public Grafo (int n, String nombre){
        this.lista = new ListaAristas (n);
        this.nodosTerminales = new ListaNumeros ();
        Scanner f = null;
        int nodoActual = 5555, lineaInt = 0, aristasSalen = 0;
        try{
            f = new Scanner (new File (nombre));
        }catch(FileNotFoundException fnfe){
            System.out.printf("%s \n", fnfe.getMessage());
        }
        if (f != null){
            while(f.hasNextLine()){
                Scanner linea = new Scanner (f.nextLine());
                lineaInt ++; 
                if(!linea.hasNextInt()){
                    while(!linea.hasNextInt() && linea.hasNext()){
                        String basura = linea.next();
                    }
                    if(linea.hasNextInt()){
                        if(aristasSalen == 0){
                            nodosTerminales.anadir(nodoActual);
                        }
                        nodoActual = linea.nextInt();
                        this.numNodos ++;
                        aristasSalen = 0;
                    }else{
                        System.out.println("linea de datos basura." +
                            " La linea " + lineaInt + " va ha ser omitida");
                    }
                }else{
                    try{
                        int nodoFinal = linea.nextInt();
                        int pasos = linea.nextInt(); //a merter en try catch o reforzar
                        double peso = linea.nextDouble();
                        if (pasos == 1){
                            this.lista.anadeArista(new Arista (nodoActual,
                                    nodoFinal,
                                    peso));
                            aristasSalen ++;
                        }
                    }catch(InputMismatchException e){
                        System.out.println("Ha ocurrido un error en la lectura" +
                            " del archivo, se omitira la linea "
                            + lineaInt + " del archivo");
                    }
                }
            }
            this.lista.ordenarLista();
            this.verificaTerminales();
            f.close();
        }
    }

    public Grafo subgrafo (int nodo){
        int test=this.lista.verNodosInicioIguales(nodo) ;
        Grafo res = new Grafo (test);
        int pos = this.lista.busquedaPosDico(new Arista (nodo, 0, 0));
        int carro = 0;
        boolean otro = true;
        res.numNodos = 1;
        if(pos != -1){
            int i = pos;
            while(i < this.numNodos && otro){
                if(this.lista.verArista(i).verN1() == nodo){
                    res.lista.anadeArista(this.lista.verArista(i));
                }else{
                    otro = false;
                }
                i++;
            }
        }
        return res;
    }

    private void verificaTerminales (){
        int carro = 0, actual;
        ListaNumeros res = new ListaNumeros();
        Grafo aux;
        while(carro < this.nodosTerminales.cuantos()){
            actual = this.nodosTerminales.ver(carro);
            aux = this.subgrafo(actual);
            if (aux.lista.verNumDatos() != 0){
                res.anadir(actual);
            }
            carro ++;
        }
        this.nodosTerminales = res;
    }
    
    public ListaNumeros alcanzablesDesde (int inicial){
        ListaNumeros visitados = new ListaNumeros();
        ListaNumeros alcanzables = new ListaNumeros();
        alcanzables.anadir(inicial);
        while(alcanzables.distinta(visitados)){
            ListaNumeros aVisitar = alcanzables.diferencia(visitados);
            visitados = new ListaNumeros(alcanzables);
            int i = 0;
            while(i < aVisitar.cuantos()){
                alcanzables.anadir(this.alcanzablesInmediatos(aVisitar.ver(i)));
                i++;
            }
        }
        alcanzables.quitar(inicial);
        return alcanzables;
    }
    
    public ListaNumeros alcanzablesInmediatos (int nodo){
        ListaNumeros res = new ListaNumeros();
        if (!esTerminal(nodo)){
            Grafo grafo = this.subgrafo(nodo);
            for(int i = 0; i < grafo.lista.verNumDatos(); i++){
                res.anadir(grafo.lista.verArista(i).verN2());
            }
        }
        return res;
    }
    
    private boolean esTerminal (int nodo){
        boolean terminal = false;
        int i = 0;
        while (!terminal && i < this.nodosTerminales.cuantos()){
            terminal = this.nodosTerminales.ver(i) == nodo; 
        }
        return terminal;
    }
    
    public void generaFichero(String nombre){
        PrintWriter g = null;
        Grafo subgrafo = null;
        int numNodosIguales, nodoAnt=0;
        ListaNodos nodosAlcanzables = null;
        try{
            g= new PrintWriter(new File(nombre));
        }catch(FileNotFoundException fnfe){
            System.out.printf("%s \n", fnfe.getMessage());
        }catch(IOException ioe){
            System.out.printf("%s \n", ioe.getMessage());
        }
        if(g!=null){
            for(int i=0; i<this.lista.verNumDatos(); i++){
                if(this.lista.verArista(i).verN1()!=nodoAnt){
                    nodoAnt=this.lista.verArista(i).verN1();
                    nodosAlcanzables = 
                    ListaNodos.nodosAlcanzablesDesde(nodoAnt, this.numNodos, lista);                    
                    subgrafo = this.subgrafo(nodoAnt);
                    g.printf("NODO \t %\n", nodoAnt);
                    for(int j=0; j<subgrafo.numNodos; j++){
                        g.printf(Locale.ENGLISH, "%d \t %d \t %.3f \n", 
                        subgrafo.lista.verArista(j).verN2());
                    }
                }
            }
            g.close();
        }
    }
    
    
}
