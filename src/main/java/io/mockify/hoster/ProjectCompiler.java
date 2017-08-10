package io.mockify.hoster;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.mockify.hoster.constants.Constants;
import io.mockify.hoster.model.Post;
import io.mockify.hoster.model.Project;
import io.mockify.hoster.model.Template;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ProjectCompiler {

    public static void compile(Project project){

        if (project != null && project.getTemplate() != null && project.getPostsList() != null) {
            Document doc = Jsoup.parse(project.getTemplate().getHTMLdata());


            final Element elementContent = doc.getElementsByTag(project.getTemplate().getContentTag()).first();

            project.getPostsList().forEach(post -> {

                elementContent.append(post.getHtmlData());
            });

            savePageToFIle(doc.html(), project);
        }

    }

    public static Project loadProject(String projectName){

        String projectDirectoryPath = getProjectDirectoryPathByProjectName(projectName);
        Project project;

        File file = new File(projectDirectoryPath);

        if (file.exists()){
            // loading project
            file = new File(projectDirectoryPath + File.separator + Constants.PROJECT_FILENAME);

            if(file.exists()){
                ObjectMapper objectMapper = new ObjectMapper();

                try {
                    project = objectMapper.readValue(file, Project.class);

                    return project;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } else {
            return null;
        }

        return null;
    }

    @Deprecated
    public static void saveProjectOld(Project project){

        String projectDirectoryPath = getProjectDirectoryPath(project);
        Template template;

        if (project != null && (template = project.getTemplate()) != null){

            ObjectMapper objectMapper = new ObjectMapper();

            File file = new File(projectDirectoryPath);
            file.mkdirs();

            file = new File(projectDirectoryPath + File.separator + Constants.TEMPLATE_FILENAME);
            try {

                file.createNewFile();
                objectMapper.writeValue(file, template);

            } catch (IOException e) {
                e.printStackTrace();
            }

            List<Post> postList = project.getPostsList();

            if(postList != null){
                file = new File(projectDirectoryPath + File.separator + Constants.POST_LIST_FILENAME);

                try {
                    file.createNewFile();
                    objectMapper.writeValue(file, project.getPostsList());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }






    }

    public static void saveProject(Project project){

        String projectDirectoryPath = getProjectDirectoryPath(project);

        if (project != null){

            ObjectMapper objectMapper = new ObjectMapper();

            File file = new File(projectDirectoryPath);
            file.mkdirs();

            file = new File(projectDirectoryPath + File.separator + Constants.PROJECT_FILENAME);
            try {

                file.createNewFile();
                objectMapper.writeValue(file, project);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }






    }


    public static String getProjectDirectoryPathByProjectName(String projectName){
        return System.getProperty("user.dir")
                + Constants.PROJECTS_DIRECTORY
                + File.separator + projectName;
    }


    public static String getProjectDirectoryPath(Project project){
        return getProjectDirectoryPathByProjectName(project.getName());
    }

    public static String getPageOutputDirectoryPath(Project project){
        return getProjectDirectoryPath(project)
                + Constants.PAGE_OUTPUT_DIRECTORY;
    }

    private static void savePageToFIle(String html , Project project){

        final String pageOutputDirectory = getPageOutputDirectoryPath(project);


        File fileDirectories = new File(pageOutputDirectory);
        fileDirectories.mkdirs();

        File file = new File( pageOutputDirectory + File.separator + Constants.PAGE_FILENAME);

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        FileWriter fileWriter;

        try
        {
            fileWriter = new FileWriter(file);

            fileWriter.write(html);

            fileWriter.close();


        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
