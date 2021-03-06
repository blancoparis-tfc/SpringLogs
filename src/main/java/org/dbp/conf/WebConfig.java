package org.dbp.conf;

import org.dbp.conf.aop.log.LogAspect;
import org.dbp.conf.interceptor.LogInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(
		basePackages={
				"org.dbp.controller"	// Es donde se ubicaran los controladores
				//,"org.dbp.conf.aop.log" // Activar los aspecto de los logs.
				}
		)

public class WebConfig extends WebMvcConfigurerAdapter{
	
	@Bean
	public LogAspect logAspect(){
		return new LogAspect();
	}
	
	/**
	 * 
	 * Configuramos que las paginas se guarden en la carpeta pages
	 * 
	 * @return
	 */
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(new LogInterceptor());
	}
    
    
    
}
