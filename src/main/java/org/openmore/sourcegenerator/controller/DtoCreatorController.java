package org.openmore.sourcegenerator.controller;

import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.codegenerator.entity.DtoResponse;
import org.codegenerator.utils.openmore.DtoService;
import org.openmore.dto.common.ErrorResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
                                       @RequestParam(required = false) String className,
                                       @RequestParam(required = false) String className_zn,
                                       @RequestParam(required = false) String controller_desc,
                                       @RequestParam(required = false) String attrs){

       DtoService dtoService=new DtoService();
        DtoResponse source_data=dtoService.pageCreateDto(t,className,className_zn,attrs,controller_desc,true);
        Gson gson=new Gson();
        if(source_data.getResponseCode() == DtoResponse.RESPONSE_CODE_SUCCESS){
            ResponseEntity re = new ResponseEntity(gson.toJson(source_data.getResponse_data()), HttpStatus.OK);
            return re;
        }else{
            return new ResponseEntity(gson.toJson(source_data.getE().getMessage()), HttpStatus.FAILED_DEPENDENCY);
        }
    }
    @ApiOperation(value = "DTO生成器并生成文件", response = String.class)
    @ApiResponses(value = { @ApiResponse(code = 400, message = "请求失败：参数错误", response = ErrorResponseEntity.class) })
    @RequestMapping(method = RequestMethod.POST, value = "/source-generator")
    @ResponseBody
    public ResponseEntity generateSourceFile(@RequestParam(required = false) String t,
                                             @RequestParam(required = false) String className,
                                             @RequestParam(required = false) String className_zn,
                                             @RequestParam(required = false) String controller_desc,
                                             @RequestParam(required = false) String attrs){
        DtoService dtoService=new DtoService();
        DtoResponse source_data=dtoService.pageCreateDto(t,className,className_zn,attrs,controller_desc,false);
        Gson gson=new Gson();
        if(source_data.getResponseCode() == DtoResponse.RESPONSE_CODE_SUCCESS){
            return new ResponseEntity(gson.toJson( source_data.getResponse_data() ), HttpStatus.OK);
        }else{
            return new ResponseEntity(gson.toJson( source_data.getE().getMessage() ), HttpStatus.FAILED_DEPENDENCY);
        }
    }
}
