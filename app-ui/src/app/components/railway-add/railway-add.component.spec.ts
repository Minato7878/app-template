import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RailwayAddComponent } from './railway-add.component';

describe('RailwayAddComponent', () => {
  let component: RailwayAddComponent;
  let fixture: ComponentFixture<RailwayAddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RailwayAddComponent]
    });
    fixture = TestBed.createComponent(RailwayAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
