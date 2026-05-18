// evidence-bar.component.ts
import { Component } from '@angular/core';
import { CommonModule, NgClass, NgFor } from '@angular/common';
import { BehaviorSubject, map } from 'rxjs';

import { Evidence } from './evidence';
import { EvidenceState } from './evicence-state';

@Component({
  selector: 'app-evidence-bar',
  standalone: true,
  imports: [NgFor, NgClass, CommonModule],
  templateUrl: './evidence-bar.html',
})
export class EvidenceBarComponent {

  readonly EvidenceState = EvidenceState;

  private isUpdating = false;

  private evidencesSubject = new BehaviorSubject<Evidence[]>([
    { name: 'EMF Level 5', state: EvidenceState.Idle },
    { name: 'Spirit Box', state: EvidenceState.Idle },
    { name: 'Ghost Writing', state: EvidenceState.Idle },
    { name: 'Freezing Temps', state: EvidenceState.Idle },
    { name: 'DOTS Projector', state: EvidenceState.Idle },
    { name: 'Fingerprints', state: EvidenceState.Idle },
    { name: 'Ghost Orb', state: EvidenceState.Idle },
  ]);

  evidences$ = this.evidencesSubject.asObservable();

  selectedEvidence$ = this.evidences$.pipe(
    map(evidences =>
      evidences.filter(e => e.state === EvidenceState.Selected)
    )
  );

  constructor() {

    this.selectedEvidence$.subscribe(selected => {

      if (this.isUpdating) {
        return;
      }

      // exactly 3 selected
      if (selected.length === 3) {
        console.log("AAA")
        this.isUpdating = true;

        const updated = this.evidencesSubject.value.map(e => {

          // keep selected
          if (e.state === EvidenceState.Selected) {
            return e;
          }

          // eliminate remaining idle evidence
          return {
            ...e,
            state: EvidenceState.Eliminated
          };
        });

        this.evidencesSubject.next(updated);

        this.isUpdating = false;
      }
    });
  }

  toggleEvidence(evidence: Evidence) {

    const current = this.evidencesSubject.value;

    const selectedCount = current.filter(
      e => e.state === EvidenceState.Selected
    ).length;

    const updated = current.map(e => {

      if (e.name !== evidence.name) {
        return e;
      }

      switch (e.state) {

        case EvidenceState.Idle:

          // Prevent selecting more than 3
          if (selectedCount >= 3) {
            return e;
          }

          return {
            ...e,
            state: EvidenceState.Selected
          };

        case EvidenceState.Selected:
          return {
            ...e,
            state: EvidenceState.Eliminated
          };

        case EvidenceState.Eliminated:
          return {
            ...e,
            state: EvidenceState.Idle
          };
      }
    });

    const finalSelectedCount = updated.filter(
      e => e.state === EvidenceState.Selected
    ).length;

    // Auto eliminate remaining idle evidence
    const finalUpdated = finalSelectedCount === 3
      ? updated.map(e => {

        if (e.state === EvidenceState.Idle) {
          return {
            ...e,
            state: EvidenceState.Eliminated
          };
        }

        return e;
      })
      : updated;

    this.evidencesSubject.next(finalUpdated as Evidence[]);
  }
}