package com.example.tiims.config.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Configuration {

    /**
     * api接口包扫描路径
     */
    public static final String SWAGGER_SCAN_BASE_PACKAGE = "com.example.tiims";
    public static final String VERSION = "0.0.1-SNAPSHOT";

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
                /*
                 * 可以根据url路径设置哪些请求加入文档，忽略哪些请求
                 */
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //设置文档的标题
                .title("培训机构信息管理系统")
                // 设置文档的描述
                .description("培训机构信息管理系统接口文档")
                // 设置文档的版本信息-> 1.0.0 Version information
                .version(VERSION)
                // 设置文档的License信息->1.3 License information
//                .termsOfServiceUrl("http://www.baidu.com")
                .build();
    }
}