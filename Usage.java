import java.time.LocalDateTime;

public class Usage {
    int id;
    int memberId;
    int equipmentId;
    LocalDateTime startTime;
    LocalDateTime endTime;
    double amount;

    public Usage(int id, int memberId, int equipmentId) {
        this.id = id;
        this.memberId = memberId;
        this.equipmentId = equipmentId;
        this.startTime = LocalDateTime.now();
    }
}

