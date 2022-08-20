export class Account{
    id:String;
    userId:String;
    bankId:String;
    number:String;
    type:String;
    balance:String;
    creationDate:String;
    deleted:String;
    lastUpdatedDate:String;

    constructor(id:any,userId:any,bankId:any,number:any,type:any,balance:any,
        creationDate:any,deleted:any,lastUpdatedDate:any){
        this.id=id;
        this.userId=userId;
        this.bankId=bankId;
        this.number=number;
        this.type=type;
        this.balance=balance;
        this.creationDate=creationDate;
        this.deleted=deleted;
        this.lastUpdatedDate=lastUpdatedDate;
    }

}