import { Component } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent {
  searchValue : string = '' ;
//  changeSearchValue(eventData:any){
//    console.log(eventData);
//    console.log((<any>eventData.target).value);
//    console.log((<HTMLInputElement>eventData.target).value);
//    this.searchValue = (<HTMLInputElement>eventData.target).value ;
//  }
}
