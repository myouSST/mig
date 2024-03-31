package spectra.attic.migration.biz.assential.repository.temp;

import org.springframework.data.jpa.repository.JpaRepository;
import spectra.attic.migration.biz.assential.domain.source.SourceAppChannel;
import spectra.attic.migration.biz.assential.domain.temp.AppChannel;

public interface AppChannelRepository extends JpaRepository<AppChannel, Long> {

}
