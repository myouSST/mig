package spectra.attic.migration.biz.assential.domain.temp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spectra.attic.migration.biz.assential.domain.source.SourceUser;

@Getter
@Entity
@Table(name = "teams")
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    public static Team fromSource(SourceUser sourceUser) {
        return new Team(sourceUser.getId(), sourceUser.getUsername(), sourceUser.getPassword());
    }
}
