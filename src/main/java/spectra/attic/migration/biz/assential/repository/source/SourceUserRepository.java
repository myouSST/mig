package spectra.attic.migration.biz.assential.repository.source;

import org.springframework.data.jpa.repository.JpaRepository;
import spectra.attic.migration.biz.assential.domain.source.SourceUser;

public interface SourceUserRepository extends JpaRepository<SourceUser, Long> {

}
