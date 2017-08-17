package io.mockify.hoster.model.dao;

import io.mockify.hoster.model.Post;
import io.mockify.hoster.model.Project;
import io.mockify.hoster.model.Template;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FileRepositoryTest {

    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    String testProjectFileData;// = "{\"id\":0,\"name\":\"TestProject\",\"template\":{\"id\":0,\"name\":\"template1\",\"contentTag\":\"content-tag\",\"resourceList\":null,\"htmldata\":\"<html><head></head><body><content-tag/></div></body></html>\"},\"postsList\":[{\"id\":0,\"name\":\"Post1\",\"postDate\":null,\"htmlData\":\"<P>post1</P>\",\"url\":null}]}";
    String testProjectsDataFolder = "ProjectsData";
    String testProjectFolder = "TestProject";
    String testProjectFileName = "Project.json";


    FileRepository fileRepository;

    @Before
    public void setUp(){
        fileRepository = new FileRepository(getTempDirectory());

        try {
            byte[] bytes = Files.readAllBytes(Paths.get(
                    System.getProperty("user.dir"),
                    "src",
                    "test",
                    "resources",
                    "TestProject.json"
            ));

            testProjectFileData = new String(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void load() throws Exception {


        saveTestProject();

        assertEquals(getTestProject(), fileRepository.load("TestProject"));
    }

    @Test
    public void save() throws IOException {
        fileRepository = new FileRepository(getTempDirectory());
        Project testProject = getTestProject();

        fileRepository.save(testProject);


        Path path = Paths.get(getTempDirectory(),testProjectsDataFolder,testProjectFolder,testProjectFileName);

        byte[] bytes = Files.readAllBytes(path);

        assertEquals(
                testProjectFileData,
                new String(bytes)
        );




    }

    private Project getTestProject(){
        Project project = new Project();
        project.setName("TestProject");
        project.setId(0);

        Template template = new Template();
        template.setName("template1");
        template.setHTMLdata("<html><head></head><body><content-tag/></div></body></html>");
        template.setContentTag("content-tag");
        template.setId(0);

        project.setTemplate(template);

        List<Post> postList = new ArrayList<>();
        postList.add(new Post());
        postList.get(0).setId(0);
        postList.get(0).setHtmlData("<P>post1</P>");
        postList.get(0).setName("Post1");

        project.setPostsList(postList);

        return project;
    }

    private void saveTestProject() throws IOException {


        String testProjectHtmlOutputFolder = "PageOutput";
        String testProjectHtmlFileName = "index.html";

        System.out.println(temporaryFolder.getRoot());

        File projectDataFolder = temporaryFolder.newFolder(testProjectsDataFolder);
        File projectFolder = temporaryFolder.newFolder(testProjectsDataFolder, testProjectFolder);
        File projectFile = temporaryFolder.newFile(testProjectsDataFolder + File.separator +  testProjectFolder + File.separator + testProjectFileName);

        //File projectFile = temporaryFolder.newFile(testProjectsDataFolder + "\\" + testProjectFolder + "\\" + testProjectFileName);

        try (FileWriter fr = new FileWriter(projectFile)) {
            fr.write(testProjectFileData);
        }

        System.out.println("");

    }

    private String getTempDirectory(){
        return temporaryFolder.getRoot().toString();
    }

}