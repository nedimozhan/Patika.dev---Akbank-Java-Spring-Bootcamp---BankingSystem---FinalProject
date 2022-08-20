import { Component, OnInit } from '@angular/core';
import { TransferService } from '../transfer.service';

@Component({
  selector: 'app-transfer',
  templateUrl: './transfer.component.html',
  styleUrls: ['./transfer.component.css']
})
export class TransferComponent implements OnInit {

  transferRequest:any={};

  constructor(private transferService:TransferService) { 
    
  }

  ngOnInit(): void {
  }

  transfer(){
    this.transferService.transfer(this.transferRequest);
  }

}
