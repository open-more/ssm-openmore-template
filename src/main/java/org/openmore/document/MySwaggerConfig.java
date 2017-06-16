package org.openmore.document;

import com.google.common.base.Predicate;
import org.openmore.dto.common.BaseResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2 //Loads the spring beans required by the framework
public class MySwaggerConfig {

    private ApiInfo apiInfo() {
        Contact contact = new Contact("Openmore", "https://github.com/open-more/ssm-openmore-template", "michaeltang@openmore.org");
        return new ApiInfoBuilder()
                .title("ssm-openmore-template API接口")
                .description("SSM-Easy-Template 是一个J2EE项目快速开发脚手架，" +
                        "集成了最常用的框架,适用于Restfull 架构风格Web Service接口开发。项目使用最灵活的构建工具-gradle" +
                        "，加入了常用的gradle插件(gretty,flydb，mybatis generator),。")
                .contact(contact)

                .version("3.0")
                .build();
    }

    private Predicate<String> userPaths() {
        return or(
                regex("/user.*")
        );
    }

    @Bean
    public Docket userApi() {
        Set<String> set = new HashSet<String>();

        com.fasterxml.classmate.TypeResolver typeResolver = new com.fasterxml.classmate.TypeResolver();

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("user-api")
                .apiInfo(apiInfo())
                .select()
                .paths(userPaths())
                .build().useDefaultResponseMessages(false)
                .genericModelSubstitutes(BaseResponse.class)
                .forCodeGeneration(true);

        return docket;
    }

}