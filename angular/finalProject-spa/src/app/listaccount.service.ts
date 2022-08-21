import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ListaccountService {

  constructor(private httpClient: HttpClient, private router: Router) { }

  listAccounts(){
      
    let path = "http://localhost:6060/accounts/all";
    let token = "Bearer " + localStorage.getItem("token");
    let headers = new HttpHeaders();
    headers = headers.append("Content-Type", "application/json")
    headers = headers.append("Authorization", token)

    return this.httpClient.get<any>(path,{ headers: headers })
      
  }
}
