package ${basepackage!""}.${subpackage!""};

import io.swagger.annotations.*;
import ${basepackage!""}.${subpackage!""}.common.BaseController;
import ${basepackage!""}.dto.common.ErrorResponseEntity;
import ${basepackage!""}.entity.${className!"*"};
import ${basepackage!""}.exception.common.InvalidateParamsException;
import ${basepackage!""}.service.${className!""}Service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ${basepackage!""}.dto.${className!""}Dto;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * THIS IS AUTO GENERATED SOURCE CODE
 *   Created by ${author!"org.openmore"}
 *      on ${.now?string("yyyy-MM-dd")}
 */
@Api(value = "/${className!""}", tags = "${className!""}Api", description = "${controller_desc!""}")
@RequestMapping(value = "/${className?uncap_first}", produces = {APPLICATION_JSON_UTF8_VALUE})
@RestController
public class ${className!""}Controller extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ${className!""}Service ${className?uncap_first}Service;
    @ApiOperation(value = "根据id获取${className_zn!""}信息", response = ${className!""}Dto.class)
    @ApiResponses(value = { @ApiResponse(code = 400, message = "请求失败：找不到id={id}的${className!""}", response = ErrorResponseEntity.class) })
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity get${className!""}ById(@PathVariable @ApiParam(required = true, value = "${className_zn!""}id") Integer id){
        logger.debug(">> get${className}ById");
        ${className}Dto ${className?uncap_first};
        try {
            ${className?uncap_first} = ${className?uncap_first}Service.get${className}DtoById(id);
        }catch (Exception e){
            throw new InvalidateParamsException("请求失败：" + e.getMessage());
        }
        if(${className?uncap_first} == null){
            throw new InvalidateParamsException("请求失败：找不到id=" + id + "的${className_zn!""}");
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @ApiOperation(value = "检索${className_zn!""}信息，返回结果列表", response = ${className}.class, responseContainer = "List")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity search${className}(@RequestParam(required = false)  @ApiParam("${className_zn}对象") ${className} ${className?uncap_first}
                                            )
    {
        logger.debug(">> search${className}");
        List<${className}Dto> searchResult;
        try {
            searchResult = ${className?uncap_first}Service.search${className}(${className?uncap_first});
        }catch (Exception e){
            throw new InvalidateParamsException("请求失败：" + e.getMessage());
        }
        return new ResponseEntity(searchResult, HttpStatus.OK);
    }

     @ApiOperation("更新${className_zn!""}")
     @ApiResponses(value = { @ApiResponse(code = 400, message = "请求失败：更新的数据不存在", response = ErrorResponseEntity.class),
                             @ApiResponse(code = 400, message = "请求失败：数据更新失败", response = ErrorResponseEntity.class) })
     @RequestMapping(method = RequestMethod.PUT, value = "{id}")
     public void update${className}(@RequestBody @ApiParam(value = "新${className_zn!""}信息", required = true) ${className} ${className?uncap_first})
     {
            logger.debug(">> update${className}");
            ${className} entity = ${className?uncap_first}Service.getEntityById(${className?uncap_first}.getId());
            if(entity == null){
                throw new InvalidateParamsException("请求失败：更新的数据不存在");
            }

            try {
                ${className?uncap_first}Service.update(${className?uncap_first});
            }catch (Exception e){
                throw new InvalidateParamsException("请求失败：" + e.getMessage());
            }
     }

     @ApiOperation("创建${className_zn!""}")
     @ApiResponses(value = { @ApiResponse(code = 400, message = "请求失败：数据创建失败", response = ErrorResponseEntity.class) })
     @RequestMapping(method = RequestMethod.POST, consumes = {APPLICATION_JSON_UTF8_VALUE})
     public void create${className!""}(@RequestBody @ApiParam(value = "创建${className_zn!""}", required = true) ${className} ${className?uncap_first}){
        logger.debug(">> create${className!""}");

        try {
            ${className?uncap_first}Service.create(${className?uncap_first});
        }catch (Exception e){
            throw new InvalidateParamsException("请求失败：" + e.getMessage());
        }
     }

     @ApiOperation(value = "删除指定${className_zn!""}")
     @ApiResponses(value = { @ApiResponse(code = 400, message = "请求失败：数据删除失败", response = ErrorResponseEntity.class) })
     @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
     public void delete${className}(@PathVariable @ApiParam(value = "${className_zn!""}id", required = true) Integer id)
     {
        logger.debug(">> delete${className!""}");
        try {
            ${className?uncap_first}Service.deleteById(id);
        }catch (Exception e){
            throw new InvalidateParamsException("请求失败：" + e.getMessage());
        }
     }
}




