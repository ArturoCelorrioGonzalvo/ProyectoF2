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

    public ListaAristas(ListaAristas lista){
        this.aristas=new Arista[lista.aristas.length];
        for(int i=0; i<lista.numDatos; i++){
            this.aristas[i]=lista.aristas[i];
        }
        this.numDatos=lista.numDatos;
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

    public ListaAristas (String nombre){
        this.aristas = new Arista [20];
        for(int i=0; i<20; i++){
            this.aristas[i]=new Arista();
        }
        Scanner f = null;
        int nodoActual = 1, lineaInt = 0;
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
                        nodoActual = linea.nextInt();
                    }else{
                        System.out.println("linea de datos basura." +
                            " La linea " + lineaInt + " va a ser omitida");
                    }
                }else{
                    try{
                        int nodoFinal = linea.nextInt();
                        int pasos = linea.nextInt(); //a merter en try catch o reforzar
                        double peso = linea.nextDouble();
                        if (pasos == 1 && nodoActual>0 && nodoFinal>0 && peso>0.0){
                            this.anadeArista(new Arista (nodoActual,nodoFinal, peso));
                        }else{
                            System.out.println("Ha ocurrido un error en la lectura" +
                            " del archivo, se omitira la linea "
                            + lineaInt + " del archivo");
                        }
                    }catch(InputMismatchException e){
                        System.out.println("Ha ocurrido un error en la lectura" +
                            " del archivo, se omitira la linea "
                            + lineaInt + " del archivo");
                    }
                }
            }
            this.setNumDatos();
            f.close();
        }
    }
    
    public static boolean esValido (String nombre){
        Scanner f = null;
        boolean valido = true;
        int nodoActual = 1, lineaInt = 0;
        try{
            f = new Scanner (new File (nombre));
        }catch(FileNotFoundException fnfe){
            System.out.printf("%s \n", fnfe.getMessage());
        }
        if (f != null){
            while(f.hasNextLine() && valido){
                Scanner linea = new Scanner (f.nextLine());
                lineaInt ++; 
                if(!linea.hasNextInt()){
                    String basura = linea.next();
                    
                    if(linea.hasNextInt()){
                        nodoActual = linea.nextInt();
                    }else{
                        valido = false;
                    }
                }else{
                    try{
                        int nodoFinal = linea.nextInt();
                        int pasos = linea.nextInt(); //a merter en try catch o reforzar
                        double peso = linea.nextDouble();
                        if(pasos <= 0 || peso <= 0.0 || nodoFinal <= 0 || nodoActual <= 0){
                            valido = false;
                        }
                    }catch(InputMismatchException e){
                        valido = false;
                    }
                }
            }
            f.close();
        }
        return valido;
    }

    /**
     * Métodos diversos con observadores y modificadores
     * 
     */
    private boolean listaLlena(){
        return this.numDatos==this.aristas.length;
    }

    public boolean listaVacia(){
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

    //Modificador del numero de datos
    private void setNumDatos(){
        int i=0; 
        while(!this.aristas[i].aristaVacia()){
            i++;
        }
        this.numDatos=i;
    }

    public int verNodosInicioIguales(int n1){
        int res=0, cont=this.verPosNodo(n1);
        while(cont<this.numDatos&&this.verArista(cont).verN1()==n1){
            res++;
            cont++;
        }
        return res;
    }

    /**
     * Búsqueda dicotómica de la posición de la primera aparición 
     * de un nodo inicial en concreto
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
        Arista menor;
        int i,j, posMenor;

        for(i=0; i<this.numDatos-1; i++){
            menor=this.aristas[i];
            posMenor=i;
            for(j=i+1; j<this.numDatos; j++){
                if(!(menor.verN1()<this.aristas[j].verN1()||!(menor.verN1()<this.aristas[j].verN1())&&menor.verN1()==this.aristas[j].verN1())&&
                menor.verN2()<this.aristas[j].verN2()){
                    menor=this.aristas[j];
                    posMenor=j;
                }

            }
            this.aristas[posMenor]=this.aristas[i];
            this.aristas[i]=menor;
        }
    }

    /**
     * Escribe la lista
     */
    public void escribirLista(){
        for(int i=0; i<this.numDatos; i++){
            System.out.printf(Locale.ENGLISH,
                "El nodo inicial es: %1d \t El nodo final es: %2d \t El peso de la arista es: %3$.3f\n"
            , this.aristas[i].verN1(), this.aristas[i].verN2(), 
                this.aristas[i].verPeso());

        }
    }

    public void anadeArista(Arista ar){
        if(!this.listaLlena()){

            if(this.numDatos==0){
                this.aristas[0]=new Arista(ar);
                this.numDatos++;
            }else{
                int i = this.numDatos-1;
                int posTeorica = this.busquedaPosDico(ar);
                if(posTeorica!=-1&&this.aristas[posTeorica].verPeso()>=ar.verPeso()){
                    this.aristas[posTeorica]=new Arista(ar);
                }else{
                    while(i>=0 && !this.aristas[i].ordenadoRespA(ar)){
                        this.aristas[i+1]=this.aristas[i];
                        i=i-1;
                    }
                    this.aristas[i+1]=new Arista(ar);
                    this.numDatos++;   
                }
            }

        }else{
            this.amplia();

            if(this.numDatos==0){
                this.aristas[0]=new Arista(ar);
                this.numDatos++;
            }else{
                int i = this.numDatos-1;
                int posTeorica = this.busquedaPosDico(ar);
                if(posTeorica!=-1&&this.aristas[posTeorica].verPeso()>ar.verPeso()){
                    this.aristas[posTeorica]=new Arista(ar);
                }else{
                    while(i>=0 && !this.aristas[i].ordenadoRespA(ar)){
                        this.aristas[i+1]=this.aristas[i];
                        i=i-1;
                    }
                    this.aristas[i+1]=new Arista(ar);
                    this.numDatos++;   
                }
            }

        }
        this.ordenarLista();
    }

    public void amplia (){
        Arista [] nuevo = new Arista [this.aristas.length + 10];
        for(int i = 0; i < this.numDatos; i++){
            nuevo[i] = this.aristas[i];
        }
        for(int j=this.numDatos; j<nuevo.length; j++){
            nuevo[j]= new Arista();
        }
        this.aristas = nuevo;
    }

    /**
     * Busca la posición de una arista de forma dicotómica
     */
    public int busquedaPosDico (Arista ar){
        int m, i, s;
        i = 0;
        s = this.numDatos-1;
        while(i != s){
            m = ( i + s ) / 2 ;
            if (ar.ordenadoRespA(this.aristas[m])){
                s = m;
            }else{
                i = m + 1;
            }
        }
        if ((!this.aristas[i].aristaVacia())){
            if(this.aristas[i].igualA(ar)){
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

    public void quitaArista(Arista ar){
        int pos = this.busquedaPosDico(ar);
        if(pos!=-1){
            for(int i = pos; i<this.numDatos; i++)
                this.aristas[i]=this.aristas[i+1];
            this.numDatos--;
        }
    }

    
    public ListaAristas fusion(ListaAristas otra){
        ListaAristas res = new ListaAristas(this.numDatos+otra.numDatos);
        
        int i=0, j=0;

        while(i<this.numDatos&&j<otra.numDatos){
            
            if(this.aristas[i].ordenadoRespA(otra.aristas[j])){
                res.anadeArista(this.aristas[i]);
                i++;
            }else{
                res.anadeArista(otra.aristas[j]);
                j++;
            }
        }

        for(int l=j; l<otra.numDatos; l++){
            res.anadeArista(otra.aristas[l]);
        }

        for(int m=i; m<this.numDatos; m++){
            res.anadeArista(this.aristas[m]);
        }

        return res;
    }
}