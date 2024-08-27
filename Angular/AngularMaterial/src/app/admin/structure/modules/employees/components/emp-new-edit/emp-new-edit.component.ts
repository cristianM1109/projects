import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { EmployeeService } from '../../services/employee.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-emp-new-edit',
  templateUrl: './emp-new-edit.component.html',
  styleUrl: './emp-new-edit.component.css',
})
export class EmpNewEditComponent {
  education: string[] = ['High School', 'Bachelor', 'Master', 'Doctorate'];

  experience: string[] = [
    'Intern',
    'Consulting',
    'Senior Consulting',
    'Manager',
    'Senior Manager',
    'Partner',
  ];
  employeeFrom!: FormGroup;
  constructor(private fb: FormBuilder,private employeeService:EmployeeService,private matDialogRef:MatDialogRef<any>,@Inject(MAT_DIALOG_DATA) public data: any
) {
    this.employeeFrom = this.fb.group({
   firstName: '',
   lastName: '',
   email: '',
   dob: '',
   gender: '',
   education: '',
   experience: '',
   salary: '',
  });
  }

  ngOnInit() {
     console.log('Injector Token data', this.data);
     this.data !== null ? this.employeeFrom.patchValue(this.data) : undefined
    }
    

    onSubmit() {
      if (this.employeeFrom.valid) {
      if (this.data) {
       this.employeeService
      .updateEmployee(this.data.id, this.employeeFrom.value)
       .subscribe({
         next: (success: any) => {
          alert('Employee was updated with success!');
        // console.log(this.matDialogRef);
         this.matDialogRef.close(true);
        },
        error: (err: any) => {
         console.error(err);
        },
       });
     } else {
        this.employeeService.addEmployee(this.employeeFrom.value)
          .subscribe({
         next: (success: any) => {
         alert('New Employee added with success!');
         console.log(this.matDialogRef);
         this.matDialogRef.close(true);
         },
         error: (err: any) => {
         console.error(err);
         },
         });
       }
     }
     }
  
}
