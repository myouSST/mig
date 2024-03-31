package spectra.attic.migration.biz.assential.repository.source;

import org.springframework.data.jpa.repository.JpaRepository;
import spectra.attic.migration.biz.assential.domain.source.SourceCenter;
import spectra.attic.migration.biz.assential.domain.source.SourceTeam;

public interface SourceTeamRepository extends JpaRepository<SourceTeam, Long> {

}
