package de.fh.stud.p2;

import java.util.ArrayList;
import java.util.List;

import de.fh.pacman.enums.PacmanTileType;

public class Knoten {

    private PacmanTileType[][] worldState;
    private int pacmanX;
    private int pacmanY;
    private List<Knoten> children;

    public Knoten(PacmanTileType[][] worldState, int pacmanX, int pacmanY) {
        this.worldState = worldState;
        this.pacmanX = pacmanX;
        this.pacmanY = pacmanY;
        this.children = new ArrayList<>();
    }

    public PacmanTileType[][] getWorldState() {
        return worldState;
    }

    public int getPacmanX() {
        return pacmanX;
    }

    public int getPacmanY() {
        return pacmanY;
    }

    public List<Knoten> getChildren() {
        return children;
    }

    public void addChild(Knoten child) {
        children.add(child);
    }

    public boolean isGoalState() {
        // TODO: Implement the goal state check
        return false;
    }

    public List<Knoten> expand() {
        List<Knoten> newNodes = new ArrayList<>();
        
        // Check if the Pac-Man can go north
        if (worldState[pacmanX-1][pacmanY] != PacmanTileType.WALL) {
            PacmanTileType[][] newState = cloneState(worldState);
            newState[pacmanX][pacmanY] = PacmanTileType.EMPTY;
            newState[--pacmanX][pacmanY] = PacmanTileType.PACMAN;
            newNodes.add(new Knoten(newState, pacmanX, pacmanY));
        }
        
        // Check if the Pac-Man can go east
        if (worldState[pacmanX][pacmanY+1] != PacmanTileType.WALL) {
            PacmanTileType[][] newState = cloneState(worldState);
            newState[pacmanX][pacmanY] = PacmanTileType.EMPTY;
            newState[pacmanX][++pacmanY] = PacmanTileType.PACMAN;
            newNodes.add(new Knoten(newState, pacmanX, pacmanY));
        }
        
        // Check if the Pac-Man can go south
        if (worldState[pacmanX+1][pacmanY] != PacmanTileType.WALL) {
            PacmanTileType[][] newState = cloneState(worldState);
            newState[pacmanX][pacmanY] = PacmanTileType.EMPTY;
            newState[++pacmanX][pacmanY] = PacmanTileType.PACMAN;
            newNodes.add(new Knoten(newState, pacmanX, pacmanY));
        }
        
        // Check if the Pac-Man can go west
        if (worldState[pacmanX][pacmanY-1] != PacmanTileType.WALL) {
            PacmanTileType[][] newState = cloneState(worldState);
            newState[pacmanX][pacmanY] = PacmanTileType.EMPTY;
            newState[pacmanX][--pacmanY] = PacmanTileType.PACMAN;
            newNodes.add(new Knoten(newState, pacmanX, pacmanY));
        }
        
        // Pac-Man can always wait
        PacmanTileType[][] newState = cloneState(worldState);
        newState[pacmanX][pacmanY] = PacmanTileType.EMPTY;
        newNodes.add(new Knoten(newState, pacmanX, pacmanY));
        
        return newNodes;
    }

    private PacmanTileType[][] cloneState(PacmanTileType[][] state) {
        PacmanTileType[][] newState = new PacmanTileType[state.length][];
        for (int i = 0; i < state.length; i++) {
            newState[i] = state[i].clone();
        }
        return newState;
    }
}
