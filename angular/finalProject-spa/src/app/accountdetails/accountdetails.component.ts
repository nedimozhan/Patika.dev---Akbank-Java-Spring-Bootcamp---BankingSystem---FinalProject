import { Component, OnInit } from '@angular/core';
import { Account } from '../Account';
import { ListaccountService } from '../listaccount.service';

@Component({
  selector: 'app-accountdetails',
  templateUrl: './accountdetails.component.html',
  styleUrls: ['./accountdetails.component.css'],
  providers:[ListaccountService]
})
export class AccountdetailsComponent implements OnInit {

  accounts:Account[] =[];
  headers = ["id", "userId", "bankId", "number", "type", "balance","creationDate","deleted","lastUpdatedDate"];

  index = ["id", "userId", "bankId", "number", "type", "balance","creationDate","deleted","lastUpdatedDate"];

  constructor(private listAccountService:ListaccountService) { 

  }

  ngOnInit(): void {
    this.listAccountService.listAccounts().subscribe(
      (response)=>{
      //this.accountss=response[0];
      //console.log(response["accounts"]);
    })
  }

}
