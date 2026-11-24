import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        String[][] times = new String[n][2];


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            times[i][0] = st.nextToken();
            times[i][1] = st.nextToken();
        }

        // 들어오는 시간순으로 정렬

        Arrays.sort(times, (a, b) -> a[0].compareTo(b[0]));

        // 들어오는 시간순으로 정렬한다음에 우선순위 큐에 나가는 순서대로 나오게 넣고
        // 나가는거랑 현재 들어오는거랑 비교해서 들어오는게 숫자가 더 크면 내보내기

        PriorityQueue<String> queue = new PriorityQueue<>((a, b) -> a.compareTo(b));

        int max = 0;
        for (int i = 0; i < n; i++) {
            if (queue.isEmpty()) {
                queue.offer(times[i][1]);

                max = Math.max(max, queue.size());
            } else {
                while (!queue.isEmpty() && compareTime(times[i][0], queue.peek())) {
                    queue.poll();
                }

                queue.offer(times[i][1]);
                max = Math.max(max, queue.size());
            }
        }

        System.out.println(max);
    }

    private static boolean compareTime(String time1, String time2) {

        return time1.compareTo(time2) >= 0;
    }
}
