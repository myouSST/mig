package spectra.attic.migration.biz.assential.domain.temp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spectra.attic.migration.biz.assential.domain.source.SourceAppChannel;
import spectra.attic.migration.biz.assential.domain.source.SourceUser;

@Getter
@Entity
@Table(name = "apchannels")
@AllArgsConstructor
@NoArgsConstructor
public class AppChannel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    public static AppChannel fromSource(SourceAppChannel sourceAppChannel) {
        return new AppChannel(sourceAppChannel.getId(), sourceAppChannel.getUsername(), sourceAppChannel.getPassword());
    }
}
