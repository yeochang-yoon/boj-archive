
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
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
        boolean[] isBasic = new boolean[n + 1];
        Arrays.fill(isBasic, true);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            count[x][y] = k;

            isBasic[x] = false;
        }

        for(int i =1; i < n; i++){

            for (int j = 1; j < n; j++) {
                if (!isBasic[j] && count[i][j] != 0) {
                    for(int k = 1; k < n; k++){
                        if (k == j) {
                            continue;
                        }
                        count[i][k] += count[i][j] * count[j][k];
                    }
                    // 분해한 중간 부품은 0으로
                    count[i][j] = 0;
                }
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();

        // 마지막에 업데이트 되어있는 놈들 다 하나하나 분해해야함.
        for(int i = 1; i < n; i++){
            if(!isBasic[i] && count[n][i] != 0){
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            // 부품으로 판정된 놈 꺼내서 업데이트하고 0으로 변경
            int num = queue.poll();

            // 0 이 아닌 부품 업데이트 근데 이미 존재할 가능성이 있음 -> 그냥 꺼냈을때 0이면 패스
            if (count[n][num] == 0) {
                continue;
            }

            for(int i = 1; i < n; i++){
                count[n][i] += count[n][num] * count[num][i];
            }

            count[n][num] = 0;

            for(int i = 1; i < n; i++){
                if (!isBasic[i] && count[n][i] != 0) {
                    queue.offer(i);
                }
            }
        }

        for (int i = 1; i < n; i++) {
            if (isBasic[i]) {
                System.out.println(i + " " + count[n][i]);
            }
        }
    }
}
