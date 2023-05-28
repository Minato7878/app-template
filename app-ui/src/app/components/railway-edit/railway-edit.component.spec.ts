import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RailwayEditComponent } from './railway-edit.component';

describe('RailwayEditComponent', () => {
  let component: RailwayEditComponent;
  let fixture: ComponentFixture<RailwayEditComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RailwayEditComponent]
    });
    fixture = TestBed.createComponent(RailwayEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
