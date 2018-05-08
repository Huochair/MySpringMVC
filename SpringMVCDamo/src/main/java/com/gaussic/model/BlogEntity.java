package com.gaussic.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "blog", schema = "springdemo", catalog = "")
public class BlogEntity {
    private int id;
    private String title;
    private UserEntity userByUserId;
    private String content;
    private Date pubDate;

//    public  BlogEntity(){
//
//    }
//    public  BlogEntity(int id,String title,String content,Date pubDate,UserEntity userByUserId){
//        this.id = id;
//        this.title = title;
//        this.content = content;
//        this.pubDate = pubDate;
//        this.userByUserId = userByUserId;
//    }
    @Id
    @Column(name = "Id", nullable = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 100)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 255)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "pub_date", nullable = true)
    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "Id", nullable = false)
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogEntity that = (BlogEntity) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (pubDate != null ? !pubDate.equals(that.pubDate) : that.pubDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (pubDate != null ? pubDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BlogEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", userByUserId=" + userByUserId +
                ", content='" + content + '\'' +
                ", pubDate=" + pubDate +
                '}';
    }
}
