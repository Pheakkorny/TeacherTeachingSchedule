/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teachingschedule;

/**
 *
 * @author Pheakkorny
 */
class TeachingSession {
    private String day;
    private String time;
    private int session;
    private String subject;
    private String teacher;

    public TeachingSession(String day, String time, int session, String subject, String teacher) {
        this.day = day;
        this.time = time;
        this.session = session;
        this.subject = subject;
        this.teacher = teacher;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSession() {
        return session;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
