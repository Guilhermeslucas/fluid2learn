package pt.c02classes.s01knowledge.s02app.app;

import pt.c02classes.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c02classes.s01knowledge.s01base.impl.Statistics;
import pt.c02classes.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;
import pt.c02classes.s01knowledge.s01base.inter.IStatistics;
import pt.c02classes.s01knowledge.s02app.actors.EnquirerAnimals;
import pt.c02classes.s01knowledge.s02app.actors.EnquirerMaze;
import pt.c02classes.s01knowledge.s02app.actors.ResponderAnimals;
import pt.c02classes.s01knowledge.s02app.actors.ResponderMaze;


/*biblioteca com scanner*/
import java.util.Scanner;


public class OrchestratorInit {
	
	public static void main(String args[])
	{
		IEnquirer enq;
		IResponder resp;
		IStatistics stat;
		IBaseConhecimento base = new BaseConhecimento();
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Qual o jogo? [A]nimals ou [M]aze");
		String tipo = scanner.nextLine();
		
		if (tipo.equalsIgnoreCase("A")) {
			base.setScenario("animals");
			System.out.println("Qual o animal?");
			String animal = scanner.nextLine();
			stat = new Statistics();
			resp = new ResponderAnimals(stat, animal);
			enq = new EnquirerAnimals();
			enq.connect(resp);
			enq.discover();
			
		}
		
		else if (tipo.equalsIgnoreCase("M")) {
			System.out.println("Qual o labirinto?");
			String labirinto = scanner.nextLine();
			stat = new Statistics();
			resp = new ResponderMaze(stat, labirinto);
			enq = new EnquirerMaze();
			enq.connect(resp);
			enq.discover();
		}
		scanner.close();
	}

}
