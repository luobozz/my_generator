package com.luobo.genHelper;

import com.luobo.bean.QueryIO;

import java.io.*;

/**
 * @author chenlingyu
 */
public class VueGenHelper {
    public static void main(String[] args) throws IOException {
        File file = new File("E:\\code\\gitCode\\luobo\\generator\\src\\main\\java\\com\\luobo\\demo\\DemoVue.txt");
        BufferedReader buff = new BufferedReader(new FileReader(file));
        String str = "";
        String read = "";
        while ((read = buff.readLine()) != null) {
            str += read + "\n";
        }

        buff.close();


        String[] names = new String[]{"Teacher"};
        String[] namesC = new String[]{"教师"};

        for (int i = 0; i < names.length; i++) {
            System.out.println(str.replaceAll("\\&\\$\\{min\\}",names[i].toLowerCase()).replaceAll("\\&\\$\\{max\\}",names[i]).replaceAll("\\&\\$\\{chinese\\}",namesC[i]));
        }

    }
}
