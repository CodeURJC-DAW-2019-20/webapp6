package com.webapp.animeshop.blog;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
	
	@GetMapping("/blog/{id}")
	public ResponseEntity<Blog> showBlog(@PathVariable long id) {
		Blog blog = this.blogRepository.findById(id);
		if (blog != null) {
			return new ResponseEntity<>(blog,HttpStatus.OK);
		}
		return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/blog/{id}")
	public Blog deleteBlog(@PathVariable long id) {
		blogService.deleteBlog(id);
		return this.deleteBlog(id);
	}
	
	@PostMapping("/blog")
	@ResponseStatus(HttpStatus.CREATED)
	public Blog addBlog(@RequestBody Blog blog) {
		blog.setImage("/img/product/newblog.png");
		blog.setProduct(productService.getProduct(blog.getProduct().getId()));
		this.blogService.addBlog(blog);
		return blog;
	}
	
	@GetMapping("/search")
	public ResponseEntity<Collection<Blog>> search(@RequestBody String key) {
		List<Blog> blogs = this.blogService.search(key);
		if(blogs.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Collection<Blog>>(blogs,HttpStatus.OK);
	}
}
