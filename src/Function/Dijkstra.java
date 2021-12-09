/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Function;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * @author phamt
 */
public class Dijkstra {
    public static int n;
    public static int m;
    public static int[][] arr;
    public static final int MAX_INT = 999;

    public Dijkstra(int n, int m, int[][] arr) {
        this.n = n;
        this.m = m;
        this.arr = arr;
    }
    
    

    //check in bound
    public static boolean isOK(int x, int y) {
        if (x < 0 || y < 0 || x >= n || y >= m) return false;
        return true;
    }

    public int check() {
        int[] dx = new int[] {0, 1, 1};
        int[] dy = new int[] {1, 0, 1};
        Queue<Point> q = new PriorityQueue<Point>();
        
        //initial array
        boolean[][] visited = new boolean[n + 1][m + 1];
        int[][] fee = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = false;
                fee[i][j] = MAX_INT;
            }
        }


        fee[0][0] = arr[0][0];
        Point src = new Point(0, 0);
        q.add(src);
        while (!q.isEmpty()) {
            Point top = q.peek();
            int x = top.getX();
            int y = top.getY();
            q.poll();

            if (visited[x][y] == true) continue;
            for (int k = 0; k < 3; k++) {
                int nextX = x + dx[k];
                int nextY = y + dy[k];
                if (isOK(nextX, nextY) && !visited[nextX][nextY]) {
                    fee[nextX][nextY] = Math.min(fee[nextX][nextY], fee[x][y] + arr[nextX][nextY]);
                    q.add(new Point(nextX, nextY));
                }
            }
        }
        return fee[n - 1][m - 1];
    }
}
