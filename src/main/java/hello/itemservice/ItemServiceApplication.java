package hello.itemservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ItemServiceApplication  {

	/**
	 * 글로벌 설정 테스트
	 * implements WebMvcConfigurer
 	 */
	/*
	@Override
	public Validator getValidator() {
		return new ItemValidator();
	}
	*/

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

}
