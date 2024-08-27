import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrl: './filter.component.css'
})
export class FilterComponent {

  selectedRadioButton: string = 'All';

   @Input() all!: number;
   @Input() free!: number ;
   @Input() medium!: number ;
   @Input() advanced!: number ;

   @Input() books:any;

   @Output()
   filterRadioButtonSelectionChanged: EventEmitter<string> = new EventEmitter<string>;

   

  onRadioButtonSelectionChanged(){
    this.filterRadioButtonSelectionChanged.emit(this.selectedRadioButton);
    //console.log(this.selectedRadioButton)

  }
  
  
  
}

  
//      constructor(){
 //       setInterval(()=>{
 //         console.log(this.all);
 //       },1000)
 //     }
  
//}
