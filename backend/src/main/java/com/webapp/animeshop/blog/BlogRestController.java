package com.webapp.animeshop.blog;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.animeshop.product.ProductService;

@RestController
public class BlogRestController {

	
	@Autowired
	public BlogService blogService;
	
	@Autowired
	public BlogRepository blogRepository;
	
	@Autowired
	public ProductService productService;
	
	@GetMapping("/api/blogs")
	public ResponseEntity<List<Blog>> showBlogs(@RequestParam (required = false) String key) {
		if(key!=null) {
			if(this.productService.search(key).isEmpty())
				return new ResponseEntity<>(blogRepository.findAll(),HttpStatus.NOT_FOUND);
			return new ResponseEntity<>(blogService.search(key),HttpStatus.OK);
		}
		return new ResponseEntity<>(blogRepository.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/api/blogs/{id}")
	public ResponseEntity<Blog> showBlog(@PathVariable long id) {
		Blog blog = this.blogRepository.findById(id);
		if (blog != null) {
			return new ResponseEntity<>(blog,HttpStatus.OK);
		}
		return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/api/blogs/{id}")
	public List<Blog> deleteBlog(@PathVariable long id) {
		blogService.deleteBlog(id);
		return this.blogRepository.findAll();
	}
	
	@PostMapping("/api/blogs")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Blog> addBlog(@RequestBody Blog blog) {
		blog.setImage("/img/product/newblog.png");
		blog.setProduct(productService.getProduct(blog.getProduct().getId()));
		this.blogService.addBlog(blog);
		return this.blogRepository.findAll();
	}

	@GetMapping("/new/api/blogs")
	void blogsG(HttpServletResponse response) throws IOException {
	  response.sendRedirect("/api/blogs");
	}

	@GetMapping("/new/api/blogs/{id}")
	void blogG(HttpServletResponse response) throws IOException {
	  response.sendRedirect("/api/blogs/{id}");
	}

	@PostMapping("/new/api/blogs")
	void blogP(HttpServletResponse response) throws IOException {
	  response.sendRedirect("/api/blogs");
	}

	@RequestMapping("/new/api/blogs/{id}")
	void blogD(HttpServletResponse response) throws IOException {
	  response.sendRedirect("/api/blogs/{id}");
	}
}
