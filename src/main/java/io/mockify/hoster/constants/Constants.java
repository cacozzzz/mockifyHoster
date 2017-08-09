package io.mockify.hoster.constants;

import java.io.File;

public class Constants {


    public static final String ProjectsDirectory = File.separator + "ProjectsData";
    /* Final page compiled and stored at /ProjectsData/(RPOJECT_NAME)/PageOutput/ */
    public static final String PageOutputDirectory = File.separator + "PageOutput";

    public static final String PageFilename = "index.html";

    public static final String ProjectFilename = "Project.json";
    public static final String TemplateFilename = "template.json";
    public static final String PostListFilename = "posts.json";




    public static final String ProjectsTestDirectory = ProjectsDirectory + File.separator + "ProjectsTest";
}
