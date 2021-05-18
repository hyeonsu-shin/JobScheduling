import java.util.Random;
public class JobScheduleling {

        public static void main(String[] args) {
            int n = 8;
            int m = 16;
            int[] t = new int[n];
            Random random = new Random();
            System.out.print("작업 시간 : ");
            for (int i = 0; i < n; i++) {
                t[i] = random.nextInt(30) + 1;
                System.out.printf("%d ", t[i]);
            }
            System.out.println();
            System.out.println(schedule(n, m, t));
        }

        public static int schedule(int n, int m, int[] t) {
            int[] L = new int[m];
            for (int j = 0; j < m; j++) {
                L[j] = 0;
            }
            for (int i = 0; i < n; i++) {
                int min = 0;
                for (int j = 1; j < m; j++) {
                    if (L[j] < L[min]) {
                        min = j;
                    }
                }
                L[min] = L[min] + t[i];
            }

            int max = L[0];
            for (int i = 1; i < m; i++) {
                if (L[i] > max) {
                    max = L[i];
                }
            }
            return max;
        }
}
