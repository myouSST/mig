package spectra.attic.migration.biz.assential.domain.temp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spectra.attic.migration.biz.assential.domain.source.SourceCenter;
import spectra.attic.migration.biz.assential.domain.source.SourcePartner;

@Getter
@Entity
@Table(name = "partners")
@AllArgsConstructor
@NoArgsConstructor
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    public static Partner fromSource(SourcePartner sourcePartner) {
        return new Partner(sourcePartner.getId(), sourcePartner.getUsername(), sourcePartner.getPassword());
    }
}
