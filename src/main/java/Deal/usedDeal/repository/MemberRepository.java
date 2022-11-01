package Deal.usedDeal.repository;


import Deal.usedDeal.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository  {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class,id);
    }

    public void delete(Long id){
        Member findMember = em.find(Member.class,id);
        em.remove(findMember);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m ",Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();
    }

    public Member findByLoginId(String loginId){

        Member findMember = new Member();
        try{
            em.createQuery("select m from Member m where m.loginId =:loginId",Member.class)
                    .setParameter("loginId",loginId)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }


        return findMember;
    }
}
