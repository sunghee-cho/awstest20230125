package dbsecure;


import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

//main 메소드 있으면 SPRING BOOT APP으로 실행 가능
//또는 main 메소드 있는 부트클래스가 실행시 같이 실행 가능
@Configuration
@EnableEncryptableProperties //이정보를 APPLICATion.propewrties에서 활용. ENC(...)
public class JasyptConfig {

	@Autowired
	Environment environment;
	
    @Bean("jasyptEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        //config.setPassword(environment.getProperty("jasypt.encryptor.password"));
        config.setPassword(System.getenv("DB_PASSWORD"));        
        config.setAlgorithm("PBEWithMD5AndDES"); // 알고리즘
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
		System.out.println("===JasyptConfig실행===");
		System.out.println(System.getenv("DB_PASSWORD") );
		System.out.println(encryptor.decrypt("rIcw9/ZaeJJUHoxOZ7Ai4jig/TlF4NtyEkf/50Mbu2Q="));
		System.out.println(encryptor.decrypt("iQ1R36fMUevBcbNS/L6sUNTcFaCNhHxTzbjhoASqBv+sinTkinmeveaGl1CklOibLi9ctdGVeTfoUD8XGXmXHPDiWEsGa7GeEkEm6ImAalpMC+s+6LFDVd7BhmWiFWzJbVrzvzwW1iM="));
		System.out.println(encryptor.decrypt("6+L7DYODZv66DQbguZ6hzR3JH6C2sX/W"));
		System.out.println(encryptor.decrypt("j1YiwOW/JS9KBJ5tmWkWN8gJTAh+7rES"));
						
        return encryptor;
    }
 
}
/*
spring.datasource.driver-class-name=ENC(MOawUNeMg3aJxdBm2B3p2XLnWzYyLqdJ+2O6XmR//A9Ry2iD3igWSw==)
spring.datasource.url=ENC(YM10+LmLKjfO5Q4TPlezbrlpS4+1NfOlbvMMz6nBf5iOC2weaxqfhXxFiZraQ/YJdkHakp8biYw=)
spring.datasource.username=ENC(7ooepbcFFEhIq9iYOZPFfA==)
spring.datasource.password=ENC(eMSXPHceqKPVnbiCVqTZZpP/SYCsCTgf)
*/







