package com.example.game1.entities;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * A builder for maze.
 */
class MazeBuilder {
    /**
     * Side length of box.
     */
    private int boxSize;
    /**
     * Margin of x and y.
     */
    private int margin;
    /**
     * An array of boxes that make up the maze.
     */
    private Box[][] boxes;
    /**
     * The maze runner in this maze.
     */
    private MazeRunner mazeRunner;
    /**
     * The target to get to in this maze.
     */
    private Target target;
    /**
     * The artifacts in this maze.
     */
    private ArrayList<MazeArtifact> artifacts;
    /**
     * Number of columns in maze.
     */
    private static final int NUM_COLS = 5;
    /**
     * Number of rows in maze.
     */
    private static final int NUM_ROWS = 8;

    /**
     * set boxSize
     *
     * @param width width of the maze
     * @return the builder
     */
    MazeBuilder setBoxSize(int width) {
        this.boxSize = width / 5;
        return this;
    }

    /**
     * set margin
     *
     * @param width width of the maze
     * @return the builder
     */
    MazeBuilder setMargin(int width) {
        this.margin = (int) Math.floor(width * 0.1);
        return this;
    }

    /**
     * set boxes
     *
     * @return the array of boxes
     */
    MazeBuilder setBoxes() {
        this.boxes = new Box[NUM_COLS][NUM_ROWS];

        Stack<Box> stack = new Stack<>();
        Box curr, next;

        // Initialize 2-D array of boxes
        for (int x = 0; x < NUM_COLS; x++) {
            for (int y = 0; y < NUM_ROWS; y++) {
                Box box = new Box(x, y, boxSize, margin);
                this.boxes[x][y] = box;
            }
        }

        // Random maze generator
        curr = this.boxes[0][0];
        curr.setVisited(true);
        stack.push(curr); // stack of visited boxes

        while (!(stack.empty())) {
            next = getNeighbour(curr);
            if (next != null) {
                removeWall(curr, next);
                stack.push(curr);
                curr = next;
                curr.setVisited(true);
            } else {
                curr = stack.pop();
            }
        }
        return this;
    }

    /**
     * set mazeRunner
     *
     * @return the MazeBuilder
     */
    MazeBuilder setMazeRunner() {
        this.mazeRunner = new MazeRunner();
        return this;
    }

    /**
     * set target
     *
     * @return the MazeBuilder
     */
    MazeBuilder setTarget() {
        this.target = new Target();
        return this;
    }

    /**
     * set artifact
     *
     * @return MazeBuilder
     */
    MazeBuilder setArtifacts() {
        artifacts = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 5; i++) {
            int randX = rand.nextInt(NUM_COLS);
            int randY = rand.nextInt(NUM_ROWS);

            // Check for duplicates
            for (MazeArtifact artifact : artifacts) {
                if (artifact.getX() == randX && artifact.getY() == randY) {
                    randX = rand.nextInt(NUM_COLS);
                    randY = rand.nextInt(NUM_ROWS);
                }
            }
            // Check if artifact is placed at (0,0) or (4,7)
            while ((randX == 0 && randY == 0) || (randX == (NUM_COLS - 1) && randY == (NUM_ROWS - 1))) {
                randX = rand.nextInt(NUM_COLS);
                randY = rand.nextInt(NUM_ROWS);
            }
            artifacts.add(new MazeArtifact(randX, randY));
        }
        return this;
    }

    /**
     * build the Maze
     *
     * @return a new maze.
     */
    Maze build() {
        return new Maze(this);
    }

    /**
     * get neighbour
     *
     * @param box the box
     * @return return the neighbour box
     */
    private Box getNeighbour(Box box) {
        ArrayList<Box> neighbours = new ArrayList<>();
        int x = box.getX();
        int y = box.getY();

        if (x > 0) {
            if (!boxes[x - 1][y].getVisited()) {
                neighbours.add(boxes[x - 1][y]);
            }
        }
        if (x < NUM_COLS - 1) {
            if (!boxes[x + 1][y].getVisited()) {
                neighbours.add(boxes[x + 1][y]);
            }
        }
        if (y > 0) {
            if (!boxes[x][y - 1].getVisited()) {
                neighbours.add(boxes[x][y - 1]);
            }
        }
        if (y < NUM_ROWS - 1) {
            if (!boxes[x][y + 1].getVisited()) {
                neighbours.add(boxes[x][y + 1]);
            }
        }
        Random rand = new Random();
        if (neighbours.size() > 0) {
            int i = rand.nextInt(neighbours.size());
            return neighbours.get(i);
        }
        return null;
    }

    /**
     * remove wall between boxes
     *
     * @param box1 a box
     * @param box2 the box beside
     */
    private void removeWall(Box box1, Box box2) {

        if (box1.getX() == box2.getX() && box1.getY() == box2.getY() + 1) {
            box1.setTop(false);
            box2.setBottom(false);
        } else if (box1.getX() == box2.getX() && box1.getY() == box2.getY() - 1) {
            box1.setBottom(false);
            box2.setTop(false);
        } else if (box1.getX() == box2.getX() + 1 && box1.getY() == box2.getY()) {
            box1.setLeft(false);
            box2.setRight(false);
        } else if (box1.getX() == box2.getX() - 1 && box1.getY() == box2.getY()) {
            box1.setRight(false);
            box2.setLeft(false);
        }
    }

    /**
     * get box size
     *
     * @return box size
     */
    int getBoxSize() {
        return boxSize;
    }

    /**
     * get margin
     *
     * @return margin
     */
    int getMargin() {
        return margin;
    }

    /**
     * get boxes
     *
     * @return boxes
     */
    Box[][] getBoxes() {
        return boxes;
    }

    /**
     * get mazeRunner
     *
     * @return mazeRunner
     */
    MazeRunner getMazeRunner() {
        return mazeRunner;
    }

    /**
     * get target
     *
     * @return target
     */
    Target getTarget() {
        return target;
    }

    /**
     * get artifact
     *
     * @return artifact
     */
    ArrayList<MazeArtifact> getArtifact() {
        return artifacts;
    }
}
