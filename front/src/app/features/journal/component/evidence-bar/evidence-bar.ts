// evidence-bar.component.ts
import { Component, OnInit } from '@angular/core';
import { CommonModule, NgClass, NgFor } from '@angular/common';
import { BehaviorSubject, distinctUntilChanged, map, switchMap, take, tap } from 'rxjs';

import { Evidence } from './evidence';
import { EvidenceState } from './evicence-state';
import { GhostService } from '../../../../service/ghost-service';

@Component({
  selector: 'app-evidence-bar',
  standalone: true,
  imports: [NgFor, NgClass, CommonModule],
  templateUrl: './evidence-bar.html',
})
export class EvidenceBarComponent implements OnInit {
  resetForm() {
    const resetedEvidence = this.evidencesSubject.getValue().map(e => ({ ...e, state: EvidenceState.Idle }));
    this.evidencesSubject.next(resetedEvidence)
  }

  readonly EvidenceState = EvidenceState;

  private isUpdating = false;

  private evidencesSubject = new BehaviorSubject<Evidence[]>([
    { name: 'EMF Level 5', value: "EMF_LEVEL_5", state: EvidenceState.Idle },
    { name: 'Spirit Box', value: "SPIRIT_BOX", state: EvidenceState.Idle },
    { name: 'Ghost Writing', value: "GHOST_WRITING", state: EvidenceState.Idle },
    { name: 'Freezing Temps', value: "FREEZING_TEMPERATURES", state: EvidenceState.Idle },
    { name: 'DOTS Projector', value: "DOTS_PROJECTOR", state: EvidenceState.Idle },
    { name: 'Fingerprints', value: "ULTRAVIOLET", state: EvidenceState.Idle },
    { name: 'Ghost Orb', value: "GHOST_ORB", state: EvidenceState.Idle },
  ]);
  expanded = true;
  evidences$ = this.evidencesSubject.asObservable();

  selectedEvidence$ = this.evidences$.pipe(
    map(evidences =>
      evidences.filter(e => e.state === EvidenceState.Selected)
    )
  );
  private calculateMaxEvidence() {
    return this.orbSelected ? 4 : 3;
  }
  constructor(private ghostService: GhostService) {

    this.selectedEvidence$.subscribe(selected => {

      if (this.isUpdating) {
        return;
      }

      // exactly 3 selected
      if (selected.length === this.calculateMaxEvidence()) {

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
  orbSelected = false;
  ngOnInit(): void {
    this.selectedEvidence$.pipe(
      distinctUntilChanged((prev, curr) =>
        JSON.stringify(prev) === JSON.stringify(curr)
      ),
      tap(se => {
        const foundOrb = se.find(e => e.value === "GHOST_ORB");
        this.orbSelected = foundOrb !== undefined;
      })
    )
      .subscribe(e => this.ghostService.setSelectedEvidence(e));

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
          if (selectedCount >= this.calculateMaxEvidence()) {
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
    const finalUpdated = finalSelectedCount === this.calculateMaxEvidence()
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