package subway.application;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import subway.domain.Line;
import subway.repository.LineRepository;
import subway.ui.dto.LineRequest;
import subway.ui.dto.LineResponse;
import subway.ui.dto.LineStationResponse;
import subway.ui.dto.StationResponse;

@Service
public class LineService {

    private final LineRepository lineRepository;

    public LineService(final LineRepository lineRepository) {
        this.lineRepository = lineRepository;
    }

    public LineResponse saveLine(LineRequest request) {
        Line line = lineRepository.save(new Line(request.getName()));
        return new LineResponse(line.getId(), line.getName());
    }

    public List<LineResponse> findAllLines() {
        List<Line> lines = lineRepository.findAll();
        return lines.stream()
                .map(line -> new LineResponse(line.getId(), line.getName()))
                .collect(Collectors.toList());
    }

    public LineResponse findLineById(Long id) {
        Line line = findByLineId(id);
        return new LineResponse(line.getId(), line.getName());
    }

    public List<LineStationResponse> findAllLinesAndStations() {
        return lineRepository.findAll()
                .stream()
                .map(line -> findStationsById(line.getId()))
                .collect(Collectors.toList());
    }

    public LineStationResponse findStationsById(Long lineId) {
        Line line = findByLineId(lineId);

        List<StationResponse> stationResponses = line.findLeftToRightRoute()
                .stream()
                .map(station -> new StationResponse(station.getId(), station.getName()))
                .collect(Collectors.toList());

        return new LineStationResponse(line.getId(), line.getName(), stationResponses);
    }

    private Line findByLineId(Long id) {
        return lineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 노선이 없습니다."));
    }

    public void deleteLineById(Long id) {
        lineRepository.deleteById(id);
    }
}
