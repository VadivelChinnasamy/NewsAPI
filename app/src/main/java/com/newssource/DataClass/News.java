package com.newssource.DataClass;


public class News {


    private  String description;
    private  String publish_at;
    private String title;
    private String author;
    private String logo;
    private String id;
    private String name, image_link;

    public News(String id, String name, String image_link) {
        this.id = id;
        this.name = name;
        this.image_link = image_link;
    }

    public News(String author, String title, String image_link, String logo,String publish_at,String description) {
        this.title = title;
        this.author = author;
        this.image_link = image_link;
        this.logo = logo;
        this.publish_at=publish_at;
        this.description=description;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    public String getPublish_at() {
        return publish_at;
    }

    public void setPublish_at(String publish_at) {
        this.publish_at = publish_at;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
