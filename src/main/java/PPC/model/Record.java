package PPC.model;

public class Record {

    private int recordId;
    private final int studentId;
    private final int questionId;

    public Record(int recordId, int studentId, int questionId) {
        this.recordId = recordId;
        this.studentId = studentId;
        this.questionId = questionId;
    }

    public Record(int studentId, int questionId) {
        this.studentId = studentId;
        this.questionId = questionId;
    }

    public int getRecordId() {
        return recordId;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getQuestionId() {
        return questionId;
    }

}
