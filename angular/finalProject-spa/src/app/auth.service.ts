import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { LoginUser } from './LoginUser';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient: HttpClient, private router: Router) {

  }
  path = "http://localhost:6060/auth";
  userToken: any;

  login(loginUser: LoginUser) {
    let headers = new HttpHeaders();
    headers = headers.append("Content-Type", "application/json")
    this.httpClient.post<any>(this.path, loginUser, { headers: headers })
      .subscribe(data => {
        localStorage.setItem("token", data.token);
        this.userToken = data.token;
        console.log(data);
        this.router.navigateByUrl("Home");
      },error => console.log(error))
  }


}
