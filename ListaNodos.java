import java.util.*;
/**
 * Write a description of class ListaNodos here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ListaNodos
{
    private Nodo [] nodos;
    private int numNodos;

    public ListaNodos(int n){
        this.nodos = new Nodo [n];
        this.numNodos = 0;
    }
    
    public ListaNodos(ListaNodos lista){
        this.nodos = lista.nodos;
        this.numNodos = lista.numNodos;
    }
    
    public int ver (int n){
        return this.nodos[n].verIdent();
    }
    
    public Nodo verNodo(int n){
        return this.nodos[n];
    }

    public int cuantos(){
        return this.numNodos;
    }

    public void anadirNodo(Nodo n){
        this.nodos[numNodos] = new Nodo(n);
        this.numNodos ++;
    }
    
    public void anadir(ListaNodos lista){
        
    }
    
    public boolean distinta (ListaNodos otra){
        if(this.numNodos != otra.numNodos){
            return true;
        }else{
            boolean distinto = false;
            int carro = 0;
            while(!distinto && carro < this.numNodos){
                distinto = !this.nodos[carro].igualA(otra.nodos[carro]); 
                carro ++;
            }
            return distinto;
        }
    }
    
    public ListaNodos diferencia(ListaNodos otra){
        ListaNodos res = new ListaNodos(this);
        
        return res;
    }
    
    private void quitar(ListaNodos otra){
        for(int i=0; i<otra.numNodos; i++){
            this.quitar(otra.nodos[i]);
        }
    }
    
    public void quitar(Nodo nod){
        boolean encontrado = false;
        int carro = -1;
        while (!encontrado && carro < this.numNodos){
            carro ++;
            encontrado = this.nodos[carro].igualA(nod);
        }
        if(encontrado){
            this.quitaPos(carro);
        }
    }
    
    private void quitaPos (int n){
        for(int i = n; i < this.numNodos; i++){
                this.nodos [i] = this.nodos [i + 1];
        }
        this.numNodos --;
    }
    
    public static ListaNodos nodosAlcanzablesDesde(int nodo, int numDatos,
    ListaAristas lista){
        int i=0, posIn=lista.verPosNodo(nodo);
        int trayect=1, numNodosIguales=lista.verNodosInicioIguales(nodo);
        int nodoSig;
        double pesoAnt;
        ListaNodos res =null;
        if(posIn!=-1){
            //Primero ver los que tienen como nodo inicial el que se pasa por parámetro
            //Luego ver los que tienen como nodo inicial el nodo final de las aristas que hemos mirado antes
            //Repetir el anterior
            pesoAnt=lista.verArista(posIn).verPeso();
            res = new ListaNodos(numDatos*numNodosIguales);
            while(i<numNodosIguales&&posIn<lista.verNumDatos()){
                res.nodos[i]=new Nodo(lista.verArista(posIn).verN2(),trayect,
                    lista.verArista(posIn).verPeso());           
                i++;
                posIn++;
            }
            res.numNodos=i;
            int l=0, m=0;
            int memoria=res.nodos[i-1].verIdent();
            while(l<res.numNodos){
                nodoSig=res.nodos[l].verIdent();
                numNodosIguales=lista.verNodosInicioIguales(nodoSig);
                posIn=lista.verPosNodo(nodoSig);
                if(nodoSig<memoria)trayect++;
                while(m<numNodosIguales&&posIn<lista.verNumDatos()&&posIn!=-1){
                    res.nodos[i]=new Nodo(lista.verArista(posIn).verN2(),trayect,
                        lista.verArista(posIn).verPeso());           
                    i++;
                    m++;
                    posIn++;
                }
                res.numNodos+=m;
                m=0;
                l++;
            }
        }
        return res;
    }

    /**
     * Escribe la lista
     */
    public void escribirLista(){
        for(int i=0; i<this.numNodos; i++){
            if(this.nodos[i]!=null){
                System.out.printf(Locale.ENGLISH,
                    "El nodo alcanzado es: %d\n La trayectoria es de: %d unidades\n El peso acumulado es: %.3f\n"
                , this.nodos[i].verIdent(), this.nodos[i].verNumPasos(), 
                    this.nodos[i].verPesoAc());
            }
        }
    }
    
    private void ordenarLista(){
        Nodo apoyo;
        for(int i=1; i<this.numNodos; i++){
            apoyo=this.nodos[i];
            int j=i;
            while(j>0&&this.nodos[j-1].verIdent()>apoyo.verIdent()){
                this.nodos[j]=this.nodos[j-1];
                j-=1;
            }
            this.nodos[j]=apoyo;
        }
    }
}