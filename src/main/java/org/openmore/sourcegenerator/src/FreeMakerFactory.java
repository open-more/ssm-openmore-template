package org.openmore.sourcegenerator.src;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by LZ on 2017/6/19.
 * 此单例类用于加载freeMaker配置
 */
public class FreeMakerFactory {

    private static FreeMakerFactory instance;

    /** freeMaker配置设置 */
    private Configuration cfg = null;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static FreeMakerFactory getInstance() throws Exception{
        if(instance == null){
            instance = new FreeMakerFactory();
        }
        return instance;
    }

    private FreeMakerFactory() throws Exception{
        try {
            cfg = new Configuration(Configuration.VERSION_2_3_22);
            //配置模板所在目录(模板需要放在此目录或子目录下)
            List<String> paths = DtoFileUtils.getTemplateFileList();
            FileTemplateLoader[] fileLoaders = new FileTemplateLoader[paths.size()];
            for(int i = 0; i < paths.size(); i++) {
                fileLoaders[i] = new FileTemplateLoader(new File(paths.get(i)));
            }
            MultiTemplateLoader multiTemplateLoader = new MultiTemplateLoader(fileLoaders);
            //加载模板所在目录
            cfg.setTemplateLoader(multiTemplateLoader);
            //设置默认编码
            cfg.setDefaultEncoding(FreeMakerConfig.DEFAULT_ENCODING);
            cfg.setNumberFormat("###############");
            cfg.setBooleanFormat("true,false");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("FreeMakerFactory 配置出错");
        }
    }

    public Configuration getConfiguration(){
        return this.cfg;
    }

    /**
     * 此方法用于生成源码
     * @param modelFileName 模板文件名
     * @param fileName 要生成的文件名
     * @param root 模板所需参数
     * */
    public void freeMaker(String modelFileName, String fileName, Map<String, Object> root) throws Exception{
        String packageName = root.get("basepackage").toString();
        logger.debug(packageName);
        String packageNameWithSepator = packageName.replace(".", File.separator);
        String sourcePath = "src" + File.separator + "main" + File.separator + "java" + File.separator + packageNameWithSepator;
        sourcePath = System.getProperty("user.dir") + File.separator + sourcePath;
        File source_dir = new File(sourcePath);
        if( !source_dir.exists() ){
            source_dir.mkdirs();
        }

        logger.debug(sourcePath);

        //设置输出源码文件
        File out_file= new File(sourcePath + File.separator + fileName);
        if(out_file.isDirectory()){
            throw new RuntimeException("请指定数据输出文件，而非目录：" + out_file.getPath());
        }
        if(!out_file.exists()){
            out_file.createNewFile();
        }

        //加载模板
        Template temp1 = cfg.getTemplate(modelFileName);
        OutputStream fos = new FileOutputStream( out_file);
        Writer out = new OutputStreamWriter(fos);
        //生成源码
        temp1.process(root, out);
        fos.flush();
        fos.close();
        out.close();
    }
    /**
     * 此方法用于生成源码
     * @param modelFileName 模板文件名
     * @param root 模板所需参数
     * */
    public String freeMaker(String modelFileName, Map<String, Object> root) throws Exception{

        //加载模板
        Template temp1 = cfg.getTemplate(modelFileName);

        StringWriter stringWriter = new StringWriter();
        BufferedWriter writer = new BufferedWriter(stringWriter);
        temp1.process(root, writer);
        writer.flush();
        writer.close();
        return stringWriter.toString();
    }

    public String freeMakerControlMethod(String modelFileName, Map<String, Object> root){
        return null;
    }
}
