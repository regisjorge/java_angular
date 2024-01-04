package br.com.CrudJava;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import br.com.CrudJava.enums.Category;
import br.com.CrudJava.models.Course;
import br.com.CrudJava.models.Lesson;
import br.com.CrudJava.repositories.CourseRepository;

@SpringBootApplication
public class CrudJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudJavaApplication.class, args);
	}

	@Bean
	@Profile("dev")
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();
			for (int i = 0; i < 20; i++) {
				Course c = new Course();
				c.setName("Angular com Spring "+i);
				c.setCategory(Category.BACKEND);

				Lesson l = new Lesson();
				l.setName("Introdução "+i);
				l.setYoutubeUrl("watch?v=1"+i);
				l.setCourse(c);
				c.getLesson().add(l);

				Lesson l1 = new Lesson();
				l1.setName("Angular "+i);
				l1.setYoutubeUrl("watch?v=2"+i);
				l1.setCourse(c);
				c.getLesson().add(l1);

				courseRepository.save(c);
			}
		};
	}

}
