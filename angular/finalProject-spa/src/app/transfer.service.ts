import { Injectable } from '@angular/core';
import { TransferRequest } from './TransferRequest';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class TransferService {

  constructor(private httpClient: HttpClient, private router: Router) {

  }

  transfer(transferRequest: TransferRequest) {
    
    let path = "http://localhost:6060/accounts/";
    let token = "Bearer " + localStorage.getItem("token");
    path = path + transferRequest.yourId;
    let headers = new HttpHeaders();
    headers = headers.append("Content-Type", "application/json")
    headers = headers.append("Authorization", token)

    this.httpClient.put<any>(path, {"amount" : transferRequest.amount,"receiverAccountId" : transferRequest.anotherId}, { headers: headers })
    .subscribe(data => console.log(data),
    error => console.log(error));
  }
}
