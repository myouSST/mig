package spectra.attic.migration.biz.assential.repository.source;

import org.springframework.data.jpa.repository.JpaRepository;
import spectra.attic.migration.biz.assential.domain.source.SourceAppChannel;
import spectra.attic.migration.biz.assential.domain.source.SourceCenter;

public interface SourceAppChannelRepository extends JpaRepository<SourceAppChannel, Long> {

}
