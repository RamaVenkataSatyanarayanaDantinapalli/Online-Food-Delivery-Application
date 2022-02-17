package com.cg.fds.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fds.dto.RestaurantBasicInfoDTO;
import com.cg.fds.dto.RestaurantSavePostDTO;
import com.cg.fds.entities.Restaurant;
import com.cg.fds.exceptions.InvalidLoginException;
import com.cg.fds.exceptions.RestaurantNotFoundException;
import com.cg.fds.service.IRestaurantServiceImpl;

@RestController
@RequestMapping("/app")
@Validated
public class RestaurantRestController {
	
	@Autowired
	IRestaurantServiceImpl restservice;
	
	@PostMapping("/restaurant")
	public ResponseEntity<RestaurantBasicInfoDTO> addRestaurant(@RequestBody @Valid RestaurantSavePostDTO r,HttpServletRequest request) throws InvalidLoginException
	{
		HttpSession session= request.getSession(false);
		if(session!=null) {
		System.out.println("--->> "+r.getRestaurantName()+" - "+r.getManagerName()+" - "+r.getContactNumber());
		RestaurantBasicInfoDTO dto=restservice.addRestaurant(r);
		return new ResponseEntity<RestaurantBasicInfoDTO>(dto,HttpStatus.OK);
		}
			else {
				throw new InvalidLoginException();
			}
	}
	
	@PutMapping("/updaterestaurant")
	public String updateRestaurant(@RequestBody Restaurant restarant,HttpServletRequest request) throws InvalidLoginException{
		HttpSession session= request.getSession(false);
		if(session!=null) {

		Restaurant res=restservice.updateRestaurant(restarant);
		if(res!=null) {
			return "Restaurant "+res.getRestaurantName()+" Details Updated Successfully\n\n"+res+"\n\nPlease Note the Restaurant ID for Further References.";
		}
		else {
			return "Restaurant "+res.getRestaurantName()+" Details Not Updated Successfully";
		}
		}
			else {
				throw new InvalidLoginException();
			}
	}
	
	@GetMapping("/removerestaurant")
	public String removeRestaurant(@RequestParam int restaurantId,HttpServletRequest request) throws RestaurantNotFoundException, InvalidLoginException
	{
		HttpSession session= request.getSession(false);
			if(session!=null) {
		String status=restservice.removeRestaurant(restaurantId);
		if(status.equals("Success")) {
			return "Restaurant "+restaurantId+" Removed Successfully";
		}
		else {
			throw new RestaurantNotFoundException(restaurantId);
		}
	}
	else {
		throw new InvalidLoginException();
	}	
	}
	
	@GetMapping("/viewallrestaurants")
	public List<Restaurant> getAllRestaurant(HttpServletRequest request) throws RestaurantNotFoundException, InvalidLoginException
	{
		HttpSession session= request.getSession(false);
		if(session!=null) {
				List<Restaurant> res= restservice.viewAllRestaurants();
		if(res.size()!=0) {
			return res;
		}
		else {
			throw new RestaurantNotFoundException();
		}
		}
			else {
				throw new InvalidLoginException();
			}
		}

	
	@GetMapping("/viewrestaurantbyid")
	public List<Restaurant> viewRestaurant(@RequestParam int restaurantId,HttpServletRequest request) throws RestaurantNotFoundException, InvalidLoginException
	{
		HttpSession session= request.getSession(false);
		if(session!=null) {
		List<Restaurant> res=restservice.viewRestaurantByID(restaurantId);
		if(res.size()!=0) {
			return res;
		}
		else {
		throw new RestaurantNotFoundException(restaurantId);
		}
	}
		else {
			throw new InvalidLoginException();
		}
	}
	
	@GetMapping("/viewrestaurantbyname")
	public List<Restaurant> viewRestaurantByName(@RequestParam String restaurantName,HttpServletRequest request) throws RestaurantNotFoundException, InvalidLoginException
	{
		HttpSession session= request.getSession(false);
		if(session!=null) {
		List<Restaurant> res=restservice.viewRestaurantByName(restaurantName);
		if(res.size()!=0) {
			return res;
		}
		throw new RestaurantNotFoundException(restaurantName);
	}
	else {
		throw new InvalidLoginException();
	}
}
	@GetMapping("/viewrestaurantbypincode")
	public List<Restaurant> viewRestaurantByPincode(@RequestParam int pincode,HttpServletRequest request) throws RestaurantNotFoundException, InvalidLoginException
	{
		HttpSession session= request.getSession(false);
		if(session!=null) {
		List<Restaurant> res=restservice.viewRestaurantByPincode(pincode);
		
		if(res.size()!=0) {
			return res;
		}
		throw new RestaurantNotFoundException(pincode);
		}
		else {
			throw new InvalidLoginException();
		}
	}
	
	@GetMapping("/viewrestaurantbycity")
	public List<Restaurant> viewRestaurantByCity(@RequestParam String cityName,HttpServletRequest request) throws RestaurantNotFoundException, InvalidLoginException
	{
		HttpSession session= request.getSession(false);
		if(session!=null) {
		List<Restaurant> res=restservice.viewRestaurantByCity(cityName);
		if(res.size()!=0) {
			return res;
		}
		else {
			throw new RestaurantNotFoundException(cityName);
		}
		}
		else {
			throw new InvalidLoginException();
		}
	}
	
	@GetMapping("/viewrestaurantbyitemname")
	public List<Restaurant> viewRestaurantByItemName(@RequestParam String ItemName,HttpServletRequest request) throws RestaurantNotFoundException, InvalidLoginException
	{
		HttpSession session= request.getSession(false);
		if(session!=null) {
		List<Restaurant> res=restservice.viewRestaurantByItemName(ItemName);
		if(res.size()!=0) {
			return res;
		}
		else {
			throw new RestaurantNotFoundException(ItemName);
		}
		}
		else {
			throw new InvalidLoginException();
		}
	}
	
	
	
	@GetMapping("/sortrestaurantsbyid")
	public List<Restaurant> sortItemsById(HttpServletRequest request) throws RestaurantNotFoundException, InvalidLoginException {
		HttpSession session= request.getSession(false);
		if(session!=null) {
		List<Restaurant> res = restservice.SortRestaurantById();
		if(res.size()!=0) {
			return res;
		}
		else {
			throw new RestaurantNotFoundException();
		}
		}
		else {
			throw new InvalidLoginException();
		}
	}
	
	@GetMapping("/sortrestaurantsbyname")
	public List<Restaurant> SortItemsByName(HttpServletRequest request) throws RestaurantNotFoundException, InvalidLoginException {
		HttpSession session= request.getSession(false);
		if(session!=null) {
		List<Restaurant> res = restservice.SortRestaurantByName();
		if(res.size()!=0) {
			return res;
		}
		else {
			throw new RestaurantNotFoundException();
		}
		}
		else {
			throw new InvalidLoginException();
		}
	}
	
	@GetMapping("/sortrestaurantsbycity")
	public List<Restaurant> SortItemsByCost(HttpServletRequest request) throws RestaurantNotFoundException, InvalidLoginException{
		HttpSession session= request.getSession(false);
		if(session!=null) {
		List<Restaurant> res = restservice.SortRestaurantByCity();
		if(res.size()!=0) {
			return res;
		}
		else {
			throw new RestaurantNotFoundException();
		}
		}
		else {
			throw new InvalidLoginException();
		}
	}
	
}
