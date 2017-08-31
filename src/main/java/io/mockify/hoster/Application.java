package io.mockify.hoster;

import io.mockify.hoster.model.dao.FileRepository;
import io.mockify.hoster.model.dao.Repository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Bean
    public FileRepository fileRepository(@Value("${hoster.file-storage}") String path){
        return new FileRepository(path);
    }

    @Bean
    public ProjectCompiler getProjectCompiler(Repository repository) {
        return new ProjectCompiler(repository);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }



}
