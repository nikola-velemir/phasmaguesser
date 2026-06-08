import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, OnDestroy } from '@angular/core';

// ── Domain types ─────────────────────────────────────────────────────────────

export interface ActiveEffect {
  id: number;
  key: 'hunt' | 'incense' | 'candle';
}

export interface LogEntry {
  timestamp: string;
  message: string;
  colorClass: string;
}

// ── Component ─────────────────────────────────────────────────────────────────

@Component({
  selector: 'app-investigation-tracker',
  imports: [CommonModule],
  templateUrl: './investigation-tracker.html',
  styleUrl: './investigation-tracker.css',
})
export class InvestigationTracker implements OnDestroy {

  // ── Clock ──────────────────────────────────────────────────────────────────

  elapsed = 0;
  running = false;
  private ticker: ReturnType<typeof setInterval> | null = null;
  private nextEffectId = 0;
  private expiryTimers: ReturnType<typeof setTimeout>[] = [];

  get clockDisplay(): string {
    return this.formatTime(this.elapsed);
  }

  get clockHunting(): boolean {
    return this.running && this.effects.some(e => e.key === 'hunt');
  }

  // ── Active CEP windows ────────────────────────────────────────────────────

  effects: ActiveEffect[] = [];

  private readonly effectDurations: Record<'hunt' | 'incense' | 'candle', number> = {
    hunt: 120,
    incense: 180,
    candle: 300,
  };

  // ── CEP event triggers ────────────────────────────────────────────────────

  flashingKeys: Record<string, boolean> = {};

  private readonly eventLogColors: Record<'hunt' | 'incense' | 'candle', string> = {
    hunt:    'text-red-400',
    incense: 'text-blue-400',
    candle:  'text-yellow-300',
  };

  private readonly eventLogLabels: Record<'hunt' | 'incense' | 'candle', string> = {
    hunt:    'HuntStartedEvent',
    incense: 'IncenceUsedEvent',
    candle:  'CandleExtinguishedEvent',
  };

  private readonly eventWindows: Record<'hunt' | 'incense' | 'candle', string> = {
    hunt: '120s', incense: '3m', candle: '5m',
  };

  fireCepEvent(key: 'hunt' | 'incense' | 'candle'): void {
    if (!this.running) return;

    const id = this.nextEffectId++;
    this.effects = [...this.effects, { id, key }];

    const expiryTimer = setTimeout(() => {
      this.effects = this.effects.filter(e => e.id !== id);
      this.cdr.markForCheck();
    }, this.effectDurations[key] * 1000);
    this.expiryTimers.push(expiryTimer);

    this.flashingKeys = { ...this.flashingKeys, [key]: true };
    setTimeout(() => {
      this.flashingKeys = { ...this.flashingKeys, [key]: false };
      this.cdr.markForCheck();
    }, 400);

    this.addLog(
      `${this.eventLogLabels[key]} fired — window: ${this.eventWindows[key]}`,
      this.eventLogColors[key],
    );
    this.cdr.markForCheck();
  }

  isFlashing(key: string): boolean {
    return !!this.flashingKeys[key];
  }

  trackEffectById(_index: number, effect: ActiveEffect): number {
    return effect.id;
  }

  // ── Log ───────────────────────────────────────────────────────────────────

  logs: LogEntry[] = [];

  private addLog(message: string, colorClass: string): void {
    this.logs = [{ timestamp: this.formatTime(this.elapsed), message, colorClass }, ...this.logs];
  }

  // ── Begin / Stop / Reset ──────────────────────────────────────────────────

  begin(): void {
    if (this.running) return;
    this.running = true;
    this.addLog('Investigation begun', 'text-zinc-400');
    this.ticker = setInterval(() => {
      this.elapsed++;
      this.cdr.markForCheck();
    }, 1000);
  }

  stop(): void {
    if (!this.running) return;
    this.running = false;
    if (this.ticker) { clearInterval(this.ticker); this.ticker = null; }
    this.addLog(`Investigation stopped at ${this.formatTime(this.elapsed)}`, 'text-red-400');
    this.reset()
  }

  reset(): void {
    this.stop();
    this.expiryTimers.forEach(t => clearTimeout(t));
    this.expiryTimers = [];
    this.elapsed = 0;
    this.effects = [];
    this.logs = [];
    this.flashingKeys = {};
    this.nextEffectId = 0;
  }

  // ── Helpers ───────────────────────────────────────────────────────────────

  private formatTime(seconds: number): string {
    const m = Math.floor(seconds / 60).toString().padStart(2, '0');
    const s = (seconds % 60).toString().padStart(2, '0');
    return `${m}:${s}`;
  }

  constructor(private cdr: ChangeDetectorRef) {}

  ngOnDestroy(): void {
    if (this.ticker) clearInterval(this.ticker);
    this.expiryTimers.forEach(t => clearTimeout(t));
  }
}