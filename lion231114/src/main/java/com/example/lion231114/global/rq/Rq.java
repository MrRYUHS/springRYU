package com.example.lion231114.global.rq;

import com.example.lion231114.domain.member.entity.Member;
import com.example.lion231114.domain.member.service.MemberService;
import com.example.lion231114.global.rsData.RsData;
import com.example.lion231114.util.Ut;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@RequestScope
@Component
@Getter
@RequiredArgsConstructor
@Slf4j
public class Rq {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    private final MemberService memberService;
    private User user;
    private Member member;

    @PostConstruct
    public void init() {
        // 현재 로그인한 회원의 인증정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof User) {
            this.user = (User) authentication.getPrincipal();
        }
    }

    public String redirect(String path, RsData<?> rs) {
        return redirect(path, rs.getMsg());
    }

    public String redirect(String path, String msg) {
        if (msg == null)
            return "redirect:" + path;

        boolean containsTtl = msg.contains(";ttl=");

        if (containsTtl) {
            msg = msg.split(";ttl=", 2)[0];
        }

        msg = URLEncoder.encode(msg, StandardCharsets.UTF_8);
        msg += ";ttl=" + (new Date().getTime() + 1000 * 5);

        return "redirect:" + path + "?msg=" + msg;
    }

    private String getMemberUsername() {
        return user.getUsername();
    }

    public boolean isLogined() {
        return user != null;
    }

    public Member getMember() {
        if (!isLogined()) {
            return null;
        }

        if (member == null)
            member = memberService.findByUsername(getMemberUsername()).get();

        return member;
    }

    public void setSessionAttr(String name, Object value) {
        req.getSession().setAttribute(name, value);
    }

    public <T> T getSessionAttr(String name) {
        return (T) req.getSession().getAttribute(name);
    }

    public void removeSessionAttr(String name) {
        req.getSession().removeAttribute(name);
    }

    public boolean isAdmin() {
        if (!isLogined()) {
            return false;
        }

        return user.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    public String historyBack(RsData<?> rs) {
        return historyBack(rs.getMsg());
    }

    public String historyBack(String msg) {
        resp.setStatus(400);
        req.setAttribute("msg", msg);

        return "global/js";
    }
    public String historyBack(Exception ex) {
        String exStr = Ut.exception.toString(ex);
        req.setAttribute("exStr", exStr);

        log.debug(exStr);

        return historyBack(ex.getMessage());
    }

    public String redirectOrBack(String url, RsData<?> rs) {
        if (rs.isFail()) return historyBack(rs);
        return redirect(url, rs);
    }
}