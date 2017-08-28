package io.mockify.hoster.model.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.mockify.hoster.constants.Constants;
import io.mockify.hoster.model.Project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileRepository implements Repository {

    private String fileRepositoryBaseDir;

    /**
     * Main constructor
     * @param fileRepositoryBaseDir Base dir where files are stored
     */
    public FileRepository(String fileRepositoryBaseDir) {
        this.fileRepositoryBaseDir = fileRepositoryBaseDir;
    }

    @Override
    public Project load(String projectName) {
        String projectDirectoryPath = getProjectDirectoryPathByProjectName(projectName);
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
                    e.printStackTrace();
                }

            } else {

                System.err.println("Project file doesn't exist in "+projectDirectoryPath);
                return null;
            }
        } else {
            System.err.println("Project directory " + projectDirectoryPath + " doesn't exist.");
            return null;
        }

        return null;
    }

    @Override
    public void save(Project project) {
        String projectDirectoryPath = getProjectDirectoryPath(project);

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


    private String getProjectDirectoryPathByProjectName(String projectName){
        return fileRepositoryBaseDir
                + Constants.PROJECTS_DIRECTORY
                + File.separator + projectName;
    }

    private String getProjectDirectoryPath(Project project){
        return getProjectDirectoryPathByProjectName(project.getName());
    }

    private String getPageOutputDirectoryPath(Project project){
        return getProjectDirectoryPath(project)
                + Constants.PAGE_OUTPUT_DIRECTORY;
    }

    @Override
    public void saveHtml(String html , Project project){

        final String pageOutputDirectory = getPageOutputDirectoryPath(project);


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

}
