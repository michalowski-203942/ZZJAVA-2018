package backend;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("backend.datastore.dao")
public class MainClass {

        public static void main(String[] args) throws Exception {
            SpringApplication.run(MainClass.class, args);
        }
}
