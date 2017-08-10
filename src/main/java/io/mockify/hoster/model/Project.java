package io.mockify.hoster.model;

import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

public class Project {

    private int id;
    private String name;
    private Template template;
    private List<Post> postsList = new ArrayList<>();

    public Project(int id) {
        this.id = id;
    }

    public Project() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public void addPost(Post post){
        this.postsList.add(post);
    }


}
