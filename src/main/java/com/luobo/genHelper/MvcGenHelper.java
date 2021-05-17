package com.luobo.genHelper;

/**
 * @author chenlingyu
 */
public class MvcGenHelper {

    private static String ctlStr="    @RequiresPermissions(value = {\"laboratory:list\", \"laboratory:all\", \"main:all\"}, logical = Logical.OR)\n" +
            "    @GetMapping(\"\")\n" +
            "    public ResponseMessage getLaboratory(@Valid GetLaboratoryQuery query) {\n" +
            "        IPage laboratoryIPage = serviceHelper.getService(LaboratoryMapper.class, Laboratory.class).getList(query);\n" +
            "        return responseMessageHandle.code(200).dataList(laboratoryIPage.getRecords()).total(laboratoryIPage.getTotal());\n" +
            "    }\n" +
            "\n" +
            "    @RequiresPermissions(value = {\"laboratory:add\", \"laboratory:all\", \"main:all\"}, logical = Logical.OR)\n" +
            "    @PostMapping(\"\")\n" +
            "    public ResponseMessage addLaboratory(@Valid AddLaboratoryQuery query) {\n" +
            "        Laboratory laboratory = (Laboratory) serviceHelper.getService(LaboratoryMapper.class, Laboratory.class).addOne(query, Laboratory.class);\n" +
            "        return responseMessageHandle.code(laboratory != null ? 200 : 1004);\n" +
            "    }\n" +
            "\n" +
            "    @RequiresPermissions(value = {\"laboratory:edit\", \"laboratory:all\", \"main:all\"}, logical = Logical.OR)\n" +
            "    @PutMapping(\"\")\n" +
            "    public ResponseMessage editLaboratory(@Valid EditLaboratoryQuery query) {\n" +
            "        boolean bl = serviceHelper.getService(LaboratoryMapper.class, Laboratory.class).editOne(query, new QueryWrapper<Laboratory>().eq(\"uuid\", query.getUuid()), Laboratory.class);\n" +
            "        return responseMessageHandle.code(bl ? 200 : 1005);\n" +
            "    }\n" +
            "\n" +
            "    @RequiresPermissions(value = {\"laboratory:delete\", \"laboratory:all\", \"main:all\"}, logical = Logical.OR)\n" +
            "    @DeleteMapping(\"\")\n" +
            "    public ResponseMessage deleteLaboratory(@Valid DeleteLaboratoryQuery query) {\n" +
            "        boolean bl = serviceHelper.getService(LaboratoryMapper.class, Laboratory.class).deleteOne(query, new QueryWrapper<Laboratory>().eq(\"uuid\", query.getUuid()));\n" +
            "        return responseMessageHandle.code(bl ? 200 : 1006);\n" +
            "    }";
    private static String serStr="";
    private static String seriStr="";

    public static void main(String[] args){

        String namespace1="laboratory";
        String namespace2="Laboratory";
        String[] names = new String[]{"laboratory", "student", "teacher","classCollective"};
        String[] names2 = new String[]{"Laboratory", "Student", "Teacher","ClassCollective"};

        for(int i=0;i<names.length;i++){
            String str=gen(namespace1,namespace2,names[i],names2[i]);
            System.out.println(str);
            System.out.println("----------------------------------------------");
            str=gen2(namespace1,namespace2,names[i],names2[i]);
            System.out.println(str);
            System.out.println("----------------------------------------------");
            str=gen3(namespace1,namespace2,names[i],names2[i]);
            System.out.println(str);
            System.out.println("----------------------------------------------");
        }

    }

    private static String gen(String namespace1,String namespace2,String name1,String name2){
        return getRemoteStr(ctlStr,namespace1,namespace2).replaceAll("\\{min\\}",name1).replaceAll("\\{max\\}",name2);
    }

    private static String gen2(String namespace1,String namespace2,String name1,String name2){
        return getRemoteStr(serStr,namespace1,namespace2).replaceAll("\\{min\\}",name1).replaceAll("\\{max\\}",name2);
    }

    private static String gen3(String namespace1,String namespace2,String name1,String name2){
        return getRemoteStr(seriStr,namespace1,namespace2).replaceAll("\\{min\\}",name1).replaceAll("\\{max\\}",name2);
    }

    private static String getRemoteStr(String str,String namespace1,String namespace2){

        String replace1="{min}";
        String replace2="{max}";

        str=str.replaceAll(namespace1,replace1);
        str=str.replaceAll(namespace2,replace2);

        return str;
    }

}
