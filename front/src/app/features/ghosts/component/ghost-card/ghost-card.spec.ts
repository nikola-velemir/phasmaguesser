import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GhostCard } from './ghost-card';

describe('GhostCard', () => {
  let component: GhostCard;
  let fixture: ComponentFixture<GhostCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GhostCard],
    }).compileComponents();

    fixture = TestBed.createComponent(GhostCard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
