package org.geektimes.projects.user.web.controller;

import org.apache.commons.lang.StringUtils;
import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.service.UserServiceImpl;
import org.geektimes.web.mvc.controller.PageController;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.logging.Logger;

/**
 * 输出 “Hello,World” Controller
 */
@Path("/register")
public class RegisterController implements PageController {

    @Resource(lookup = "java:comp/env", name = "jndi/userService")
    private UserServiceImpl userService;

    private Logger logger = Logger.getLogger(RegisterController.class.getName());

    @Override
    @GET
    @POST
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        boolean result = this.userService.register(getUserFromRequest(request));

        return result ? "registerSuccess.jsp" : "registerFail.jsp";
    }


    protected User getUserFromRequest(HttpServletRequest request) {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phoneNumber");

        User user = new User();
        if (!StringUtils.isNotBlank(name)) {
            user.setName(name);
        }
        if (StringUtils.isNotBlank(email)) {
            user.setEmail(email);
        }
        if (StringUtils.isNotBlank(password)) {
            user.setPassword(password);
        }
        if (StringUtils.isNotBlank(phoneNumber)) {
            user.setPhoneNumber(phoneNumber);
        }

        return user;
    }

}
