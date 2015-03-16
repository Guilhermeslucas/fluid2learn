package pt.c02classes.s01knowledge.s02app.actors;

import java.util.HashMap;

import pt.c02classes.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IDeclaracao;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IObjetoConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;

public class EnquirerAnimals implements IEnquirer {

	IResponder responder;
	IObjetoConhecimento obj;
    String pergunta;
    String respostaEsperada;
    String resposta;
    int i;
    String animal;
	
	public void connect(IResponder responder) {
		this.responder = responder;
	}
	
	public boolean discover() {
		
		
        IBaseConhecimento bc = new BaseConhecimento();
        HashMap<String,String> map=new HashMap<String,String>();
        
        /*lista o nome de todos animais do banco de dados*/
        String animais[] = bc.listaNomes();
        boolean encontrou = true;
        
        for (i = 0; i < animais.length && encontrou; i++) {
        	animal = animais[i];
        	obj = bc.recuperaObjeto(animal);
        	IDeclaracao decl = obj.primeira();
        	boolean esperado = true;
        	
        	while (decl != null && esperado) {
        		pergunta = decl.getPropriedade();
        		respostaEsperada = decl.getValor();
        		
        		if(!map.containsKey(pergunta)){
					resposta = responder.ask(pergunta);
					map.put(pergunta, resposta);

				}
				else
					//ja fez a pergunta
					resposta = map.get(pergunta);
					
        		if (resposta.equalsIgnoreCase(respostaEsperada)) {
        			decl = obj.proxima();
        		}
        		
        		else {
        			esperado = false;
        		}
        	}
        		
        	
        	if (esperado) {
        		encontrou = false;
        		
        	}
        }
 
        boolean acertei = responder.finalAnswer(animal);
        
        if (acertei) {
        	System.out.println("Oba! Acertei!");
        	return true;
        }
        
        else {
        	System.out.println("fuem! fuem! fuem");
        	return false;
        }
        
	}
}
