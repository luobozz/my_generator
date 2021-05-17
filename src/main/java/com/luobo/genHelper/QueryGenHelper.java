package com.luobo.genHelper;

import com.luobo.bean.QueryIO;
import org.springframework.util.StringUtils;

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


        String[] names = new String[]{"Laboratory", "Student", "Teacher", "ClassCollective"};
        String[] modules = new String[]{"laboratory", "student", "teacher", "classCollective"};
        String[] ways = new String[]{"Get", "Edit", "Add", "Delete"};

        for (int i = 0; i < names.length; i++) {
            String name = names[i], module = modules[i];
            for (String way : ways) {
                String paramStr = "";
                if (way.equals("Delete") || way.equals("Edit")) {
                    paramStr = "    @NotEmpty\n" +
                            "    private String uuid;";
                }
                QueryIO queryIO = new QueryIO();
                queryIO.setModule(module);
                queryIO.setName(way + name + "Query.java");
                queryIO.setContent(str.replaceAll("\\{name\\}", way + name).replaceAll("\\{params\\}", paramStr).replaceAll("\\{module\\}", module));
                write(queryIO);
            }
        }

    }

    private static void write(QueryIO queryIO) throws IOException {
        File dirFile=new File("F:\\gen\\com\\shtf\\edu\\query\\" + queryIO.getModule() + "\\query");
        if(!dirFile.exists()){
            dirFile.mkdirs();
        }
        File file = new File( dirFile.getPath()+"\\"+ queryIO.getName());
        Writer out = null;
        out = new FileWriter(file);
        out.write(queryIO.getContent());
        out.close();
    }
}
