import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginUser:any={};


  constructor(private authService:AuthService) {

  }

  ngOnInit(): void {
  }

  login(){
    this.authService.login(this.loginUser);
  }

}
