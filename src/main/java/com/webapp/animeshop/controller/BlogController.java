package com.webapp.animeshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webapp.animeshop.model.Blog;
import com.webapp.animeshop.service.BlogService;
import com.webapp.animeshop.service.ProductService;

@Controller
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/")
	public String showBlogs(Model model) {
		model.addAttribute("blogs", this.blogService.getBlogs());
		return "/index";
	}
	
	@RequestMapping("/blogspage")
	public String showBlogsPage(Model model) {
		model.addAttribute("blogspage", this.blogService.getBlogs());
		model.addAttribute("productsblog", this.productService.getProducts());
		return "/blog";
	}
	
	@RequestMapping("/blog/{id}")
	public String showBlog(Model model, @PathVariable long id) {
		Blog blog = blogService.getBlog(id);
		model.addAttribute("blog", blog);
		model.addAttribute("popularblogs", this.blogService.getBlogs());
		return "/singleBlog";
	}
	
	@RequestMapping("deleteBlog/{id}")
	public String deleteBlog(Model model, @PathVariable long id) {
		blogService.deleteBlog(id);
		return this.showBlogs(model);
	}
	
	@RequestMapping("/addBlog")
	public String addBlog(Model model, @RequestParam String author, @RequestParam String name, @RequestParam String text, @RequestParam String textfull, @RequestParam String idproduct) {
		Blog blog = new Blog(author, name, text, textfull, idproduct);
		blog.setProduct(productService.getProduct(Long.parseLong(idproduct)));
		this.blogService.addBlog(blog);
		return this.showBlogs(model);
	}
	
}

