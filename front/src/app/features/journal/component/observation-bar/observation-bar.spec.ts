import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ObservationBar } from './observation-bar';

describe('ObservationBar', () => {
  let component: ObservationBar;
  let fixture: ComponentFixture<ObservationBar>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ObservationBar],
    }).compileComponents();

    fixture = TestBed.createComponent(ObservationBar);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
