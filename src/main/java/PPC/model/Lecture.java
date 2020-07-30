package PPC.model;

public class Lecture {

    public final static String LECTURES_FILES_PATH = "src/main/webapp/resources/lecture/";

    private int lectureId;
    private final String lectureName;
    private final String fileName;
    private final String videoUrl;

    public Lecture(int lectureId, String lectureName, String fileName, String videoUrl) {
        this.lectureId = lectureId;
        this.lectureName = lectureName;
        this.fileName = fileName;
        this.videoUrl = videoUrl;
    }

    public Lecture(String lectureName) {
        this.lectureName = lectureName;
        fileName = lectureName + ".txt";
        videoUrl = null;
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

    public String getVideoUrl() {
        return videoUrl;
    }

}
