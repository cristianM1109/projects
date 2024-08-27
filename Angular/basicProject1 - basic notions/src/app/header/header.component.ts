import { Component } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

  source :string = 'shopping.jpg';
  siteSlogan : string = 'Do SHOP not eat when depressed!';
  getSlogan(){
    return 'this is a new slogan for this web app';
  }
}
