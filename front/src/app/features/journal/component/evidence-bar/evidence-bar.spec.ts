import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EvidenceBar } from './evidence-bar';

describe('EvidenceBar', () => {
  let component: EvidenceBar;
  let fixture: ComponentFixture<EvidenceBar>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EvidenceBar]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EvidenceBar);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
