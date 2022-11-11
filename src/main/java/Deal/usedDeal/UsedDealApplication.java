package Deal.usedDeal;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UsedDealApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsedDealApplication.class, args);
	}

}
