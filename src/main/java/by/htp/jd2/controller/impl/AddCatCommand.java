package by.htp.jd2.controller.impl;

import by.htp.jd2.controller.Command;
import by.htp.jd2.entity.Breed;
import by.htp.jd2.entity.Cat;
import by.htp.jd2.entity.Gender;
import by.htp.jd2.entity.User;
import by.htp.jd2.service.CatService;
import by.htp.jd2.service.ServiceException;
import by.htp.jd2.service.ServiceFactory;
import by.htp.jd2.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static by.htp.jd2.util.ConstantPool.*;

public class AddCatCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        ServiceFactory factory = ServiceFactory.getInstance();
        CatService catService = factory.getCatService();
        try {
            Cat cat = new Cat(request.getParameter("name"), new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter(BIRTH_DATE_PARAMETER)),
                    (User)session.getAttribute(USER_ATTRIBUTE), new Breed(Integer.parseInt(request.getParameter("breed"))),
                    Gender.getById(Integer.parseInt(request.getParameter("gender"))), request.getParameter("description"));
            Part filePart = request.getPart("catPhoto");
            String fileName = filePart.getSubmittedFileName();
            for (Part part : request.getParts()) {
                part.write(PHOTO_SAVE_PATH + fileName);
            }
            catService.addCat(cat, fileName);
            response.sendRedirect(MAIN_PAGE_REDIRECT);
        } catch (ParseException e) {
            response.sendRedirect(ERROR_PAGE_REDIRECT + "&" + ERROR_MS_PARAMETER + "=" + VALIDATION_ERROR_MS);
        } catch (ServiceException e) {
            response.sendRedirect(ERROR_PAGE_REDIRECT + "&" + ERROR_MS_PARAMETER + "=" + BASIC_ERROR_MS);
        }
    }
}
