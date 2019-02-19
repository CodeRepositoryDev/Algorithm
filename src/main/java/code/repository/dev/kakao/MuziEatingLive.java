package code.repository.dev.kakao;

/**
 * @author seok
 */
public class MuziEatingLive {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{3, 1, 2}, 5));
    }

    public static int solution(int[] food_times, long k) {
        int[] temp = food_times;

        long sum = 0;

        if (food_times.length > 200000 || food_times.length < 1) {
            return -1;
        }

        for (int food_time : food_times) {
            if (food_time > 100000000 || food_time < 1) {
                return -1;
            }


            sum += food_time;
        }

        if (sum <= k) {
            return -1;
        }

        long quotient = k / food_times.length;
        int remainder = (int) (k % food_times.length);
        int answer = 0;

        for (int i = 0; i < food_times.length; i++) {
            food_times[i] -= quotient;
            if (food_times[i] < 0) {
                for (int j = 1; j <= Math.abs(food_times[i]); j++) {
                    if (food_times[(i + j) % food_times.length] > 0) {
                        food_times[(i + j) % food_times.length] -= 1;
                    }
                }

                food_times[i] = 0;
            }
        }

        while (remainder > 0) {
            if (food_times[answer] > 0) {
                food_times[answer] -= 1;
                remainder--;
            }
            answer++;
            answer = answer % food_times.length;
        }

        return answer + 1;
    }
}
