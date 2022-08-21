import { Component, OnInit } from '@angular/core';
import { SignService } from '../sign.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private signService:SignService) { }

  signUser:any={};


  ngOnInit(): void {
    
  }

  register(){
    this.signService.signIn(this.signUser);
  }


}
