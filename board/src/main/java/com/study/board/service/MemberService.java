package com.study.board.service;

import com.study.board.entity.Member;
import com.study.board.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public Integer register(Member member){
        memberRepository.findByUserId(member.getUserId())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });

        memberRepository.save(member);
        return member.getId();
    }

    public Boolean login(String userId, String password){
        return memberRepository.findByUserIdAndPassword(userId, password)
                .isPresent();
    }


    public Member findMember(Integer id) {
        return memberRepository.findById(id).orElse(null);
    }
}
