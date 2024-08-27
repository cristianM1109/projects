import { Component, ElementRef, ViewChild } from '@angular/core';
import { CustumerListComponent } from './custumer-list/custumer-list.component';
import { DemoComponent } from './demo/demo.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title: string = 'TemplateReferenceCurs';
  @ViewChild('dobInput')
  dateOfBirth!: ElementRef;

  @ViewChild('ageInput')
  age!: ElementRef;

  @ViewChild(CustumerListComponent)
  customerListComponent!: CustumerListComponent;

  @ViewChild(DemoComponent)
  demoComponent!: DemoComponent;

  constructor() {
     setTimeout(() => console.log(this.customerListComponent.customers), 1);
    // setTimeout(() => console.log(this.demoComponent), 1);
  }

  calculateAge() {
    let birthYear = parseInt(
      this.dateOfBirth.nativeElement.value.substring(0, 4)
    );
    let currentYear = new Date().getFullYear();
    let age = currentYear - birthYear;
    this.age.nativeElement.value = age;
  }
}
