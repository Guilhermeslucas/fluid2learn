package pt.c02classes.s01knowledge.s02app.actors;

import java.util.Stack;

import pt.c02classes.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;

public class EnquirerMaze implements IEnquirer {

	IResponder responder;
	//pilha para armazenar as ultimas direções que o personagem andou
	Stack <String> coordenadas;

	public void connect(IResponder responder) {
		this.responder = responder;
	}

	//funcao para verificar se o personagem esta voltando no caminho
	public boolean labirinto() {

		/*
		System.out.println("Norte: " + responder.ask("norte"));
		System.out.println("Sul: " + responder.ask("sul"));
		System.out.println("Leste: " + responder.ask("leste"));
		System.out.println("Oeste: " + responder.ask("oeste"));
		System.out.println("=================================");
		*/

		if (responder.ask("aqui").equalsIgnoreCase("saida"))
			return true;

		boolean possivel = false;

		if (responder.ask("norte").equalsIgnoreCase("passagem") &&  !possivel || responder.ask("norte").equalsIgnoreCase("saida")) {
			if (coordenadas.empty()){
				responder.move("norte");
				coordenadas.push("norte");
				possivel = labirinto();
				coordenadas.pop();
			}
			else{
				if(!coordenadas.peek().equalsIgnoreCase("sul")){
					responder.move("norte");
					coordenadas.push("norte");
					possivel = labirinto();
					coordenadas.pop();
				}
			}


		}
		if (responder.ask("sul").equalsIgnoreCase("passagem") && !possivel || responder.ask("sul").equalsIgnoreCase("saida")) {
			if (coordenadas.empty()){
				responder.move("sul");
				coordenadas.push("sul");
				possivel = labirinto();
				coordenadas.pop();
			}
			if (!coordenadas.empty()) {
				if(!coordenadas.peek().equalsIgnoreCase("norte")){
					responder.move("sul");
					coordenadas.push("sul");
					possivel = labirinto();
					coordenadas.pop();
				}
			}


		}
		if (responder.ask("leste").equalsIgnoreCase("passagem") && !possivel || responder.ask("leste").equalsIgnoreCase("saida")) {
			if (coordenadas.empty()){
				responder.move("leste");
				coordenadas.push("leste");
				possivel = labirinto();
				coordenadas.pop();
			}
			if (!coordenadas.empty()) {
				if(!coordenadas.peek().equalsIgnoreCase("oeste")){
					responder.move("leste");
					coordenadas.push("leste");
					possivel = labirinto();
					coordenadas.pop();
				}
			}


		}
		if (responder.ask("oeste").equalsIgnoreCase("passagem") && !possivel || responder.ask("oeste").equalsIgnoreCase("saida")) {
			if (coordenadas.empty()){
				responder.move("oeste");
				coordenadas.push("oeste");
				possivel = labirinto();
				coordenadas.pop();
			}
			if (!coordenadas.empty()) {
				if(!coordenadas.peek().equalsIgnoreCase("leste")){
					responder.move("oeste");
					coordenadas.push("oeste");
					possivel = labirinto();
					coordenadas.pop();
				}
			}


		}

		return possivel;
	}

	public boolean discover() {
		coordenadas = new Stack <String>();

		if (labirinto())
			System.out.println("Você encontrou a saida!");
		else
			System.out.println("Fuém fuém fuém!");

		return true;
	}
}
