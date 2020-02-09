package com.webapp.animeshop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.animeshop.model.Blog;
import com.webapp.animeshop.repositories.BlogRepository;

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
}

