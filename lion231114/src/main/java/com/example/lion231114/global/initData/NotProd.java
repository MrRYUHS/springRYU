package com.example.lion231114.global.initData;

import com.example.lion231114.domain.article.service.ArticleService;
import com.example.lion231114.domain.attr.service.SystemService;
import com.example.lion231114.domain.member.entity.Member;
import com.example.lion231114.domain.member.service.MemberService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotProd {
    @Bean
    public ApplicationRunner initNotProd(
            SystemService systemService,
            MemberService memberService,
            ArticleService articleService
    ) {
        return args -> {
            if (systemService.isSampleDataCreated()) return;

            Member memberAdmin = memberService.join("admin", "1234").getData();
            Member memberUser1 = memberService.join("user1", "1234").getData();
            Member memberUser2 = memberService.join("user2", "1234").getData();

            articleService.write(memberAdmin, "제목1", "내용1");
            articleService.write(memberUser1, "제목2", "내용2");
            articleService.write(memberUser1, "제목3", "내용3");
        };
    }
}
