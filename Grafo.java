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
    
    public Grafo(Grafo gr){
        this.lista=new ListaAristas(gr.lista);
        this.numNodos=gr.numNodos;
        this.nodosTerminales=new ListaNumeros(gr.nodosTerminales);
    }
    
    public Grafo(String nombre){
        this.lista=new ListaAristas(nombre);
        this.numNodos=this.numeroNodos();
        this.terminalesBis();
    }
    
    public ListaNumeros terminales (){
        ListaNumeros salen = new ListaNumeros();
        ListaNumeros entran = new ListaNumeros();
        for(int i = 0; i < this.lista.verNumDatos(); i++){
            salen.anadeSinRep(this.lista.verArista(i).verN1());
            entran.anadeSinRep(this.lista.verArista(i).verN2());
        }
        entran.quitar(salen);
        this.nodosTerminales = salen;
        return this.nodosTerminales;
    }
    
    
    public void terminalesBis (){
        ListaNumeros nodosIn = new ListaNumeros();
        ListaNumeros nodosFin = new ListaNumeros();
        ListaNumeros intersec;
        for(int i=0; i<this.lista.verNumDatos(); i++){
            nodosIn.anadeSinRep(this.lista.verArista(i).verN1());
            nodosFin.anadeSinRep(this.lista.verArista(i).verN2());
        }
        intersec=nodosIn.filtrar(nodosFin);
        nodosFin.quitar(intersec);
        this.nodosTerminales= new ListaNumeros(nodosFin);
    }
    
    private int numeroNodos(){
        ListaNumeros visitados = new ListaNumeros();
        for(int i=0; i<this.lista.verNumDatos();i++){
            visitados.anadeSinRep(this.lista.verArista(i).verN1());
            visitados.anadeSinRep(this.lista.verArista(i).verN2());
        }
        return visitados.cuantos();
    }

    public ListaAristas subgrafo (int nodo){
        
        ListaAristas res = new ListaAristas (10);
        int i = 0;
        boolean posible = true;
        while(i < this.lista.verNumDatos() && posible){
            if(this.lista.verArista(i).verN1() == nodo){
                res.anadeArista(this.lista.verArista(i));
            }
            posible = this.lista.verArista(i).verN1() <= nodo;
            i++;
        }
        return res;
    }

    public void verificaTerminales (){
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
        nuevos.anadeNodo(new Nodo(inicial, 0, 0));
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
        alcanzables.anadeNodo(new Nodo(inicial, 0, 0));
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
                res.anadeNodo(new Nodo(subgrafo.verArista(i).verN2(), nodo.verNumPasos() + 1,
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
            i++;
        }
        return terminal;
    }
    
    public void generaFichero(String nombre){
        PrintWriter g = null;
        try{
            g= new PrintWriter(new File(nombre));
        }catch(FileNotFoundException fnfe){
            System.out.printf("%s \n", fnfe.getMessage());
        }catch(IOException ioe){
            System.out.printf("%s \n", ioe.getMessage());
        }
        if(g!=null){
            for(int i=0; i<this.lista.verNumDatos(); i++){
                g.printf(Locale.ENGLISH, " %1d \t %2d \t %3$.3f \n",
                        this.lista.verArista(i).verN1(),
                        this.lista.verArista(i).verN2(),
                        this.lista.verArista(i).verPeso());
            }
            g.close();
        }
    }
    
    public void generaFicheroAlcanzables(String nombre){
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
                        g.printf(Locale.ENGLISH, " %1d \t %2d \t %3$.3f \n",
                        nodosAlcanzables.verNodo(j).verIdent(),
                        nodosAlcanzables.verNodo(j).verNumPasos(),
                        nodosAlcanzables.verNodo(j).verPesoAc());
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
        System.out.println("Hay "+this.lista.verNumDatos()+" aristas.");
        for(int i=0; i<this.lista.verNumDatos(); i++){
            System.out.printf(Locale.ENGLISH, "%1dª Nodo inicial: %2d \t Nodo final: %3d \t Peso: %4$.3f \n",
            i+1,this.lista.verArista(i).verN1(),this.lista.verArista(i).verN2(), this.lista.verArista(i).verPeso());
        }
    }
    
    public double existeAristaEntre(int n1, int n2){
        double res=-1;
        Arista apoyo = new Arista(n1, n2, 0.0);
        int pos =this.lista.busquedaPosDico(apoyo);
        if(pos!=-1)res=this.lista.verArista(pos).verPeso();
        return res;
    }
    
    public int verNumAristas(){
        return this.lista.verNumDatos();
    }
    
    public void modificarPesoArista(Arista ar, double peso){
        int pos = this.lista.busquedaPosDico(ar);
        if(pos!=-1){
            this.lista.verArista(pos).setPeso(peso);
        }else{
            System.out.println("Esa arista no pertenece al grafo");
        }
    }
    
    public int gradoNodo(int nodo){
        return this.subgrafo(nodo).verNumDatos();
    }
    
    public int numNodosTerminales(){
        return this.nodosTerminales.cuantos();
    }
    
    public int verNumBucles(){
        int res=0;
        if(!this.lista.listaVacia()){
            for(int i=0; i<this.lista.verNumDatos(); i++){
            if(this.lista.verArista(i).verN1()==this.lista.verArista(i).verN2()){
                res++;
            }
        }
        }
        return res;
    }
    
    public void fusiona(Grafo gr){
        if(this.distinto(gr)){
            this.lista=this.lista.fusion(gr.lista);
        }
    }
    
    private boolean distinto(Grafo gr){
        if(this.lista.verNumDatos()!=gr.lista.verNumDatos()){
            return true;
        }else{
            boolean res = false;
            int i=0;

            while(!res && i<this.lista.verNumDatos()){
                res= this.lista.verArista(i).verN1()==gr.lista.verArista(i).verN1()
                    &&this.lista.verArista(i).verN2()==gr.lista.verArista(i).verN2();
                    i++;
            }
            return res;
        }
    }
}