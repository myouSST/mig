package spectra.attic.migration.biz.assential.service;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spectra.attic.migration.biz.assential.domain.temp.User;
import spectra.attic.migration.biz.assential.repository.source.SourceAppChannelRepository;
import spectra.attic.migration.biz.assential.repository.source.SourceCenterRepository;
import spectra.attic.migration.biz.assential.repository.source.SourcePartnerGroupRepository;
import spectra.attic.migration.biz.assential.repository.source.SourcePartnerRepository;
import spectra.attic.migration.biz.assential.repository.source.SourceTeamRepository;
import spectra.attic.migration.biz.assential.repository.source.SourceUserRepository;
import spectra.attic.migration.biz.assential.repository.temp.UserRepository;

@Service
@RequiredArgsConstructor
public class BizDumpService {
    private final SourceUserRepository sourceUserRepository;

    private final SourceAppChannelRepository sourceAppChannelRepository;

    private final SourceCenterRepository sourceCenterRepository;

    private final SourcePartnerGroupRepository sourcePartnerGroupRepository;

    private final SourcePartnerRepository sourcePartnerRepository;

    private final SourceTeamRepository sourceTeamRepository;

    private final UserRepository userRepository;

    // 라이선스

    public void dump() {
        List<User> users = sourceUserRepository.findAll().stream()
            .map(User::fromSource)
            .collect(Collectors.toList());

        userRepository.saveAll(users);
    }
}
