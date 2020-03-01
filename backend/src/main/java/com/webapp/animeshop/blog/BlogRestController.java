package com.webapp.animeshop.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.animeshop.product.ProductService;

@RestController
@RequestMapping("/api")
public class BlogRestController {

	
	@Autowired
	public BlogService blogService;
	
	@Autowired
	public BlogRepository blogRepository;
	
	@Autowired
	public ProductService productService;
	
	@GetMapping("/")
	public List<Blog> showBlogs() {
		
		return blogService.getBlogs();
	}
	
	@GetMapping("/showBlog/{id}")
	public Blog showBlog(@PathVariable long id) {
		
		return this.blogService.getBlog(id);
	}
	
	@DeleteMapping("/deleteBlog/{id}")
	public Blog deleteBlog(@PathVariable long id) {
		blogService.deleteBlog(id);
		return this.deleteBlog(id);
	}
	
	@PostMapping("/addBlog")
	public Blog addBlog(@RequestParam String author , @RequestParam String name, @RequestParam String text, @RequestParam String textfull, @RequestParam String idproduct) {
		Blog blog = new Blog(author, name, text, textfull, idproduct);
		blog.setImage("/img/product/newblog.png");
		blog.setProduct(productService.getProduct(Long.parseLong(idproduct)));
		this.blogService.addBlog(blog);
		return blogService.addBlog(blog);
	}
}
