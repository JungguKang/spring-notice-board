package com.study.board.repository;

import com.study.board.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    public Optional<Member> findByUserId(String userId);
    public Optional<Member> findByUserIdAndPassword(String userId, String password);
}
