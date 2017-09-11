package io.mockify.hoster.dao;

import io.mockify.hoster.model.Post;
import io.mockify.hoster.model.Project;
import io.mockify.hoster.model.Template;
import io.mockify.hoster.model.User;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileRepositoryTest {

    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    private String testProjectFileData;// = "{\"id\":0,\"name\":\"TestProject\",\"template\":{\"id\":0,\"name\":\"template1\",\"contentTag\":\"content-tag\",\"resourceList\":null,\"htmldata\":\"<html><head></head><body><content-tag/></div></body></html>\"},\"postsList\":[{\"id\":0,\"name\":\"Post1\",\"postDate\":null,\"htmlData\":\"<P>post1</P>\",\"url\":null}]}";
    private String testProjectsDataFolder = "ProjectsData";
    private String testProjectFolder = "TestProject";
    private String testProjectFileName = "Project.json";
    private final String testProjectHtmlOutputFolder = "PageOutput";
    private final String testProjectHtmlFileName = "index.html";
    private String testProjectHtmlFileData;


    private FileRepository fileRepository;
    private User user;

    @Before
    public void setUp(){
        fileRepository = new FileRepository(getTempDirectory());
        user = getTestUser();

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

        try {
            byte[] bytes = Files.readAllBytes(Paths.get(
                    System.getProperty("user.dir"),
                    "src",
                    "test",
                    "resources",
                    "index.html"
            ));

            testProjectHtmlFileData = new String(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void load() throws Exception {


        saveTestProject();

        assertEquals(getTestProject(), fileRepository.load("TestProject", user.getId()));
    }

    @Test
    public void save() {
        fileRepository = new FileRepository(getTempDirectory());
        Project testProject = getTestProject();

        fileRepository.save(testProject, user.getId());

        Path path = Paths.get(getTempDirectory(),testProjectsDataFolder,user.getId(),testProjectFolder,testProjectFileName);

        try {
            byte[] bytes = Files.readAllBytes(path);

            assertEquals(
                    testProjectFileData,
                    new String(bytes)
            );
        } catch (IOException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @Test
    public void saveHtml(){
        final Project testProject = getTestProject();
        fileRepository.saveHtml(testProjectHtmlFileData, testProject, user.getId());

        byte[] bytes;

        try{
            bytes = Files.readAllBytes(Paths.get(
                    getTempDirectory(),
                    testProjectsDataFolder,
                    user.getId(),
                    testProjectFolder,
                    testProjectHtmlOutputFolder,
                    testProjectHtmlFileName
            ));

            assertEquals(testProjectHtmlFileData,
                    new String(bytes));
        } catch (IOException e) {
            e.printStackTrace();

            assertTrue(false);
        }
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



        System.out.println(temporaryFolder.getRoot());

        File testProjectFileFolder = Paths.get(temporaryFolder.getRoot().getCanonicalPath(), testProjectsDataFolder, user.getId(), testProjectFolder).toFile();
        testProjectFileFolder.mkdirs();

        File testProjectFile = new File(testProjectFileFolder.toString(), testProjectFileName);


        try (FileWriter fr = new FileWriter(testProjectFile)) {
            fr.write(testProjectFileData);
        }

        System.out.println("");

    }

    private User getTestUser() {
        User u = new User();
        u.setId("test@test.com");
        u.setNickName("Tester");
        return u;
    }

    private String getTempDirectory(){
        return temporaryFolder.getRoot().toString();
    }

}