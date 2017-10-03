package io.mockify.hoster.view;

import java.util.ArrayList;
import java.util.List;

public class ProjectPresentable {

    private long id;
    private String name;
    private TemplatePresentable template;
    private List<PostPresentable> postsList = new ArrayList<>();
    private List<ResourcePresentable> resourceList = new ArrayList<>();

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

    public TemplatePresentable getTemplate() {
        return template;
    }

    public void setTemplate(TemplatePresentable template) {
        this.template = template;
    }

    public List<PostPresentable> getPostsList() {
        return postsList;
    }

    public void setPostsList(List<PostPresentable> postsList) {
        this.postsList = postsList;
    }

    public PostPresentable getPost(int id) {
        return postsList.get(id);
    }

    public void setPost(PostPresentable post) {
        postsList.add(post.getId(), post);
    }

    public List<ResourcePresentable> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<ResourcePresentable> resourceList) {
        this.resourceList = resourceList;
    }

    public void setResource(ResourcePresentable resource) {
        resourceList.add(resource.getId(), resource);
    }

    public ResourcePresentable getResource(int id) {
        return resourceList.get(id);
    }

    public void addPost(PostPresentable post){
        this.postsList.add(post);
    }

}
