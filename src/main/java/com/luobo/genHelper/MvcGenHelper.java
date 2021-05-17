package com.luobo.genHelper;

/**
 * @author chenlingyu
 */
public class MvcGenHelper {

    private static String ctlStr="@RequiresPermissions(value={\"role:list\",\"role:all\",\"all\"},logical = Logical.OR)\n" +
            "    @GetMapping(\"role\")\n" +
            "    public ResponseMessage getRole(GetRoleQuery query) {\n" +
            "        IPage roleIPage=serviceHelper.getService(SysRoleMapper.class).getList(query);\n" +
            "        return responseMessageHandle.code(200).dataList(roleIPage.getRecords()).total(roleIPage.getTotal());\n" +
            "    }\n" +
            "\n" +
            "    @RequiresPermissions(value={\"role:add\",\"role:all\",\"all\"},logical = Logical.OR)\n" +
            "    @PostMapping(\"role\")\n" +
            "    public ResponseMessage addRole(AddRoleQuery query) {\n" +
            "        SysRole role= (SysRole) serviceHelper.getService(SysRoleMapper.class).addOne(query);\n" +
            "        return responseMessageHandle.code(role!=null?200:1004);\n" +
            "    }\n" +
            "\n" +
            "    @RequiresPermissions(value={\"role:edit\",\"role:all\",\"all\"},logical = Logical.OR)\n" +
            "    @PutMapping(\"role\")\n" +
            "    public ResponseMessage editRole(EditRoleQuery query) {\n" +
            "        int code =serviceHelper.getService(SysRoleMapper.class).editOne(query,new QueryWrapper<SysPermission>().eq(\"uuid\", query.getUuid()));\n" +
            "        return responseMessageHandle.code(code);\n" +
            "    }\n" +
            "\n" +
            "    @RequiresPermissions(value={\"role:delete\",\"role:all\",\"all\"},logical = Logical.OR)\n" +
            "    @DeleteMapping(\"role\")\n" +
            "    public ResponseMessage deleteRole(DeleteRoleQuery query) {\n" +
            "        int code =serviceHelper.getService(SysRoleMapper.class).deleteOne(query,new QueryWrapper<SysPermission>().eq(\"uuid\", query.getUuid()));\n" +
            "        return responseMessageHandle.code(code);\n" +
            "    }";
    private static String serStr="";
    private static String seriStr="";

    public static void main(String[] args){

        String namespace1="role";
        String namespace2="Role";
        String[] names = new String[]{"route", "permission"};
        String[] names2 = new String[]{"Route", "Permission"};

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
