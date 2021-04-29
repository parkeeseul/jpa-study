package com.seul.jpa.study.repository;

import com.seul.jpa.study.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

//    @PersistenceContext //  == @Autowired (Spring Data JPA 를 사용하면 @Autowired 지원)
//    private EntityManager em;

//    @PersistenceUnit
//    private EntityManagerFactory emf;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("SELECT m FROM Member m", Member.class) // 객체를 대상으로 쿼리
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("SELECT m FROM Member m WHERE m.name = :NAME", Member.class)
                .setParameter("NAME", name)
                .getResultList();
    }
}
