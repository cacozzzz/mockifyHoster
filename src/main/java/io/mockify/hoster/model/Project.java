package io.mockify.hoster.model;

import java.util.ArrayList;
import java.util.List;

public class Project {

    private long id;
    private String name;
    private Template template;
    private List<Post> postsList = new ArrayList<>();
    private List<Resource> resourceList = new ArrayList<>();

    public Project(int id) {
        this.id = id;
    }

    public Project() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public List<Post> getPostsList() {
        return postsList;
    }

    public void setPostsList(List<Post> postsList) {
        this.postsList = postsList;
    }

    public Post getPost(int id) {
        return postsList.get(id);
    }

    public void setPost(Post post) {
        postsList.add(post.getId(), post);
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }

    public void setResource(Resource resource) {
        resourceList.add(resource.getId(), resource);
    }

    public Resource getResource(int id) {
        return resourceList.get(id);
    }

    public void addPost(Post post){
        this.postsList.add(post);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (id != project.id) return false;
        if (name != null ? !name.equals(project.name) : project.name != null) return false;
        if (template != null ? !template.equals(project.template) : project.template != null) return false;
        if (postsList != null ? !postsList.equals(project.postsList) : project.postsList != null) return false;
        return resourceList != null ? resourceList.equals(project.resourceList) : project.resourceList == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + (template != null ? template.hashCode() : 0);
        result = 31 * result + (postsList != null ? postsList.hashCode() : 0);
        result = 31 * result + (resourceList != null ? resourceList.hashCode() : 0);
        return result;
    }
}
