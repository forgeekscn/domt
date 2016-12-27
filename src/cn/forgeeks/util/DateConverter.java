package cn.forgeeks.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/*
 * 实现自定义日期格式转换，格式为：yyyy-MM-dd
 * 
 * 	
 * 为何在springmvc-servlet.xml中配置不起作用，直接controller中声明起作用
	<!-- 拦截器 -->
	<bean id="annotationMethodHandlerAdapter" class="org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter">
		<!-- 日期格式转换 -->
        <property name="webBindingInitializer">
         <bean class="cn.itcast.jk.util.DateConverter"/>
        </property>
	</bean>
	
	
 */
public class DateConverter implements WebBindingInitializer {
	public void initBinder(WebDataBinder binder, WebRequest request) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, true));
		binder.registerCustomEditor(Timestamp.class, new CustomDateEditor(df, true));
	}
}
