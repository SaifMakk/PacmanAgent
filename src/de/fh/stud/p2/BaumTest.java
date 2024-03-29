package de.fh.stud.p2;

import de.fh.kiServer.util.Util;
import de.fh.pacman.enums.PacmanTileType;

import java.net.StandardSocketOptions;
import java.util.List;

public class BaumTest {

	public static void main(String[] args) {
		//Anfangszustand nach Aufgabe
		PacmanTileType[][] view = {
				{PacmanTileType.WALL,PacmanTileType.WALL,PacmanTileType.WALL,PacmanTileType.WALL},
				{PacmanTileType.WALL,PacmanTileType.EMPTY,PacmanTileType.DOT,PacmanTileType.WALL},
				{PacmanTileType.WALL,PacmanTileType.DOT,PacmanTileType.WALL,PacmanTileType.WALL},
				{PacmanTileType.WALL,PacmanTileType.WALL,PacmanTileType.WALL,PacmanTileType.WALL}
		};
		//Startposition des Pacman
		int posX = 1, posY = 1;
		Knoten wurzel=new Knoten(null,posX,posY,view,null);
		
			List<Knoten> openList =wurzel.expand();
			for( Knoten k :  openList){

				System.out.println(k);
				Util.printView(k.view);
			
		}
		
		/*
		 * TODO Praktikum 2 [3]: Baut hier basierend auf dem gegebenen 
		 * Anfangszustand (siehe view, posX und posY) den Suchbaum auf.
		 */
	}
}
