package study.datajpa.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Test
    public void testEntity() {
        Team teamA = Team.builder().name("teamA").build();
        Team teamB = Team.builder().name("teamB").build();
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = Member.builder()
                .userName("member1")
                .age(10)
                .team(teamA).build();

        Member member2 = Member.builder()
                .userName("member2")
                .age(20)
                .team(teamB).build();

        Member member3 = Member.builder()
                .userName("member3")
                .age(30)
                .team(teamA).build();

        Member member4 = Member.builder()
                .userName("member4")
                .age(40)
                .team(teamB).build();

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.flush();
        em.clear();

        List<Member> members = em.createQuery("SELECT m from Member m", Member.class).getResultList();

        for (Member member : members) {
            System.out.println(member);
            System.out.println(member.getTeam());
        }
    }

}