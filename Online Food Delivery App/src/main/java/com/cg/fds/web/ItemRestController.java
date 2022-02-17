package com.cg.fds.web;

import java.util.Collections;
import java.util.Comparator;
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

import com.cg.fds.dto.ItemBasicInfoDTO;
import com.cg.fds.dto.ItemSavePostDTO;

import com.cg.fds.entities.Item;
import com.cg.fds.exceptions.InvalidLoginException;
import com.cg.fds.exceptions.ItemNotFoundException;
import com.cg.fds.service.IItemServiceImpl;

@RestController
@RequestMapping("/app")
@Validated
public class ItemRestController {
	
	@Autowired
	IItemServiceImpl itemservice;
	
	
	@PostMapping("/additem")
	public ResponseEntity<ItemBasicInfoDTO> addItem(@RequestBody @Valid ItemSavePostDTO item,HttpServletRequest request) throws InvalidLoginException {
		HttpSession session= request.getSession(false);
		if(session!=null) {
		System.out.println("--->> "+item.getItemName()+" - "+item.getCategory()+" - "+item.getCost());
		ItemBasicInfoDTO dto=itemservice.addItem(item);
		return new ResponseEntity<ItemBasicInfoDTO>(dto,HttpStatus.OK);
		}
		else {
			throw new InvalidLoginException();
		}
	}
	
	@GetMapping("/removeitem")
	public String removeItems(@RequestParam int itemId,HttpServletRequest request) throws ItemNotFoundException, InvalidLoginException {
		HttpSession session= request.getSession(false);
		if(session!=null) {
		String status=itemservice.removeItem(itemId);
		if(status.equals("Success")) {
			return "Item "+itemId+" Removed Successfully.";
		}
		else {
			throw new ItemNotFoundException(itemId);
		}
	}
	else {
		throw new InvalidLoginException();
	}
		
	}
	
	@PutMapping("/updateitem")
	public String updateItem(@RequestBody Item item,HttpServletRequest request) throws ItemNotFoundException, InvalidLoginException {
		HttpSession session= request.getSession(false);
		if(session!=null) {
		Item it=itemservice.updateItem(item);
		if(it!=null) {
			return "Item "+it.getItemName()+" Details Updated Successfully.\n\n"+it+"\n\nPlease Note the Item ID for Further References.";
		}
		else {
			throw new ItemNotFoundException(item.getItemId());
		}
		}
		else {
			throw new InvalidLoginException();
		}
	}
	
	@GetMapping("/viewitemsbyid")
	public Item viewItem(@RequestParam int itemId,HttpServletRequest request) throws ItemNotFoundException, InvalidLoginException {
		HttpSession session= request.getSession(false);
		if(session!=null) {
		Item items= itemservice.viewItem(itemId);
		if(items!=null) {
			return items;
		}
		else {
			throw new ItemNotFoundException(itemId);
		}
	}
	else {
		throw new InvalidLoginException();
	}
	}
	
	@GetMapping("/viewallitems")
	public List<Item> getAllItems(HttpServletRequest request) throws ItemNotFoundException, InvalidLoginException {
		HttpSession session= request.getSession(false);
		if(session!=null) {
		List<Item> items= itemservice.getAllItems();
		if(items.size()!=0) {
			return items;
		}
		else {
			throw new ItemNotFoundException();
		}
		}
		else {
			throw new InvalidLoginException();
		}
	}
	
	@GetMapping("/viewitemsbyname")
	public List<Item> viewAllItemsByName(String itemName,HttpServletRequest request) throws ItemNotFoundException, InvalidLoginException {
		HttpSession session= request.getSession(false);
		if(session!=null) {
		List<Item> items=itemservice.viewAllItemsByName(itemName);
		if(items.size()!=0) {
			return items;
		}
		else {
			throw new ItemNotFoundException(itemName);
		}
	}
	else {
		throw new InvalidLoginException();
	}	
	}
	
	@GetMapping("/viewitemsbycategory")
	public List<Item> viewAllItemsByCategory(String category,HttpServletRequest request) throws ItemNotFoundException, InvalidLoginException {
		HttpSession session= request.getSession(false);
		if(session!=null) {
		List<Item> items= itemservice.viewAllItemsByCategory(category);
		if(items.size()!=0) {
			return items;
		}
		else {
			throw new ItemNotFoundException(category);
		}
		}
		else {
			throw new InvalidLoginException();
		}	
	}
	
	@GetMapping("/viewitemsbyrestaurant")
	public List<Item> viewAllItemsByRestaurant(String restaurantName,HttpServletRequest request) throws ItemNotFoundException, InvalidLoginException {
		HttpSession session= request.getSession(false);
		if(session!=null) {
		List<Item> items=  itemservice.viewAllItemsByRestaurantName(restaurantName);
		if(items.size()!=0) {
			return items;
		}
		else {
			throw new ItemNotFoundException(restaurantName);
		}
	}
	else {
		throw new InvalidLoginException();
	}	
	}
	
	@GetMapping("/sortitemsbyid")
	public List<Item> sortItemsById(HttpServletRequest request) throws ItemNotFoundException, InvalidLoginException {
		HttpSession session= request.getSession(false);
		if(session!=null) {
		List<Item> items= itemservice.SortItemsById();
		if(items.size()!=0) {
			return items;
		}
		else {
			throw new ItemNotFoundException();
		}
	}
	else {
		throw new InvalidLoginException();
	}
	}
	
	@GetMapping("/sortitemsbyname")
	public List<Item> SortItemsByName(HttpServletRequest request) throws ItemNotFoundException, InvalidLoginException {
		HttpSession session= request.getSession(false);
		if(session!=null) {
		List<Item> items= itemservice.SortItemsByName();
		if(items.size()!=0) {
			return items;
		}
		else {
			throw new ItemNotFoundException();
		}
	}
	else {
		throw new InvalidLoginException();
	}
	}
	
	@GetMapping("/sortitemsbycost")
	public List<Item> SortItemsByCost(HttpServletRequest request) throws ItemNotFoundException, InvalidLoginException {
		HttpSession session= request.getSession(false);
		if(session!=null) {
		List<Item> items= itemservice.SortItemsByCost();
		if(items.size()!=0) {
			return items;
		}
		else {
			throw new ItemNotFoundException();
		}
	}
	else {
		throw new InvalidLoginException();
	}
	}
	
	@GetMapping("/getItemsByCostRange")
	public List<Item> getAllItemsByCostRange(int cost1,int cost2,HttpServletRequest request) throws ItemNotFoundException, InvalidLoginException {
		HttpSession session= request.getSession(false);
		if(session!=null) {
		List<Item> items= itemservice.viewAllItemsByCostRange(cost1,cost2);
		List<Item> list=itemservice.SortItems(items);
		
		if(list.size()!=0) {
			
			return list ;
		}
		else {
			throw new ItemNotFoundException();
		}
	}
	else {
		throw new InvalidLoginException();
	}
	}

}
