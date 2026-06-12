import { CommonModule } from "@angular/common";
import { ChangeDetectionStrategy, ChangeDetectorRef, Component, OnDestroy, OnInit } from "@angular/core";
import { Subscription } from "rxjs";
import { EventApiService } from "../../../../service/events/event-api-service";
import { ClockService } from "../../../../service/clock/clock-service";
import { ActiveEffect, EventService, LogEntry } from "../../../../service/events/event-service";

@Component({
  selector: "app-investigation-tracker",
  imports: [CommonModule],
  templateUrl: "./investigation-tracker.html",
  styleUrl: "./investigation-tracker.css",
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class InvestigationTracker implements OnInit, OnDestroy {

  // ── Clock ──────────────────────────────────────────────────────────────────

  running = false;
  private ticker: ReturnType<typeof setInterval> | null = null;

  get elapsed(): number {
    return this.eventService.elapsed;
  }

  get clockDisplay(): string {
    return this.formatTime(this.elapsed);
  }

  get clockHunting(): boolean {
    return this.running && this.effects.some((e) => e.key === "hunt");
  }

  // ── Projected state from EventService ─────────────────────────────────────

  effects: ActiveEffect[] = [];
  logs: LogEntry[] = [];
  flashingKeys: Record<string, boolean> = {};

  private subs = new Subscription();

  constructor(
    private readonly eventService: EventService,
    private readonly eventApiService: EventApiService,
    private readonly clockService: ClockService,
    private readonly cdr: ChangeDetectorRef,
  ) {}

  ngOnInit(): void {
    this.subs.add(this.eventService.effects$.subscribe((effects) => {
      this.effects = effects;
      this.cdr.markForCheck();
    }));
    this.subs.add(this.eventService.logs$.subscribe((logs) => {
      this.logs = logs;
      this.cdr.markForCheck();
    }));
    this.subs.add(this.eventService.flashingKeys$.subscribe((keys) => {
      this.flashingKeys = keys;
      this.cdr.markForCheck();
    }));
  }

  ngOnDestroy(): void {
    this.subs.unsubscribe();
    if (this.ticker) clearInterval(this.ticker);
  }

  // ── CEP event triggers ─────────────────────────────────────────────────────

  fireCepEvent(key: "hunt" | "incense" | "candle"): void {
    if (!this.running) return;

    this.eventService.addEffect(key);

    const timestamp = Date.now();
    switch (key) {
      case "hunt":   this.eventApiService.insertHuntStarted({ timestamp }).subscribe(); break;
      case "incense": this.eventApiService.insertIncenseUsed({ timestamp }).subscribe(); break;
      case "candle":  this.eventApiService.insertCandleExtinguished({ timestamp }).subscribe(); break;
    }
  }

  advanceTime(seconds: 5 | 10 | 30): void {
    if (!this.running) return;

    this.eventService.setElapsed(this.elapsed + seconds);
    this.eventService.flash(`advance${seconds}`);
    this.eventService.addLog(`Time advanced by ${seconds}s`, "text-violet-400");
    this.cdr.markForCheck();

    this.clockService.advanceTime(seconds).subscribe(() =>
      console.log(`Advanced pseudo clock by ${seconds}s`)
    );
  }

  isFlashing(key: string): boolean {
    return !!this.flashingKeys[key];
  }

  trackEffectById(_index: number, effect: ActiveEffect): number {
    return effect.id;
  }

  // ── Begin / Stop / Reset ───────────────────────────────────────────────────

  begin(): void {
    if (this.running) return;
    this.running = true;
    this.eventService.addLog("Investigation begun", "text-zinc-400");
    this.ticker = setInterval(() => {
      this.eventService.tick();
      this.cdr.markForCheck();
    }, 1000);
  }

  stop(): void {
    if (!this.running) return;
    this.running = false;
    if (this.ticker) { clearInterval(this.ticker); this.ticker = null; }
    this.eventService.addLog(
      `Investigation stopped at ${this.formatTime(this.elapsed)}`,
      "text-red-400",
    );
    this.reset();
  }

  reset(): void {
    this.stop();
    this.eventService.clear();
    this.eventApiService.clearEvents().subscribe(() => console.log("Cleared all events!"));
  }

  // ── Helpers ────────────────────────────────────────────────────────────────

  private formatTime(seconds: number): string {
    const m = Math.floor(seconds / 60).toString().padStart(2, "0");
    const s = (seconds % 60).toString().padStart(2, "0");
    return `${m}:${s}`;
  }
}