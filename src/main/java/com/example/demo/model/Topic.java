package com.example.demo.model;

public class Topic {
        private  Integer idtopic;
        private  String title;
        private  String topiccontext;
        private  Long   create_time;
        private  Long   modify_time;
        private  Integer read_count;
        private  Integer comment_count;
        private  Integer like_count;
        private  String tags;
        private  Integer post_id;

    public Integer getPost_id() {
        return post_id;
    }

    public void setPost_id(Integer post_id) {
        this.post_id = post_id;
    }

    public Integer getIdtopic() {
        return idtopic;
    }

    public void setIdtopic(Integer idtopic) {
        this.idtopic = idtopic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopiccontext() {
        return topiccontext;
    }

    public void setTopiccontext(String topiccontext) {
        this.topiccontext = topiccontext;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

    public Long getModify_time() {
        return modify_time;
    }

    public void setModify_time(Long modify_time) {
        this.modify_time = modify_time;
    }

    public Integer getRead_count() {
        return read_count;
    }

    public void setRead_count(Integer read_count) {
        this.read_count = read_count;
    }

    public Integer getComment_count() {
        return comment_count;
    }

    public void setComment_count(Integer comment_count) {
        this.comment_count = comment_count;
    }

    public Integer getLike_count() {
        return like_count;
    }

    public void setLike_count(Integer like_count) {
        this.like_count = like_count;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "idtopic=" + idtopic +
                ", title='" + title + '\'' +
                ", topiccontext='" + topiccontext + '\'' +
                ", create_time=" + create_time +
                ", modify_time=" + modify_time +
                ", read_count=" + read_count +
                ", comment_count=" + comment_count +
                ", like_count=" + like_count +
                ", tags='" + tags + '\'' +
                '}';
    }
}
