package com.cg.fds.web;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fds.dto.OrderBasicInfoDTO;
import com.cg.fds.dto.OrderSavePostDTO;
import com.cg.fds.entities.OrderDetails;
import com.cg.fds.exceptions.InvalidLoginException;
import com.cg.fds.exceptions.OrderNotFoundException;
import com.cg.fds.exceptions.RestaurantNotFoundException;
import com.cg.fds.service.IOrderService;


@RestController
@RequestMapping("/order")
@Validated
public class OrderRestController {
	@Autowired
	IOrderService service;
	
	
	@PostMapping("/dtoOrder")
	public ResponseEntity<OrderBasicInfoDTO> addOrder(@RequestBody @Valid OrderSavePostDTO o,HttpServletRequest request) throws InvalidLoginException
	{
		HttpSession session= request.getSession(false);
		if(session!=null)
		{
			
		System.out.println(o.getOrderStatus());
		OrderBasicInfoDTO dto=service.addOrders(o);
		return new ResponseEntity<OrderBasicInfoDTO>(dto,HttpStatus.OK);
		}
		else
		{
			throw new InvalidLoginException();
		}
	}
	
	
	
	@PostMapping("/save")
	public boolean saveOrder(@RequestBody OrderDetails order)
	{
		service.saveOrder(order);
		return true;
	}
	
	@DeleteMapping("/del/{orderId}")
	public boolean removeOrder(@PathVariable int orderId,HttpServletRequest request) throws OrderNotFoundException, InvalidLoginException
	{
		HttpSession session= request.getSession(false);
		if(session!=null) {
			
		
		OrderDetails od=service.getOrderById(orderId);
		if(od!=null)
		{
			service.deleteOrderById(orderId);
			return true;
		}
		else
		{
			throw new OrderNotFoundException();
		}
		}
		else {
			throw new InvalidLoginException();
		}
	}
	@GetMapping("/checkOrder/{orderId}")
	public boolean checkOrderById(@PathVariable int orderId,HttpServletRequest request) throws OrderNotFoundException, InvalidLoginException
	{
		HttpSession session= request.getSession(false);
		if(session!=null) {
		OrderDetails od=service.getOrderById(orderId);
		if(od!=null)
		{
		return true;
		}
		else
			throw new OrderNotFoundException();
		}
		else
		{
			throw new InvalidLoginException();
		}
	}
	
	@GetMapping("/getOrder/{orderId}")
	public OrderDetails getOrderById(@PathVariable int orderId,HttpServletRequest request) throws OrderNotFoundException, InvalidLoginException
	{
		HttpSession session= request.getSession(false);
		if(session!=null) {
		OrderDetails od=service.getOrderById(orderId);
		if(od!=null)
		{
			return od;
		}
		else
		{
			throw new OrderNotFoundException();
		}
		}
		else {
			throw new InvalidLoginException();
		}
		
	}
	
	@GetMapping("/orders")
	public List<OrderDetails> getAllOrders(HttpServletRequest request) throws InvalidLoginException
	{ 
		HttpSession session= request.getSession(false);
	
	    if(session!=null) {
		return service.getAllOrders();
	    }
	    else
	    {
	    	throw new InvalidLoginException();
	    }
	}
	
	@GetMapping("/dateorders")
	public List<OrderDetails> getAllOrdersByDate(@RequestParam String date,HttpServletRequest request)throws InvalidLoginException
	{
		HttpSession session= request.getSession(false);
		if(session!=null) {
		List<OrderDetails> od=service.getAllOrdersByDate(LocalDate.parse(date));
		return od;
		}
		else
		{
			throw new InvalidLoginException();
		}
	}
	
	
	@GetMapping("/AllOrderStatus")
	public List<Integer> getAllOrdersByStatus(@RequestParam @Valid String status,HttpServletRequest request)throws InvalidLoginException
	{
		HttpSession session= request.getSession(false);
		if(session!=null) {
		List<Integer> od=service.getAllOrders(status);
		return od;
		}
		else
		{
			throw new InvalidLoginException();
		}
	}
	
	@PutMapping("/update")
	public String updateOrderStatus(@RequestParam int orderId,@RequestParam @Valid String status,HttpServletRequest request)throws InvalidLoginException
	{
		HttpSession session= request.getSession(false);
		if(session!=null) {
		OrderDetails list= service.updateStatus(orderId, status);
		
		if(list!=null)
		{
			return "updated successfully";
		}
		else
		{
			return "not updated";
		}
		}
		else
		{
			throw new InvalidLoginException();
		}
		
	}
	
	@GetMapping("/sortOrderById")
	public List<OrderDetails> sortOrdersById(HttpServletRequest request) throws OrderNotFoundException,InvalidLoginException
	{
		HttpSession session= request.getSession(false);
		if(session!=null) {
		List<OrderDetails> orders=service.SortOrdersById();
		if(orders.size()!=0)
		{
			return orders;
		}
		else
		{
			throw new OrderNotFoundException();
		}
		}
		else {
			throw new InvalidLoginException();
		}
	}
	
	@GetMapping("/sortOrderByDate")
	public List<OrderDetails> sortOrdersByDate(HttpServletRequest request) throws InvalidLoginException
	{
		HttpSession session= request.getSession(false);
		if(session!=null) {
		List<OrderDetails> order=service.SortOrdersByDate();
		if(order.size()!=0)
		{
			return order;
		}
		else
		{
			return null;
		}
		}
		else {
			throw new InvalidLoginException();
		}
	}
	
	@GetMapping("/countOfOrdersByDate")
	public int countOrdersByDate(@RequestParam String date,HttpServletRequest request) throws InvalidLoginException
	{
		HttpSession session= request.getSession(false);
		if(session!=null) {
		return service.countOrdersByDate(LocalDate.parse(date));
		}
		else {
			throw new InvalidLoginException();
		}
	}
	/*@GetMapping("/city")
	 * {
	 * 
	public int getAllOrdersByCityName(@RequestParam String cityName)
	{
		return service.getAllOrdersByCityName(cityName);
	}
	
	*/
	
	
	
	
	
	

}
