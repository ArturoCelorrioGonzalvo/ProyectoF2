import java.util.*;
import java.io.*;

/**
 * TESTEO TESTEO TESTEO
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ListaAristas{
    private Arista aristas[];
    private Random azar = new Random();
    private int numDatos;

    /**
     * Constructores del objeto ListaAristas
     */
    public ListaAristas(int num){
        this.aristas = new Arista[num];
        for (int i = 0; i < this.aristas.length; i++){
            aristas[i] = new Arista();
        }
        this.numDatos=0;
    }
    
    public static ListaAristas leerLista(){
        int n;
        ListaAristas res;
        do n=Teclado.leerEntero("¿Número de elementos?");while(n<=0);
        res=new ListaAristas(n);
        for(int i=0; i<n; i++){
            res.aristas[i]=Arista.leerArista();
            res.numDatos+=1;
        }
        res.ordenarLista();
        
        return res;
    }

    public static ListaAristas azarLista(){
        int n,nMax;
        ListaAristas res;
        do n=Teclado.leerEntero("¿Número de elementos?");while(n<=0);
        res=new ListaAristas(n);
        do nMax=Teclado.leerEntero("¿Cuántos quieres que haya? Menos de "+(n+1));while(nMax>n||nMax>=1000);
        for(int i=0; i<nMax; i++){
            res.aristas[i]=Arista.inicioArista();
            res.numDatos+=1;
        }
        res.ordenarLista();
        return res;
    }

    /**
     * Métodos diversos con observadores y modificadores
     * 
     */
    private boolean listaLlena(){
        return this.numDatos==this.aristas.length;
    }

    private boolean listaVacia(){
        return this.numDatos==0;
    }

    public int verNumDatos(){
        return this.numDatos;
    }

    public Arista verArista(int n){
        return this.aristas[n];
    }
    
    public boolean elemOrdenados(int pos1, int pos2){
        return this.aristas[pos1].ordenadoRespA(this.aristas[pos2]);
    }
    
    public int verNodosInicioIguales(int n1){
        int res=0, cont=0;
        while(cont<this.numDatos&&this.verArista(cont).verN1()<n1)cont++;
        while(cont<this.numDatos&&this.verArista(cont).verN1()==n1){
            res++;
            cont++;
        }
        if (res==0)return -1;
        else return res;
    }
    
    /**
     * Búsqueda dicotómica de la posición de la primera aparición de un nodo inicial en concreto
     */
    public int verPosNodo(int nodo){
        int indice = -1;
        int i, m, s;

        if ( !this.listaVacia() ) {
            i = 0 ;
            s = this.numDatos - 1;
            while (i != s) {
                m = ( i + s ) / 2 ;
                if (nodo<=this.aristas[m].verN1())
                    s = m ;
                else
                    i = m + 1 ;
            }
            if (nodo==this.aristas[i].verN1())
                indice = i ;
        }

        return indice;
        
    }
    
    /**
     * Métodos de ordenación
     */
    private boolean listaOrdenada(){
        boolean ordenada=false;
        int i=0;
        do{
            ordenada=elemOrdenados(i,i+1);
            i++;
        }while(ordenada&&i+1<this.aristas.length);
        return ordenada;
    }
    
    public void ordenarLista(){
        Arista apoyo;
        for(int i=1; i<this.numDatos; i++){
            apoyo=this.aristas[i];
            int j=i;
            while(j>0&&!this.aristas[j-1].ordenadoRespA(apoyo)){
                this.aristas[j]=this.aristas[j-1];
                j-=1;
            }
            this.aristas[j]=apoyo;
        }
    }
    
    /**
     * Escribe la lista
     */
    public void escribirLista(){
        for(int i=0; i<this.numDatos; i++){
            System.out.printf(Locale.ENGLISH,
            "El nodo inicial es: %d\n El nodo final es: %d\n El peso de la arista es: %.3f\n"
            , this.aristas[i].verN1(), this.aristas[i].verN2(), 
            this.aristas[i].verPeso());
            
        }
    }

    /**
     * Añade una arista de forma que quede ordenada
     */
    public void anadeArista(Arista ar){
        int m, i, s;
        if(!this.listaLlena()){
            i = 0;
            s = this.numDatos;
            while(i != s){
                m = ( i + s ) / 2 ;
                if (this.aristas[m].anteriorA(ar)){
                    s = m;
                }else{
                    i = m + 1;
                }
            }
            if ((this.aristas[i] != null)){
                if(!this.aristas[i].igualA(ar)){
                    this.hazHueco(i + 1);
                    this.aristas[i + 1] = ar;
                    this.numDatos ++;
                }else{
                    System.out.println("se han encontrado 2 aristas iguales, la mas reciente "+
                        "se ignorará");
                }
            }else {
                this.aristas[i + 1] = ar;
                this.numDatos ++;
            }
        }
    }

    /**
     * Busca la posición de una arista de forma dicotómica
     */
    public int busquedaPosDico (Arista ar){
        int m, i, s;
        i = 0;
        s = this.numDatos;
        while(i != s&&i<this.numDatos){
            m = ( i + s ) / 2 ;
            if (this.aristas[m].anteriorA(ar)){
                s = m;
            }else{
                i = m + 1;
            }
        }
        if ((this.aristas[i] != null)){
            if(this.aristas[i].verN1() == ar.verN1()){
                return i;
            }else{
                return -1;
            }
        }else {
            return -1;
        }
    } 
    
    public boolean esTerminal(int nodo){
        boolean loEs = false, existe=true;
        int i=0, apoyo;
        while(i<this.verNumDatos()&&!loEs){
            apoyo=this.verArista(i).verN1();
            loEs=nodo<apoyo;
            i++;
        }
        return loEs;
    }
    
    private void hazHueco (int hueco){
        if(hueco + 1 < this.aristas.length){
            for(int carro = this.numDatos; carro > hueco; carro --){
                this.aristas[carro + 1] = this.aristas[carro];
            }
        }
    }

    public int posicionPrimeraArista(Arista ar){
        int indice=-1;
        boolean existe=true;
        if(!this.listaVacia()){
            indice=0;
            while(existe&&indice<this.numDatos){
                existe=ar.ordenadoRespA(this.aristas[indice]);
            }
        }
        return indice;
    }

    public double existeAristaEntre(int n1, int n2){
        Arista apoyo = new Arista(n1, n2, 0.0);
        double res =-1.0;
        int pos=-1;
        pos=this.posicionPrimeraArista(apoyo);
        if(pos!=-1)res=this.aristas[pos].verPeso();
        return res;
    }

    private int numeroBucles(){
        int res=-1;
        if(!this.listaVacia()){
            res=0;
            for(int i=0; i<this.numDatos; i++){
                if(this.aristas[i].verN1()==this.aristas[i].verN2())res++;
            }
        }
        return res;
    }
    
    private int gradoNodo(int N1){
        int res=-1, posN=0;
        if(!this.listaVacia()){
            while(this.aristas[posN].verN1()!=N1&&posN<this.numDatos){
                posN++;
            }
            res=0;
            if(posN+1!=this.numDatos){
                while(this.aristas[posN].verN1()==N1){
                    res++;
                }
            }
        }
        return res;
    }
}
