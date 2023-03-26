import java.io.*;
import java.util.*;

public class batch {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("batch.inp"));
        PrintWriter pw = new PrintWriter(new File("batch.out"));

        int processCount = Integer.parseInt(scanner.nextLine());
        int cpu_using_time = 0;
        int io_using_time = 0;
        int total_using_time = 0;

        String process_times_string;

        for(int j = 0; j < processCount; j++) {
            process_times_string = scanner.nextLine();
            Process process = new Process(process_times_string);
            cpu_using_time += process.cpu_time;
            io_using_time += process.io_time;
        }
        scanner.close();

        total_using_time = cpu_using_time + io_using_time;
        pw.println(io_using_time + " " + total_using_time);

        pw.close();
    }

}

class Process{
    int cpu_time;
    int io_time;


    Process(String process_times_string){
        parse(process_times_string);
    }

    public void parse(String process_times_string){
        boolean onCpuTime = true;
        for(String str : process_times_string.split(" ")){
            int times = Integer.parseInt(str);

            if(times == -1) break;

            if(onCpuTime){
                cpu_time += times;
            }else{
                io_time += times;
            }
            onCpuTime = !onCpuTime;
        }

    }
}
