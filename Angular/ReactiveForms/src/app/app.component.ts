import { Component } from '@angular/core';
import { AsyncValidatorFn, FormArray, FormControl, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { Observable } from 'rxjs/internal/Observable';
import { noSpaceAllowed, passwordValidator } from './custom-validators/customSyncValidators';
import { emailNotAllowedValidator } from './custom-validators/customAsyncValidators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'ReactiveForms';
  reactiveForm!: FormGroup;
  skillsArray!: any[];
  formStatus!:string;

  ngOnInit(): void {
    this.reactiveForm = new FormGroup({
    fullName: new FormGroup({
    firstName: new FormControl(null,[Validators.required,noSpaceAllowed]),
    lastName: new FormControl(null,Validators.required),
    
    }),
    password: new FormControl(null, passwordValidator as ValidationErrors),
    email: new FormControl(null,[Validators.required, Validators.email],emailNotAllowedValidator as AsyncValidatorFn),
    country: new FormControl("romania"),
    gender: new FormControl(null),
    hobbies: new FormControl(null),
    skills: new FormArray([
      new FormControl(null),
    ])
    });
    this.skillsArray = (<FormArray> this.reactiveForm.get("skills"))!['controls'];

    this.reactiveForm.statusChanges.subscribe((value) => { 
      this.formStatus = value;
      console.log(this.formStatus);
    });

  }
    
  OnSubmit(){
    console.log(this.reactiveForm);
  }
  addSkills(){ (<FormArray>this.reactiveForm.get('skills'))!
    .push(new FormControl(null, Validators.required));
    }

    
  

     
}
