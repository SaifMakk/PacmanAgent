package de.fh.stud.p1;

import java.util.*;

import de.fh.kiServer.agents.Agent;
import de.fh.kiServer.util.Util;
import de.fh.pacman.PacmanAgent;
import de.fh.pacman.PacmanGameResult;
import de.fh.pacman.PacmanPercept;
import de.fh.pacman.PacmanStartInfo;
import de.fh.pacman.enums.PacmanAction;
import de.fh.pacman.enums.PacmanActionEffect;

public class MyAgent_P1 extends PacmanAgent {

	boolean n = false;
	boolean leer = false;
	private int f = 0;
	private PacmanAction nextAction;
	private Queue<PacmanAction> XYactions;
	private Queue<PacmanAction> actions;
	private Queue<PacmanAction> PWactions;
	PacmanAction m = PacmanAction.GO_SOUTH;

	public MyAgent_P1(String name) {
		super(name);
		PWactions = new ArrayDeque<>();
		XYactions = new ArrayDeque<>();
		actions = new ArrayDeque<>();

		actions.add(PacmanAction.GO_EAST);
		actions.add(PacmanAction.GO_WEST);
		
		
		XYactions.add(PacmanAction.GO_NORTH);
		XYactions.add(PacmanAction.GO_WEST);
	    XYactions.add(PacmanAction.GO_SOUTH);
	    XYactions.add(PacmanAction.GO_EAST);
		

	}

	public static void main(String[] args) {
		MyAgent_P1 agent = new MyAgent_P1("MyAgent");
		Agent.start(agent, "127.0.0.1", 5000);
	}

	@Override
	public PacmanAction action(PacmanPercept percept, PacmanActionEffect actionEffect) {
		Util.printView(percept.getView());

		return Umlaufen(actionEffect);

	}

	@Override
	protected void onGameStart(PacmanStartInfo startInfo) {

	}

	@Override
	protected void onGameover(PacmanGameResult gameResult) {

	}

	private PacmanAction Umlaufen(PacmanActionEffect actionEffect) {
		if (actionEffect == PacmanActionEffect.BUMPED_INTO_WALL)
			XYactions.add(XYactions.poll());
		return XYactions.peek();
	}
	
	private PacmanAction PW_01(PacmanActionEffect actionEffect) {
		if (n) {
		m=PacmanAction.GO_EAST;
			if (actionEffect == PacmanActionEffect.BUMPED_INTO_WALL)
				m=PacmanAction.GO_WEST;
			n=false;
			return m;}
		
		
		
		return Umlaufen( actionEffect);
	}
	
	
	
	

	private PacmanAction EatAllDots(PacmanActionEffect actionEffect) {
		if (actionEffect == PacmanActionEffect.BUMPED_INTO_WALL) {

			f++;
			actions.add(actions.poll());

			if (f == 2) {
				if (m == PacmanAction.GO_SOUTH) {

					m = PacmanAction.GO_NORTH;
					actions.add(actions.poll());
					f = 0;
					n = true;

				} else {
					m = PacmanAction.GO_SOUTH;
					if (n) {
						actions.add(actions.poll());
						n = false;
					}
					f = 0;
				}
			}

			return m;

		}
		f = 0;

		return actions.peek();
	}

}
