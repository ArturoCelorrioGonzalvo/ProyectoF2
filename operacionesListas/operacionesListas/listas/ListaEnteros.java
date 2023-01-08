package listas;


/**
 * Clase que desarrolla operaciones estructuradas
 * en una lista de valores enteros (simples y genéricos)
 * 
 * @author Alberto Ciriano 
 * @version ABR/2018
 */


public class ListaEnteros
{
    // atributos de objeto 
    private int [] elVector ;
    private int num ; 
    // num: número de valores almacenados en las 
    // primeras posiciones

    /**
     * Constructor for objects of class VectorEnteros
     */
    public ListaEnteros( int n )
    {
        // crea un vector "vacío" de enteros de tamaño n
        this.elVector = new int[n];
        this.num = 0;
    }
    

    /**
     * Method numeroDatos, observador del campo num
     */
    public int numeroDatos() {
        return this.num ;
    }
    
    
    /**
     * Method muestraLista
     * visualiza en forma tabulada con algo de formato los
     * valores guardados en el vector, de primero a último
     */
    public void muestraLista(){
        System.out.println("Datos almacenados en el vector:");
        for ( int i = 0 ; i < this.num ; i++ ) {
           System.out.printf("Dato número %4d = %8d ,",i+1,
                                this.elVector[i]);
           if ( (i + 1) % 3 == 0 ) System.out.println();
        }
        System.out.println();
    }
    
    
    public boolean listaLLena() {
        return this.num == this.elVector.length ;
    }
    
    
    public boolean listaVacia() {
        return this.num == 0 ;
    }
    
    
    public void anadeAlFinal(int dato){
        if (!this.listaLLena()){
            this.elVector[this.num] = dato ;
            this.num += 1;
        }
    }
    
    
    public void anadeAlPrincipio(int dato){
        if (!this.listaLLena()){
            for (int i = this.num - 1 ; i >= 0 ; i--)
                this.elVector[i+1] = this.elVector[i];
            this.elVector[0] = dato ;
            this.num += 1 ;
        }
    }
    
    
    public void anadeEnPosicion(int dato, int posi){
        if (!this.listaLLena() && posi >= 1 
                                && posi <= this.num + 1){
            for (int i = this.num - 1 ; i >= posi - 1 ; i--)
                this.elVector[i+1] = this.elVector[i];
            this.elVector[posi - 1] = dato ;
            this.num += 1 ;
        }
    }
    
    
    public static boolean anterior (int dato1, int dato2){
        return dato1 < dato2 ;
    }
    
    
    public static boolean iguales (int dato1, int dato2){
        return dato1 == dato2 ;
    }
    
    
    public static boolean ordenados (int dato1, int dato2){
        return anterior(dato1,dato2) || iguales(dato1,dato2) ;
    }
    
    
    public void anadeOrdenado(int dato){
        if (!this.listaLLena()){
            int i = this.num - 1 ;
            while (i>=0 && !ordenados(this.elVector[i],dato)){
                this.elVector[i+1] = this.elVector[i];
                i = i - 1 ;
            }
            this.elVector[i+1] = dato ;
            this.num += 1 ;
        }
    }
    
    
    public void quitarDelPrincipio(){
        if (!this.listaVacia()){
            for (int i = 1 ; i < this.num ; i++)
                this.elVector[i-1] = this.elVector[i];
            this.num -= 1 ;
        }
    }
    
    
    public void quitarDelFinal(){
       if (!this.listaVacia())
            this.num -= 1;
    }
    
    
    public void quitarDePosicion(int posi){
        if (!this.listaVacia() && posi>=1 && posi<=this.num){
            for (int i = posi ; i < this.num ; i++)
                this.elVector[i-1] = this.elVector[i];
            this.num -= 1 ;
        }
    }
    
    
    public void quitarPrimeraAparicion(int dato){
        boolean encontrado = false ;
        int i = 0 ;
            
        while ( !encontrado && i < this.num ){
            encontrado = iguales(dato , this.elVector[i]);
            i = i + 1 ;
        }
        
        if ( encontrado ) {
            //desplazo desde i-1
            for ( int j = i - 1 ; j < this.num - 1 ; j++ )
                this.elVector[j] = this.elVector[j+1] ;
            this.num -= 1 ;
        }
    }
    
    
    public void quitarTodosIguales(int dato){        
        int i = 0 ;        
        while ( i < this.num ){
            if ( iguales( dato , this.elVector[i] )){
                for ( int j = i ; j < this.num - 1 ; j++ )
                    this.elVector[j] = this.elVector[j+1] ;
                this.num = this.num - 1 ;
            }
            else
                i = i + 1 ;
        }
    }
    
    
    public boolean existeDato(int dato){
        //caso de búsqueda en vector NO ordenado
        boolean encontrado = false ;
        int i = 0 ;
        
        while ( i < this.num && !encontrado ){
            encontrado = iguales( dato , this.elVector[i] );
            i = i + 1 ;
        }

        return encontrado ;
    }
    
    
    public int numeroApariciones(int dato){
        //búsqueda lineal exhaustiva en vector NO ordenado       
        int cont = 0 ;
        
        for ( int i = 0 ; i < this.num ; i++ ){
            if ( iguales( dato , this.elVector[i] ))
                cont += 1 ;
        }

        return cont ;
    }
    
    
    public boolean existeDatoOrdenado(int dato){
        //caso de búsqueda lineal en vector ordenado en 
        //sentido creciente
        boolean encontrado = false , puedeExistir = true ;
        int i = 0 ;
        
        while (i < this.num && !encontrado && puedeExistir){
            encontrado = iguales( dato , this.elVector[i] );
            puedeExistir = ordenados(this.elVector[i],dato);
            i = i + 1 ;
        }

        return encontrado ;
    }
    
    
    public int posicionDeDatoOrdenado(int dato){
        // caso de búsqueda dicotómica en vector ordenado
        // devolverá entre 1 y num (no el índice sino la 
        //posición) y cero en caso de no existir
        int indice = -1;
        int i, m, s;
        
        if (  !this.listaVacia() ) {
            i = 0 ;
            s = this.num - 1;
            while (i != s) {
                m = ( i + s ) / 2 ;
                if ( ordenados (dato , this.elVector[m]))
                    s = m ;
                else
                    i = m + 1 ;
            }
            if ( iguales (dato , this.elVector[i]))
                indice = i ;
        }
        
        return indice + 1 ;
    }
    
    
    public static ListaEnteros fusionVectores(ListaEnteros vec1, 
                                           ListaEnteros vec2){
        
        ListaEnteros res = new ListaEnteros(vec1.num+vec2.num);
        // otra posibilidad:
        // ListaEnteros res = new ListaEnteros
        //      (vec1.elVector.length + vec2.elVector.length);
        // otra sería comprobar si cabe la fusión cuando sean 
        // vectores de tamaño máximo fijo
        
        int i1 = 0 , i2 = 0 , iRes = 0 , j ;
        
        while (i1 < vec1.num && i2 < vec2.num) {
            if (ordenados(vec1.elVector[i1],vec2.elVector[i2])){
                res.elVector[iRes] = vec1.elVector[i1] ;
                i1 += 1 ;
            }
            else {
                res.elVector[iRes] = vec2.elVector[i2] ;
                i2 += 1 ;
            }
            iRes += 1 ;
        }
        
        for ( j = i1 ; j < vec1.num ; j++ ) {
            res.elVector[iRes] = vec1.elVector[j] ;
            iRes += 1 ;
        }
        
        for ( j = i2 ; j < vec2.num ; j++ ) {
            res.elVector[iRes] = vec2.elVector[j] ;
            iRes += 1 ;
        }
        
        res.num = iRes;

        return res ;
    }
    
    
    public void insercionDirecta(){
        //metodo de ordenación por inserción directa
        int i, j;
        int apoyo ;
        
        for ( i = 1 ; i < this.num ; i++ ) {
            apoyo = this.elVector[i] ;
            j = i ;
            while ( j > 0 && 
                    !ordenados(this.elVector[j-1],apoyo) ) {
                this.elVector[j] = this.elVector[j-1] ;
                j = j - 1 ;
            }
            this.elVector[j] = apoyo ;
        }
    }
    
    
    public void seleccionDirecta(){
        //metodo de ordenación por selección directa
        int i, j, posmenor;
        int menor ;
        
        for ( i = 0 ; i < this.num - 1 ; i++ ) {
            menor = this.elVector[i] ;
            posmenor = i ;
            for ( j = i + 1 ; j < this.num ; j++ ) {
                if (!ordenados(menor,this.elVector[j])){
                    menor = this.elVector[j];
                    posmenor = j ;
                }
            }
            this.elVector[posmenor] = this.elVector[i] ;
            this.elVector[i] = menor ;
        }
    }
    
    
    public void intercambioDirecto(){
        //metodo de ordenación por intercambio directo o burbuja
        int i, j ;
        int apoyo ;
        
        for ( i = 0 ; i < this.num - 1 ; i++ ) {            
            for ( j = this.num - 1 ; j >= i + 1 ; j-- ) {
                if ( !ordenados( this.elVector[j-1] , 
                                        this.elVector[j] )){
                    apoyo = this.elVector[j-1];
                    this.elVector[j-1] = this.elVector[j] ;
                    this.elVector[j] = apoyo ;
                }
            }
        }
    }
}
