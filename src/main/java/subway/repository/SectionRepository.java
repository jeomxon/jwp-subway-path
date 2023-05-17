package subway.repository;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import subway.dao.SectionDao;
import subway.domain.Section;
import subway.entity.SectionEntity;

@Repository
public class SectionRepository {

    private final SectionDao sectionDao;

    public SectionRepository(final SectionDao sectionDao) {
        this.sectionDao = sectionDao;
    }

    public void saveAllByLineId(Long lineId, List<Section> sections) {
        List<SectionEntity> sectionEntities = sections.stream()
                .map(section -> SectionEntity.of(lineId, section))
                .collect(Collectors.toList());
        sectionDao.saveAll(sectionEntities);
    }

    public void deleteAllByLineId(Long lineId) {
        sectionDao.deleteAllByLineId(lineId);
    }
}
