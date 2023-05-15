package de.fh.stud.p3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.fh.pacman.enums.PacmanTileType;
import de.fh.stud.p2.Knoten;


public class Suche {
	
	/*
	 * TODO Praktikum 3 [1]: Erweitern Sie diese Klasse um die notwendigen
	 * Attribute und Methoden um eine Tiefensuche durchführen zu können.
	 * Die Erweiterung um weitere Suchstrategien folgt in Praktikum 4.
	 */
	public enum SucheStrategy {
	    BreitenSuche,
	    TiefenSuche,
	    UNIFORM_COST_SEARCH,
	    GREEDY_SEARCH,
	    A_STAR
	}
	
	private SucheStrategy sucheStartegy;
	private Knoten startNode;
	//private Knoten parentNode;
	public Suche(Knoten startNode,SucheStrategy sucheStartegy) {
		this.startNode=startNode;
		this.sucheStartegy=sucheStartegy;
	}
	
	
	
	/*
	 * TODO Praktikum 4 [1]: Erweitern Sie diese Klasse um weitere Suchstrategien (siehe Aufgabenblatt)
	 * zu unterstützen.
	 */
	
	public Knoten start() {
		
		switch (sucheStartegy) {
        case BreitenSuche:
            return breitenSuche();
        case TiefenSuche:
            return tiefenSuche();
        case UNIFORM_COST_SEARCH:
            return uniformCostSearch();
        case GREEDY_SEARCH:
            return greedySearch();
        case A_STAR:
            return aStarSearch();
         default:return null;
	}
	}
	public Knoten uniformCostSearch() {
		Queue<Knoten> openList= new PriorityQueue<>(Comparator.comparingInt(Knoten::getKosten));
		List<Knoten>   closeList =new ArrayList<Knoten>();
		openList.add(startNode);
		while(!openList.isEmpty()) {
			Knoten knote=openList.poll();
			if(knote.isViewLeer()) {
				System.out.println("OpenList :" +openList.size()+" CloseList : "+ closeList.size());
				return knote;
			}
			
			if(!closeList.contains(knote)) {
				closeList.add(knote);
				List<Knoten> childs=knote.expand();
				childs.forEach(child -> {
					if(!closeList.contains(child)) 
						openList.add(child);
				}
					);
				}
			
		}
		return null;
	}
	public Knoten greedySearch() {
		Queue<Knoten> openList=new PriorityQueue<>(Comparator.comparingInt(Knoten::getSchaetzung));
		List<Knoten> closeList=new ArrayList<Knoten>();
		openList.add(startNode);
		while(!openList.isEmpty()) {
			Knoten knote=openList.poll();
			if(knote.isViewLeer()) {
				System.out.println("OpenList :" +openList.size()+" CloseList : "+ closeList.size());
				return knote;
			}
			
			if(!closeList.contains(knote)) {
				closeList.add(knote);
				List<Knoten> childs=knote.expand();
				childs.forEach(child -> {
					if(!closeList.contains(child)) 
						openList.add(child);
				}
					);
				}
			
		}
		return null;
	}
	public Knoten aStarSearch() {
		Queue<Knoten> openList=new PriorityQueue<>(Comparator.comparingInt(Knoten::getSchaetzungA));
		List<Knoten> closeList=new ArrayList<Knoten>();
		openList.add(startNode);
		while(!openList.isEmpty()) {

			Knoten knote=openList.poll();
			if(knote.isViewLeer()) {
				System.out.println("OpenList :" +openList.size()+" CloseList : "+ closeList.size());
				return knote;
			}
			
			if(!closeList.contains(knote)) {
				closeList.add(knote);
				List<Knoten> childs=knote.expand();
				childs.forEach(child -> {
					if(!closeList.contains(child)) 
						openList.add(child);
				}
					);
				}
			
		}
		return null;
	}

	
	
	public Knoten breitenSuche() {
		Queue<Knoten> openList =new LinkedList();
		List<Knoten>   closeList =new ArrayList<Knoten>();
		  openList.add(startNode);
			while(!openList.isEmpty()) {
				Knoten knote=openList.remove();
				if(knote.isViewLeer()) {
					System.out.println("OpenList :" +openList.size()+" CloseList : "+ closeList.size());
					return knote;
			}
				if(!closeList.contains(knote)) {
					closeList.add(knote);
					List<Knoten> childs=knote.expand();
					childs.forEach(child -> {
						if(!closeList.contains(child)) 
							openList.add(child);
					}
						);
					}
			}

			return null;

		
	}
	public Knoten tiefenSuche() {
		Stack <Knoten>  openList=new Stack<Knoten>();
		List<Knoten>   closeList =new ArrayList<Knoten>();

		openList.push(startNode);
		while(!openList.isEmpty()) {
			Knoten knote=openList.pop();
			if(knote.isViewLeer()) {
				System.out.println("OpenList :" +openList.size()+" CloseList : "+ closeList.size());

				return knote;
		}
			if(!closeList.contains(knote)) {
				closeList.add(knote);
				List<Knoten> childs=knote.expand();
				childs.forEach(child -> {
					if(!closeList.contains(child)) 
						openList.push(child);
				}
					);
				}
		}

		return null;
		
	}
	
}
