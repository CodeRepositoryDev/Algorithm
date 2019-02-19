package code.repository.dev.kakao;

/**
 * @author seok
 */
public class MuziEatingLive {

    public static void main(String[] args) {
        System.out.println(solution(new int[]{3, 1, 2}, 5));
    }

    public static int solution(int[] food_times, long k) {
        int quotient = (int) Math.floor(k / food_times.length);
        int remainder = (int) (k % food_times.length);
        int sum = 0;

        for (int i = 0; i < food_times.length; i++) {
            sum += food_times[i];
            food_times[i] -= quotient;
            if (food_times[i] < 0) {
                if (i + 1 >= food_times.length) {
                    food_times[0] += food_times[i];
                    sum += food_times[i];
                } else {
                    food_times[i + 1] += food_times[i];
                    sum += food_times[i];
                }

                food_times[i] = 0;
            }
        }

        if (sum <= k) {
            return -1;
        }

        int answer = -1;

        for (int i = 0; i <= remainder; i++) {
            if (i == remainder) {
                while (true) {
                    if (i < food_times.length) {
                        if (food_times[i] > 0) {
                            answer = i;
                            break;
                        }
                    } else {
                        if (food_times[i - food_times.length] > 0) {
                            answer = i - food_times.length;
                            break;
                        }
                    }
                    i += 1;
                }
            } else {
                food_times[i] -= 1;
                if (food_times[i] < 0) {
                    if (i + 1 >= food_times.length) {
                        food_times[0] += food_times[i];
                    } else {
                        food_times[i + 1] += food_times[i];
                    }
                    food_times[i] = 0;
                }
            }

        }

        return answer + 1;
    }
}
