package PPC.model;

public class Record {

    private int recordId;
    private final int userId;
    private final int questionId;

    public Record(int recordId, int userId, int questionId) {
        this.recordId = recordId;
        this.userId = userId;
        this.questionId = questionId;
    }

    public Record(int userId, int questionId) {
        this.userId = userId;
        this.questionId = questionId;
    }

    public int getRecordId() {
        return recordId;
    }

    public int getUserId() {
        return userId;
    }

    public int getQuestionId() {
        return questionId;
    }

}
