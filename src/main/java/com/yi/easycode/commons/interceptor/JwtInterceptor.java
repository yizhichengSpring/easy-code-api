package com.yi.easycode.commons.interceptor;

import cn.hutool.core.util.StrUtil;
import com.yi.easycode.commons.exception.ApiException;
import com.yi.easycode.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.yi.easycode.commons.constant.SystemConstant.*;

/**
 * @author yizhicheng
 * @ClassName JwtFilter
 * @Description JWT的拦截器
 * @Date 2020/12/20 8:42 下午
 **/
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;
    @Value("${jwt.headerKeyPrefix}")
    private String bearer;
    @Value("${jwt.headlerValuePrefix}")
    private String headlerValuePrefix;
    @Value("${spring.profiles.active}")
    private String active;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(bearer);
        log.info("token info {}",token);
        /**
         * 为了方便开发，在dev环境下，如果token 为 'easycode' 则直接放行
         */
        log.info("spring.profiles.active:{}",active);
        if (DEFAULT_PROFILE.equals(active) && DEFAULT_GLOBAL_TOKEN.equals(token)) {
            return true;
        }
        /**
         * step1 验证token是否携带
         */
        if (StrUtil.isBlank(token)) {
            throw new ApiException("token不存在");
        }
        /**
         * step2 验证是否携带bearer
         */
        if (!token.contains(headlerValuePrefix)) {
            throw new ApiException("token信息不完整");
        }
        /**
         * step3 验证token是否失效
         */
        String jwtToken = token.substring(headlerValuePrefix.length());
        log.info("substring token {}",jwtToken);
        if (!jwtUtil.isExpire(jwtToken)) {
            throw new ApiException("token已失效，请重新登录");
        }
        log.info("token 验证成功!");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
