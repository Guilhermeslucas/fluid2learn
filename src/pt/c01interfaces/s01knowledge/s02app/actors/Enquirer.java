package pt.c01interfaces.s01knowledge.s02app.actors;

import pt.c01interfaces.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IDeclaracao;
import pt.c01interfaces.s01knowledge.s01base.inter.IEnquirer;
import pt.c01interfaces.s01knowledge.s01base.inter.IObjetoConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IResponder;

public class Enquirer implements IEnquirer
{
    IObjetoConhecimento obj;
    String pergunta;
    String respostaEsperada;
    String resposta;
	
	public Enquirer()
	{
	}
	
	
	@Override
	public void connect(IResponder responder)
	{
        IBaseConhecimento bc = new BaseConhecimento();
        
        /*lista o nome de todos animais do banco de dados*/
        String animais[] = bc.listaNomes();
        boolean encontrou = true;
        
        for (String animal : animais && encontrou) {
        	obj = bc.recuperaObjeto(animal);
        	IDeclaracao decl = obj.primeira();
        	boolean esperado = true;
        	
        	while (decl != null && esperado) {
        		pergunta = decl.getPropriedade();
        		respostaEsperada = decl.getValor();
        		resposta = responder.ask(pergunta);
        		
        		if (resposta.equalsIgnoreCase(respostaEsperada)) {
        			decl = obj.proxima();
        		}
        		
        		else {
        			esperado = false;
        		}
        		
        		}
        	
        	if (esperado) {
        		encontrou = false;
        		boolean acertei = responder.finalAnswer(animal)
        		
        	}
        	
        	
	}
		
		
	}

}
