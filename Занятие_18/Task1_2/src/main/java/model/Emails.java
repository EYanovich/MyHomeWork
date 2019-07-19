package model;

public class Emails {
    private int id;
    private String sendTo;
    private String theme;
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheeme(String theme) {
        this.theme = theme;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Countries:: id = " + id + " code = " + this.sendTo + " Name = " + this.theme + " description = " + this.text;
    }

}
