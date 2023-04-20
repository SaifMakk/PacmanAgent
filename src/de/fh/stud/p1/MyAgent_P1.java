package de.fh.stud.p1;

import de.fh.kiServer.agents.Agent;
import de.fh.kiServer.util.Util;
import de.fh.pacman.PacmanAgent;
import de.fh.pacman.PacmanGameResult;
import de.fh.pacman.PacmanPercept;
import de.fh.pacman.PacmanStartInfo;
import de.fh.pacman.enums.PacmanAction;
import de.fh.pacman.enums.PacmanActionEffect;

public class MyAgent_P1 extends PacmanAgent {

	/*
	 * TODO Praktikum 1: Fügt gemäß der Aufgabenstellung neue Attribute hinzu, falls notwendig.
	 */
	
	/**
	 * Die als nächstes auszuführende Aktion
	 */
	private PacmanAction nextAction;
	
	public MyAgent_P1(String name) {
		super(name);
	}
	
	public static void main(String[] args) {
		MyAgent_P1 agent = new MyAgent_P1("MyAgent");
		Agent.start(agent, "127.0.0.1", 5000);
	}
	
	/**
	 * @param percept - Aktuelle Wahrnehmung des Agenten, bspw. Position der Geister und Zustand aller Felder der Welt.
	 * @param actionEffect - Aktuelle Rückmeldung des Server auf die letzte übermittelte Aktion.
	 */
	@Override
	public PacmanAction action(PacmanPercept percept, PacmanActionEffect actionEffect) {
	    //Gebe den aktuellen Zustand der Welt auf der Konsole aus
	    Util.printView(percept.getView());

	    // Initial direction is east
	    if (nextAction == null) {
	        nextAction = PacmanAction.GO_EAST;
	    }

	    // Check the effect of the last action and update the current direction accordingly
	    switch (actionEffect) {
	        case BUMPED_INTO_WALL:
	            switch (nextAction) {
	                case GO_NORTH:
	                    nextAction = PacmanAction.GO_EAST;
	                    break;
	                case GO_EAST:
	                    nextAction = PacmanAction.GO_SOUTH;
	                    break;
	                case GO_SOUTH:
	                    nextAction = PacmanAction.GO_WEST;
	                    break;
	                case GO_WEST:
	                    nextAction = PacmanAction.GO_NORTH;
	                    break;
	            }
	            break;
	        case ATE_DOT:
	            // continue moving in the current direction
	            break;
	        case ATE_POWERPILL:
	            // continue moving in the current direction
	            break;
	        case ATE_GHOST:
	            // continue moving in the current direction
	            break;
	        case ATE_GHOST_AND_DOT:
	            // continue moving in the current direction
	            break;
	        case ATE_GHOST_AND_POWERPILL:
	            // continue moving in the current direction
	            break;
	        case BUMPED_INTO_PACMAN:
	            // continue moving in the current direction
	            break;
	        case MOVED_ON_EMPTY_TILE:
	        	
	            break;
	        case GAME_INITIALIZED:
	            // continue moving in the current direction
	            break;
	        case GAME_OVER:
	            // do nothing
	            break;
	    }

	    return nextAction;
	}

	@Override
	protected void onGameStart(PacmanStartInfo startInfo) {
		
	}

	@Override
	protected void onGameover(PacmanGameResult gameResult) {
		
	}
}
