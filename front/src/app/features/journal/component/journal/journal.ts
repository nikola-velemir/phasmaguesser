import { Component, OnInit } from '@angular/core';
import { EvidenceBarComponent } from "../evidence-bar/evidence-bar";
import { ObservationBar } from "../observation-bar/observation-bar";
import { CommonModule } from '@angular/common';
import { GhostService } from '../../../../service/ghost-service';
import { InvestigationTracker } from "../investigation-tracker/investigation-tracker";

@Component({
  selector: 'app-journal',
  imports: [EvidenceBarComponent, ObservationBar, CommonModule, InvestigationTracker],
  templateUrl: './journal.html',
  styleUrl: './journal.css',
})
export class JournalComponent {
  /**
   *
   */
  constructor(private ghostService: GhostService) {

  }
  analyze() {
    this.ghostService.indetify().subscribe(s=>console.log(s))
  }
}
