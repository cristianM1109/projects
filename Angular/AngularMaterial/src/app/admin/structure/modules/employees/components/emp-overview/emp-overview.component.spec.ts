import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpOverviewComponent } from './emp-overview.component';

describe('EmpOverviewComponent', () => {
  let component: EmpOverviewComponent;
  let fixture: ComponentFixture<EmpOverviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EmpOverviewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmpOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
