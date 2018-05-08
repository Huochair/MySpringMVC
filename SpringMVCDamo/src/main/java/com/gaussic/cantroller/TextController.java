package com.gaussic.cantroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public class TextController {
    @RequestMapping(value = "/admin/blogs/indexdggs", method = RequestMethod.GET)
    public ModelAndView showBlog() {
        String x = "fsadfasdfsadf";
        ModelAndView nao = new ModelAndView("/WEB-INF/pages/admin/index.html","x",x);
        return nao;
    }

}
