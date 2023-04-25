/*
미로 탈출하기 문제
*/

import java.util.ArrayList;

public class MazeSolver {
    int[][] map;
    Position start;
    Position end;
    Position current;

    public MazeSolver(int[][] map, Position start, Position end) {
        this.map = map;
        this.start = start;
        this.end = end;
        this.current = new Position(start.getRow(), start.getCol());
    }

    public boolean canMove(int row, int col) {
        if (row >= this.map.length || row < 0) {
            return false;
        }
        if (col >= this.map[row].length || col < 0) {
            return false;
        }
        return this.map[row][col] == 0;
    }

    public boolean canMove(Position p) {
        return this.canMove(p.getRow(), p.getCol());
    }

    boolean findExit(Position previous, Position current, ArrayList<Position> path) {
        if (current.equals(this.end)) {
            path.add(current);
            return true;
        }

        Position next = new Position(current.getRow() - 1, current.getCol());
        if (canMove(next) && !previous.equals(next)) {
            if (findExit(current, next, path)) {
                path.add(0, current);
                return true;
            }
        }
        next = new Position(current.getRow() + 1, current.getCol());
        if (canMove(next) && !previous.equals(next)) {
            if (findExit(current, next, path)) {
                path.add(0, current);
                return true;
            }
        }
        next = new Position(current.getRow(), current.getCol() - 1);
        if (canMove(next) && !previous.equals(next)) {
            if (findExit(current, next, path)) {
                path.add(0, current);
                return true;
            }
        }
        next = new Position(current.getRow(), current.getCol() + 1);
        if (canMove(next) && !previous.equals(next)) {
            if (findExit(current, next, path)) {
                path.add(0, current);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Position> findExit() {
        ArrayList<Position> path = new ArrayList<>();
        boolean success = this.findExit(this.current, this.current, path);
        if (success) {
            System.out.println("true");
            return path;

        } else {
            System.out.println("false");
            return new ArrayList<>();
        }
    }
}

class Position {
    int row;
    int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean equals(int row, int col) {
        return this.row == row && this.col == col;
    }

    public boolean equals(Position p) {
        return this.equals(p.row, p.col);
    }

    public void set(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public String toString() {
        return String.format("(%d,%d)", this.row, this.col);
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }
}

public class App {
    public static void main(String[] args) {
        MazeSolver solver1 = new MazeSolver(new int[][] {
        {0, 1, 1, 1},
        {0, 0, 0, 0},
        {1, 0, 1, 1},
        {0, 0, 0, 0}
    }, new Position(0, 0), new Position(3, 3));

    System.out.println("MAP1 = " + solver1.findExit().toString());

    MazeSolver solver2 = new MazeSolver(new int[][] {
        {0, 1, 1, 1},
        {0, 1, 0, 0},
        {1, 0, 1, 1},
        {0, 0, 0, 0}
    }, new Position(0, 0), new Position(3, 3));
    System.out.println("MAP2 = " + solver2.findExit().toString());

    MazeSolver solver3 = new MazeSolver(new int[][] {
        {0, 0, 0, 0},
        {1, 0, 1, 0},
        {1, 0, 1, 0},
        {0, 0, 0, 0}
    }, new Position(0, 0), new Position(3, 3));
    System.out.println("MAP3 = " + solver3.findExit().toString());
    }
}

// MazeSolver 클래스 ArrayList 대신 Stack 활용

public class MazeSolver {
    int[][] map;
    Position start;
    Position end;
    Position current;

    public MazeSolver(int[][] map, Position start, Position end) {
        this.map = map;
        this.start = start;
        this.end = end;
        this.current = new Position(start.getRow(), start.getCol());
    }

    public boolean canMove(int row, int col) {
        if (row >= this.map.length || row < 0) {
            return false;
        }
        if (col >= this.map[row].length || col < 0) {
            return false;
        }
        return this.map[row][col] == 0;
    }

    public boolean canMove(Position p) {
        return this.canMove(p.getRow(), p.getCol());
    }

    public ArrayList<Position> findExit() {
        Stack<Position> path = new Stack<>();
        Stack<Position> moves = new Stack<>();
        moves.push(this.current);

        while (!moves.isEmpty()) {
            Position current = moves.pop();

            if (current.equals(this.end)) {
                path.add(current);
                System.out.println("true");
                return new ArrayList<>(path);
            }

            this.map[current.getRow()][current.getCol()] = 2; // Mark as visited

            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
            for (int[] direction : directions) {
                Position next = new Position(current.getRow() + direction[0], current.getCol() + direction[1]);
                if (canMove(next)) {
                    moves.push(next);
                    path.push(current);
                }
            }
        }

        System.out.println("false");
        return new ArrayList<>(); // No path found
    }
}

