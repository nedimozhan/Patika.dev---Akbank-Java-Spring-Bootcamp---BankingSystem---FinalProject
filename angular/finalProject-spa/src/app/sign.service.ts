import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { RegisterRequest } from './RegisterRequest';


@Injectable({
  providedIn: 'root'
})
export class SignService {

  constructor(private httpClient: HttpClient, private router: Router) { }

  signIn(register:RegisterRequest){

    let path = "http://localhost:6060/register";
    let headers = new HttpHeaders();
    headers = headers.append("Content-Type", "application/json")

    return this.httpClient.post<any>(path,{"username":register.username,"email":register.email,"password":register.password},{ headers: headers }).subscribe(data => console.log(data),
    error => console.log(error));
  }


}
