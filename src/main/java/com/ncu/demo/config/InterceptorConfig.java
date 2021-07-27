package com.ncu.demo.config;

import com.ncu.demo.handler.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] addPathPatterns = {
                "/**"
        };
        String[] excludePathPatterns = {
                "/user/login",
                "/user/out",
                "/user/error"
        };
        registry.addInterceptor(new MyInterceptor()).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns);
    }

    /**
     *为了方便访问templates中的页面，减少controller中的代码
     *         registry.addViewController("/manager").setViewName("manager");
     *         相当于
     * @RequestMapping(value = "/manager")
     * public String manager(){
     *     return "manager"
     * }
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/manager").setViewName("manager");
        registry.addViewController("/index").setViewName("index");
    }

}
