package views;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
  
public class TamanhoFixoJText extends PlainDocument {
  
       /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tamMax;  
     
       public TamanhoFixoJText(int tamMax) {  
             super();  
             this.tamMax = tamMax;  
       }  
   
       public void insertString(int offset, String str, AttributeSet attr)  
                    throws BadLocationException {  
  
             if (str == null) 
                    return;  
   
             //Define a condi para aceitar qualquermero de caracteres
        if (tamMax <= 0)
        {
            super.insertString(offset, str, attr);
            return;
        }
  
        int tam = (getLength() + str.length());
         
        //Se o tamanho final for menor, chama insertString() aceitando a String
        if (tam <= tamMax)
            super.insertString(offset, str, attr);
        } 
        
}