import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpNewEditComponent } from './emp-new-edit.component';

describe('EmpNewEditComponent', () => {
  let component: EmpNewEditComponent;
  let fixture: ComponentFixture<EmpNewEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EmpNewEditComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmpNewEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
