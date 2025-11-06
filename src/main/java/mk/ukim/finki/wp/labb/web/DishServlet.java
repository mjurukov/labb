package mk.ukim.finki.wp.labb.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.labb.model.Chef;
import mk.ukim.finki.wp.labb.service.ChefService;
import mk.ukim.finki.wp.labb.service.DishService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "DishServlet", urlPatterns = "/dish")
public class DishServlet extends HttpServlet {

    private final DishService dishService;
    private final ChefService chefService;
    private final SpringTemplateEngine springTemplateEngine;

    public DishServlet(DishService dishService, ChefService chefService, SpringTemplateEngine springTemplateEngine) {
        this.dishService = dishService;
        this.chefService = chefService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String chefId = request.getParameter("chefId");
        response.sendRedirect("/dish?chefId=" + chefId);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(request, response);
        WebContext context = new WebContext(webExchange);

        Long chefId = Long.parseLong(request.getParameter("chefId"));
        Chef chef = chefService.findById(chefId);

        context.setVariable("dishes", dishService.listDishes());
        context.setVariable("chef", chef);

        springTemplateEngine.process("dishesList.html", context, response.getWriter());
    }
}
