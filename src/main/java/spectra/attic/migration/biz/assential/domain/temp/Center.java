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
import spectra.attic.migration.biz.assential.domain.source.SourceUser;

@Getter
@Entity
@Table(name = "centers")
@AllArgsConstructor
@NoArgsConstructor
public class Center {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    public static Center fromSource(SourceCenter sourceCenter) {
        return new Center(sourceCenter.getId(), sourceCenter.getUsername(), sourceCenter.getPassword());
    }
}
