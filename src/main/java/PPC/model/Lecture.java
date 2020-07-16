package PPC.model;

public class Lecture {

    public final static String LECTURE_FILES_PATH = "src/main/webapp/resources/lecture/";

    private int lectureId;
    private final String lectureName;
    private final String fileName;

    public Lecture(int lectureId, String lectureName, String fileName) {
        this.lectureId = lectureId;
        this.lectureName = lectureName;
        this.fileName = fileName;
    }

    public Lecture(String lectureName) {
        this.lectureName = lectureName;
        fileName = lectureName + ".txt";
    }

    public int getLectureId() {
        return lectureId;
    }

    public String getLectureName() {
        return lectureName;
    }

    public String getFileName() {
        return fileName;
    }

}
