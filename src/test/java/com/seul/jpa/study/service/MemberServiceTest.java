package com.seul.jpa.study.service;

import com.seul.jpa.study.domain.Address;
import com.seul.jpa.study.domain.Member;
import com.seul.jpa.study.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // spring boot 를 띄운 상태에서 테스트를 하려면 있어야 한 (ex.@Autowired 사용 가능)
@Transactional // 테스트 데이터 롤백을 위해
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
//    @Rollback(false) // 롤백을 하지 않 커밋
    void 회원_가입() {
        // given
        Address address = Address.of("1", "2", "3");
        Member member = Member.of("park", address, new ArrayList<>());

        // when
        long savedId = memberService.join(member);

        // then
/**
 * @Transactional 로 인해 롤백을 하기 때문에 쿼리를 확인 할 수 없는데
 * flush 를 해주게 되면 쿼리를 실행하고 롤백을 하기 때문에 쿼리 확인이 가능하다
 * **/
//        em.flush();

        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    void 중복_회원_예외() {
        // given
        Address address1 = Address.of("1", "2", "3");
        Member member1 = Member.of("park", address1, new ArrayList<>());

        Address address2 = Address.of("1", "2", "3");
        Member member2 = Member.of("park", address2, new ArrayList<>());

        // when
        memberService.join(member1);

        //then
        assertThrows(IllegalStateException.class, () -> memberService.join(member2), "이미 존재하는 회원입니다.");
    }
}