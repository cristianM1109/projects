import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanDeactivate,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UnsavedChangesGuard implements CanDeactivate<any> {
  canDeactivate(component: any): boolean {
    if (
      component.loginForm.value.email !== '' ||
      component.loginForm.value.password !== ''
    ) {
      if (
        confirm(
          'You have some unsaved changes, are you sure you want to leave this page?'
        )
      ) {
        return true;
      } else {
        return false;
      }
    }
    return true;
  }
}
