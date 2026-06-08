import { ComponentFixture, TestBed } from "@angular/core/testing";

import { InvestigationTracker } from "./investigation-tracker";

describe("InvestigationTracker", () => {
  let component: InvestigationTracker;
  let fixture: ComponentFixture<InvestigationTracker>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InvestigationTracker],
    }).compileComponents();

    fixture = TestBed.createComponent(InvestigationTracker);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });
});
