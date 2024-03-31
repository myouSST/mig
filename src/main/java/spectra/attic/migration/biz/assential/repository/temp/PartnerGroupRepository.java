package spectra.attic.migration.biz.assential.repository.temp;

import org.springframework.data.jpa.repository.JpaRepository;
import spectra.attic.migration.biz.assential.domain.source.SourcePartnerGroup;
import spectra.attic.migration.biz.assential.domain.temp.PartnerGroup;

public interface PartnerGroupRepository extends JpaRepository<PartnerGroup, Long> {

}
