package com.gaussic.cantroller;

import com.gaussic.model.BlogEntity;
import com.gaussic.model.UserEntity;
import com.gaussic.repository.BlogRepository;
import com.gaussic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Controller
public class BlogController {
    @Autowired
    BlogRepository blogRepository;
    // 自动装配
    @Autowired
    private UserRepository userRepository;
    // 查看所有博文
    @RequestMapping(value = "/admin/blogs", method = RequestMethod.GET)
    public String showBlogs(ModelMap modelMap) {
        List<BlogEntity> blogList = blogRepository.findAll();
        modelMap.addAttribute("blogList", blogList);
        return "admin/blogs";
    }
    // 添加博文
    @RequestMapping(value = "/admin/blogs/add", method = RequestMethod.GET)
    public String addBlog(ModelMap modelMap) {
        // 查询user表中所有记录
        List<UserEntity> userList = userRepository.findAll();
        // 向jsp注入用户列表
        modelMap.addAttribute("userList", userList);
        System.out.println("toaaioajiojufisafefhai");
        return "admin/addBlog";
    }
/*
    // 添加博文，POST请求，重定向为查看博客页面
    @RequestMapping(value = "/admin/blogs/addP", method = RequestMethod.POST)
    public String addBlogPost(@ModelAttribute("blog") BlogEntity blogEntity) {
        // 打印博客标题
        System.out.println(blogEntity.getTitle());
        // 打印博客作者
        System.out.println(blogEntity.getUserByUserId().getNickname());
        // 存库
        blogRepository.saveAndFlush(blogEntity);
        // 重定向地址
        return "redirect:/admin/blogs";
    }
*/

    @RequestMapping(value = "/admin/blogs/addP", method = RequestMethod.POST)
    public String addBlogPost(HttpServletRequest request)throws Exception{
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String data_string = request.getParameter("pubDate");
        Date date = format.parse(data_string);
        BlogEntity blogEntity = new BlogEntity();
        blogEntity.setId(0);
        blogEntity.setTitle(request.getParameter("title"));
        blogEntity.setContent(request.getParameter("content"));
        blogEntity.setPubDate(date);
        Integer a = new Integer( request.getParameter("userByUserId.id"));
        UserEntity userEntity = userRepository.findOne(new Integer(5));
        userEntity.setBlogsById(null);
        blogEntity.setUserByUserId(userEntity);
        //BlogEntity blogEntity = new BlogEntity(0,request.getParameter("title"),request.getParameter("content"),date,userRepository.findOne(Integer.getInteger(request.getParameter("userByUserId.id"))));
        //BlogEntity blogEntity = new BlogEntity();
        blogRepository.saveAndFlush(blogEntity);
        return "redirect:/admin/blogs";
    }
    @RequestMapping("/admin/blogs/show/{id}")
    public String showBlog(@PathVariable("id") int id, ModelMap modelMap) {
        BlogEntity blog = blogRepository.findOne(id);
        modelMap.addAttribute("blog", blog);
        return "admin/blogDetail";
    }
    // 修改博文内容，页面
    @RequestMapping("/admin/blogs/update/{id}")
    public String updateBlog(@PathVariable("id") int id, ModelMap modelMap) {
        // 是不是和上面那个方法很像
        BlogEntity blog = blogRepository.findOne(id);
        List<UserEntity> userList = userRepository.findAll();
        modelMap.addAttribute("blog", blog);
        modelMap.addAttribute("userList", userList);
        return "admin/updateBlog";
    }


    // 修改博客内容，POST请求
    @RequestMapping(value = "/admin/blogs/updateP", method = RequestMethod.POST)
    public String updateBlogP(@ModelAttribute("blogP") BlogEntity blogEntity) {
        // 更新博客信息
        blogRepository.updateBlog(blogEntity.getTitle(), blogEntity.getUserByUserId().getId(),
                blogEntity.getContent(), blogEntity.getPubDate(), blogEntity.getId());
        blogRepository.flush();
        return "redirect:/admin/blogs";
    }
    // 删除博客文章
    @RequestMapping("/admin/blogs/delete/{id}")
    public String deleteBlog(@PathVariable("id") int id) {
        blogRepository.delete(id);
        blogRepository.flush();
        return "redirect:/admin/blogs";
    }
}