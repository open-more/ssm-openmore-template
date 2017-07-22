package org.openmore.utils.DtoCreaterUtils;

/**
 * Created by LZ on 2017/6/19.
 */
public class FreeMakerConfig {

    /** 默认模块根目录 */
    public static String ROOT_PATH = System.getProperty("user.dir") + "\\template";

    /**默认配置文件名称*/
    public static String GENERATOR_CONFIG_FILE_NAME = "generator.xml";
    /**默认模板所在根目录*/
    public static String MODEL_ROOT_DIRECTORY = "template-model";
    /**默认默认编码*/
    public static String DEFAULT_ENCODING = "UTF-8";
    /**
     * 默认源码默认生成目录
     * generator.xml配置文件中的 outRoot 将覆盖此值
     */
    public static String DEFAULT_SOURCES_DIRECTORY = "template-sources";
}
