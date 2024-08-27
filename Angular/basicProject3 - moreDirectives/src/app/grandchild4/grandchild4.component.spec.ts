import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Grandchild4Component } from './grandchild4.component';

describe('Grandchild4Component', () => {
  let component: Grandchild4Component;
  let fixture: ComponentFixture<Grandchild4Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [Grandchild4Component]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Grandchild4Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
