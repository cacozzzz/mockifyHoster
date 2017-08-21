package io.mockify.hoster.constants;

import java.io.File;

public class Constants {


    public static final String PROJECTS_DIRECTORY = File.separator + "ProjectsData";
    /* Final page compiled and stored at /ProjectsData/(RPOJECT_NAME)/PageOutput/ */
    public static final String PAGE_OUTPUT_DIRECTORY = File.separator + "PageOutput";

    public static final String PAGE_FILENAME = "index.html";

    public static final String PROJECT_FILENAME = "Project.json";
    public static final String TEMPLATE_FILENAME = "template.json";
    public static final String POST_LIST_FILENAME = "posts.json";

    public static final String ProjectsTestDirectory = PROJECTS_DIRECTORY + File.separator + "ProjectsTest";

    /* Resource html tag attributes */
    public static final String RESOURCE_HTML_ATTR_ID = "data-resource-id";
    public static final String RESOURCE_HTML_ATTR_URL = "data-resource-url";
}
