package com.ned.finalProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ned.finalProject.createrequest.AccountCreateRequest;
import com.ned.finalProject.createrequest.AccountDepositRequest;
import com.ned.finalProject.exception.AccountAccessDeniedException;
import com.ned.finalProject.exception.AccountAccessDeniedResponse;
import com.ned.finalProject.exception.AccountNotFoundException;
import com.ned.finalProject.exception.AccountNotFoundResponse;
import com.ned.finalProject.exception.UnknownErrorException;
import com.ned.finalProject.exception.UnknownErrorResponse;
import com.ned.finalProject.model.Account;
import com.ned.finalProject.service.AccountCreateService;
import com.ned.finalProject.service.IAccountCreateService;
import com.ned.finalProject.service.IAccountDepositService;
import com.ned.finalProject.service.IAccountDetailService;
import com.ned.finalProject.service.IAccountRelationUserControlService;
import com.ned.finalProject.service.IAccountRemoveService;
import com.ned.finalProject.successresponse.AccountCreateSuccessResponse;
import com.ned.finalProject.successresponse.AccountDepositSuccessResponse;
import com.ned.finalProject.successresponse.AccountDetailSuccessResponse;
import com.ned.finalProject.successresponse.AccountRemoveSuccessResponse;

@RestController
public class AccountController {

	private IAccountCreateService accountCreateService;
	private IAccountDetailService accountDetailService;
	private IAccountRemoveService accountRemoveService;
	private IAccountDepositService accountDepositService;
	private IAccountRelationUserControlService accountRelationUserControlService;

	@Autowired
	public AccountController(@Qualifier("AccountCreateService") IAccountCreateService accountCreateService,
			@Qualifier("AccountDetailService") IAccountDetailService accountDetailService,
			@Qualifier("AccountRemoveService") IAccountRemoveService accountRemoveService,
			@Qualifier("AccountDepositService") IAccountDepositService accountDepositService,
			@Qualifier("AccountRelationUserControlService") IAccountRelationUserControlService accountRelationUserControlService) {
		this.accountCreateService = accountCreateService;
		this.accountDetailService = accountDetailService;
		this.accountRemoveService = accountRemoveService;
		this.accountDepositService = accountDepositService;
		this.accountRelationUserControlService = accountRelationUserControlService;
	}

	@PostMapping(path = "/accounts")
	public ResponseEntity<?> createAccount(@RequestBody AccountCreateRequest request) {

		Account account = this.accountCreateService.createAccount(request);
		AccountCreateSuccessResponse accountCreateSuccessResponse = new AccountCreateSuccessResponse(account);
		return new ResponseEntity<>(accountCreateSuccessResponse, null, HttpStatus.CREATED);
	}

	@GetMapping(path = "/accounts/{id}")
	public ResponseEntity<?> accountDetail(@PathVariable int id) {

		Account account = this.accountDetailService.accountDetail(id);
		AccountDetailSuccessResponse accountDetailSuccessResponse = new AccountDetailSuccessResponse(account);

		ResponseEntity<?> responseEntity = new ResponseEntity<>(accountDetailSuccessResponse, null, HttpStatus.OK);
		responseEntity.ok().lastModified(account.getLastUpdatedDate().getTime());
		return responseEntity;
	}

	@DeleteMapping("/accounts/{id}")
	public ResponseEntity<?> removeAccount(@PathVariable int id) {

		this.accountRemoveService.removeAccount(id);
		AccountRemoveSuccessResponse accountRemoveSuccessResponse = new AccountRemoveSuccessResponse();
		return new ResponseEntity<>(accountRemoveSuccessResponse, null, HttpStatus.OK);
	}
	
	/*
	 *  Deposite money to account that given id 
	 *  RequestBody include amount for the deposit action
	 */
	@PatchMapping("/accounts/{id}")
	public ResponseEntity<?> depositAccount(@PathVariable int id,
			@RequestBody AccountDepositRequest accountDepositRequest) {

		try {
			Account account = this.accountDepositService.depositAccount(id, accountDepositRequest.getAmount());
			AccountDepositSuccessResponse accountDepositSuccessResponse = new AccountDepositSuccessResponse(account.getBalance());
			ResponseEntity<?> responseEntity = new ResponseEntity<>(accountDepositSuccessResponse, null, HttpStatus.OK);
			responseEntity.ok().lastModified(account.getLastUpdatedDate().getTime());
			return responseEntity;

		} catch (AccountAccessDeniedException e) {
			AccountAccessDeniedResponse accessDeniedResponse = new AccountAccessDeniedResponse();
			return new ResponseEntity<>(accessDeniedResponse, null, HttpStatus.FORBIDDEN);
		} catch (AccountNotFoundException e) {
			AccountNotFoundResponse accountNotFoundResponse = new AccountNotFoundResponse();
			return new ResponseEntity<>(accountNotFoundResponse, null, HttpStatus.NOT_FOUND);
		} catch (UnknownErrorException e) {
			UnknownErrorResponse unknownErrorResponse = new UnknownErrorResponse();
			return new ResponseEntity<>(unknownErrorResponse, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
