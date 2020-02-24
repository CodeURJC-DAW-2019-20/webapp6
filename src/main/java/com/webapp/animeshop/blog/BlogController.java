package com.webapp.animeshop.blog;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webapp.animeshop.order.Order;
import com.webapp.animeshop.order.OrderRepository;
import com.webapp.animeshop.order.OrderService;
import com.webapp.animeshop.product.Product;
import com.webapp.animeshop.product.ProductAmount;
import com.webapp.animeshop.product.ProductAmountRepository;
import com.webapp.animeshop.product.ProductRepository;
import com.webapp.animeshop.product.ProductService;
import com.webapp.animeshop.user.UserComponent;
import com.webapp.animeshop.web.WebController;

@Controller
public class BlogController extends WebController{
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;
	
	@Autowired
    private OrderRepository orderRepository;
	
	@Autowired
	private UserComponent userSession;
	
	@Autowired
	private ProductAmountRepository pAmountRepository;
	
	@RequestMapping("/")
	public String showBlogs(Model model) {
		model.addAttribute("blogs", this.blogService.getBlogs());
		boolean is = false;
		//Point[] data = new Point[3];
		List<Product> products = productService.getProducts();
		//data[0] = new Point(3,3);
		//data[1] = new Point(5,3);
		//data[2] = new Point(2,4);
		
		//model.addAttribute("data",data);
		if(userSession.isLoggedUser()) {
			Order order = this.orderRepository.findNotRelated();
			Order userOrder = new Order();
			if(userSession.isLoggedUser())
				userOrder = this.orderRepository.findByStatus(userSession.getLoggedUser().getId());
			if(userOrder==null)
				userOrder = new Order(new ArrayList<>(),userSession.getLoggedUser(),0);
	    	List<ProductAmount> pAmountAux = this.pAmountRepository.findByOrderId(order.getId());
	    	for(int p=0;p<pAmountAux.size();p++) {
	    		//pAmountAux.get(p).setOrder(userOrder);
	    		is = false;
		    	if(!userOrder.getProductList().isEmpty()) {
					for(int i=0;i<userOrder.getProductList().size();i++) {
						if(userOrder.getProductList().get(i).getProduct().getName().equals(
								order.getProductList().get(p).getProduct().getName())) {
							userOrder.getProductList().get(i).setAmount(
									userOrder.getProductList().get(i).getAmount()+order.getProductList().get(p).getAmount());
							userOrder.getProductList().get(i).setTotal(
									userOrder.getProductList().get(i).getAmount(), userOrder.getProductList().get(i).getProduct().getPrice());
							userOrder.setTotal();
							this.pAmountRepository.deleteProductAmount(pAmountAux.get(p).getId());
							is = true;
						}
					}
					if(is==false) {
						pAmountAux.get(p).setOrder(userOrder);
						userOrder.getProductList().add(order.getProductList().get(p));
						this.pAmountRepository.deleteProductAmount(pAmountAux.get(p).getId());
					}
						
		    	}
		    	else {
		    		pAmountAux.get(p).setOrder(userOrder);
					userOrder.getProductList().add(order.getProductList().get(p));
					this.orderRepository.save(userOrder);
					this.pAmountRepository.deleteProductAmount(pAmountAux.get(p).getId());
		    	}
	    	}
	    	this.orderRepository.delete(order);
	    	order = new Order();
			userOrder.setTotal();
			userOrder.setUser(userSession.getLoggedUser());
			this.orderRepository.save(userOrder);
			order.setProductList(new ArrayList<ProductAmount>());
			order.resetTotal();
			this.orderRepository.save(order);
		}
		model.addAttribute("products", products);
		
		return "/index";
	}
	
	@RequestMapping("/blogspage")
	public String showBlogsPage(Model model) {
		model.addAttribute("blogspage", this.blogService.getBlogs());
		model.addAttribute("productsblog", this.productService.getProducts());
		model.addAttribute("cartSize",orderService.getCartSize());
		return "/blog";
	}
	
	@RequestMapping("/blog/{id}")
	public String showBlog(Model model, @PathVariable long id) {
		Blog blog = blogService.getBlog(id);
		model.addAttribute("blog", blog);
		model.addAttribute("product", blog.getProduct());
		model.addAttribute("popularblogs", this.blogService.getBlogs());
		model.addAttribute("productsblog", this.productService.getProducts());
		model.addAttribute("cartSize",orderService.getCartSize());
		return "/singleBlog";
	}
	
	@RequestMapping("/deleteBlog/{id}")
	public String deleteBlog(Model model, @PathVariable long id) {
		blogService.deleteBlog(id);
		return this.showBlogsPage(model);
	}
	
	@RequestMapping("/addBlog")
	public String addBlog(Model model, @RequestParam String author, @RequestParam String name, @RequestParam String text, @RequestParam String textfull, @RequestParam String idproduct) {
		Blog blog = new Blog(author, name, text, textfull, idproduct);
		blog.setProduct(productService.getProduct(Long.parseLong(idproduct)));
		this.blogService.addBlog(blog);
		return this.showBlogs(model);
	}
	
	@RequestMapping("/searchBlog")
	public String search(Model model, @RequestParam String key) {
		List<Blog> blogs = this.blogService.search(key);
		model.addAttribute("blogspage", blogs);
		return "/blog";
	}
	
	@RequestMapping("/randomBlog")
	public String random(Model model) {
		List<Blog> blogs = this.blogService.getBlogs();
		Integer rand = (int) (Math.random() * blogs.size());
		Blog blog = blogs.get(rand);
		model.addAttribute("blog", blog);
		model.addAttribute("product", blog.getProduct());
		model.addAttribute("popularblogs", this.blogService.getBlogs());
		model.addAttribute("productsblog", this.productService.getProducts());
		return "/singleBlog";
	}
	
}

