import { CommonModule, AsyncPipe } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { BehaviorSubject, map, Subscription } from 'rxjs';
import { Observation } from './observation';
import { GhostService } from '../../../../service/ghost-service';

export interface ToggleField {
  field: keyof Observation;
  label: string;
  hint?: string;
  revealsSectionId?: string;
}

export interface NumberField {
  field: keyof Observation;
  label: string;
  hint?: string;
  placeholder: string;
}

export interface SelectField {
  field: keyof Observation;
  label: string;
  options: { value: string; label: string }[];
}

export type SectionItem =
  | { kind: 'toggle'; config: ToggleField }
  | { kind: 'toggle-child'; config: ToggleField; parentSectionId: string }
  | { kind: 'number'; config: NumberField }
  | { kind: 'select'; config: SelectField };

export interface Section {
  id: string;
  label: string;
  icon: string;
  iconColor: string;
  items: SectionItem[];
  expanded: boolean;
}

@Component({
  selector: 'app-observation-bar',
  imports: [CommonModule],
  templateUrl: './observation-bar.html',
  styleUrl: './observation-bar.css',
})
export class ObservationBar implements OnInit, OnDestroy {


  /**
   *
   */
  constructor(private ghostService: GhostService) {
  }
  // ── Core reactive state ──────────────────────────────────────────
  readonly state$ = new BehaviorSubject<Partial<Observation>>({});

  /** IDs of conditional sub-rows that are currently visible */
  visibleSubRows = new Set<string>();
  expanded = true;

  // ── Derived streams ──────────────────────────────────────────────



  readonly filledFields$ = this.state$.pipe(
    map(state => this.sections.reduce(
      (acc, s) => acc + this.filledCountForSectionState(s, state), 0
    ))
  );

  readonly progressPercent$ = this.filledFields$.pipe(
    map(filled => Math.round((filled / this.getTotalFields()) * 100))
  );

  private readonly sub = new Subscription();

  // ── Sections schema ──────────────────────────────────────────────
  sections: Section[] = [
    {
      id: 'temp',
      label: 'Temperature & environment',
      icon: 'ti ti-thermometer',
      iconColor: 'text-blue-400',
      expanded: false,
      items: [
        {
          kind: 'toggle',
          config: {
            field: 'ghostMovingFastInCold',
            label: 'Ghost moving fast in cold',
            hint: 'Notably accelerated in freezing areas',
          },
        },
      ],
    },
    {
      id: 'hunt',
      label: 'Hunt triggers & items',
      icon: 'ti ti-flame',
      iconColor: 'text-orange-400',
      expanded: false,
      items: [
        {
          kind: 'toggle',
          config: {
            field: 'incenseUsed',
            label: 'Incense used before hunt',
            revealsSectionId: 'incense-time',
          },
        },
        {
          kind: 'toggle-child',
          parentSectionId: 'incense-time',
          config: {
            field: 'secondsUntilHuntAfterIncense',
            label: 'Seconds until hunt after incense',
            hint: 'Leave blank if ghost did not hunt',
          } as any,
        },
        {
          kind: 'toggle',
          config: {
            field: 'huntTriggeredByVoice',
            label: 'Hunt triggered by voice',
            hint: 'Ghost reacted to players speaking',
          },
        },
        {
          kind: 'toggle',
          config: { field: 'huntStartedInDarkRoom', label: 'Hunt started in dark room' },
        },
        {
          kind: 'toggle',
          config: {
            field: 'huntAtHighSanity',
            label: 'Hunt at high sanity',
            hint: 'Team sanity was high when hunt started',
          },
        },
      ],
    },
    {
      id: 'speed',
      label: 'Speed & movement',
      icon: 'ti ti-run',
      iconColor: 'text-red-400',
      expanded: false,
      items: [
        {
          kind: 'number',
          config: { field: 'highHuntSpeed', label: 'Approximate high hunt speed', placeholder: 'val' },
        },
        {
          kind: 'toggle',
          config: {
            field: 'fuseBoxOn',
            label: 'Fuse box on during hunt',
            revealsSectionId: 'fusebox-accel',
          },
        },
        {
          kind: 'toggle-child',
          parentSectionId: 'fusebox-accel',
          config: {
            field: 'ghostAcceleratedWithFuseBox',
            label: 'Ghost accelerated with fuse box on',
          },
        },
        {
          kind: 'toggle',
          config: {
            field: 'ghostSlowedDownNearPlayer',
            label: 'Ghost slowed near player',
            hint: 'Noticeable deceleration when approaching',
          },
        },
        {
          kind: 'toggle',
          config: { field: 'ghostSpeedDeclinedOverTime', label: 'Speed declined over time' },
        },
        {
          kind: 'toggle',
          config: { field: 'footstepsVeryQuietDuringHunt', label: 'Footsteps very quiet during hunt' },
        },
      ],
    },
    {
      id: 'players',
      label: 'Players & activity',
      icon: 'ti ti-users',
      iconColor: 'text-violet-400',
      expanded: false,
      items: [
        {
          kind: 'toggle',
          config: { field: 'multiplePlayersPresent', label: 'Multiple players present' },
        },
        {
          kind: 'toggle',
          config: { field: 'activityDroppedWithPlayers', label: 'Activity dropped with more players' },
        },
        {
          kind: 'toggle',
          config: { field: 'activityIncreasedWithPlayers', label: 'Activity increased with more players' },
        },
        {
          kind: 'toggle',
          config: {
            field: 'ghostAlwaysKnewPlayerPosition',
            label: 'Ghost always knew player position',
            hint: 'Hiding never worked',
          },
        },
        {
          kind: 'toggle',
          config: { field: 'activityDeclinedOverTime', label: 'Activity declined over time' },
        },
        {
          kind: 'select',
          config: {
            field: 'ghostActivityLevel',
            label: 'Overall ghost activity level',
            options: [
              { value: 'LOW', label: 'Low' },
              { value: 'MEDIUM', label: 'Medium' },
              { value: 'HIGH', label: 'High' },
              { value: 'EXTREME', label: 'Extreme' },
            ],
          },
        },
      ],
    },
    {
      id: 'evidence',
      label: 'Evidence & senses',
      icon: 'ti ti-camera',
      iconColor: 'text-teal-400',
      expanded: false,
      items: [
        {
          kind: 'toggle',
          config: { field: 'saltPlaced', label: 'Salt placed in ghost area', revealsSectionId: 'salt-fp' },
        },
        {
          kind: 'toggle-child',
          parentSectionId: 'salt-fp',
          config: { field: 'saltFootprintFound', label: 'Footprint found in salt' },
        },
        {
          kind: 'toggle',
          config: { field: 'ghostPhotoTaken', label: 'Ghost photo taken', revealsSectionId: 'photo-vanish' },
        },
        {
          kind: 'toggle-child',
          parentSectionId: 'photo-vanish',
          config: { field: 'ghostDisappearedAfterPhoto', label: 'Ghost disappeared after photo' },
        },
        {
          kind: 'number',
          config: { field: 'objectsThrownAtOnce', label: 'Objects thrown at once', placeholder: '0' },
        },
        {
          kind: 'toggle',
          config: {
            field: 'parabolicMicrophoneUsed',
            label: 'Parabolic microphone used',
            revealsSectionId: 'scream-row',
          },
        },
        {
          kind: 'toggle-child',
          parentSectionId: 'scream-row',
          config: { field: 'uniqueScreamHeard', label: 'Unique scream heard via mic' },
        },
        {
          kind: 'toggle',
          config: { field: 'dotsVisibleOnCamera', label: 'DOTS visible on camera' },
        },
        {
          kind: 'toggle',
          config: { field: 'dotsVisibleToNakedEye', label: 'DOTS visible to naked eye' },
        },
        {
          kind: 'toggle',
          config: { field: 'sixFingerHandprintFound', label: 'Six-finger handprint found' },
        },
      ],
    },
    {
      id: 'elec',
      label: 'Electronics & candles',
      icon: 'ti ti-bolt',
      iconColor: 'text-amber-400',
      expanded: false,
      items: [
        {
          kind: 'toggle',
          config: {
            field: 'activeElectronicsNearby',
            label: 'Active electronics nearby during hunt',
            revealsSectionId: 'elec-speed',
          },
        },
        {
          kind: 'toggle-child',
          parentSectionId: 'elec-speed',
          config: { field: 'ghostSpeedIncreasedNearElectronics', label: 'Ghost speed increased near electronics' },
        },
        {
          kind: 'number',
          config: {
            field: 'candlesExtinguishedBeforeHunt',
            label: 'Candles extinguished before hunt',
            placeholder: '0',
          },
        },
        {
          kind: 'toggle',
          config: { field: 'huntStartedAfterCandleOut', label: 'Hunt started right after candle went out' },
        },
      ],
    },
    {
      id: 'behavior',
      label: 'Behavior & timing',
      icon: 'ti ti-clock',
      iconColor: 'text-violet-400',
      expanded: false,
      items: [
        {
          kind: 'number',
          config: {
            field: 'secondsBetweenHunts',
            label: 'Seconds between hunts',
            hint: 'Cooldown time observed',
            placeholder: 'sec',
          },
        },
        {
          kind: 'toggle',
          config: { field: 'doorSlammedAndLockedInRoom', label: 'Door slammed and locked players in room' },
        },
        {
          kind: 'toggle',
          config: {
            field: 'simultaneousInteractionsInDifferentRooms',
            label: 'Simultaneous interactions in different rooms',
            hint: 'Ghost seemed to act in multiple places',
          },
        },
        {
          kind: 'toggle',
          config: { field: 'behaviorChangedMidInvestigation', label: 'Behavior changed mid-investigation' },
        },
        {
          kind: 'toggle',
          config: {
            field: 'spiritBoxResponseReceived',
            label: 'Spirit box response received',
            revealsSectionId: 'sanity-drop',
          },
        },
        {
          kind: 'toggle-child',
          parentSectionId: 'sanity-drop',
          config: {
            field: 'playerSanityDroppedFastAfterResponse',
            label: 'Sanity dropped fast after spirit box response',
          },
        },
      ],
    },
  ];
  /** Total number of schema fields across all sections */
  private readonly totalFields = this.sections
    ? 0  // initialised below after sections is defined
    : 0;
  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

  // ── Section toggle ────────────────────────────────────────────────
  toggleSection(section: Section): void {
    section.expanded = !section.expanded;
  }

  // ── Field reads (from current snapshot for template bindings) ─────
  isChecked(field: keyof Observation): boolean {
    return !!this.state$.value[field];
  }

  getNumberValue(field: keyof Observation): string {
    const v = this.state$.value[field];
    return v != null ? String(v) : '';
  }

  getSelectValue(field: keyof Observation): string {
    return (this.state$.value[field] as string) ?? '';
  }

  isSubRowVisible(sectionId: string): boolean {
    return this.visibleSubRows.has(sectionId);
  }

  // ── Field writes (patch BehaviorSubject) ──────────────────────────
  onToggleChange<K extends keyof Observation>(
    field: K,
    checked: boolean,
    revealsSectionId?: string
  ): void {
    // Build the patch, starting with the toggled field
    const patch: Partial<Observation> = { [field]: checked as Observation[K] };

    if (revealsSectionId) {
      if (checked) {
        this.visibleSubRows.add(revealsSectionId);
      } else {
        this.visibleSubRows.delete(revealsSectionId);
        // Null-out children so they don't linger in state
        this.collectChildFields(revealsSectionId).forEach(f => {
          (patch as any)[f] = null;
        });
      }
    }

    this.patch(patch);
  }

  onNumberInput<K extends keyof Observation>(field: K, value: string): void {
    this.patch({
      [field]: (value.trim() === '' ? null : parseInt(value, 10)) as Observation[K],
    });
  }

  onSelectChange<K extends keyof Observation>(field: K, value: string): void {
    this.patch({ [field]: (value || null) as Observation[K] });
  }

  // ── Badge helpers (use snapshot — called from template, no async needed) ──
  filledCountForSection(section: Section): number {
    return this.filledCountForSectionState(section, this.state$.value);
  }

  // ── Reset ─────────────────────────────────────────────────────────
  resetForm(): void {
    this.visibleSubRows.clear();
    this.state$.next({});
  }

  // ── Analyse ───────────────────────────────────────────────────────
  analyzeObservation(): void {
    const s = this.state$.value;
    const lines: string[] = [];

    if (s.freezingTemperatures) lines.push('- Freezing temperatures observed');
    if (s.ghostMovingFastInCold) lines.push('- Ghost moved fast in cold');
    if (s.incenseUsed)
      lines.push(`- Incense used${s.secondsUntilHuntAfterIncense != null ? ` (hunt after ${s.secondsUntilHuntAfterIncense}s)` : ''}`);
    if (s.huntTriggeredByVoice) lines.push('- Hunt triggered by voice');
    if (s.huntStartedInDarkRoom) lines.push('- Hunt started in dark room');
    if (s.huntAtHighSanity) lines.push('- Hunt at high sanity');
    if (s.fuseBoxOn) lines.push(`- Fuse box was on${s.ghostAcceleratedWithFuseBox ? ' (ghost accelerated)' : ''}`);
    if (s.ghostSlowedDownNearPlayer) lines.push('- Ghost slowed near player');
    if (s.ghostSpeedDeclinedOverTime) lines.push('- Speed declined over time');
    if (s.footstepsVeryQuietDuringHunt) lines.push('- Footsteps very quiet during hunt');
    if (s.highHuntSpeed) lines.push(`- High hunt speed: ${s.highHuntSpeed}`);
    if (s.saltPlaced) lines.push(`- Salt placed${s.saltFootprintFound ? ' (footprint found)' : ' (no footprint)'}`);
    if (s.ghostPhotoTaken) lines.push(`- Ghost photo taken${s.ghostDisappearedAfterPhoto ? ' (disappeared after)' : ''}`);
    if ((s.objectsThrownAtOnce as number) > 0) lines.push(`- ${s.objectsThrownAtOnce} objects thrown at once`);
    if (s.parabolicMicrophoneUsed) lines.push(`- Parabolic mic used${s.uniqueScreamHeard ? ' (unique scream heard)' : ''}`);
    if (s.dotsVisibleOnCamera) lines.push('- DOTS on camera');
    if (s.dotsVisibleToNakedEye) lines.push('- DOTS visible to naked eye');
    if (s.sixFingerHandprintFound) lines.push('- Six-finger handprint found');
    if (s.multiplePlayersPresent) lines.push('- Multiple players present');
    if (s.activityDroppedWithPlayers) lines.push('- Activity dropped with more players');
    if (s.activityIncreasedWithPlayers) lines.push('- Activity increased with more players');
    if (s.ghostAlwaysKnewPlayerPosition) lines.push('- Ghost always knew player position');
    if (s.activityDeclinedOverTime) lines.push('- Activity declined over time');
    if (s.ghostActivityLevel) lines.push(`- Activity level: ${s.ghostActivityLevel}`);
    if (s.activeElectronicsNearby) lines.push(`- Active electronics nearby${s.ghostSpeedIncreasedNearElectronics ? ' (ghost sped up)' : ''}`);
    if ((s.candlesExtinguishedBeforeHunt as number) > 0) lines.push(`- ${s.candlesExtinguishedBeforeHunt} candle(s) extinguished before hunt`);
    if (s.huntStartedAfterCandleOut) lines.push('- Hunt started after candle went out');
    if (s.secondsBetweenHunts != null) lines.push(`- ${s.secondsBetweenHunts}s between hunts`);
    if (s.doorSlammedAndLockedInRoom) lines.push('- Door slammed and locked players in room');
    if (s.simultaneousInteractionsInDifferentRooms) lines.push('- Simultaneous interactions in different rooms');
    if (s.behaviorChangedMidInvestigation) lines.push('- Behavior changed mid-investigation');
    if (s.spiritBoxResponseReceived)
      lines.push(`- Spirit box response received${s.playerSanityDroppedFastAfterResponse ? ' (sanity dropped fast after)' : ''}`);

    // Emit or route as needed in the real app
  }

  // ── Private helpers ───────────────────────────────────────────────

  /** Emit a shallow-merged patch into the BehaviorSubject */
  private patch(partial: Partial<Observation>): void {
    this.state$.next({ ...this.state$.value, ...partial });
  }

  private isFilled(v: Observation[keyof Observation] | null | undefined): boolean {
    return v !== undefined && v !== null && v !== '' && v !== false && v !== 0;
  }

  private filledCountForSectionState(
    section: Section,
    state: Partial<Observation>
  ): number {
    return section.items.filter(item => this.isFilled(state[item.config.field])).length;
  }

  private getTotalFields(): number {
    return this.sections.reduce((acc, s) => acc + s.items.length, 0);
  }

  /** Collect all child fields that belong to a conditional sub-row */
  private collectChildFields(sectionId: string): Array<keyof Observation> {
    const fields: Array<keyof Observation> = [];
    for (const section of this.sections) {
      for (const item of section.items) {
        if (item.kind === 'toggle-child' && item.parentSectionId === sectionId) {
          fields.push(item.config.field);
        }
      }
    }
    return fields;
  }

  ngOnInit(): void {
    this.state$.subscribe(o => this.ghostService.setObservation(o))
  }
}