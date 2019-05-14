package com.techview.ayodeji.attendance;

public class MatricNumber {
    private long id;
    private String matricNo;
    private String course;
    //public MatricNumber(long id, String matric_number, String course){

    //}
    public MatricNumber(long id, String matricNo){

        this.id = id;
        this.matricNo = matricNo;
       // this.course = course;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMatricNo() {
        return matricNo;
    }

   // public  String getCourse() {return course;}

    public void setCourse(String course) {
        this.course = course;
    }

    public void setMatricNo(String matricNo) {
        this.matricNo = matricNo;
    }

    @Override
    public String toString() {
        return "MatricNumber{" +
                "id=" + id +
                ", matricNo='" + matricNo + '\'' +
                '}';
    }
}
