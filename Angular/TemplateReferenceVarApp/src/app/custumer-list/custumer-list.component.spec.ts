import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustumerListComponent } from './custumer-list.component';

describe('CustumerListComponent', () => {
  let component: CustumerListComponent;
  let fixture: ComponentFixture<CustumerListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CustumerListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustumerListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
