package pt.c02classes.s01knowledge.s02app.actors;

import java.util.Stack;

import pt.c02classes.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;

public class EnquirerMaze implements IEnquirer {

	IResponder responder;
	
	public void connect(IResponder responder) {
		this.responder = responder;
	}
	
	//funcao para verificar se o personagem esta voltando no caminho
	public boolean voltando(String veio, String vai) {
		if (veio.equalsIgnoreCase("Norte") && vai.equalsIgnoreCase("Sul"))
			return true;
		
		else if(veio.equalsIgnoreCase("Leste") && vai.equalsIgnoreCase("Oeste"))
			return true;
		
		else if(veio.equalsIgnoreCase("Oeste") && vai.equalsIgnoreCase("Leste"))
			return true;
		
		else if(veio.equalsIgnoreCase("Sul") && vai.equalsIgnoreCase("Norte"))
			return true;
		
		return false;
			
	}
	
	public String volta(String ultimo) {
		if (ultimo.equalsIgnoreCase("Norte"))
			return "sul";
		
		else if (ultimo.equalsIgnoreCase("Sul"))
			return "norte";
		
		else if (ultimo.equalsIgnoreCase("Leste"))
			return "oeste";
		
		else
			return "leste";
	}
	
	public boolean discover() {
		//Vetor que mostra quais as direcoes possiveis a serem seguidas
		String[] coordenadas = new String[]{"norte","oeste","sul","leste"};
		//pilha para armazenar o caminho que o usuario vai realizando
		Stack<String> caminho = new Stack<>();
		
		IBaseConhecimento bc = new BaseConhecimento();
        bc.setScenario("Maze");
		
		String local = responder.ask("Aqui");
		
		while (!local.equalsIgnoreCase("Saida")) {
			//variavel boleana para garantir se conseguiu ou não andar
			boolean caminhou = true;
			//variavel para analisar qual foi o ultima direcao que o usuario foi
			String ultMove = null;
			
			if (!caminho.empty())
				ultMove = caminho.peek();
			
			//tentar andar nas 4 direcoes
			for (int i = 0; i < 4 && caminhou; i++) {
				String movimento = coordenadas[i];
				
				if (responder.ask(movimento).equalsIgnoreCase("passagem") && !voltando(ultMove,coordenadas[i])) {
					caminhou = false;
					caminho.push(movimento);
					responder.move(movimento);
					local = responder.ask("Aqui");
				}
			}
			
			String ultMove2 = null;
			if (!caminho.empty()) 
				ultMove2 = caminho.peek();
			
			if (caminhou && !caminho.empty()) {
				String movimento = volta(ultMove2);
				responder.move(movimento);
				caminho.pop();
			}
		}
				
		if (responder.finalAnswer("cheguei"))
			System.out.println("Voc� encontrou a saida!");
		else
			System.out.println("Fu�m fu�m fu�m!");
		
		return true;

	}
}
