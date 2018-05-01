package com.sothatsit.cits2200.algorithm;

import java.util.Random;

public class LargestRectangleO3 {

    public static void main(String[] args) {
        int size = 5;
        int[][] grid = new int[size][size];

        Random random = new Random();
        for(int i=0; i < size; ++i) {
            for(int j=0; j < size; ++j) {
                grid[i][j] = (random.nextBoolean() ? 1 : 0);
            }
        }

        printGrid(grid, size);

        int[][] horizontals = new int[size + 1][size + 1];
        int[][] verticals = new int[size + 1][size + 1];

        int maxArea = 0;
        int maxX = 0;
        int maxY = 0;
        int maxWidth = 0;
        int maxHeight = 0;

        for(int left = size - 1; left >= 0; --left) {
            for(int top = size - 1; top >= 0; --top) {
                if(grid[left][top] == 1)
                    continue;

                int width = horizontals[left + 1][top] + 1;
                int height = verticals[left][top + 1] + 1;

                horizontals[left][top] = width;
                verticals[left][top] = height;

                int h = height;
                for(int dx = 0; dx < width; ++dx) {
                    if(verticals[left + dx][top] < h) {
                        h = verticals[left + dx][top];
                    }

                    int area = (dx + 1) * h;

                    if(area > maxArea) {
                        maxArea = area;
                        maxX = left;
                        maxY = top;
                        maxWidth = dx + 1;
                        maxHeight = h;
                    }
                }

                int w = width;
                for(int dy = 0; dy < height; ++dy) {
                    if(horizontals[left][top + dy] < w) {
                        w = horizontals[left][top + dy];
                    }

                    int area = (dy + 1) * w;

                    if(area > maxArea) {
                        maxArea = area;
                        maxX = left;
                        maxY = top;
                        maxWidth = w;
                        maxHeight = dy + 1;
                    }
                }
            }
        }

        System.out.println();
        System.out.println("max: " + maxArea + " @ (" + maxX + ", " + maxY + "), of size " + maxWidth + " x " + maxHeight);
    }

    private static void printGrid(int[][] grid, int size) {
        for(int i=0; i < size; ++i) {
            StringBuilder lineString = new StringBuilder();

            for(int j=0; j < size; ++j) {
                lineString.append(grid[j][i]);
                lineString.append(" ");
            }

            System.out.println(lineString);
        }
    }
}
