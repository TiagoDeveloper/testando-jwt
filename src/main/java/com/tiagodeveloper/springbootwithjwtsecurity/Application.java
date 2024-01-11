package com.tiagodeveloper.springbootwithjwtsecurity;

import com.tiagodeveloper.springbootwithjwtsecurity.config.properties.RsaKeyConfigProperties;
import com.tiagodeveloper.springbootwithjwtsecurity.config.properties.SecurityMappingConfig;
import com.tiagodeveloper.springbootwithjwtsecurity.config.factorys.YamlPropertySourceFactory;
import com.tiagodeveloper.springbootwithjwtsecurity.repositorys.OverdueStatementRepository;
import com.tiagodeveloper.springbootwithjwtsecurity.utils.StatementUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableConfigurationProperties({RsaKeyConfigProperties.class, SecurityMappingConfig.class})
@PropertySource(value = "classpath:security-mapping.yml", factory = YamlPropertySourceFactory.class)
public class Application {

	public static void main(String[] args) {
		var context = SpringApplication.run(Application.class, args);
		StatementUtils.configureRepository(context.getBean(OverdueStatementRepository.class));
	}

}
