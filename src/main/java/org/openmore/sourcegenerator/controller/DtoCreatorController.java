package org.openmore.sourcegenerator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.openmore.dto.common.ErrorResponseEntity;
import org.openmore.sourcegenerator.src.DtoParam;
import org.openmore.sourcegenerator.src.FreeMakerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * Created by LZ on 2017/6/20.
 */
@CrossOrigin(maxAge = 3600)
@Api(value = "/dto", tags = "DTO半自动生成器", description = "DTO半自动生成器")
@RequestMapping(value = "/dto", produces = {APPLICATION_JSON_UTF8_VALUE})
@Controller
public class DtoCreatorController{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(method = RequestMethod.GET, value = "/home")
    public String pageToDtoCreater()
    {
        return "DtoCreator";
    }


    @ApiOperation(value = "DTO生成器返回生成内容", response = String.class)
    @ApiResponses(value = { @ApiResponse(code = 400, message = "请求失败：参数错误", response = ErrorResponseEntity.class) })
    @RequestMapping(method = RequestMethod.POST, value = "/source-viewer")
    @ResponseBody
    public ResponseEntity showSourceDo(@RequestParam(required = false) String t,
                                       @RequestParam(required = false) String packageName,
                                       @RequestParam(required = false) String className,
                                       @RequestParam(required = false) String className_zn,
                                       @RequestParam(required = false) String controller_desc,
                                       @RequestParam(required = false) String attrs){

        List<DtoParam> att = null;
        try{
            Gson gson = new Gson();
            att = gson.fromJson(attrs,
                    new TypeToken<List<DtoParam>>() {
                    }.getType());
            if(null != att){
                for(DtoParam ap : att){
                    if(null == ap || null == ap.getName() || "".equals(ap.getName())){
                        att.remove(ap);
                    }
                }
            }
            logger.debug(att == null ? "att is null" : att.size() + "");
        }catch (Exception e){
            logger.debug("参数错误->无法解析");
        }

        try {
            Map<String, Object> root = new HashMap<String, Object>();
            root.put("basepackage", packageName);
            root.put("className", className);
            root.put("className_zn", className_zn);
            root.put("controller_desc", controller_desc);
            root.put("attrs",att);
            if(null == t){
                return new ResponseEntity("参数错误->t is null", HttpStatus.BAD_REQUEST);
            }
            if (t.equals("dto")) {//生成dto
                root.put("subpackage", "dto");
                String modelFile1 = "${className}Dto.java";
                String data = FreeMakerFactory.getInstance().freeMaker(modelFile1, root);
                data = new ObjectMapper().writeValueAsString(data);
                return new ResponseEntity(data, HttpStatus.OK);
            } else if (t.equals("service")) {//生成service
                root.put("subpackage", "service");
                String modelFile1 = "${className}Service.java";
                String data = FreeMakerFactory.getInstance().freeMaker(modelFile1, root);
                data=new ObjectMapper().writeValueAsString(data);
                return new ResponseEntity(data, HttpStatus.OK);
            } else if(t.equals("serviceImpl")){//生成serviceImpl
                root.put("subpackage", "service");
                String modelFile1 = "${className}ServiceImpl.java";
                String data = FreeMakerFactory.getInstance().freeMaker(modelFile1, root);
                data=new ObjectMapper().writeValueAsString(data);
                return new ResponseEntity(data, HttpStatus.OK);
            }else if (t.equals("controller")) {//生成controller
                root.put("subpackage", "controller");
                String modelFile1 = "${className}Controller.java";
                String data = FreeMakerFactory.getInstance().freeMaker(modelFile1, root);
                data=new ObjectMapper().writeValueAsString(data);
                return new ResponseEntity(data, HttpStatus.OK);
            } else {
                return new ResponseEntity("参数错误->t is invalid", HttpStatus.METHOD_NOT_ALLOWED);
            }
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }
    @ApiOperation(value = "DTO生成器并生成文件", response = String.class)
    @ApiResponses(value = { @ApiResponse(code = 400, message = "请求失败：参数错误", response = ErrorResponseEntity.class) })
    @RequestMapping(method = RequestMethod.POST, value = "/source-generator")
    @ResponseBody
    public ResponseEntity generateSourceFile(@RequestParam(required = false) String packageName,
                                             @RequestParam(required = false) String className,
                                             @RequestParam(required = false) String className_zn,
                                             @RequestParam(required = false) String controller_desc,
                                             @RequestParam(required = false) String attrs){

        logger.debug(packageName);
        logger.debug(className);
        logger.debug(className_zn);
        logger.debug(controller_desc);
        logger.debug(attrs);

        List<DtoParam> att=null;
        try{
            Gson gson=new Gson();
            att = gson.fromJson(attrs,
                    new TypeToken<List<DtoParam>>() {
                    }.getType());
            if(null != att){
                for(DtoParam ap:att){
                    if(null == ap || null == ap.getName() || "".equals(ap.getName())){
                        att.remove(ap);
                    }
                }
            }
        }catch (Exception e){
            logger.error("参数错误->无法解析");
        }

        try {
            Map<String, Object> root = new HashMap<String, Object>();
            root.put("basepackage", packageName);
            root.put("className", className);
            root.put("className_zn", className_zn);
            root.put("controller_desc", controller_desc);
            root.put("attrs",att);
            //生成dto
            root.put("subpackage", "dto");
            String modelFile = "${className}Dto.java";
            String sourceFile = "dto" + File.separator + className + "Dto.java";
            FreeMakerFactory.getInstance().freeMaker(modelFile, sourceFile, root);

            // 生成service
            root.put("subpackage", "service");
            modelFile = "${className}Service.java";
            sourceFile = "service" + File.separator + className + "Service.java";
            FreeMakerFactory.getInstance().freeMaker(modelFile, sourceFile, root);

            //生成serviceImpl
            root.put("subpackage", "service");
            modelFile = "${className}ServiceImpl.java";
            sourceFile = "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
            FreeMakerFactory.getInstance().freeMaker(modelFile, sourceFile,root);
            //生成controller
            root.put("subpackage", "controller");
            modelFile = "${className}Controller.java";
            sourceFile = "controller" + File.separator + className + "Controller.java";
            FreeMakerFactory.getInstance().freeMaker(modelFile, sourceFile, root);
            return new ResponseEntity("ok", HttpStatus.OK);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
