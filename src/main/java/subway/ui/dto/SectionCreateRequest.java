package subway.ui.dto;

public class SectionCreateRequest {

    private Long lineId;
    private String leftStationName;
    private String rightStationName;
    private Integer distance;

    private SectionCreateRequest() {
    }

    public SectionCreateRequest(Long lineId, String leftStationName, String rightStationName, Integer distance) {
        this.lineId = lineId;
        this.leftStationName = leftStationName;
        this.rightStationName = rightStationName;
        this.distance = distance;
    }

    public Long getLineId() {
        return lineId;
    }

    public String getLeftStationName() {
        return leftStationName;
    }

    public String getRightStationName() {
        return rightStationName;
    }

    public Integer getDistance() {
        return distance;
    }
}
