package spectra.attic.migration.biz.assential.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;

public class DBDumper<SourceType, TargetType> {

    private final JpaRepository<SourceType, Long> sourceRepository;
    private final JpaRepository<TargetType, Long> targetRepository;
    private final Function<SourceType, TargetType> converter;

    public DBDumper(JpaRepository<SourceType, Long> sourceRepository,
                    JpaRepository<TargetType, Long> targetRepository,
                    Function<SourceType, TargetType> converter) {
        this.sourceRepository = sourceRepository;
        this.targetRepository = targetRepository;
        this.converter = converter;
    }

    public void dump() {
        List<TargetType> targetObjects = sourceRepository.findAll().stream()
            .map(converter)
            .collect(Collectors.toList());

        targetRepository.saveAll(targetObjects);
    }
}