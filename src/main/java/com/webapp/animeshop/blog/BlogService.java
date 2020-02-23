package com.webapp.animeshop.blog;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.animeshop.product.Product;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	public BlogService(BlogRepository blogRepository) {
    	this.blogRepository = blogRepository;
    }
	
	public Blog getBlog(long blogId) {
		Blog blog = this.blogRepository.findById(blogId);
		if (blog != null) {
			return blog;
		}
		return null;
	}
	
	public Blog getBlogByName(String name) {
		Blog blog = this.blogRepository.findByName(name);
		if(blog != null) {
			return blog;
		}
		return null;
	}
	
	public List<Blog> getBlogs(){
		return this.blogRepository.findAll();
	}
	
	public Blog addBlog(Blog blog) {
		return this.blogRepository.save(blog);
	}
	
	public void deleteBlog(long blogId) {
		Blog blog = this.blogRepository.findById(blogId);
		if(blog != null) {
			this.blogRepository.delete(blog);
		}
	}
	
	public List<Blog> search(String key){
		List<Blog> blogs = this.blogRepository.findAll();
		List<Blog> aux = new ArrayList<Blog>();
		for(int i = 0; i < blogs.size(); i++) {
			if(blogs.get(i).getName().toLowerCase().contains(key.toLowerCase()))
				aux.add(blogs.get(i));
		}
		return aux;
	}
}

