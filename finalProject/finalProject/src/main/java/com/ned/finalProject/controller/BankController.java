package com.ned.finalProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ned.finalProject.createrequest.BankCreateRequest;
import com.ned.finalProject.errorresponse.BankNameAlreadyUsedResponse;
import com.ned.finalProject.errorresponse.UnknownErrorResponse;
import com.ned.finalProject.exception.BankNameAlreadyUsedException;
import com.ned.finalProject.exception.UnknownErrorException;
import com.ned.finalProject.model.Bank;
import com.ned.finalProject.service.IBankCreateService;
import com.ned.finalProject.successresponse.BankCreateSuccessResponse;

@RestController
public class BankController {

	private IBankCreateService bankCreateService;

	@Autowired
	public BankController(@Qualifier("BankCreateService") IBankCreateService bankCreateService) {
		this.bankCreateService = bankCreateService;
	}

	/*
	 * Create bank with name that given in request body
	 */
	@PostMapping(path = "/banks")
	public ResponseEntity<?> createBank(@RequestBody BankCreateRequest request) {

		try {
			Bank bank = this.bankCreateService.createBank(request.getName());
			BankCreateSuccessResponse bankCreateSuccessResponse = new BankCreateSuccessResponse(bank);
			return new ResponseEntity<>(bankCreateSuccessResponse, null, HttpStatus.CREATED);
		} catch (BankNameAlreadyUsedException e) {
			System.out.println("xxxx");
			BankNameAlreadyUsedResponse bankNameAlreadyUsedResponse = new BankNameAlreadyUsedResponse(e.getMessage());
			return new ResponseEntity<>(bankNameAlreadyUsedResponse, null, HttpStatus.UNPROCESSABLE_ENTITY);
		}catch (UnknownErrorException e) {
			UnknownErrorResponse unknownErrorResponse = new UnknownErrorResponse();
			return new ResponseEntity<>(unknownErrorResponse, null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
