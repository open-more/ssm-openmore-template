package org.openmore.sourcegenerator.src;

/**
 * Created by LZ on 2017/6/19.
 * Dto生成器属性工具类
 */
public class DtoParam {

    /**DTO模型属性名*/
    private String name;
    /**DTO模型属性类型*/
    private String type;
    /**DTO模型属性描述*/
    private String desc;

    public DtoParam(String name,String type,String desc){
        this.name = name;
        this.type = type;
        this.desc = desc;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
