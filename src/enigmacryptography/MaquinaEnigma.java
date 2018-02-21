/*
Implementacion Máquina Enigma modelo Enigma I 
con rotores de los tipos I, II, III, utilizando reflector B 

Por elssbbboy
 */
package enigmacryptography;

public class MaquinaEnigma {
    private Rotor entrada; // rotor entrada
    private Rotor rotor1; // rotor derecho
    private Rotor rotor2; // rotor medio
    private Rotor rotor3; // rotor izquierdo
    private Rotor rotor1i; // inicial derecho
    private Rotor rotor2i; // inicial medio
    private Rotor rotor3i; // inicial izquierdo
    private final Rotor reflector; // reflector
    private ConexionClavijas plugboard;
    
    /**
     * Crea maquina enigma por defecto
     */
    public MaquinaEnigma(){
        entrada = new Rotor("ABCDEFGHIJKLMNOPQRSTUVWXYZ", '-');
        rotor1 = new Rotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ", 'Q');
        rotor2 = new Rotor("AJDKSIRUXBLHWTMCQGZNPYFVOE", 'E');
        rotor3 = new Rotor("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V');
        reflector = new Rotor("YRUHQSLDPXNGOKMIEBFZCWVJAT", '-'); //Reflector B
        plugboard = new ConexionClavijas();
    }
    
    /**
     * Crea maquina enigma
     * @param r1 rotor derecho
     * @param r2 rotor medio
     * @param r3 rotor izquierdo
     */
    public MaquinaEnigma(Rotor r1, Rotor r2, Rotor r3){
        entrada = new Rotor("ABCDEFGHIJKLMNOPQRSTUVWXYZ", '-');
        rotor1 = r1;
        rotor2 = r2;
        rotor3 = r3;
        reflector = new Rotor("YRUHQSLDPXNGOKMIEBFZCWVJAT", '-'); //Reflector B
        plugboard = new ConexionClavijas();
    }
    
    /**
     * Crea maquina enigma
     * @param r1 rotor derecho
     * @param r2 rotor medio
     * @param r3 rotor izquierdo
     * @param ref reflector
     */
    public MaquinaEnigma(Rotor r1, Rotor r2, Rotor r3, Rotor ref){
        entrada = new Rotor("ABCDEFGHIJKLMNOPQRSTUVWXYZ", '-');
        rotor1 = r1;
        rotor2 = r2;
        rotor3 = r3;
        reflector = ref;
        plugboard = new ConexionClavijas();
    }
    
    /**
     * Poner posicion inicial de los rotores
     * @param c1 Caracter Posicion inicial derecho
     * @param c2 Caracter Posicion inicial medio
     * @param c3 Caracter Posicion inicial izquierdo
     */
    public void setRotorsIni(char c1, char c2, char c3){
        int p1 = c1 - 'A';
        int p2 = c2 - 'A';
        int p3 = c3 - 'A';
        rotor1 = rotor1.giro(p1,true);
        rotor2 = rotor2.giro(p2,true);
        rotor3 = rotor3.giro(p3,true);
        rotor1i = rotor1;
        rotor2i = rotor2;
        rotor3i = rotor3;
    }
    
    /**
     * Actualizacion posicion de los rotores
     */
    public void actualizarRotores(){
        char c1 = rotor1.obtenerContPrev().charAt(0);
        char c2 = rotor2.obtenerContPrev().charAt(0);
        
        if (c1 == rotor1.obtenerNotch()) {
            if (c2 == rotor2.obtenerNotch()) {
                rotor3 = rotor3.giro(1, true);
            }
            rotor2 = rotor2.giro(1, true);
        } else {
            if (c2 == rotor2.obtenerNotch()){
                rotor3 = rotor3.giro(1, true);
                rotor2 = rotor2.giro(1, true);
            }
        }
        rotor1 = rotor1.giro(1, true);
        System.out.println(c1);
    }
    
    /**
     * Cifrar caracter
     * @param c Caracter a cifrar
     * @return Caracter cifrado
     */
    public char cifrado(char c){
        char aux;
        actualizarRotores();
        int index = entrada.obtenerContenido().indexOf(c);
        index = rotor1.x_a_y(index);
        index = rotor2.x_a_y(index);
        index = rotor3.x_a_y(index);
        index = reflector.x_a_y(index);
        index = rotor3.y_a_x(index);
        index = rotor2.y_a_x(index);
        index = rotor1.y_a_x(index);
        aux = entrada.obtenerContenido().charAt(index);
        return aux;
    }
    
    /**
     * Recupera la configuracion
     */
    public void reinicializar(){
        rotor1 = rotor1i;
        rotor2 = rotor2i;
        rotor3 = rotor3i;
    }
    
    /**
     * Poner clavija en el panel
     * @param a caracter a
     * @param b caracter b
     */
    public void ponerClavija(char a, char b){
        if(plugboard.establecerConexion(a, b) != -1){
            String aux = "";
            String contenido = entrada.obtenerContenido();
            char sus;
            if(a >= 'a' && a <= 'z'){
                a = (char) (a + 'A' - 'a');
            }
            if(b >= 'a' && b <= 'z'){
                b = (char) (b + 'A' - 'a');
            }
            if(a < b){
                sus = a;
            } else{
                sus = b;
            }
            for(int i = 0; i < contenido.length(); i++){
                if(contenido.charAt(i)==sus){
                    aux += sus;
                    sus = contenido.charAt(i);
                } else{
                    aux += contenido.charAt(i);
                }
            }
            entrada = new Rotor(aux, entrada.obtenerNotch());
        }
    }
    
    /**
     * Eliminar clavija
     * @param a Caracter A
     * @param b Caracter B
     */
    public void eliminarClavija(char a, char b){
        if(plugboard.eliminarConexion(a, b) != -1){
            String aux = "";
            String contenido = entrada.obtenerContenido();
            char sus;
            if(a >= 'a' && a <= 'z'){
                a = (char) (a + 'A' - 'a');
            }
            if(b >= 'a' && b <= 'z'){
                b = (char) (b + 'A' - 'a');
            }
            if(a < b){
                sus = a;
            } else{
                sus = b;
            }
            for(int i = 0; i < contenido.length(); i++){
                if(contenido.charAt(i)==sus){
                    aux += sus;
                    sus = contenido.charAt(i);
                } else{
                    aux += contenido.charAt(i);
                }
            }
            entrada = new Rotor(aux, entrada.obtenerNotch());
        }
    }
    
    /**
     * Devuelve las posiciones de los rotores
     * @return String con las posiciones de los rotores
     */
    public String obtenerPosicionesRotores(){
        return "Posicion rotor 1 (derecho): "+rotor1.obtenerPosicion()+
                "\nPosicion rotor 2 (medio): "+rotor2.obtenerPosicion()+
                "\nPosicion rotor 3 (izquierdo): "+rotor3.obtenerPosicion()+"\n";
    }
}
