package com.luobo.genHelper;

import com.luobo.bean.QueryIO;

import java.io.*;

/**
 * @author chenlingyu
 */
public class QueryGenHelper {
    public static void main(String[] args) throws IOException {
        File file = new File("E:\\code\\gitCode\\luobo\\generator\\src\\main\\java\\com\\luobo\\demo\\DemoQuery.txt");
        BufferedReader buff = new BufferedReader(new FileReader(file));
        String str = "";
        String read = "";
        while ((read = buff.readLine()) != null) {
            str += read + "\n";
        }

        buff.close();

        String module = "system.account";

        str = str.replaceAll("\\{module\\}", module);

        String[] names = new String[]{"Role", "Route", "Permission"};
        String[] ways = new String[]{"Get", "Edit", "Add", "Delete"};

        for (String name : names) {
            for (String way : ways) {
                QueryIO queryIO = new QueryIO();
                queryIO.setName(way + name + "Query.java");
                queryIO.setContent(str.replaceAll("\\{name\\}", way + name));
                write(queryIO);
            }
        }

    }

    private static void write(QueryIO queryIO) throws IOException {
        File file = new File("F:\\gen\\com\\shtf\\edu\\query\\" + queryIO.getName());
        Writer out = null;
        out = new FileWriter(file);
        out.write(queryIO.getContent());
        out.close();
    }
}
