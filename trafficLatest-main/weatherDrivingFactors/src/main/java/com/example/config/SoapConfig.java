package com.example.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.oxm.jaxb.Jaxb2Marshaller;
// import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
// import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
// import org.springframework.xml.xsd.XsdSchema;
// import org.springframework.xml.xsd.commons.CommonsXsdSchema;
//
// import com.example.infrastructure.dto.soapRequest.ComputeWeatherRequest;
// import com.example.infrastructure.dto.soapResponse.ComputeWeatherResponse;

// @Configuration
// public class SoapConfig {
//
//     @Bean
//     public Jaxb2Marshaller marshaller() {
//         Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//         // Set the context path to the package where your generated classes reside
//         marshaller.setContextPath("com.example.generated"); // Replace with your package
//         return marshaller;
//     }
//
//     @Bean
//     public SoapClient soapClient(Jaxb2Marshaller marshaller) {
//         SoapClient client = new SoapClient();
//         client.setDefaultUri("http://example.com/your-service-url"); // Set your service URL
//         client.setMarshaller(marshaller);
//         client.setUnmarshaller(marshaller);
//         return client;
//     }
// }
// @Configuration
// public class SoapConfig {
//     // ... other beans and configurations
//     
//     @Bean
//     public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema) {
//         DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
//         wsdl11Definition.setPortTypeName("WeatherPort");
//         wsdl11Definition.setLocationUri("/ws");
//         wsdl11Definition.setTargetNamespace("http://example.com/weather");
//         wsdl11Definition.setSchema(schema);
//         return wsdl11Definition;
//     }
//
//     @Bean
//     public XsdSchema schema() {
//         return new CommonsXsdSchema(new Class[] { ComputeWeatherRequest.class, ComputeWeatherResponse.class });
//     }
// }

// @Configuration
// @EnableWs
// public class SoapConfig extends WsConfigurerAdapter {
//
//     @Bean
//     public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema weatherSchema) {
//         DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
//         wsdl11Definition.setPortTypeName("WeatherPort");
//         wsdl11Definition.setLocationUri("/ws");
//         wsdl11Definition.setTargetNamespace("http://example.com/weather");
//         wsdl11Definition.setSchema(weatherSchema);
//         return wsdl11Definition;
//     }
//
//     @Bean
//     public XsdSchema weatherSchema() {
//         return new SimpleXsdSchema(new ClassPathResource("weather.xsd"));
//     }
// }


import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;
@EnableWs
@Configuration
public class SoapConfig extends WsConfigurerAdapter {
	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<>(servlet, "/ws/*");
	}

	@Bean(name = "weather")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema weatherSchema) {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("WeatherForDriving");
		wsdl11Definition.setLocationUri("/ws");
		wsdl11Definition.setTargetNamespace("http://example.com/weather");
		wsdl11Definition.setSchema(weatherSchema);
		return wsdl11Definition;
	}

	@Bean
	public XsdSchema countriesSchema() {
		return new SimpleXsdSchema(new ClassPathResource("weather.xsd"));
	}
}
