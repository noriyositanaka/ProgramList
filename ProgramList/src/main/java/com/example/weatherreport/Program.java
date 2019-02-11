package com.example.weatherreport;

public class Program {
    private String id;
    private String event_id;
    private String start_time;
    private String end_time;
    private String title;
    private String subtitle;
    private String content;
    private String act;

    public String getId() {
        return id;
    }

    public String getAct() {
        return act;
    }

    public String getContent() {
        return content;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getEvent_id() {
        return event_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
