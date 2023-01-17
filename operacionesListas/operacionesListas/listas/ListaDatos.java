package listas;

/**
 * Esta clase representa una lista de datos genéricos de 
 * clase TpDato. Se implementan operaciones estructuradas sobre
 * los datos que almacena la estructura, que quedarán guardados
 * en las primeras posiciones del vector. Para esta forma de uso
 * es necesario guardar el número de datos que almacena la
 * estructura en todo momento.
 * 
 * @author Alberto Ciriano 
 * @version abril 2018 
 */
public class ListaDatos {

    private TpDato [] elVector ;
    private int num ; //número de datos

    /**
     * Constructor for objects of class VectorDatos. Genera 
     * una lista vacía de datos de tamaño n 
     */
    public ListaDatos( int n ) {
        this.elVector = new TpDato[n];
        this.num = 0;
    }

    /**
     * Method numeroDatos  --> devuelve el número de datos 
     * útiles que tiene almacenados la estructura en las 
     * primeras componentes del array
     *
     * @return número de datos útiles guardados
     */
    public int numeroDatos() {
        return this.num ;
    }

    /**
     * Method listaLLena
     *
     * @return --> valor booleano que indica si la estructura
     * de datos está llena
     */
    public boolean listaLLena() {
        return this.num == this.elVector.length ;
    }

    /**
     * Method listaVacia
     *
     * @return valor booleano que indica si la estructura de
     * datos está vacía
     */
    public boolean listaVacia() {
        return this.num == 0 ;
    }

    /**
     * Method muestraLista --> visualiza por pantalla de forma
     * tabulada los datos contenidos en la estructura en el 
     * orden en el que se encuentran.
     */
    public void muestraLista(){
        System.out.println("Datos almacenados en la lista:");
        for ( int i = 0 ; i < num ; i++ ) 
            this.elVector[i].mostrarDato();
        System.out.println();
    }

    
    /**
     * Method anadeAlFinal --> inserta un nuevo dato en la 
     * primera posición disponible detrás de los datos 
     * existentes de forma que los datos sigan ocupando las
     * primeras posiciones.
     *
     * @param dato --> dato a añadir en la estructura
     */
    public void anadeAlFinal(TpDato dato){
        if (!this.listaLLena()){
            this.elVector[this.num] = new TpDato(dato);
            this.num += 1;
        }
    }

    /**
     * Method anadeAlPrincipio --> inserta un nuevo dato 
     * delante de todos los existentes en la estructura.
     *
     * @param dato --> dato a añadir a la estructura
     */
    public void anadeAlPrincipio(TpDato dato){
        if (!this.listaLLena()){
            for (int i = this.num - 1 ; i >= 0 ; i--)
                this.elVector[i+1] = this.elVector[i];
            this.elVector[0] = new TpDato(dato);
            this.num += 1 ;
        }
    }

    /**
     * Method anadeEnPosicion --> inserta un nuevo dato en una
     * posición concreta de la estructura de datos si el valor
     * de dicha posición es válido o sea, si no se generan huecos
     * innecesarios.
     *
     * @param dato --> dato a añadir a la estructura
     * @param posi --> valor de la posición donde insertar
     */
    public void anadeEnPosicion(TpDato dato, int posi){
        if (!this.listaLLena() && posi >= 1 
        && posi <= this.num + 1){
            for (int i = this.num - 1 ; i >= posi - 1 ; i--)
                this.elVector[i+1] = this.elVector[i];
            this.elVector[posi - 1] = new TpDato(dato) ;
            this.num += 1 ;
        }
    }

    /**
     * Method anadeOrdenado --> inserta un dato en una 
     * estructura ordenada, en la posición que le corresponde 
     * para mantener el orden entre los datos.
     *
     * @param dato --> nuevo dato a ser añadido
     */
    public void anadeOrdenado(TpDato dato){
        if (!this.listaLLena()){
            int i = this.num - 1 ;
            while ( i >= 0  && 
            !this.elVector[i].ordenadoRespA(dato)){
                this.elVector[i+1] = this.elVector[i];
                i = i - 1 ;
            }
            this.elVector[i+1] = new TpDato(dato) ;
            this.num += 1 ;
        }
    }

    /**
     * version usando métodos static
     * quizá menos interesante
     */
    public void anadeOrdenadoBis(TpDato dato){
        if (!this.listaLLena()){
            int i = this.num - 1 ;
            while ( i >= 0  && 
            !TpDato.ordenados(this.elVector[i],dato)){
                this.elVector[i+1] = this.elVector[i];
                i = i - 1 ;
            }
            this.elVector[i+1] = new TpDato(dato) ;
            this.num += 1 ;
        }
    }

    /**
     * Method quitarDelPrincipio --> elimina de la estructura
     * de datos el que ocupa la primera posición.
     */
    public void quitarDelPrincipio(){
        if (!this.listaVacia()){
            for (int i = 1 ; i < this.num ; i++)
                this.elVector[i-1] = this.elVector[i];
            this.num -= 1 ;
        }
    }

    /**
     * Method quitarDelFinal --> elimina de la estructura
     * de datos el que ocupa la última posición.
     */
    public void quitarDelFinal(){
        if (!this.listaVacia())
            this.num -= 1;
    }

    /**
     * Method quitarDePosicion --> elimina de la estructura
     * de datos el que ocupa una determinada posición si es
     * válida.
     */
    public void quitarDePosicion(int posi){
        if (!this.listaVacia() && posi>=1 && posi<=this.num){
            for (int i = posi ; i < this.num ; i++)
                this.elVector[i-1] = this.elVector[i];
            this.num -= 1 ;
        }
    }

    /**
     * Method quitarPrimeraAparicion --> elimina de la 
     * estructura de datos el primer dato que aparece que sea
     * igual a uno que se pasa como parámetro.
     */
    public void quitarPrimeraAparicion(TpDato dato){
        boolean encontrado = false ;
        int i = 0 ;

        while ( !encontrado && i < this.num ){
            encontrado = this.elVector[i].igualA(dato);
            i = i + 1 ;
        }

        if ( encontrado ) {
            //desplazo desde i-1
            for ( int j = i - 1 ; j < this.num - 1 ; j++ )
                this.elVector[j] = this.elVector[j+1] ;
            this.num -= 1 ;
        }
    }

    /**
     * version usando métodos static
     * quizá menos adecuado
     */
    public void quitarPrimeraAparicionBis(TpDato dato){
        boolean encontrado = false ;
        int i = 0 ;

        while ( !encontrado && i < this.num ){
            encontrado = TpDato.iguales(dato,this.elVector[i]);
            i = i + 1 ;
        }

        if ( encontrado ) {
            //desplazo desde i-1
            for ( int j = i - 1 ; j < this.num - 1 ; j++ )
                this.elVector[j] = this.elVector[j+1] ;
            this.num -= 1 ;
        }
    }

    /**
     * Method quitarTodosIguales --> elimina de la estructura
     * de datos todos aquellos que sean iguales a uno que se
     * pasa como parámetro.
     */
    public void quitarTodosIguales(TpDato dato){        
        int i = 0 ;        
        while ( i < this.num ){
            if ( this.elVector[i].igualA( dato )){
                for ( int j = i ; j < this.num - 1 ; j++ )
                    this.elVector[j] = this.elVector[j+1] ;
                this.num = this.num - 1 ;
            }
            else
                i = i + 1 ;
        }
    }

    /**
     * version usando métodos static
     * quizá menos adecuado
     */
    public void quitarTodosIgualesBis(TpDato dato){        
        int i = 0 ;        
        while ( i < this.num ){
            if ( TpDato.iguales(dato,this.elVector[i])){
                for ( int j = i ; j < this.num - 1 ; j++ )
                    this.elVector[j] = this.elVector[j+1] ;
                this.num = this.num - 1 ;
            }
            else
                i = i + 1 ;
        }
    }

    /**
     * Method existeDato --> comprueba si un dato que se pasa
     * como parámetro existe o no en la estructura de datos y 
     * devuelve un valor booleano que lo indica.
     */
    public boolean existeDato(TpDato dato){
        //caso de búsqueda en vector NO ordenado
        boolean encontrado = false ;
        int i = 0 ;

        while ( i < this.num && !encontrado ){
            encontrado = this.elVector[i].igualA(dato);
            i = i + 1 ;
        }

        return encontrado ;
    }

    /**
     * version usando métodos static
     * quizá menos adecuado
     */
    public boolean existeDatoBis(TpDato dato){
        //caso de búsqueda en vector NO ordenado
        boolean encontrado = false ;
        int i = 0 ;

        while ( i < this.num && !encontrado ){
            encontrado = TpDato.iguales(dato,this.elVector[i]);
            i = i + 1 ;
        }

        return encontrado ;
    }

    /**
     * Method numeroApariciones --> comprueba y devuelve el
     * número de veces que aparece un dato, que se pasa como
     * parámetro en la estructura en caso de que esté NO 
     * ordenada.
     */
    public int numeroApariciones(TpDato dato){
        //búsqueda lineal exhaustiva en vector NO ordenado       
        int cont = 0 ;

        for ( int i = 0 ; i < this.num ; i++ ){
            if ( this.elVector[i].igualA( dato ))
                cont += 1 ;
        }

        return cont ;
    }

    /**
     * version usando métodos static
     * quizá menos adecuado
     */
    public int numeroAparicionesBis(TpDato dato){
        //búsqueda lineal exhaustiva en vector NO ordenado       
        int cont = 0 ;

        for ( int i = 0 ; i < this.num ; i++ ){
            if ( TpDato.iguales( dato , this.elVector[i] ))
                cont += 1 ;
        }

        return cont ;
    }

    /**
     * Method existeDatoOrdenado --> comprueba si existe o no
     * un dato en la estructura que sea igual a otro que se 
     * pasa como parámetro y devuelve un valor booleano que
     * lo indica. Caso de vector ordenado.
     */
    public boolean existeDatoOrdenado(TpDato dato){
        //caso de búsqueda lineal en vector ordenado
        boolean encontrado = false , puedeExistir = true ;
        int i = 0 ;

        while (i < this.num && !encontrado && puedeExistir){
            encontrado = this.elVector[i].igualA( dato );
            puedeExistir=this.elVector[i].ordenadoRespA(dato);
            i = i + 1 ;
        }

        return encontrado ;
    }

    /**
     * version usando métodos static
     * quizá menos adecuado
     */
    public boolean existeDatoOrdenadoBis(TpDato dato){
        //caso de búsqueda lineal en vector ordenado
        boolean encontrado = false , puedeExistir = true ;
        int i = 0 ;

        while (i < this.num && !encontrado && puedeExistir){
            encontrado = TpDato.iguales(dato,this.elVector[i]);
            puedeExistir = TpDato.ordenados(this.elVector[i],
                dato);
            i = i + 1 ;
        }

        return encontrado ;
    }

    /**
     * Method posicionDeDatoOrdenado --> busca la posición en la 
     * que se encuentra un dato igual a otro que se pasa como
     * parámetro en una estructura ordenada.
     */
    public int posicionDeDatoOrdenado(TpDato dato){
        // caso de búsqueda dicotómica en vector ordenado
        // devolverá entre 1 y num (no el índice sino la 
        // posición) y cero en caso de no existir
        int indice = -1;
        int i, m, s;

        if ( !this.listaVacia() ) {
            i = 0 ;
            s = this.num - 1;
            while (i != s) {
                m = ( i + s ) / 2 ;
                if (dato.ordenadoRespA(this.elVector[m]))
                    s = m ;
                else
                    i = m + 1 ;
            }
            if ( dato.igualA ( this.elVector[i] ) )
                indice = i ;
        }

        return indice + 1 ;
    }

    /**
     * version usando métodos static
     * quizá menos adecuado
     */
    public int posicionDeDatoOrdenadoBis(TpDato dato){
        // caso de búsqueda dicotómica en vector ordenado
        // devolverá entre 1 y num (no el índice sino la 
        // posición) y cero en caso de no existir
        int indice = -1;
        int i, m, s;

        if ( !this.listaVacia() ) {
            i = 0 ;
            s = this.num - 1;
            while (i != s) {
                m = ( i + s ) / 2 ;
                if (TpDato.ordenados(dato,this.elVector[m]))
                    s = m ;
                else
                    i = m + 1 ;
            }
            if (TpDato.iguales (dato,this.elVector[i]))
                indice = i ;
        }

        return indice + 1 ;
    }

    /**
     * Method fusion --> genera una estructura de clase
     * VectorDatos que mezcle los datos que provienen de 
     * sendas estructuras de la misma clase que se pasan 
     * como parámetros.
     */
    public static ListaDatos fusion(ListaDatos vec1, 
    ListaDatos vec2){
        // mezcla o fusión de vectores ordenados

        ListaDatos res = new ListaDatos(vec1.elVector.length 
                + vec2.elVector.length);
        // otra posibilidad:
        // ListaDatos res = new ListaDatos(vec1.num+vec2.num);
        // otra sería comprobar si cabe la fusión cuando sean 
        // vectores de tamaño máximo fijo

        int i1 = 0 , i2 = 0 , iRes = 0 , j ;

        while (i1 < vec1.num && i2 < vec2.num) {
            if (TpDato.ordenados(vec1.elVector[i1],
                vec2.elVector[i2])){
                res.elVector[iRes] = new TpDato
                (vec1.elVector[i1]);
                i1 += 1 ;
            }
            else {
                res.elVector[iRes] = new TpDato
                (vec2.elVector[i2]);
                i2 += 1 ;
            }
            iRes += 1 ;
        }

        for ( j = i1 ; j < vec1.num ; j++ ) {
            res.elVector[iRes] = new TpDato(vec1.elVector[j]);
            iRes += 1 ;
        }

        for ( j = i2 ; j < vec2.num ; j++ ) {
            res.elVector[iRes] = new TpDato(vec2.elVector[j]);
            iRes += 1 ;
        }

        // res.num = iRes;

        return res ;
    }

    /**
     * Method insercionDirecta --> ordena el contenido de la 
     * estructura mediante el método de inserción directa.
     */
    public void insercionDirecta(){
        //metodo de ordenación por inserción directa
        int i, j;
        TpDato apoyo ;

        for ( i = 1 ; i < this.num ; i++ ) {
            apoyo = this.elVector[i] ;
            j = i ;
            while ( j > 0 && 
            !this.elVector[j-1].ordenadoRespA(apoyo)){
                this.elVector[j] = this.elVector[j-1] ;
                j = j - 1 ;
            }
            this.elVector[j] = apoyo ;
        }
    }

    /**
     * version usando métodos static
     * quizá menos adecuado
     */
    public void insercionDirectaBis(){
        //metodo de ordenación por inserción directa
        int i, j;
        TpDato apoyo ;

        for ( i = 1 ; i < this.num ; i++ ) {
            apoyo = this.elVector[i] ;
            j = i ;
            while ( j > 0 && !TpDato.ordenados(
                this.elVector[j-1],apoyo)){
                this.elVector[j] = this.elVector[j-1] ;
                j = j - 1 ;
            }
            this.elVector[j] = apoyo ;
        }
    }

    /**
     * Method seleccionDirecta --> ordena el contenido de la 
     * estructura mediante el método de selección directa.
     */
    public void seleccionDirecta(){
        //metodo de ordenación por selección directa
        int i, j, posmenor;
        TpDato menor ;

        for ( i = 0 ; i < this.num - 1 ; i++ ) {           
            menor = this.elVector[i] ;
            posmenor = i ;
            for (j = i + 1 ; j < this.num ; j++) {
                if (!menor.ordenadoRespA(this.elVector[j])){
                    menor = this.elVector[j];
                    posmenor = j ;
                }
            }
            this.elVector[posmenor] = this.elVector[i] ;
            this.elVector[i] = menor ;
        }
    }

    /**
     * version usando métodos static
     * quizá menos adecuado
     */
    public void seleccionDirectaBis(){
        //metodo de ordenación por selección directa
        int i, j, posmenor;
        TpDato menor ;

        for ( i = 0 ; i < this.num - 1 ; i++ ) {           
            menor = this.elVector[i] ;
            posmenor = i ;
            for ( j = i + 1 ; j < this.num ; j++ ) {
                if (!TpDato.ordenados(menor,this.elVector[j])){
                    menor = this.elVector[j];
                    posmenor = j ;
                }
            }
            this.elVector[posmenor] = this.elVector[i] ;
            this.elVector[i] = menor ;
        }
    }

    /**
     * Method seleccionDirecta2 --> ordena el contenido de la 
     * estructura mediante el método de selección directa.
     * Es otra implementación del método anterior.
     */
    public void seleccionDirecta2(){
        //metodo de ordenación por selección directa
        int i, j, posmenor;
        TpDato menor ;

        for ( i = 0 ; i < this.num - 1 ; i++ ) {
            posmenor = i ;
            for ( j = i + 1 ; j < this.num ; j++ ) 
                if (!this.elVector[posmenor].
                ordenadoRespA(this.elVector[j])) 
                    posmenor = j ;
            menor = this.elVector[posmenor];
            this.elVector[posmenor] = this.elVector[i] ;
            this.elVector[i] = menor ;
        }
    }

    /**
     * version usando métodos static
     * quizá menos adecuado
     */
    public void seleccionDirecta2Bis(){
        //metodo de ordenación por selección directa
        int i, j, posmenor;
        TpDato menor ;

        for ( i = 0 ; i < this.num - 1 ; i++ ) {
            posmenor = i ;
            for ( j = i + 1 ; j < this.num ; j++ ) 
                if (!TpDato.ordenados(this.elVector[posmenor],
                    this.elVector[j])) 
                    posmenor = j ;
            menor = this.elVector[posmenor];
            this.elVector[posmenor] = this.elVector[i] ;
            this.elVector[i] = menor ;
        }
    }

    /**
     * Method intercambioDirecto --> ordena el contenido de la 
     * estructura mediante el método de intercambio directo o 
     * burbuja.
     */
    public void intercambioDirecto(){
        //metodo de ordenación por intercambio directo o burbuja
        int i, j ;
        TpDato apoyo ;

        for ( i = 0 ; i < this.num - 1 ; i++ ) {            
            for ( j = this.num - 1 ; j >= i + 1 ; j-- ) {
                if ( !this.elVector[j-1].
                ordenadoRespA(this.elVector[j])){
                    apoyo = this.elVector[j-1];
                    this.elVector[j-1] = this.elVector[j] ;
                    this.elVector[j] = apoyo ;
                }
            }
        }
    }

    /**
     * version usando métodos static
     * quizá menos adecuado
     */
    public void intercambioDirectoBis(){
        //metodo de ordenación por intercambio directo o burbuja
        int i, j ;
        TpDato apoyo ;

        for ( i = 0 ; i < this.num - 1 ; i++ ) {            
            for ( j = this.num - 1 ; j >= i + 1 ; j-- ) {
                if ( !TpDato.ordenados(this.elVector[j-1],
                    this.elVector[j])){
                    apoyo = this.elVector[j-1];
                    this.elVector[j-1] = this.elVector[j] ;
                    this.elVector[j] = apoyo ;
                }
            }
        }
    }
}
