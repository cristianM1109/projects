import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class ToastService {
   constructor(private snackBarService: MatSnackBar) {}
   openToast(message: string, action: string = 'Close', success: boolean) {
    let cssClass = [''];
    success ? (cssClass = ['successToast']) : (cssClass = ['errorToast']);
    this.snackBarService.open(message, action, {
     duration: 3000,
     verticalPosition: 'top',
     panelClass: cssClass,
    });
   }
  }
  
