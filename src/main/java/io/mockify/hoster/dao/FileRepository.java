package io.mockify.hoster.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.mockify.hoster.constants.Constants;
import io.mockify.hoster.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class FileRepository implements Repository {

    private static final Logger log = LoggerFactory.getLogger(FileRepository.class);

    private String fileRepositoryBaseDir;

    /**
     * Main constructor
     * @param fileRepositoryBaseDir Base dir where files are stored
     */
    public FileRepository(String fileRepositoryBaseDir) {
        this.fileRepositoryBaseDir = fileRepositoryBaseDir;
    }

    @Override
    public Project load(String projectName, String userId) {
        String projectDirectoryPath = getProjectDirectoryPathByProjectName(projectName, userId);
        Project project;

        // Check for directory existance
        File file = new File(projectDirectoryPath);

        if (file.exists()){
            // loading project
            file = new File(projectDirectoryPath, Constants.PROJECT_FILENAME);

            if(file.exists()){
                ObjectMapper objectMapper = new ObjectMapper();

                try {
                    project = objectMapper.readValue(file, Project.class);

                    return project;
                } catch (IOException e) {
                    log.error("Could not read project from json file", e);
                }

            } else {
                log.error("Project file doesn't exist in {}", projectDirectoryPath);
                return null;
            }
        } else {
            System.err.println("Project directory " + projectDirectoryPath + " doesn't exist.");
            return null;
        }

        return null;
    }

    @Override
    public void save(Project project, String userId) {
        String projectDirectoryPath = getProjectDirectoryPath(project, userId);

        if (project != null){

            ObjectMapper objectMapper = new ObjectMapper();

            File file = new File(projectDirectoryPath);
            file.mkdirs();

            file = new File(projectDirectoryPath, Constants.PROJECT_FILENAME);
            try {

                file.createNewFile();
                objectMapper.writeValue(file, project);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public void saveHtml(String html , Project project, String userId){
        final String pageOutputDirectory = getPageOutputDirectoryPath(project, userId);
        File fileDirectories = new File(pageOutputDirectory);

        fileDirectories.mkdirs();

        File file = new File(pageOutputDirectory, Constants.PAGE_FILENAME);

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getPageOutputDirectoryPath(Project project, String userId){
        return Paths.get(getProjectDirectoryPath(project, userId), Constants.PAGE_OUTPUT_DIRECTORY).toString();
    }

    private String getProjectDirectoryPath(Project project, String userId){
        return getProjectDirectoryPathByProjectName(project.getName(), userId);
    }

    private String getProjectDirectoryPathByProjectName(String projectName, String userId){
        return Paths.get(fileRepositoryBaseDir, Constants.PROJECTS_DIRECTORY, userId, projectName).toString();
    }

}
