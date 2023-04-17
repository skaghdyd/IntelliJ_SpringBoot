package org.example.baekjoon._2798;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
        StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st1.nextToken());
        int M = Integer.parseInt(st1.nextToken());

        int[] cardArray = new int[N];
        for (int i = 0; i < cardArray.length; i++) {
            cardArray[i] = Integer.parseInt(st2.nextToken());
        }

        int answer = 0;
        //1번째 카드는 0 ~ N-2까지 순회
        for (int i = 0; i < N - 2; i++) {
            //1번째 카드가 M-2보다 크면 continue (2, 3번째 카드가 1이더라도 무조건 M보다 커짐)
            if (cardArray[i] > M - 2) continue;
            //2번째 카드는 1번째 카드 다음부터(i+1) ~ N-1까지 순회
            for (int j = i + 1; j < N - 1; j++) {
                //1번째 카드 + 2번째 카드의 합이 M-1보다 크면 continue (3번째 카드가 1이더라도 무조건 M보다 커짐)
                if (cardArray[i] + cardArray[j] > M - 1) continue;
                //3번째 카드는 2번째 카드 다음부터(j+1) ~ N까지 순회(끝까지)
                for (int k = j + 1; k < N; k++) {
                    int sum = cardArray[i] + cardArray[j] + cardArray[k];
                    //3수의 합이 정답과 같으면 종료
                    if (sum == M) {
                        System.out.println(sum);
                        return;
                    }

                    //3수의 합이 정답보다 큰 경우 (M보다는 작아야함)
                    if (answer < sum && sum < M) {
                        answer = sum;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}