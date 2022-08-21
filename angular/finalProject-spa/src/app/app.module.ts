import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { HomeComponent } from './home/home.component';
import {RouterModule, Routes} from '@angular/router';
import { TransferComponent } from './transfer/transfer.component';
import { AccountdetailsComponent } from './accountdetails/accountdetails.component';
import { RegisterComponent } from './register/register.component';

const appRoute:Routes=[
  {path:'Home',component:HomeComponent},
  {path:'',component:LoginComponent},
  {path:'transfer',component:TransferComponent},
  {path:'myaccounts',component:AccountdetailsComponent},
  {path:'register',component:RegisterComponent}
]


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    TransferComponent,
    AccountdetailsComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(appRoute)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
