/*
Implementacion Máquina Enigma modelo Enigma I 
con rotores de los tipos I, II, III, utilizando reflector B 

Por elssbbboy
 */
package enigmacryptography;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Rotor r1 = new Rotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ", 'Q'); //tipo1
        Rotor r2 = new Rotor("AJDKSIRUXBLHWTMCQGZNPYFVOE", 'E'); //tipo2
        Rotor r3 = new Rotor("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V'); //tipo3 
        
        // derecho, medio, izquierdo
        MaquinaEnigma enigma = new MaquinaEnigma(r1, r2, r3);
        //enigma.ponerClavija('A', 'B');
        
        // derecho, medio, izquierdo
        enigma.setRotorsIni('A', 'A', 'A');
        System.out.println(enigma.obtenerPosicionesRotores());
        procesar(enigma);
    }
    
    public static void procesar(MaquinaEnigma enigma){
        String s, resultado = "";
        char c = '0';
        Scanner sc = new Scanner(System.in);
        
        while(true){
            System.out.println("Introduce una cadena de letras.\nPon un numero para terminar.");
            s = sc.next();
            for(int i = 0; i < s.length(); i++){
                c = s.charAt(i);
                if(c >'0' && c < '9'){
                    System.exit(0);
                }
                if(c >= 'a' && c <= 'z'){
                    c = (char) (c + 'A' - 'a');
                }
                resultado += enigma.cifrado(c);
            }
            
            System.out.println("La cadena "+s+" la pasa a "+resultado);
            enigma.reinicializar();
            resultado = "";
            System.out.println("Maquina reinicializada");
        }
    }
    
}
