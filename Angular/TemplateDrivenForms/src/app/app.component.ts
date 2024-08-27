import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Gender } from './models/gender.num';
import { Registration } from './models/registration';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'angularForms';

  // onSubmit(ngform:NgForm){
  //   console.log(ngform);
  // }

  defaultCountry="romania";
  defaultGender="Male";
  genders = [
    { id: 1, value: 'Male' },
    { id: 2, value: 'Female' },
    { id: 3, value: 'Other' },
   ];
   
   registrations: Registration[] = [
    {
    id: '1',
    fullName: {
     firstName: 'Stefan',
     lastName: 'TheGreat',
     },
     email: 'TheGreat@example.com',
    gender: Gender.Male,
    country: 'Romania',
   },
   {
    id: '2',
    fullName: {
    firstName: 'Mihai',
    lastName: 'TheBrave',
    },
    email: 'TheBrave@example.com',
    gender: Gender.Male,
    country: 'Romania',
   },
   {
    id: '3',
    fullName: {
     firstName: 'Queen',
     lastName: 'Maria',
    },
    email: 'QueenMaria@example.com',
    gender: Gender.Female,
    country: 'Romania',
   },
   ];

   @ViewChild('myForm') ngform!: NgForm;

   showRegistrationExample() {
    this.ngform.form.patchValue({
      fullName: {
       firstName: 'Jane',
       lastName: 'Smith',
      },
      email: 'jane.smith@example.com',
      gender: 'Female',
     });
     
    }

    
      
//     console.log(this.form);
//     this.form.value.fullName.firstName = 'Jane'; 
//     this.form.value.fullName.lastName = 'Smith';
//     this.form.value.email = 'jane.smith@example.com'; 
//     this.form.value.gender = 'Female';
// }

onSubmit(form: NgForm) {
  console.log(form);
  if (form.valid) {
  this.determineGender(form.value.gender)
  this.registrations.push(form.value);
  form.resetForm();
  }
 }
 determineGender(gender: string) {
  if (gender === 'Male') {
   return (this.ngform.value.gender = Gender.Male);
  } else if (gender === 'Female') {
  return (this.ngform.value.gender = Gender.Female);
  } else {
  return (this.ngform.value.gender = Gender.Other);
  }
 }
 getGenderString(gender: Gender): string {
  switch (gender) {
  case Gender.Male:
   return 'Male';
  case Gender.Female:
   return 'Female';
  default:
   return 'Other';
  }
  }
}
