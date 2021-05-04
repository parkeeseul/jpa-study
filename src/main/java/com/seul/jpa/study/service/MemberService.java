package com.seul.jpa.study.service;

import com.seul.jpa.study.domain.Member;
import com.seul.jpa.study.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor // final 이 있는 필드만 가지고 생성자를 만듬.
public class MemberService {

    private final MemberRepository memberRepository;

    // 생성자가 하나만 있는 경우 @Autowired 이 없더라도 spring이 자동으로 주입을 해준다. (@RequiredArgsConstructor 사용으로 생성자 자동으로 만들어 줌)
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /**
     *  회원 가입
     */
    @Transactional
    public long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     *  회원 전체 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(long memberId) {
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(long id, String name) {
        Member member = findOne(id);
        member.setName(name);
    }
}
