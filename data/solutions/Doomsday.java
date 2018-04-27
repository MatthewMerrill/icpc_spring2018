import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.util.Collections.emptyMap;

public class Doomsday {

  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);

    int N = scn.nextInt();
    int T = scn.nextInt();
    int S = scn.nextInt();

    Integer[][] edges = new Integer[N][N];

    while (true) {
      int from = scn.nextInt();

      if (from == -1) {
        break;
      }

      int to = scn.nextInt();
      int cost = scn.nextInt();

      edges[from][to] = cost;
    }

    boolean isPossible = isPossible(T, S, edges, emptyMap());
    System.out.println(isPossible ? "I can save the world" : "The world is doomsdayed");
  }

  private static boolean isPossible(int timer, int pos, Integer[][] edges, Map<Integer, Integer> visited) {
    if (timer <= 0) {
      return false;
    }

    if (visited.containsKey(pos)) {
      return visited.get(pos) >= timer;
    }

    HashMap<Integer, Integer> nowVisited = new HashMap<>();
    nowVisited.putAll(visited);
    nowVisited.put(pos, timer);

    for (int next = 0; next < edges.length; next++) {
      if (edges[pos][next] != null && pos != next &&
          isPossible(timer - edges[pos][next], next, edges, nowVisited)) {
        return true;
      }
    }

    return false;
  }

}