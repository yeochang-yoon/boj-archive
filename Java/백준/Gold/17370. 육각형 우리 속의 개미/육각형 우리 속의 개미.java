
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int total_count;
    private static boolean[][] visit;

    private static int[] d1X = {-1, 1, 0};
    private static int[] d1Y = {1, 1, -1};

    private static int[] d2X = {-1, 1, 0};
    private static int[] d2Y = {-1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        total_count = 0;
        visit = new boolean[50][50];

        int startX = 23;
        int startY = 23;

        visit[startX][startY] = true;
        visit[startX][startY+1] = true;

        dfs(startX, startY + 1, startX, startY, true, 0);

        System.out.println(total_count);
    }

    // 현재 도착한 좌표, 이전 꼭짓점 모양, 회전 횟수,
    private static void dfs(int x, int y, int px, int py, boolean dir, int count) {

        // 종료 못했는데 n보다 커짐 -> 끝
        if (count > n) {
            return;
        }

        if (dir) {
            for (int i = 0; i < 3; i++) {
                int nx = x + d1X[i];
                int ny = y + d1Y[i];

                if (nx == px && ny == py) {
                    continue;
                }

                if (visit[nx][ny]) {
                    if (count + 1 == n) {
                        total_count++;
                    }
                    continue;
                }

                visit[nx][ny] = true;
                dfs(nx, ny, x, y, false, count + 1);
                visit[nx][ny] = false;
            }
        } else {
            for (int i = 0; i < 3; i++) {
                int nx = x + d2X[i];
                int ny = y + d2Y[i];

                if (nx == px && ny == py) {
                    continue;
                }

                if (visit[nx][ny]) {
                    if (count + 1 == n) {
                        total_count++;
                    }
                    continue;
                }

                visit[nx][ny] = true;
                dfs(nx, ny, x, y, true, count + 1);
                visit[nx][ny] = false;
            }
        }
    }
}
