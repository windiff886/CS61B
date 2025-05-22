package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        int[] Ns = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};
        int opt = 10000;
        AList<Integer> Nss = new AList<Integer>();
        AList<Double> times = new AList<Double>();
        AList<Integer> opCounts = new AList<Integer>();
        for (int N : Ns) {
            SLList<Integer> list = new SLList<Integer>();

            // 执行N次addLast操作
            for (int i = 0; i < N; i++) {
                list.addLast(i);
            }

            // 计时开始
            long startTime = System.nanoTime();

            for (int i = 0; i < opt; i++) {
                list.getLast();
            }

            // 计时结束
            long endTime = System.nanoTime();

            // 计算总时间(秒)
            double timeInSeconds = (endTime - startTime) / 1e9;

            Nss.addLast(N);
            times.addLast(timeInSeconds);
            opCounts.addLast(opt);
        }
        printTimingTable(Nss, times, opCounts);
    }

}
