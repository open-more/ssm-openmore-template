package org.openmore.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.openmore.controller.common.BaseController;
import org.openmore.dto.api.UserDto;
import org.openmore.dto.common.ErrorResponseEntity;
import org.openmore.utils.DtoCreaterUtils.DtoParam;
import org.openmore.utils.DtoCreaterUtils.FreeMakerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * Created by LZ on 2017/6/20.
 */
@Api(value = "/page", tags = "页面跳转控制器", description = "页面跳转控制器")
@RequestMapping(value = "/dto", produces = {APPLICATION_JSON_UTF8_VALUE})
@Controller
public class DtoCreaterController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ApiOperation(value = "跳转DTO生成器页面", response = UserDto.class)
    @ApiResponses(value = { @ApiResponse(code = 400, message = "请求失败：找不到id=2的用户", response = ErrorResponseEntity.class) })
    @RequestMapping(method = RequestMethod.GET, value = "/dtoCreater")
    public String pageToDtoCreater()
    {
        return "DTOCreater";
    }


    @ApiOperation(value = "DTO生成器返回生成内容", response = String.class)
    @ApiResponses(value = { @ApiResponse(code = 400, message = "请求失败：参数错误", response = ErrorResponseEntity.class) })
    @RequestMapping(method = RequestMethod.POST, value = "/dtoCreaterDo")
    @ResponseBody
    public ResponseEntity pageToDtoCreaterDo(@RequestParam(required = false) String t,
                                             @RequestParam(required = false) String className,
                                             @RequestParam(required = false) String className_zn,
                                             @RequestParam(required = false) String attrs,
                                             @RequestParam(required = false) String controller_desc){
        List<DtoParam> att=null;
        try{
            Gson gson=new Gson();
            att = gson.fromJson(attrs,
                    new TypeToken<List<DtoParam>>() {
                    }.getType());
            if(null!=att){
                for(DtoParam ap:att){
                    if(null==ap||null==ap.getName()||"".equals(ap.getName())){
                        att.remove(ap);
                    }
                }
            }
            logger.debug(att==null?"att is null":att.size()+"");
        }catch (Exception e){
            logger.debug("参数错误->无法解析");
        }

        try {
            Map<String, Object> root = new HashMap<String, Object>();
            root.put("basepackage", "org.openmore");
            root.put("className", className);
            root.put("className_zn", className_zn);
            //root.put("attrs", attrs);
            root.put("controller_desc", controller_desc);
            root.put("attrs",att);
            if(null==t){
                return new ResponseEntity("参数错误->t is null", HttpStatus.METHOD_NOT_ALLOWED);
            }
            if (t.equals("dto")) {//生成dto
                root.put("subpackage", "dto");
                String modelFile1 = "${className}Dto.java";
                String data = FreeMakerFactory.getInstance().freeMaker(modelFile1, root);
                data=new ObjectMapper().writeValueAsString(data);
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
    @RequestMapping(method = RequestMethod.POST, value = "/dtoCreaterDoFile")
    @ResponseBody
    public ResponseEntity pageToDtoCreaterDoFile(@RequestParam(required = false) String t,
                                             @RequestParam(required = false) String className,
                                             @RequestParam(required = false) String className_zn,
                                             @RequestParam(required = false) String attrs,
                                             @RequestParam(required = false) String controller_desc){
        List<DtoParam> att=null;
        try{
            Gson gson=new Gson();
            att = gson.fromJson(attrs,
                    new TypeToken<List<DtoParam>>() {
                    }.getType());
            if(null!=att){
                for(DtoParam ap:att){
                    if(null==ap||null==ap.getName()||"".equals(ap.getName())){
                        att.remove(ap);
                    }
                }
            }
            logger.debug(att==null?"att is null":att.size()+"");
        }catch (Exception e){
            logger.debug("参数错误->无法解析");
        }

        try {
            Map<String, Object> root = new HashMap<String, Object>();
            root.put("basepackage", "org.openmore");
            root.put("className", className);
            root.put("className_zn", className_zn);
            //root.put("attrs", attrs);
            root.put("controller_desc", controller_desc);
            root.put("attrs",att);
            if(null==t){
                return new ResponseEntity("参数错误->t is null", HttpStatus.METHOD_NOT_ALLOWED);
            }
            if (t.equals("dto")) {//生成dto
                root.put("subpackage", "dto");
                String modelFile1 = "${className}Dto.java";
                String sourceFile1 = className==null?"":className+"Dto.java";
                FreeMakerFactory.getInstance().freeMaker(modelFile1,sourceFile1, root);
                String data = FreeMakerFactory.getInstance().freeMaker(modelFile1, root);
                data=new ObjectMapper().writeValueAsString(data);
                return new ResponseEntity(data, HttpStatus.OK);
            } else if (t.equals("service")) {//生成service
                root.put("subpackage", "service");
                String modelFile1 = "${className}Service.java";
                String sourceFile1 = className==null?"":className+"Service.java";
                FreeMakerFactory.getInstance().freeMaker(modelFile1, root);
                String data = FreeMakerFactory.getInstance().freeMaker(modelFile1, root);
                data=new ObjectMapper().writeValueAsString(data);
                return new ResponseEntity(data, HttpStatus.OK);
            } else if(t.equals("serviceImpl")){//生成serviceImpl
                root.put("subpackage", "service");
                String modelFile1 = "${className}ServiceImpl.java";
                String sourceFile1 = className==null?"":className+"Impl.java";
                FreeMakerFactory.getInstance().freeMaker(modelFile1, sourceFile1,root);
                String data = FreeMakerFactory.getInstance().freeMaker(modelFile1, root);
                data=new ObjectMapper().writeValueAsString(data);
                return new ResponseEntity(data, HttpStatus.OK);
            }else if (t.equals("controller")) {//生成controller
                root.put("subpackage", "controller");
                String modelFile1 = "${className}Controller.java";
                String sourceFile1 = className==null?"":className+"Controller.java";
                FreeMakerFactory.getInstance().freeMaker(modelFile1,sourceFile1, root);
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
}
