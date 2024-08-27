import { Component } from '@angular/core';

@Component({
  selector: 'app-message',
  template: `<div [hidden] = "displayMessage">
  <p>
  This website uses cookies to provide better user experience!
  </p> 
  <div class="close"><span class="btn" (click)="closeMessage()">&#10060;</span></div> 
  </div>`,
  styles: [
    `
    div{
    margin:10px 0 10px 0;
    padding:10px 20px;
    background-color: skyblue;
    text-align: center;
    }
    `
    ,
    `
    p{
     font-size: 14px;
    }
    `
    ,
    `
    .close{
    background:none;
    float: right;
    margin-top:-43px;
    }
    `
  ]
})
export class MessageComponent {
    displayMessage : boolean = false;

    closeMessage(){
      this.displayMessage = true
    }
}
