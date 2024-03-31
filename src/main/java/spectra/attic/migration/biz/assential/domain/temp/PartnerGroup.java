package spectra.attic.migration.biz.assential.domain.temp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spectra.attic.migration.biz.assential.domain.source.SourcePartner;
import spectra.attic.migration.biz.assential.domain.source.SourcePartnerGroup;

@Getter
@Entity
@Table(name = "partnergroups")
@AllArgsConstructor
@NoArgsConstructor
public class PartnerGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    public static PartnerGroup fromSource(SourcePartnerGroup sourcePartnerGroup) {
        return new PartnerGroup(sourcePartnerGroup.getId(), sourcePartnerGroup.getUsername(), sourcePartnerGroup.getPassword());
    }
}
