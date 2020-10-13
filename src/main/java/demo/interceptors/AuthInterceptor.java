package demo.interceptors;

import demo.entity.User;
import demo.service.UserserviceImpl;
import demo.support.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserserviceImpl userservice;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        if(request.getRequestURI().contains("login")||request.getRequestURI().contains("add_user")){
            return true;
        }
        if (null != token) {
            User login = JWTUtil.unsign(token, User.class);
//            String loginId = request.getParameter("password");
            //解密用户信息，根据用户名密码查用户。
            if (null != login) {
                User loginResult = userservice.findbyid(login.getId());
                if (loginResult != null) {
                    return true;
                } else {
                    responseMessage(response, response.getWriter());
                    return false;
                }
            } else {
                responseMessage(response, response.getWriter());
                return false;
            }
        } else {
            responseMessage(response, response.getWriter());
            return false;
        }
    }

    private void responseMessage(HttpServletResponse response, PrintWriter out) {
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(403);
        out.print("验证未通过");
        out.flush();
        out.close();
    }
}
