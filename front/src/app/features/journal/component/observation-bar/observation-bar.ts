import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { BehaviorSubject, combineLatest, map, Observable, of, Subscription } from 'rxjs';
import { Observation } from './observation';
import { GhostService } from '../../../../service/ghost-service';
import { EventService } from '../../../../service/events/event-service';
import { EventApiService } from '../../../../service/events/event-api-service';


export interface ToggleField {
  field: keyof Observation;
  label: string;
  value?: string;
  hint?: string;
  revealsSectionId?: string;
}

export interface NumberField {
  field: keyof Observation;
  label: string; value?: string;
  hint?: string;
  placeholder: string;
}

export interface SelectField {
  field: keyof Observation; value?: string;
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
  running$: Observable<boolean> = of(false);
  isRecordButtonDisabled$: Observable<boolean> = of(true);
  constructor(
    private ghostService: GhostService,
    private eventService: EventService,
    private eventApiService: EventApiService,
  ) {
    this.running$ = eventService.running$;

    this.isRecordButtonDisabled$ = combineLatest([this.running$, this.filledFields$])
      .pipe(map(([running, filledFields]) =>
        running === false || filledFields === 0


      ))
  }

  // ── Core reactive state ──────────────────────────────────────────
  readonly state$ = new BehaviorSubject<Partial<Observation>>({});

  /** IDs of conditional sub-rows that are currently visible */
  visibleSubRows = new Set<string>();
  expanded = true;

  /** Whether the record button flash overlay is active */
  recordFlashing = false;

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
            value: 'Temperature_Based_Speed',
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
        // {
        //   kind: 'toggle',
        //   config: {
        //     value: '',
        //     field: 'incenseUsed',
        //     label: 'Incense used before hunt',
        //     revealsSectionId: 'incense-time',
        //   },
        // },
        // {
        //   kind: 'toggle-child',
        //   parentSectionId: 'incense-time',
        //   config: {
        //     field: 'secondsUntilHuntAfterIncense',
        //     label: 'Seconds until hunt after incense',
        //     hint: 'Leave blank if ghost did not hunt',
        //   } as any,
        // },
        {
          kind: 'toggle',
          config: {
            value: 'Voice_Sensitivity',
            field: 'huntTriggeredByVoice',
            label: 'Hunt triggered by voice',
            hint: 'Ghost reacted to players speaking',
          },
        },
        {
          kind: 'toggle',
          config: { field: 'huntStartedInDarkRoom', label: 'Hunt started in dark room', value: 'Attack_In_Darkness' },
        },
        {
          kind: 'toggle',
          config: {
            value: 'Early_Hunt_Low_Cooldown',
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
        // {
        //   kind: 'number',
        //   config: {
        //     value: 'High_Hunt_Speed',
        //     field: 'highHuntSpeed',
        //     label: 'Approximate high hunt speed', placeholder: 'val'
        //   },
        // },
        {
          kind: 'toggle',
          config: {
            value: '',
            field: 'fuseBoxOn',
            label: 'Fuse box on during hunt',
            revealsSectionId: 'fusebox-accel',
          },
        },
        {
          kind: 'toggle-child',
          parentSectionId: 'fusebox-accel',
          config: {
            value: 'Speed_Increase_With_Fuse_Box',
            field: 'ghostAcceleratedWithFuseBox',
            label: 'Ghost accelerated with fuse box on',
          },
        },
        {
          kind: 'toggle',
          config: {
            value: 'Close_Slowdown',
            field: 'ghostSlowedDownNearPlayer',
            label: 'Ghost slowed near player',
            hint: 'Noticeable deceleration when approaching',
          },
        },
        {
          kind: 'toggle',
          config: {
            value: 'Aging_Mechanic',
            field: 'ghostSpeedDeclinedOverTime', label: 'Speed declined over time'
          },
        },
        {
          kind: 'toggle',
          config: { value: 'Quiet_Footsteps_During_Hunt', field: 'footstepsVeryQuietDuringHunt', label: 'Footsteps very quiet during hunt' },
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
          config: { value: 'High_Activity_Near_Player', field: 'activityDroppedWithPlayers', label: 'Activity dropped with more players' },
        },
        {
          kind: 'toggle',
          config: { value: 'Shy_In_Presence', field: 'activityIncreasedWithPlayers', label: 'Activity increased with more players' },
        },
        {
          kind: 'toggle',
          config: {
            value: 'Player_Tracking',
            field: 'ghostAlwaysKnewPlayerPosition',
            label: 'Ghost always knew player position',
            hint: 'Hiding never worked',
          },
        },
        {
          kind: 'toggle',
          config: { value: 'Aging_Mechanic', field: 'activityDeclinedOverTime', label: 'Activity declined over time' },
        },
        // {
        //   kind: 'select',
        //   config: {
        //     field: 'ghostActivityLevel',
        //     label: 'Overall ghost activity level',
        //     options: [
        //       { value: 'LOW', label: 'Low' },
        //       { value: 'MEDIUM', label: 'Medium' },
        //       { value: 'HIGH', label: 'High' },
        //       { value: 'EXTREME', label: 'Extreme' },
        //     ],
        //   },
        // },
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

          config: { value: 'No_Salt_Footprints', field: 'saltPlaced', label: 'Salt placed in ghost area', revealsSectionId: 'salt-fp' },
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
          config: { value: 'Photo_Disappearance', field: 'ghostDisappearedAfterPhoto', label: 'Ghost disappeared after photo' },
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
          config: { value: 'Parabolic_Scream', field: 'uniqueScreamHeard', label: 'Unique scream heard via mic' },
        },
        {
          kind: 'toggle',
          config: { field: 'dotsVisibleOnCamera', label: 'DOTS visible on camera' },
        },
        {
          kind: 'toggle',
          config: { value: 'Camera_Only_DOTS', field: 'dotsVisibleToNakedEye', label: 'DOTS visible to naked eye' },
        },
        {
          kind: 'toggle',
          config: { value: 'Six_Finger_Handprint', field: 'sixFingerHandprintFound', label: 'Six-finger handprint found' },
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
          config: { value: 'Electronics_Speed_Boost', field: 'ghostSpeedIncreasedNearElectronics', label: 'Ghost speed increased near electronics' },
        },
        // {
        //   kind: 'number',
        //   config: {
        //     field: 'candlesExtinguishedBeforeHunt',
        //     label: 'Candles extinguished before hunt',
        //     placeholder: '0',
        //   },
        // },
        // {
        //   kind: 'toggle',
        //   config: { field: 'huntStartedAfterCandleOut', label: 'Hunt started right after candle went out' },
        // },
      ],
    },
    {
      id: 'behavior',
      label: 'Behavior & timing',
      icon: 'ti ti-clock',
      iconColor: 'text-violet-400',
      expanded: false,
      items: [
        // {
        //   kind: 'number',
        //   config: {
        //     field: 'secondsBetweenHunts',
        //     label: 'Seconds between hunts',
        //     hint: 'Cooldown time observed',
        //     placeholder: 'sec',
        //   },
        // },
        {
          kind: 'toggle',
          config: { value: 'Door_Manipulation', field: 'doorSlammedAndLockedInRoom', label: 'Door slammed and locked players in room' },
        },
        {
          kind: 'toggle',
          config: {
            value: 'Dual_Interactions',
            field: 'simultaneousInteractionsInDifferentRooms',
            label: 'Simultaneous interactions in different rooms',
            hint: 'Ghost seemed to act in multiple places',
          },
        },
        // {
        //   kind: 'toggle',
        //   config: { field: 'behaviorChangedMidInvestigation', label: 'Behavior changed mid-investigation' },
        // },
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
            value: 'Curse_And_Speed_Sanity',
            field: 'playerSanityDroppedFastAfterResponse',
            label: 'Sanity dropped fast after spirit box response',
          },
        },
      ],
    },
  ];

  ngOnInit(): void {
    this.running$.subscribe((s) => console.log(s))
    this.sub.add(
      this.state$.subscribe(o => this.ghostService.setObservation(o))
    );
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

  // ── Section toggle ────────────────────────────────────────────────
  toggleSection(section: Section): void {
    section.expanded = !section.expanded;
  }

  // ── Field reads ───────────────────────────────────────────────────
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

  // ── Field writes ──────────────────────────────────────────────────
  onToggleChange<K extends keyof Observation>(
    field: K,
    checked: boolean,
    revealsSectionId?: string
  ): void {
    const patch: Partial<Observation> = { [field]: checked as Observation[K] };

    if (revealsSectionId) {
      if (checked) {
        this.visibleSubRows.add(revealsSectionId);
      } else {
        this.visibleSubRows.delete(revealsSectionId);
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

  // ── Badge helpers ─────────────────────────────────────────────────
  filledCountForSection(section: Section): number {
    return this.filledCountForSectionState(section, this.state$.value);
  }

  // ── Record observations ───────────────────────────────────────────

  /**
   * Explicitly push all currently-filled observations as a batch of
   * TraitObservedEvents into EventService, where they surface in the
   * investigation-tracker log.
   */
  recordObservations(): void {
    const state = this.state$.value;
    let count = 0;

    for (const section of this.sections) {
      for (const item of section.items) {
        const field = item.config.field;
        const value = state[field];
        if (this.isFilled(value)) {
          const coerced = value as boolean | number | string | null;
          this.eventService.recordTraitObserved(field, item.config.label, coerced);
          const traitName = item.config.value;
          if (traitName) {
            this.eventApiService.recordTraitObserved({
              traitName,
              timestamp: Date.now(),
            }).subscribe();
          }
          count++;
        }
      }

    }

    if (count === 0) return;

    // Flash the button as confirmation
    this.recordFlashing = true;
    setTimeout(() => { this.recordFlashing = false; }, 400);
    this.resetForm()
    this.ghostService.resetObservation()
  }

  // ── Reset ─────────────────────────────────────────────────────────
  resetForm(): void {
    this.visibleSubRows.clear();
    this.state$.next({});
  }

  // ── Private helpers ───────────────────────────────────────────────
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
}