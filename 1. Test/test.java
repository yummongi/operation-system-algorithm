import java.io.*;
import java.util.*;

public class test{
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("test.inp"));
        PrintWriter pw = new PrintWriter(new File("test.out"));

        ArrayList<People> peopleInfo = new ArrayList<>();
        int peopleCount = scanner.nextInt();
        int number = 0;
        String firstName = "";
        String lastName = "";

        for(int i = 0; i < peopleCount; i++){
            number = scanner.nextInt();
            firstName = scanner.next();
            lastName = scanner.next();

            peopleInfo.add(new People(number, firstName, lastName));
        }

        scanner.close();

        Collections.sort(peopleInfo);
        int longestFirstNameLength = 0;
        for(People people: peopleInfo){
            if(people.firstName.length() > longestFirstNameLength){
                longestFirstNameLength = people.firstName.length();
            }
        }

        String formatString = "%05d %-" + longestFirstNameLength + "s %s\n";

        Map<String, Integer> lastNameCountMap = new HashMap<>();
        List<String> duplicateLastNameList = new ArrayList<>();
        for(People people : peopleInfo)
        {
            String currentLastName = people.lastName;
            int currentCount = lastNameCountMap.containsKey(currentLastName) == true ? lastNameCountMap.get(currentLastName) : 0;
            lastNameCountMap.put(currentLastName, ++currentCount);

            pw.printf(formatString, people.number, people.firstName, people.lastName);
            if(currentCount == 2)
            {
                duplicateLastNameList.add(currentLastName);
            }
        }
        pw.println();

        Collections.sort(duplicateLastNameList);
        for(String name : duplicateLastNameList)
        {
            pw.println(name + " " + lastNameCountMap.get(name));
        }

        pw.close();
    }
}

class People implements Comparable<People>{
    int number;
    String firstName;
    String lastName;

    People(int number, String firstName, String lastName){
        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int compareTo(People o) {
        if(this.number > o.number){
            return 1;
        }else if(this.number < o.number){
            return -1;
        }else{
            return 0;
        }
    }
}