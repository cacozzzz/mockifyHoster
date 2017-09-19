package io.mockify.hoster;

import io.mockify.hoster.dao.FileRepository;
import io.mockify.hoster.dao.Repository;
import io.mockify.hoster.gateways.RemoteHtmlGateway;
import io.mockify.hoster.usecase.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

    @Bean
    public FileRepository getFileRepository(@Value("${hoster.file-storage}") String path){
        return new FileRepository(path);
    }

    @Bean
    public SaveProjectUseCase getSaveProjectUseCase(Repository repository) {
        return new SaveProjectUseCase(repository);
    }

    @Bean
    public LoadProjectUseCase getLoadProjectUseCase(Repository repository) {
        return new LoadProjectUseCase(repository);
    }

    @Bean
    public ProjectCompilerUseCase getProjectCompilerUseCase() {
        return new ProjectCompilerUseCase();
    }

    @Bean
    public PublishProjectUseCase getPublishProjectUseCase(Repository repository) {
        return new PublishProjectUseCase(repository);
    }

    @Bean
    public LoadRemoteTemplateFromUrlUseCase getLoadRemoteTemplateFromUrlUseCase(RemoteHtmlGateway remoteHtmlGateway) {
        return new LoadRemoteTemplateFromUrlUseCase(remoteHtmlGateway);
    }

    @Bean
    public RemoteHtmlGateway getRemoteHtmlGateway(RestTemplate restTemplate) {
        return new RemoteHtmlGateway(restTemplate);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }



}
