package jp.gihyo.projava.gameguide.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.logging.Handler;

@Configuration
public class BlogConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver>
                                             argumentResolvers) {
        PageableHandlerMethodArgumentResolver resolver = new
                PageableHandlerMethodArgumentResolver();
        //ページ単位に表示する件数
        resolver.setFallbackPageable(PageRequest. of(0, 10));
        argumentResolvers.add(resolver);
    }
}
