package junitcucumber.page;

public class Emails {
    private int id;
    private int userId;
    private String sendTo;
    private String theme;
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        return "Countries:: userid = " + userId + " send to = " + this.sendTo + " Theme = " + this.theme + " Text = " + this.text;
    }

}
