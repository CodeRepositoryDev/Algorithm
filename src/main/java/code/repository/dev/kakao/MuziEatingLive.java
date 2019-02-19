package code.repository.dev.kakao;

/**
 * @author seok
 */
public class MuziEatingLive {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{3, 1, 2}, 5));
    }

    public static int solution(int[] food_times, long k) {
        int answer = -1;
        int quotient = (int) Math.floor(k / food_times.length);
        int remainder = (int) (k % food_times.length);
        int sum = 0;

        for (int i = 0; i < food_times.length; i++) {
            sum += food_times[i];
            food_times[i] -= quotient;

            if (remainder > 0) {
                food_times[i] -= 1;
                remainder--;
            }

            if (remainder == 0) {
                answer = i;
                remainder--;
            }

            if (food_times[i] < 0) {
                if (i + 1 >= food_times.length) {
                    food_times[0] += food_times[i];
                    sum -= food_times[i];
                } else {
                    food_times[i + 1] += food_times[i];
                    sum -= food_times[i];
                }

                food_times[i] = 0;
            }
        }

        if (sum <= k) {
            return -1;
        }

        while (true) {
            if (answer < food_times.length) {
                if (food_times[answer] > 0) {
                    break;
                }
            } else {
                if (food_times[answer - food_times.length] > 0) {
                    answer = answer - food_times.length;
                    break;
                }
            }
            answer += 1;
        }


        return answer + 1;
    }
}
