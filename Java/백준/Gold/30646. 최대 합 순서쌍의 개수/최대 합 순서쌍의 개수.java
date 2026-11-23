import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        HashMap<Integer, Integer> min_idx = new HashMap<>();
        HashMap<Integer, Integer> max_idx = new HashMap<>();
        long[] prefixSum = new long[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            // 실제 값을 받아옴
            arr[i] = Integer.parseInt(st.nextToken());

            // 누적합 미리 해둠
            if(i == 0){
                prefixSum[i] = arr[i];
            }else{
                prefixSum[i] = prefixSum[i - 1] + arr[i];
            }

            // 같은 값의 맨 왼쪽 위치와 맨 오른쪽 위치를 저장
            min_idx.putIfAbsent(arr[i], i);
            max_idx.putIfAbsent(arr[i], i);

            if(min_idx.get(arr[i]) > i){
                min_idx.put(arr[i], i);
            }

            if(max_idx.get(arr[i]) < i){
                max_idx.put(arr[i], i);
            }
        }

        // i, j 고르는데 ai = aj, i <= j
        // i번재부터 j번째까지 합. 순서쌍의 최대합 + 최대합을 가지는 순서쌍의 개수

        // 양끝에서 시작해서 그 놈의 위치를 저장?
        // 근데 작은놈이랑 큰놈 두개만 저장해두고 그렇게 꺼내쓰면 될듯?
        // a가 10억이라 배열은 포기하고 map으로 두개 만들어서 저장.
        // 합은 미리미리 저장해두면 될듯 누적합

        long max = 0;
        int count = 0;
        HashSet<Integer> set = new HashSet<>();

        for(int i = 0; i < n; i++){
            if(set.contains(arr[i])){
                continue;
            }
            set.add(arr[i]);
            int idx1 = min_idx.get(arr[i]);
            int idx2 = max_idx.get(arr[i]);

            long sum = 0;
            if(idx1 == 0){
                sum = prefixSum[idx2];
            }else{
                sum = prefixSum[idx2] - prefixSum[idx1-1];
            }

            if(max < sum){
                max = sum;
                count = 1;
            }else if (max == sum){
                count++;
            }
        }

        System.out.println(max + " " + count);
    }
}
