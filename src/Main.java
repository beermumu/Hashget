import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("bbbb.txt")));
        String line;
        String fin = " ";
        int index = 0;

        while ((line = bufferedReader.readLine()) != null) {
            fin = fin + line + " ";

        }

        Pattern MY_PATTERN = Pattern.compile("#(\\w+)");
        Matcher mat = MY_PATTERN.matcher(fin);
        ArrayList<String> strs = new ArrayList<String>();
        while (mat.find()) {
            strs.add(mat.group(1).toLowerCase());
        }
        Set<String> hs = new LinkedHashSet<>(strs);
        ArrayList<String> hashtag = new ArrayList<>(hs);



        FileWriter fileWriter;


        System.out.println(hashtag);


        BufferedReader bufferedReader2 = new BufferedReader(new FileReader(new File("bbbb.txt")));

        int[][] data = new int[5260][hashtag.size()];
        while ((line = bufferedReader2.readLine()) != null) {
            line = line.toLowerCase();
            line = line.trim();
            for (int i = 0; i < hashtag.size(); i++) {
                if (line.contains(hashtag.get(i))) {
                    data[index][hashtag.indexOf(hashtag.get(i))] = 1;
                    System.out.println("yes");
                } else {
                    data[index][hashtag.indexOf(hashtag.get(i))] = 0;
                    System.out.println("no");
                }
            }
            index++;
            System.out.println(index + " addlist");
        }

        try {
            fileWriter = new FileWriter("data.txt", true);
            for (int i = 0; i < 5260; i++) {
                System.out.println(i + " writing");
                for (int j = 0; j < hashtag.size(); j++) {
                    if (j == hashtag.size()-1) {
                        if (data[i][j] == 1) {
                            fileWriter.write("1 \n");
                        } else {
                            fileWriter.write("0 \n");
                        }
                    } else {
                        if (data[i][j] == 1) {
                            fileWriter.write("1,");
                        } else {
                            fileWriter.write("0,");
                        }
                    }


                }
            }
            fileWriter.close();
        } catch (IOException e) {
        }

        try {
            fileWriter = new FileWriter("hashtag.txt", true);
            for (int i = 0; i < hashtag.size(); i++) {
//                fileWriter.write("@attribute " + hashtag.get(i) + " {yes, no} \n");
                fileWriter.write(hashtag.get(i)+",");
            }
            fileWriter.close();
        } catch (IOException e) {
        }

    }
}
