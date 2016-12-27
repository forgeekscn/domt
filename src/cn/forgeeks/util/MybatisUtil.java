package cn.forgeeks.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {
	private static SqlSessionFactory factory;
	//利用静态块初始化
	static{
		try{
			InputStream inputStream = Resources.getResourceAsStream("MybatisTestConfig.xml");
			Properties properties = Resources.getResourceAsProperties("jdbc.properties");		//获取属性文件
			factory = new SqlSessionFactoryBuilder().build(inputStream, properties);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//获得session对象
	public static SqlSession openSession(){
		return factory.openSession();
	}
}
