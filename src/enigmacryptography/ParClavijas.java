/*
Implementacion Máquina Enigma modelo Enigma I 
con rotores de los tipos I, II, III, utilizando reflector B 

Por elssbbboy
 */
package enigmacryptography;

public class ParClavijas {
    private char x;
    private char y;
    
    public ParClavijas(char a, char b){
        x=a;
        y=b;
    }
    
    public char getX(){
        return x;
    }
    
    public char getY(){
        return y;
    }
}
