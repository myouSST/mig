package spectra.attic.migration.biz.assential.repository.temp;

import org.springframework.data.jpa.repository.JpaRepository;
import spectra.attic.migration.biz.assential.domain.source.SourcePartner;
import spectra.attic.migration.biz.assential.domain.temp.Partner;

public interface PartnerRepository extends JpaRepository<Partner, Long> {

}
