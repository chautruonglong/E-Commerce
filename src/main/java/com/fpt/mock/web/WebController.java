package com.fpt.mock.web;

import com.fpt.mock.dto.IndexProductDto;
import com.fpt.mock.dto.OrderProductDto;
import com.fpt.mock.entity.Customer;
import com.fpt.mock.service.OrderService;
import com.fpt.mock.service.ProductService;
import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class WebController {

    private final ProductService productService;
    private final OrderService orderService;

    @GetMapping(value = {"/home", "/index", "/"})
    public String index(Model model,
                        @RequestParam(defaultValue = "40") int limit,
                        @RequestParam(defaultValue = "0") int page,
                        @Nullable @RequestParam String category,
                        @Nullable @RequestParam String q) {
        try {
            List<IndexProductDto> products;

            if(category != null && !category.equals("")) {
                products = productService.getIndexProducts(limit, page, category);
            }
            else if(q != null && !q.equals("")) {
                products = productService.getIndexProductsByName(limit, page, q);
            }
            else {
                products = productService.getIndexProducts(limit, page);

            }

            model.addAttribute("products", products);
            return "Index";
        }
        catch(Exception exception) {
            model.addAttribute("exception", exception);
            return "NotFound";
        }
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        try {
            return "Profile";
        }
        catch(Exception exception) {
            model.addAttribute("exception", exception);
            return "NotFound";
        }
    }

    @GetMapping("/orders")
    public String orders(Model model,
                         HttpSession session,
                         @RequestParam(defaultValue = "20") int limit,
                         @RequestParam(defaultValue = "0") int page) {
        try {
            Customer customer = (Customer) session.getAttribute("customer");
            List<OrderProductDto> products = orderService.getOrderProductByCustomerId(customer.getId(), limit, page);
            model.addAttribute("products", products);

            return "Orders";
        }
        catch(Exception exception) {
            model.addAttribute("exception", exception);
            return "NotFound";
        }
    }

}
