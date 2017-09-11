package io.mockify.hoster;

import io.mockify.hoster.dao.FileRepository;
import io.mockify.hoster.dao.Repository;
import io.mockify.hoster.usecase.LoadProjectUseCase;
import io.mockify.hoster.usecase.ProjectCompilerUseCase;
import io.mockify.hoster.usecase.PublishProjectUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootApplication
public class Application {

    @Bean
    public FileRepository fileRepository(@Value("${hoster.file-storage}") String path){
        return new FileRepository(path);
    }

    @Bean
    public ProjectCompilerUseCase projectCompilerUseCase() {
        return new ProjectCompilerUseCase();
    }

    @Bean
    public PublishProjectUseCase publishProjectUseCase(Repository repository) {
        return new PublishProjectUseCase(repository);
    }

    @Bean
    public LoadProjectUseCase loadProjectUseCase(Repository repository) {
        return new LoadProjectUseCase(repository);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }



}
