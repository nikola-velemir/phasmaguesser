import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GhostContainer } from './ghost-container';

describe('GhostContainer', () => {
  let component: GhostContainer;
  let fixture: ComponentFixture<GhostContainer>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GhostContainer],
    }).compileComponents();

    fixture = TestBed.createComponent(GhostContainer);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
