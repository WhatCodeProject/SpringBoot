package whatcode.study.whatcode.domain.memberRoom.memberRoomService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whatcode.study.whatcode.domain.member.member.Member;
import whatcode.study.whatcode.domain.member.member.MemberRepository;
import whatcode.study.whatcode.domain.memberRoom.dtos.RoomFindRequestDto;
import whatcode.study.whatcode.domain.memberRoom.memberRoom.MemberRoom;
import whatcode.study.whatcode.domain.memberRoom.memberRoom.MemberRoomRepository;
import whatcode.study.whatcode.domain.memberTeam.dtos.TeamFindRequestDto;
import whatcode.study.whatcode.domain.memberTeam.memberTeam.MemberTeam;
import whatcode.study.whatcode.domain.memberTeam.memberTeam.MemberTeamRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberRoomService {
    private final MemberRoomRepository memberRoomRepository;
    private final MemberRepository memberRepository;

    public List<MemberRoom> findTeamsByMember(RoomFindRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.getEmail());
        return memberRoomRepository.findByMember(member);
    }
}
