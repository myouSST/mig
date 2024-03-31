package spectra.attic.migration.biz.assential.repository.temp;

import org.springframework.data.jpa.repository.JpaRepository;
import spectra.attic.migration.biz.assential.domain.source.SourceTeam;
import spectra.attic.migration.biz.assential.domain.temp.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
