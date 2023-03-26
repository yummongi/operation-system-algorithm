import java.io.*;
import java.util.*;

public class command {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("command.inp"));
        PrintWriter pw = new PrintWriter(new File("command.out"));
        HashMap<String, String> map = new HashMap<>();

        ArrayList<Input> commandInfo = new ArrayList<>();

        int count = scanner.nextInt();

        String inputCommand = "";

        map.put("ls", "dir");
        map.put("mkdir", "md");
        map.put("rmdir", "rd");
        map.put("rm", "del");
        map.put("cp", "copy");
        map.put("mv", "rename");
        map.put("clear", "cls");
        map.put("pwd", "cd");
        map.put("cat", "type");
        map.put("man", "help");
        map.put("date", "time");
        map.put("find", "find");
        map.put("grep", "findstr");
        map.put("more", "more");
        map.put("diff", "comp");
        map.put("ed", "edlin");
        map.put("sort", "sort");
        map.put("lsattr", "attrib");
        map.put("pushd", "pushd");
        map.put("popd", "popd");
        map.put("ps", "taskmgr");
        map.put("kill", "tskill");
        map.put("halt", "shutdown");
        map.put("ifconfig", "ipconfig");
        map.put("fsck", "chkdsk");
        map.put("free", "mem");
        map.put("debugfs", "scandisk");
        map.put("lpr", "print");

        for(int i = 0; i < count; i++){
            inputCommand = scanner.next();
            commandInfo.add(new Input(inputCommand));
        }

        scanner.close();

        for(Input input : commandInfo){
            if(getKey(map, input.inputCommand)==null){
                pw.println(input.inputCommand + " -> " + map.get(input.inputCommand));
            }
            else pw.println(input.inputCommand + " -> " +getKey(map, input.inputCommand));
        }

        pw.close();
    }

    public static <K, V> K getKey(Map<K, V> map, V value) {
        for (K key : map.keySet()) {
            if (value.equals(map.get(key))) {
                return key;
            }
        }
        return null;
    }
}

class Input{
    String inputCommand;

    Input(String inputCommand){
        this.inputCommand = inputCommand;
    }
}
