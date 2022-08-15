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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ned.finalProject.createrequest.AccountCreateRequest;
import com.ned.finalProject.createrequest.AccountDepositRequest;
import com.ned.finalProject.createrequest.AccountTransferRequest;
import com.ned.finalProject.exception.AccountAccessDeniedException;
import com.ned.finalProject.exception.AccountAccessDeniedResponse;
import com.ned.finalProject.exception.AccountInsufficientBalanceException;
import com.ned.finalProject.exception.AccountInsufficientBalanceResponse;
import com.ned.finalProject.exception.AccountNotFoundException;
import com.ned.finalProject.exception.AccountNotFoundResponse;
import com.ned.finalProject.exception.AccountTypeInvalidException;
import com.ned.finalProject.exception.AccountTypeInvalidResponse;
import com.ned.finalProject.exception.UnknownErrorException;
import com.ned.finalProject.exception.UnknownErrorResponse;
import com.ned.finalProject.model.Account;
import com.ned.finalProject.service.IAccountCreateService;
import com.ned.finalProject.service.IAccountDepositService;
import com.ned.finalProject.service.IAccountDetailService;
import com.ned.finalProject.service.IAccountRemoveService;
import com.ned.finalProject.service.IAccountTransferService;
import com.ned.finalProject.service.IValidateService;
import com.ned.finalProject.successresponse.AccountCreateSuccessResponse;
import com.ned.finalProject.successresponse.AccountDepositSuccessResponse;
import com.ned.finalProject.successresponse.AccountDetailSuccessResponse;
import com.ned.finalProject.successresponse.AccountRemoveSuccessResponse;
import com.ned.finalProject.successresponse.AccountTransferSuccessResponse;

@RestController
public class AccountController {

	private IAccountCreateService accountCreateService;
	private IAccountDetailService accountDetailService;
	private IAccountRemoveService accountRemoveService;
	private IAccountDepositService accountDepositService;
	private IAccountTransferService accountTransferService;
	private IValidateService validateService;

	@Autowired
	public AccountController(@Qualifier("AccountCreateService") IAccountCreateService accountCreateService,
			@Qualifier("AccountDetailService") IAccountDetailService accountDetailService,
			@Qualifier("AccountRemoveService") IAccountRemoveService accountRemoveService,
			@Qualifier("AccountDepositService") IAccountDepositService accountDepositService,
			@Qualifier("AccountTransferService") IAccountTransferService accountTransferService,
			@Qualifier("ValidateService") IValidateService validateService) {
		this.accountCreateService = accountCreateService;
		this.accountDetailService = accountDetailService;
		this.accountRemoveService = accountRemoveService;
		this.accountDepositService = accountDepositService;
		this.accountTransferService = accountTransferService;
		this.validateService = validateService;
	}

	/*
	 * Create account with bank id and bank type Another columns (user
	 * id,balance,isDeleted,creation date,update date) set automatically
	 */
	@PostMapping(path = "/accounts")
	public ResponseEntity<?> createAccount(@RequestBody AccountCreateRequest request) {

		try {
			Account account = this.accountCreateService.createAccount(request);
			AccountCreateSuccessResponse accountCreateSuccessResponse = new AccountCreateSuccessResponse(account);
			return new ResponseEntity<>(accountCreateSuccessResponse, null, HttpStatus.CREATED);
		} catch (AccountTypeInvalidException e) {
			AccountTypeInvalidResponse accountTypeInvalidResponse = new AccountTypeInvalidResponse(e.getMessage());
			return new ResponseEntity<>(accountTypeInvalidResponse, null, HttpStatus.BAD_REQUEST);
		} catch (UnknownErrorException e) {
			UnknownErrorResponse unknownErrorResponse = new UnknownErrorResponse();
			return new ResponseEntity<>(unknownErrorResponse, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/*
	 * Show account detail if user id and account.user id can matching
	 */
	@GetMapping(path = "/accounts/{id}")
	public ResponseEntity<?> accountDetail(@PathVariable int id) {

		try {
			// Control User id and Account.UserID
			this.validateService.isAccountRelatedToUser(id);

			// Detail
			Account account = this.accountDetailService.accountDetail(id);
			AccountDetailSuccessResponse accountDetailSuccessResponse = new AccountDetailSuccessResponse(account);
			ResponseEntity<?> responseEntity = new ResponseEntity<>(accountDetailSuccessResponse, null, HttpStatus.OK);
			
			// Update last Modified
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

	/*
	 * Remove account by user who has REMOVE_ACCOUNT authority
	 */
	@DeleteMapping("/accounts/{id}")
	public ResponseEntity<?> removeAccount(@PathVariable int id) {

		try {
			// Control is there an account
			this.validateService.isAccountFound(id);

			// Remove account
			this.accountRemoveService.removeAccount(id);
			AccountRemoveSuccessResponse accountRemoveSuccessResponse = new AccountRemoveSuccessResponse();
			return new ResponseEntity<>(accountRemoveSuccessResponse, null, HttpStatus.OK);
		} catch (AccountNotFoundException e) {
			AccountNotFoundResponse accountNotFoundResponse = new AccountNotFoundResponse();
			return new ResponseEntity<>(accountNotFoundResponse, null, HttpStatus.NOT_FOUND);

		} catch (UnknownErrorException e) {
			UnknownErrorResponse unknownErrorResponse = new UnknownErrorResponse();
			return new ResponseEntity<>(unknownErrorResponse, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/*
	 * Deposite money to account that given id RequestBody include amount for the
	 * deposit action. This method's web service is transactional
	 */
	@PatchMapping("/accounts/{id}")
	public ResponseEntity<?> depositAccount(@PathVariable int id,
			@RequestBody AccountDepositRequest accountDepositRequest) {

		try {
			// Validation
			this.validateService.isAccountRelatedToUser(id);

			// Deposit
			Account account = this.accountDepositService.depositAccount(id, accountDepositRequest.getAmount());
			AccountDepositSuccessResponse accountDepositSuccessResponse = new AccountDepositSuccessResponse(
					account.getBalance());
			return new ResponseEntity<>(accountDepositSuccessResponse, null, HttpStatus.OK);

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

	/*
	 * Transfer money from sender account to receiver account If sender account and
	 * receiver account contain different bank types, sender has to pay (EFT) If
	 * sender and receiver have different currency types we should convert sender
	 * types to receiver types
	 */
	@PutMapping("/accounts/{senderAccountId}")
	public ResponseEntity<?> transferAccount(@PathVariable int senderAccountId,
			@RequestBody AccountTransferRequest accountTransferRequest) {

		try {

			// Validation
			this.validateService.isAccountRelatedToUser(senderAccountId);
			this.validateService.isAccountFound(accountTransferRequest.getReceiverAccountId());

			// Transfer
			this.accountTransferService.transferAccount(senderAccountId, accountTransferRequest);
			AccountTransferSuccessResponse accountTransferSuccessResponse = new AccountTransferSuccessResponse();
			return new ResponseEntity<>(accountTransferSuccessResponse, null, HttpStatus.OK);

		} catch (AccountInsufficientBalanceException e) {
			AccountInsufficientBalanceResponse accountInsufficientBalanceResponse = new AccountInsufficientBalanceResponse();
			return new ResponseEntity<>(accountInsufficientBalanceResponse, null, HttpStatus.BAD_REQUEST);
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
