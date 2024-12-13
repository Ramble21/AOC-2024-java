package com.github.Ramble21;

import com.github.Ramble21.classes.Location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Day10 extends DaySolver{
    private final List<String> input;
    private int[][] grid;
    public Day10() throws IOException {
        input = getInputLines(10);
        inputToGrid();
    }
    public int solvePart1() throws IOException {
        print2DArr(grid);
        int count = 0;
        for (int r = 0; r < grid.length; r++){
            for (int c = 0; c < grid[0].length; c++){
                if (grid[r][c] != 0) continue;
                Location pathStart = new Location(c,r);
                System.out.println("\n" + pathStart);
                HashSet<Location> completedPaths = new HashSet<>();
                dfsPath(pathStart, completedPaths);
                System.out.println("Trailhead: " + completedPaths);
                count += completedPaths.size();
            }
        }
        return count;
    }

    public int solvePart2() throws IOException {
        print2DArr(grid);
        int count = 0;
        for (int r = 0; r < grid.length; r++){
            for (int c = 0; c < grid[0].length; c++){
                if (grid[r][c] != 0) continue;
                Location pathStart = new Location(c,r);
                System.out.println("\n" + pathStart);
                ArrayList<Location> completedPaths = new ArrayList<>();
                dfsPathTwo(pathStart, completedPaths);
                System.out.println("Trailhead: " + completedPaths);
                count += completedPaths.size();
            }
        }
        return count;
    }
    public void inputToGrid(){
        int[][] g = new int[input.size()][input.get(0).length()];
        for (int r = 0; r < g.length; r++){
            for (int c = 0; c < input.get(0).length(); c++){
                g[r][c] = Integer.parseInt(String.valueOf(input.get(r).charAt(c)));
            }
        }
        grid = g;
    }
    public void dfsPath(Location position, HashSet<Location> completedPaths){
        Location[] paths = possiblePaths(position.getY(), position.getX());
        if (grid[position.getY()][position.getX()] == 9) completedPaths.add(position);
        if (paths == null){
            return;
        }
        for (Location path : paths){
            dfsPath(path, completedPaths);
        }
    }
    public void dfsPathTwo(Location position, ArrayList<Location> completedPaths){
        Location[] paths = possiblePaths(position.getY(), position.getX());
        if (grid[position.getY()][position.getX()] == 9) completedPaths.add(position);
        if (paths == null){
            return;
        }
        for (Location path : paths){
            dfsPathTwo(path, completedPaths);
        }
    }
    public Location[] possiblePaths(int r, int c){
        ArrayList<Location> list = new ArrayList<>();
        int num = grid[r][c];
        if (r+1 < grid.length && grid[r+1][c] == num+1){
            list.add(new Location(c, r+1));
        }
        if (r-1 >= 0 && grid[r-1][c] == num+1){
            list.add(new Location(c, r-1));
        }
        if (c+1 < grid[0].length && grid[r][c+1] == num+1){
            list.add(new Location(c+1, r));
        }
        if (c-1 >= 0 && grid[r][c-1] == num+1){
            list.add(new Location(c-1, r));
        }
        if (list.isEmpty()) return null;
        Location[] fin = new Location[list.size()];
        for (int i = 0; i < list.size(); i++){
            fin[i] = list.get(i);
        }
        return fin;
    }
    public static void print2DArr(int[][] array) {
        for (int[] row : array) {
            System.out.println(Arrays.toString(row));
        }
    }
}