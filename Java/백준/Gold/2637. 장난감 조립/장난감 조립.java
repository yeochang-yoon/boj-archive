
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 기본 부품과 기본 부품으로 조립해서 만든 중간 부품.
        // 기본 부품은 다른 부품을 사용하여 조립 불가능
        // 중간 부품은 기본 또는 다른 중간 부품을 이용하여 만들어짐

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        int[][] count = new int[n + 1][n + 1];
        HashMap<Integer, List<Integer>> child = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        int[] parents = new int[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            count[x][y] = k;

            child.putIfAbsent(y, new ArrayList<>());
            child.get(y).add(x);

            parents[x]++;

            set.add(x);
        }

        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            if (parents[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            // 중간 부품 하나 꺼내.
            int p = queue.poll();

            if (!child.containsKey(p)) {
                continue;
            }

            // 꺼낸놈의 자식들에다가 부모 업데이트 해주기.
            for (int c : child.get(p)) {
                if (set.contains(p)) {
                    for (int i = 1; i <= n; i++) {
                        if (i == p) {
                            continue;
                        }
                        count[c][i] += count[c][p] * count[p][i];
                    }
                    count[c][p] = 0;
                }
                parents[c]--;

                if (parents[c] == 0) {
                    queue.offer(c);
                }
            }
        }

        for (int i = 1; i < n; i++) {
            if (count[n][i] != 0) {
                System.out.println(i + " " + count[n][i]);
            }
        }
    }
}
