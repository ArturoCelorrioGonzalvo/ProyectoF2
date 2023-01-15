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
    private ListaNodos nodosTerminalesBis;

    public Grafo(int n){
        this.lista=new ListaAristas(n);
        this.numNodos=0;
        this.nodosTerminales = new ListaNumeros ();
    }
    
    public Grafo(Grafo gr){
        this.lista=new ListaAristas(gr.lista);
        this.numNodos=gr.numNodos;
        this.nodosTerminales=new ListaNumeros(gr.nodosTerminales);
    }
    
    public ListaNumeros terminales (){
        ListaNumeros salen = new ListaNumeros();
        ListaNumeros entran = new ListaNumeros();
        for(int i = 0; i < this.lista.verNumDatos(); i++){
            salen.anadeSinRep(this.lista.verArista(i).verN1());
            entran.anadeSinRep(this.lista.verArista(i).verN1());
        }
        entran.quitar(salen);
        this.nodosTerminales = entran;
        return this.nodosTerminales;
    }

    public ListaAristas subgrafo (int nodo){
        int test=this.lista.verNodosInicioIguales(nodo) ;
        ListaAristas res = new ListaAristas (test);
        int i = 0;
        boolean posible = true;
        while(i < this.lista.verNumDatos() && posible){
            if(this.lista.verArista(i).verN1() == nodo){
                res.anadeArista(this.lista.verArista(i));
            }
            posible = this.lista.verArista(i).verN1() < nodo;
        }
        return res;
    }

    private void verificaTerminales (){
        int carro = 0, actual;
        ListaNumeros res = new ListaNumeros();
        ListaAristas aux;
        while(carro < this.nodosTerminales.cuantos()){
            actual = this.nodosTerminales.ver(carro);
            aux = this.subgrafo(actual);
            if (aux.verNumDatos() != 0){
                res.anadir(actual);
            }
            carro ++;
        }
        this.nodosTerminales = res;
    }

    public ListaNodos alcanzablesDesdeConMinPeso (int inicial){
        ListaNodos alcanzables = new ListaNodos(this.numNodos);
        ListaNodos nuevos = new ListaNodos(this.numNodos);
        nuevos.anadirNodo(new Nodo(inicial, 0, 0));
        boolean actualizado = true;
        while(actualizado){
            actualizado = false;
            ListaNodos creados = new ListaNodos(this.numNodos);
            for(int i = 0; i < nuevos.cuantos(); i++){
                ListaNodos actuales = alcanzablesInmediatos(nuevos.verNodo(i));
                for(int j = 0; j < actuales.cuantos(); j++){
                    boolean anadido = alcanzables.anadirPeso(actuales.verNodo(j));
                    actualizado = actualizado || anadido;
                    if (anadido){
                        creados.anadirPeso(actuales.verNodo(j));
                    }
                }
            }
            nuevos = creados;
        }
        return alcanzables;
    }

    public ListaNodos alcanzablesDesde (int inicial){
        ListaNumeros visitados = new ListaNumeros();
        ListaNodos alcanzables = new ListaNodos(this.numNodos);
        alcanzables.anadirNodo(new Nodo(inicial, 0, 0));
        while(alcanzables.distinta(visitados)){
            ListaNodos aVisitar = new ListaNodos(alcanzables);
            aVisitar.quitar(visitados);
            for(int i = 0; i < aVisitar.cuantos(); i++){
                alcanzables.anadir(this.alcanzablesInmediatos(aVisitar.verNodo(i)));
                visitados.anadir(aVisitar.ver(i));
            }
        }
        alcanzables.quitar(new Nodo(inicial, 0, 0));
        return alcanzables;
    }
    
    public ListaNumeros alcanzablesInmediatos (int nodo){
        ListaNumeros res = new ListaNumeros();
        if (!esTerminal(nodo)){
            ListaAristas grafo = this.subgrafo(nodo);
            for(int i = 0; i < grafo.verNumDatos(); i++){
                res.anadir(grafo.verArista(i).verN2());
            }
        }
        return res;
    }
    
    public ListaNodos alcanzablesInmediatos (Nodo nodo){
        ListaNodos res = new ListaNodos(10);
        ListaAristas subgrafo = null;
        if(!esTerminal(nodo.verIdent())){
            subgrafo = this.subgrafo(nodo.verIdent());
            for(int i = 0; i < subgrafo.verNumDatos(); i++){
                res.anadirNodo(new Nodo(subgrafo.verArista(i).verN2(), nodo.verNumPasos() + 1,
                                   (subgrafo.verArista(i).verPeso() + nodo.verPesoAc())));
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
    
    private boolean esTerminalBis (Nodo nodo){
        boolean terminal = false;
        int i = 0;
        while (!terminal && i < this.nodosTerminalesBis.cuantos()){
            terminal = this.nodosTerminales.ver(i) == nodo.verIdent(); 
        }
        return terminal;
    }
    
    /**
     * SIN TERMINAR CUIDADO
     */
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
                    nodosAlcanzables = this.alcanzablesDesde(nodoAnt);                    
                    
                    g.printf("NODO \t %\n", nodoAnt);
                    for(int j=0; j < nodosAlcanzables.cuantos(); j++){
                        g.printf(Locale.ENGLISH, "%1d \t %2d \t %3$.3f \n", 
                        nodosAlcanzables.verNodo(j).toString());
                    }
                }
            }
            g.close();
        }
    }
    
    public void anadeArista(){
        int n1, n2;
        double peso;
        do n1=Teclado.leerEntero("¿Nodo inicial de la arista?");
        while(n1<=0||n1>1000);
        do n2=Teclado.leerEntero("¿Nodo final de la arista?");
        while(n2<=0||n2>1000);
        do peso = Teclado.leerReal("¿Peso de la arista?");
        while(peso<0.0);
        this.lista.anadeArista(new Arista(n1,n2,peso));
    }
    
    public void quitaArista(){
        int n1,n2;
        do n1=Teclado.leerEntero("¿Nodo inicial de la arista?");
        while(n1<=0||n1>1000);
        do n2=Teclado.leerEntero("¿Nodo final de la arista?");
        while(n2<=0||n2>1000);
        this.lista.quitaArista(new Arista(n1,n2,0.0));
    }
    
    public void escribeGrafo(){
        for(int i=0; i<this.lista.verNumDatos(); i++){
            System.out.printf(Locale.ENGLISH, "Nodo inicial: %1d \t Nodo final: %2d \t Peso: %3$.3f \n",
            this.lista.verArista(i).verN1(),this.lista.verArista(i).verN2(), this.lista.verArista(i).verPeso());
        }
    }
    
    public double existeAristaEntre(int n1, int n2){
        double res=-1.0;
        Arista apoyo = new Arista(n1, n2, 0.0);
        int pos =this.lista.posicionPrimeraArista(apoyo);
        if(pos!=-1)res=this.lista.verArista(pos).verPeso();
        return res;
    }
    
    public int verNumAristas(){
        return this.lista.verNumDatos();
    }
    
    public void modificarPesoArista(int pos, double peso){
        this.lista.verArista(pos).setPeso(peso);
    }
    
    public int gradoNodo(int nodo){
        return this.lista.verNodosInicioIguales(nodo);
    }
    
    public int numNodosTerminales(){
        return this.nodosTerminales.cuantos();
    }
}