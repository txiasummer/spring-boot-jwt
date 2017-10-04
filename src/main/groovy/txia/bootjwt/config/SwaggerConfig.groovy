package txia.bootjwt.config

import com.google.common.base.Predicates
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    Docket api(){
        new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex('/error'))) //The error path comes with Spring Boot web applications; we do not need to document that. :)
                .build()
    }

    static ApiInfo getApiInfo() {
        new ApiInfoBuilder().title('API Documentation for Simple Task Management Application')
                .description('Discover how to get, create, update and delete Tasks here!')
                .version('1.0')
                .build()
    }
}
