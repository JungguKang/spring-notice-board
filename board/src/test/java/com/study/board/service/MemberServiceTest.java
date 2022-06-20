package com.study.board.service;

import com.study.board.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberService memberService;

    private Member getTestMember(){
        Member member = new Member();
        member.setName("강정구");
        member.setUserId("justin403502");
        member.setPassword("1234321");
        member.setEmailAddress("justin403502@naver.com");
        member.setPhoneNumber("01057610716");

        return member;
    }

    @Test
    public void registrationTest(){
        //given
        Member member = getTestMember();

        //when
        Integer saveId = memberService.register(member);

        //then
        Member findMember = memberService.findMember(saveId);
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    public void loginOKTest(){
        //given
        Member member = getTestMember();
        
        //when
        memberService.register(member);
        Boolean result = memberService.login(member.getUserId(), member.getPassword());
        
        //then
        assertThat(result).isTrue();
    }

    @Test
    public void loginNOKTest(){
        //given
        Member member = getTestMember();

        //when
        memberService.register(member);
        Boolean result = memberService.login(member.getUserId()+"1", member.getPassword());

        //then
        assertThat(result).isFalse();
    }

}