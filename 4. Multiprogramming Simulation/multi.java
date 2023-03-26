import java.io.*;
import java.util.*;

public class multi {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("multi.inp"));
        PrintWriter pw = new PrintWriter(new File("multi.out"));
        final int program_count = Integer.valueOf(scanner.nextLine());
        List<Queue<Integer>> program_job_times = new ArrayList<Queue<Integer>>(program_count);


        int total_cpu_wait = 0;
        int total_process_times = 0;

        for (int i = 0; i < program_count; i++) {
            String program_times_string = scanner.nextLine();

            Queue<Integer> job_times = new LinkedList<>();

            for (String time_string : program_times_string.split(" ")) {
                int num = Integer.valueOf(time_string);
                if (num == -1) break;
                job_times.add(num);
            }
            program_job_times.add(job_times);
        }

        int current_times = 0;
        int[] next_start_times = new int[program_count];

        while (true) {
            boolean all_finish_process = true;
            int skip_to = 0;
            for (int i = 0; i < program_job_times.size(); i++) {
                Queue<Integer> job_times = program_job_times.get(i);

                if (job_times.isEmpty() == true) continue;

                all_finish_process = false;

                if (next_start_times[i] <= current_times) {
                    current_times += job_times.poll();

                    if (job_times.size() != 0) {
                        next_start_times[i] = current_times + job_times.poll();
                    }
                    skip_to = 0;
                    break;
                } else {
                    if (skip_to == 0 || next_start_times[i] < skip_to) {
                        skip_to = next_start_times[i];
                    }
                }
            }
            if (skip_to != 0) {
                total_cpu_wait += skip_to - current_times;
                current_times = skip_to;
            }
            if (all_finish_process == true) {
                total_process_times = current_times;
                for (int i = 0; i < program_count; i++) {
                    if (next_start_times[i] > total_process_times) {
                        total_process_times = next_start_times[i];
                    }
                }
                break;
            }
        }
        pw.printf(total_cpu_wait + " " + total_process_times);
        pw.close();
    }
}
