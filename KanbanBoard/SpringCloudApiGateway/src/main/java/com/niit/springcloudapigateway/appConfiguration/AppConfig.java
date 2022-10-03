package com.niit.springcloudapigateway.appConfiguration;

import com.niit.springcloudapigateway.filter.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig
{
    @Bean
    public RouteLocator locateRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(p->
                        p.path("/api/user/**")
                                .uri("http://localhost:8085/"))
                .route(p->p.path("/api/admin/**")
                        .uri("http://localhost:8081/"))
                .route(p->p.path("/api/admin/**")
                        .uri("http://localhost:8082/"))
                .build();
    }
    @Bean
    public FilterRegistrationBean jwtFilterBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new JwtFilter());
        filterRegistrationBean.addUrlPatterns("/api/admin/task/*","/api/admin/team/*");
        return filterRegistrationBean;
    }
}
