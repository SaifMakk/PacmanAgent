package de.fh.stud.p2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.fh.pacman.enums.PacmanAction;
import de.fh.pacman.enums.PacmanActionEffect;
import de.fh.pacman.enums.PacmanTileType;

public class Knoten {
	
	/*
	 * TODO Praktikum 2 [1]: Erweitert diese Klasse um die notwendigen
	 * Attribute, Methoden und Konstruktoren um die möglichen verschiedenen Weltzustände darstellen und
	 * einen Suchbaum aufspannen zu können.
	 */
	private int posX;
	private int posY;
	private PacmanTileType[][] view;
	private Knoten parentNode;
	public PacmanAction myAction;
	private int kosten ;// kosten fuer UCS
	private int schaetzung ; //Greedy Suche 
	private int schaetzungA;//Astern

	
	public Knoten(int posX , int posY , PacmanTileType[][] view,Knoten parentNode,PacmanAction myAction) {
			this.posX=posX;
			this.posY=posY;
			this.view=view;
			this.parentNode=parentNode;
			this.myAction=myAction;
			this.kosten=berechneKosten();
			this.schaetzung=berechneSchaetzung();
			this.schaetzungA=berechneSchaetzungA();
	}
	public int getPosX() {
		return this.posX;
	}
	public int getPosY() {
		return this.posY;
	}
	public PacmanTileType getInfo() {
		return this.view[posX][posY];
	}
	public Knoten getParentNode() {
		return this.parentNode;
	}
	public boolean isViewLeer() {
		for(int i=0; i<this.view.length;i++) {
			for (int j=0; j<view[i].length;j++) {
				if(view[i][j]==PacmanTileType.DOT) {
					return false;
				}
			}
		}
			return true ;
	}
	 public  int berechneKosten() {
		  
		  if(this.parentNode!=null) {
			  return parentNode.kosten+1;
		  }
		  return 0;
	  }
	 public int getKosten() {
		 return this.kosten;
	 }
	 public int getSchaetzung() {
		 return this.schaetzung;
	 }
	 public int berechneSchaetzung() {
		 int schaetzung =0;
		 for(int i=0;i<view.length;i++) {
			 for(int j=0;j<view[i].length;j++) {
				 if(view[i][j]==PacmanTileType.DOT)schaetzung++;
			 }
		 }
		 return schaetzung ;
	 }
	 public int berechneSchaetzungA() {
		 if(this.parentNode!=null)return parentNode.getKosten()+this.schaetzung;
		 
		 return this.schaetzung;
	 }
	 public int getSchaetzungA() {
		 return this.schaetzungA;
	 }



	public PacmanTileType [][] getView(){
		return this.view;
	}
	public void printView() {
		for(int i=0;i<view.length;i++) {
			for(int j=0;j<view[i].length;j++) {
				System.out.print(view[i][j] +"|");
			}
			System.out.println();
		}
		System.out.println("-----------------------------");
	}
	public static PacmanTileType[][] copyView(PacmanTileType[][] view){
		PacmanTileType[][] cpy=new PacmanTileType[view.length][view[0].length];
		for(int i=0;i<view.length;i++) {
			for(int j=0;j<view[i].length;j++) {
				cpy[i][j]=view[i][j];
			}
		}
		return cpy;
	}
	
	@Override
	public boolean equals(Object o) {
		Knoten k=(Knoten) o;
		if(this.getPosX()==k.getPosX() && this.getPosY()==k.getPosY() && Arrays.deepEquals(this.view, k.view) ) {
				return true;
		}
			return false;
	}
	public List<Knoten> expand() {
		/*
		 * TODO Praktikum 2 [2]: Implementiert in dieser Methode das Expandieren des Knotens.
		 * Die Methode soll die neu erzeugten Knoten (die Kinder des Knoten) zurückgeben.
		 */
		
		List<Knoten> kinder=new ArrayList<>();
		if(posX>0 && view[posX-1][posY]!=PacmanTileType.WALL) {
			PacmanTileType [][]kView=copyView(view);
			kView[posX-1][posY]=PacmanTileType.EMPTY;
			Knoten kind=new Knoten(posX-1,posY,kView,this,PacmanAction.GO_WEST);
			kinder.add(kind);
		}
		if(posY>0 && view[posX][posY-1]!=PacmanTileType.WALL) {
			PacmanTileType[][] kView=copyView(view);
			kView[posX][posY-1]=PacmanTileType.EMPTY;
			Knoten kind=new Knoten(posX,posY-1,kView,this,PacmanAction.GO_NORTH);
			kinder.add(kind);
		}
		if(posX<view.length-1 && view[posX+1][posY]!=PacmanTileType.WALL) {
			PacmanTileType[][] kView=copyView(view);
			kView[posX+1][posY]=PacmanTileType.EMPTY;
			Knoten kind=new Knoten(posX+1,posY,kView,this,PacmanAction.GO_EAST);
			kinder.add(kind);
		}
		if(posY<view[posX].length-1 && view[posX][posY+1]!=PacmanTileType.WALL) {
			PacmanTileType[][] kView=copyView(view);
			kView[posX][posY+1]=PacmanTileType.EMPTY;
			Knoten kind=new Knoten(posX, posY+1,kView,this,PacmanAction.GO_SOUTH);
			kinder.add(kind);
		}
		return kinder;
	}
	
}
