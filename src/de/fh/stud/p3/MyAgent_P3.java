package de.fh.stud.p3;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.fh.kiServer.agents.Agent;

import de.fh.kiServer.util.Util;
import de.fh.pacman.PacmanAgent;
import de.fh.pacman.PacmanGameResult;
import de.fh.pacman.PacmanPercept;
import de.fh.pacman.PacmanStartInfo;
import de.fh.pacman.enums.PacmanAction;
import de.fh.pacman.enums.PacmanActionEffect;
import de.fh.pacman.enums.PacmanTileType;
import de.fh.stud.p2.Knoten;

public class MyAgent_P3 extends PacmanAgent {

	
	/**
	 * Die als nächstes auszuführende Aktion
	 */
	private PacmanAction nextAction;
	
	/**
	 * Der gefundene Lösungknoten der Suche
	 */
	
	private Knoten loesungsKnoten;
	public Stack<Knoten> solutionPath=new Stack<>();
	
	public MyAgent_P3(String name) {
		super(name);
	}
	
	public static void main(String[] args) {
		MyAgent_P3 agent = new MyAgent_P3("MyAgent");
		Agent.start(agent, "127.0.0.1", 5000);
	}

	/**
	 * @param percept - Aktuelle Wahrnehmung des Agenten, bspw. Position der Geister und Zustand aller Felder der Welt.
	 * @param actionEffect - Aktuelle Rückmeldung des Server auf die letzte übermittelte Aktion.
	 */
	@Override
	public PacmanAction action(PacmanPercept percept, PacmanActionEffect actionEffect) {
		//Gebe den aktuellen Zustand der Welt auf der Konsole aus
		//Util.printView(percept.getView());
		//Wenn noch keine Lösung gefunden wurde, dann starte die Suche
		if (loesungsKnoten == null) {
			/*
			 * TODO Praktikum 3 [3]: Übergebt hier der Suche die notwendigen Informationen, um
			 * von einem Startzustand zu einem Zielzustand zu gelangen.
			 */
			/*
			 * TODO Praktikum 4 [2]: Entscheidet hier welches Suchverfahren ausgeführt werden soll.
			 */
			Suche suche = new Suche(new Knoten(percept.getPosX(),percept.getPosY(),percept.getView(),null,null),Suche.SucheStrategy.GREEDY_SEARCH);
			this.loesungsKnoten = suche.start();
			solutionPath=getPath(loesungsKnoten);
			
		}
		
		//Wenn die Suche eine Lösung gefunden hat, dann ermittle die als nächstes auszuführende Aktion
            if (loesungsKnoten != null) {
			/*
			 * TODO Praktikum 3 [4]: Ermittelt hier die als naechstes auszufuehrende Aktion,
			 * basierend auf dem loesungsknoten und weist diese nextAction zu.
			 */
            	
			nextAction=solutionPath.pop().myAction;
						
		}
		 else {
				//Ansonsten wurde keine Lösung gefunden und der Pacman kann das Spiel aufgeben
			 	nextAction = PacmanAction.QUIT_GAME;
			}
		return nextAction;
	}
	
	public static Stack<Knoten> getPath(Knoten loesungsKnoten) {
			Stack<Knoten>path=new Stack<>();
		
			while(loesungsKnoten.getParentNode()!=null) {
				path.push(loesungsKnoten);
				loesungsKnoten=loesungsKnoten.getParentNode();
				}
			System.out.println("Aktionen  : "+path.size());
			return path;
	}

	@Override
	protected void onGameStart(PacmanStartInfo startInfo) {
		
	}

	@Override
	protected void onGameover(PacmanGameResult gameResult) {
		
	}
	
}
