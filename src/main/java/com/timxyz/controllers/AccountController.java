package com.timxyz.controllers;

import com.timxyz.controllers.forms.Account.AccountCreateForm;
import com.timxyz.models.Account;
import com.timxyz.services.AccountService;
import com.timxyz.services.exceptions.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

@RestController
public class AccountController extends BaseController<Account, AccountService> {
    
    @Autowired
    AccountService accountService;
    
    @ResponseBody
    public ResponseEntity create(@RequestBody @Valid AccountCreateForm newAccount) {
        try {
            // Maps our DTO (data transfer object) to the proper Account class after the
            // validations in our DTO (AccountCreateForm) have passed
            Account acc = modelMapper.map(newAccount, Account.class);
            acc.setId(null); // modelMapper somehow seems to map the roleId field to Id...which shouldn't happen
            acc = service.save(acc);
            return ResponseEntity.ok(acc);
        } catch(ServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Account> deleteUser(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id " + id);
  
        Account user = accountService.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with username " + id + " not found");
            return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
        }
  
        accountService.deleteUserById(id);
        return new ResponseEntity<Account>(HttpStatus.NO_CONTENT);
    }
    
    @ResponseBody
    public ResponseEntity filterByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(service.getByPartOfEmail(email));
    }
}