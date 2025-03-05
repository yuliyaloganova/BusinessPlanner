import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class CreateTableTaskTagTest {

    public static void main(String[] args) {
        SpringApplication.run(CreateTableTaskTagTest.class, args);
    }

    @Bean
    public CommandLineRunner demo(TaskTagRepository taskTagRepository, TaskRepository taskRepository, TagRepository tagRepository) {
        return (args) -> {
            // Создаём тестовые задачи и теги
            Task task1 = new Task();
            task1.setName("Task 1");
            taskRepository.save(task1);

            Task task2 = new Task();
            task2.setName("Task 2");
            taskRepository.save(task2);

            Tag tag1 = new Tag();
            tag1.setName("Tag 1");
            tagRepository.save(tag1);

            Tag tag2 = new Tag();
            tag2.setName("Tag 2");
            tagRepository.save(tag2);

            Tag tag3 = new Tag();
            tag3.setName("Tag 3");
            tagRepository.save(tag3);

            // Создаём связи между задачами и тегами
            TaskTag taskTag1 = new TaskTag();
            taskTag1.setTask(task1);
            taskTag1.setTag(tag1);
            taskTagRepository.save(taskTag1);

            TaskTag taskTag2 = new TaskTag();
            taskTag2.setTask(task1);
            taskTag2.setTag(tag2);
            taskTagRepository.save(taskTag2);

            TaskTag taskTag3 = new TaskTag();
            taskTag3.setTask(task2);
            taskTag3.setTag(tag3);
            taskTagRepository.save(taskTag3);

            // Выводим информацию о созданных связях
            System.out.println("Созданы связи между задачами и тегами:");
            taskTagRepository.findAll().forEach(tt -> {
                System.out.println("Task: " + tt.getTask().getName() + ", Tag: " + tt.getTag().getName());
            });
        };
    }
}