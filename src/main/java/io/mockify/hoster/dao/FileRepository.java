package io.mockify.hoster.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.primitives.Longs;
import io.mockify.hoster.constants.Constants;
import io.mockify.hoster.exceptions.persistence.NullProjectRepositoryException;
import io.mockify.hoster.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileRepository implements Repository {

    private static final Logger log = LoggerFactory.getLogger(FileRepository.class);

    private String fileRepositoryBaseDir;
    private Sequence sequence;
    /**
     * Main constructor
     * @param fileRepositoryBaseDir Base dir where files are stored
     */
    public FileRepository(String fileRepositoryBaseDir) {
        this.fileRepositoryBaseDir = fileRepositoryBaseDir;
        sequence = new Sequence();
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
            log.error("Project directory {} doesn't exist.", projectDirectoryPath);
            return null;
        }

        return null;
    }

    @Override
    public List<Project> loadAllByUserId(String userId) {
        File userDirectory = new File(getUserDirectory(userId));

        if(!userDirectory.exists() || !userDirectory.isDirectory()) return null;

        String[] dirContents = userDirectory.list();

        List<Project> projects = new ArrayList<>();
        for (String s : dirContents) {
            if (Files.isDirectory(Paths.get(userDirectory.toString(), s))) {
                projects.add(load(s, userId));
            }
        }

        return projects;
    }

    @Override
    public void save(Project project, String userId) throws NullProjectRepositoryException {
        if (project == null) {
            RuntimeException e = new NullProjectRepositoryException("Can't save: Project is null!");
            log.error(e.getMessage(),e);
            throw e;
        }

        project.setId(sequence.getNewId());

        String projectDirectoryPath = getProjectDirectoryPath(project, userId);
        ObjectMapper objectMapper = new ObjectMapper();
        File projectDirs = new File(projectDirectoryPath);
        projectDirs.mkdirs();
        File projectFile = new File(projectDirectoryPath, Constants.PROJECT_FILENAME);

        try {
            projectFile.createNewFile();
            objectMapper.writeValue(projectFile, project);
        } catch (IOException e) {
            log.error("Couldn't save project",e);

        }
    }

    private class Sequence {
        private final String sequenceFileName = "sequence.txt";
        private final File sequenceFile;
        private long currentId;
        private int maxId;
        private int idReserveOffest = 10;

        public Sequence() {
            sequenceFile = new File(Paths.get(fileRepositoryBaseDir).toString(), sequenceFileName);


        }

        public synchronized long getNewId() {
            if (maxId > currentId) {
                return ++currentId;
            }

            currentId = getNewIdFromSeqFile(idReserveOffest);
            maxId += idReserveOffest;
            return currentId;
        }

        private long getNewIdFromSeqFile(long idsToReserve){
            if(!sequenceFile.exists()) try {
                sequenceFile.createNewFile();
                saveNewIdToSeqFile(idsToReserve);
                return 0;
            } catch (IOException e) {
                log.error("Can't create sequence file!",e);
            }

            return getIdFromSeqFile();
        }

        private long getIdFromSeqFile() {
            long maxId = 0;
            try {
                byte[] b = new byte[Long.BYTES];
                FileInputStream sequenceFileInputStream = new FileInputStream(sequenceFile);
                sequenceFileInputStream.read(b);

                maxId = Longs.fromByteArray(b);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return maxId;
        }

        private void saveNewIdToSeqFile(long id) throws IOException {
            try {
                FileOutputStream sequenceOutputStream = new FileOutputStream(sequenceFile);
                sequenceOutputStream.write(Longs.toByteArray(id));
                sequenceOutputStream.close();
            } catch (IOException e) {
                String msg = "Can't write to sequence file";
                log.error(msg);
                throw e;
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
            log.error("Couldn't create html file",e);
        }

        try(FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(html);
        } catch (IOException e) {
            log.error("Couldn't write html data into file while saving",e);
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

    private String getUserDirectory(String userId) {
        return Paths.get(fileRepositoryBaseDir, Constants.PROJECTS_DIRECTORY, userId).toString();
    }

}
