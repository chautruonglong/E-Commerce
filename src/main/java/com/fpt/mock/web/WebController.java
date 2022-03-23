package com.fpt.mock.web;

import com.fpt.mock.dto.IndexProductDto;
import com.fpt.mock.service.ProductService;
import java.util.List;
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

    @GetMapping(value = {"/home", "/", ""})
    public String index(Model model,
                        @RequestParam(defaultValue = "40") int limit,
                        @RequestParam(defaultValue = "0") int page,
                        @Nullable @RequestParam String category) {
        try {
            List<IndexProductDto> products = null;

            if(category == null || category.equals("")) {
                products = productService.getIndexProducts(limit, page);
            }
            else {
                products = productService.getIndexProducts(limit, page, category);
            }

            model.addAttribute("products", products);
            return "index";
        }
        catch(Exception exception) {
            model.addAttribute("exception", exception);
            return "notFound";
        }
    }

}
