package com.mera.inkrot.carshowroom.config;

import com.mera.inkrot.carshowroom.service.CustomerService;
import com.mera.inkrot.carshowroom.service.OrderService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.mera.inkrot.carshowroom.model")
@ComponentScan({"com.mera.inkrot.carshowroom.service", "com.mera.inkrot.carshowroom.rest"})
@EnableJpaRepositories("com.mera.inkrot.carshowroom.repository")
@ImportResource({ "classpath:META-INF/cxf/cxf.xml" })
public class CxfConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @Bean
    public ServletRegistrationBean servletRegistrationBean(ApplicationContext context) {
        return new ServletRegistrationBean(new CXFServlet(), "/soap/*");
    }

    /*@Bean
    public EndpointImpl helloService() {
        Bus bus = (Bus) applicationContext.getBean(Bus.DEFAULT_BUS_ID);
        Object implementor = new HelloServiceImpl();
        EndpointImpl endpoint = new EndpointImpl(bus, implementor);
        endpoint.publish("/hello");
        return endpoint;
    }*/

    @Bean
    public EndpointImpl customerService() {
        Bus bus = (Bus) applicationContext.getBean(Bus.DEFAULT_BUS_ID);
        EndpointImpl endpoint = new EndpointImpl(bus, customerService);
        endpoint.publish("/customer");
        return endpoint;
    }

    @Bean
    public EndpointImpl orderService() {
        Bus bus = (Bus) applicationContext.getBean(Bus.DEFAULT_BUS_ID);
        EndpointImpl endpoint = new EndpointImpl(bus, orderService);
        endpoint.publish("/order");
        return endpoint;
    }
}
