package mk.ukim.finki.wp.labb.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.labb.model.Chef;
import mk.ukim.finki.wp.labb.model.Dish;
import mk.ukim.finki.wp.labb.service.ChefService;
import mk.ukim.finki.wp.labb.service.DishService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "ChefDetails", urlPatterns = "/chefDetails")
public class ChefDetailsServlet extends HttpServlet {

    private final DishService dishService;
    private final ChefService chefService;
    private final SpringTemplateEngine springTemplateEngine;

    public ChefDetailsServlet(DishService dishService, ChefService chefService, SpringTemplateEngine springTemplateEngine) {
        this.dishService = dishService;
        this.chefService = chefService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        Long chefId = Long.parseLong(req.getParameter("chefId"));
        Chef chef = chefService.findById(chefId);

        context.setVariable("chefName", chef.getFirstName() + " " + chef.getLastName());
        context.setVariable("chefBio", chef.getBio());
        context.setVariable("dishes", chef.getDishes());

        springTemplateEngine.process("chefDetails.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);
        Long chefId = -1L;
        chefId = Long.parseLong(req.getParameter("chefId"));
        String dishId = req.getParameter("dishId");

        Dish dish = dishService.findByDishId(dishId);
        Chef chef = chefService.addDishToChef(chefId, dish.getDishId());
        resp.sendRedirect("/chefDetails?chefId=" + chef.getId());
    }
}
