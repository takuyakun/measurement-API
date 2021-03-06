package com.example.sample.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SampleController {

    @GetMapping("/")
    public ModelAndView someTemplate() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("home");
        return mav;
    }

    @RequestMapping("/sample")
    public String getSample(final HttpServletRequest request, final HttpServletResponse response
        , @CookieValue(name = "visited", required = false, defaultValue = "0") String visited) {
        int a = Integer.parseInt(visited);
        int b = a + 1;
        String value = Integer.toString(b);
        setCookie(request, response, "/sample", "visited", value, 10);
        return value;
    }

    public static void setCookie(final HttpServletRequest request, final HttpServletResponse response,
            final String path, final String name, final String value, final int maxAge) {
        final Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath(path);
        // httpsで稼働している環境であればCookieが暗号化されるようSecure属性をつける
        if ("https".equals(request.getScheme())) {
            cookie.setSecure(true);
        }
        response.addCookie(cookie);
    }

    //public String readCookie(
            //@CookieValue(name = "visited") String visited) {
        //return visited;
    //}
}