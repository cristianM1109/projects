import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { EmpNewEditComponent } from '../emp-new-edit/emp-new-edit.component';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { EmployeeService } from '../../services/employee.service';
import { DialogRef } from '@angular/cdk/dialog';
import { ToastService } from '../../../../../../shared/toast-service.service';

@Component({
  selector: 'app-emp-overview',
  templateUrl: './emp-overview.component.html',
  styleUrl: './emp-overview.component.css',
})
export class EmpOverviewComponent {
  constructor(
    private matDialog: MatDialog,
    private employeeService: EmployeeService,
    private toastService:ToastService
  ) {}

  openAddNewDialog() {
    const dialogRef = this.matDialog.open(EmpNewEditComponent);
    dialogRef.afterClosed().subscribe((formVal) => {
      console.log(formVal);
      this.getEmployeeList();
    });
  }

  openEditExistingForm(data: any) {
    this.matDialog
      .open(EmpNewEditComponent, { data })
      .afterClosed()
      .subscribe(() => {
        this.getEmployeeList();
      });
  }

  // inside EmpOverviewComponent Class
  displayedColumns: string[] = [
    'actions',
    'id',
    'firstName',
    'lastName',
    'email',
    'dob',
    'gender',
    'education',
    'experience',
    'salary',
  ];
  dataSource!: MatTableDataSource<any>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  ngOnInit() {
    this.getEmployeeList();
  }
  getEmployeeList() {
    this.employeeService.getAllEmployees().subscribe({
      next: (response) => {
        this.dataSource = new MatTableDataSource(response);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;

      },
      error: (err) => {
        console.error(err);
      },
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  deleteExistingEmployee(id: number) {
    this.employeeService.deleteEmployee(id).subscribe({
      next: (success) => {
        this.toastService.openToast('Emloyee successfully deleted!', 'OK', true);
        this.getEmployeeList();
      },
      error: (err) => {
        this.toastService.openToast('Error!', 'OK', false);
      },
    });
  }
}
