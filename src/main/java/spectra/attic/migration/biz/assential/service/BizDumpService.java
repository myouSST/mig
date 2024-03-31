package spectra.attic.migration.biz.assential.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spectra.attic.migration.biz.assential.domain.source.SourceAppChannel;
import spectra.attic.migration.biz.assential.domain.source.SourceCenter;
import spectra.attic.migration.biz.assential.domain.source.SourcePartner;
import spectra.attic.migration.biz.assential.domain.source.SourcePartnerGroup;
import spectra.attic.migration.biz.assential.domain.source.SourceTeam;
import spectra.attic.migration.biz.assential.domain.source.SourceUser;
import spectra.attic.migration.biz.assential.domain.temp.AppChannel;
import spectra.attic.migration.biz.assential.domain.temp.Center;
import spectra.attic.migration.biz.assential.domain.temp.Partner;
import spectra.attic.migration.biz.assential.domain.temp.PartnerGroup;
import spectra.attic.migration.biz.assential.domain.temp.Team;
import spectra.attic.migration.biz.assential.domain.temp.User;
import spectra.attic.migration.biz.assential.repository.source.SourceAppChannelRepository;
import spectra.attic.migration.biz.assential.repository.source.SourceCenterRepository;
import spectra.attic.migration.biz.assential.repository.source.SourcePartnerGroupRepository;
import spectra.attic.migration.biz.assential.repository.source.SourcePartnerRepository;
import spectra.attic.migration.biz.assential.repository.source.SourceTeamRepository;
import spectra.attic.migration.biz.assential.repository.source.SourceUserRepository;
import spectra.attic.migration.biz.assential.repository.temp.AppChannelRepository;
import spectra.attic.migration.biz.assential.repository.temp.CenterRepository;
import spectra.attic.migration.biz.assential.repository.temp.PartnerGroupRepository;
import spectra.attic.migration.biz.assential.repository.temp.PartnerRepository;
import spectra.attic.migration.biz.assential.repository.temp.TeamRepository;
import spectra.attic.migration.biz.assential.repository.temp.UserRepository;

// 센터, 사용자, 앱채널, 라이선스, 사용자, 파트너
// 그룹톡, 마이톡, 등등.. 다 덤프해야함
@Service
@RequiredArgsConstructor
public class BizDumpService {

    private final SourceUserRepository sourceUserRepository;

    private final SourceAppChannelRepository sourceAppChannelRepository;

    private final SourceCenterRepository sourceCenterRepository;

    private final SourcePartnerGroupRepository sourcePartnerGroupRepository;

    private final SourcePartnerRepository sourcePartnerRepository;

    private final SourceTeamRepository sourceTeamRepository;

    // 로컬 레포지토리
    private final UserRepository userRepository;

    private final AppChannelRepository appChannelRepository;

    private final CenterRepository centerRepository;

    private final PartnerGroupRepository partnerGroupRepository;

    private final PartnerRepository partnerRepository;

    private final TeamRepository teamRepository;

    // 라이선스

    public void dump() {
        dumpUser();
        dumpAppChannel();
        dumpCenter();
        dumpPartnerGroup();
        dumpPartner();
        dumpTeam();
    }

    private void dumpTeam() {
        DBDumper<SourceTeam, Team> dumper = new DBDumper<>(
            sourceTeamRepository,
            teamRepository,
            Team::fromSource
        );
        dumper.dump();
    }

    private void dumpPartner() {
        DBDumper<SourcePartner, Partner> dumper = new DBDumper<>(
            sourcePartnerRepository,
            partnerRepository,
            Partner::fromSource
        );
        dumper.dump();
    }

    private void dumpPartnerGroup() {
        DBDumper<SourcePartnerGroup, PartnerGroup> dumper = new DBDumper<>(
            sourcePartnerGroupRepository,
            partnerGroupRepository,
            PartnerGroup::fromSource
        );
        dumper.dump();
    }

    private void dumpCenter() {
        DBDumper<SourceCenter, Center> dumper = new DBDumper<>(
            sourceCenterRepository,
            centerRepository,
            Center::fromSource
        );
        dumper.dump();
    }

    private void dumpAppChannel() {
        DBDumper<SourceAppChannel, AppChannel> dumper = new DBDumper<>(
            sourceAppChannelRepository,
            appChannelRepository,
            AppChannel::fromSource
        );
        dumper.dump();
    }

    private void dumpUser() {
        DBDumper<SourceUser, User> dumper = new DBDumper<>(
            sourceUserRepository,
            userRepository,
            User::fromSource
        );
        dumper.dump();
    }
}
