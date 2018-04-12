package com.recyclerush.group5.recyclerush;

/**
 * Created by Jeppe on 2018-04-10.
 */

public class itemObject {
    String name;
    String scanId;
    Boolean recycleAble ;
    String materials;

    public itemObject(String name, String scanId, Boolean recycleAble, String materials){
        this.name = name;
        this.scanId = scanId;
        this.recycleAble = recycleAble;
        this.materials = materials;
    }

    public String getName (){
        return this.name;
    }
    public String getScanId(){
        return scanId;
    }
    public Boolean isRecycleable(){
        return recycleAble;
    }
    public String getMaterials(){
        return materials;
    }
}