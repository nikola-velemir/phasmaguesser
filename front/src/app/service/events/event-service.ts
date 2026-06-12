import { Injectable, OnDestroy } from "@angular/core";
import { BehaviorSubject } from "rxjs";

// ── Domain types ──────────────────────────────────────────────────────────────

export interface ActiveEffect {
  id: number;
  key: "hunt" | "incense" | "candle";
}

export interface LogEntry {
  timestamp: string;
  message: string;
  colorClass: string;
}

export interface TraitObservedEvent {
  field: string;
  label: string;
  value: boolean | number | string | null;
  timestamp: string;
}

// ── Constants ─────────────────────────────────────────────────────────────────

export const EFFECT_DURATIONS: Record<"hunt" | "incense" | "candle", number> = {
  hunt: 120,
  incense: 180,
  candle: 300,
};

export const EVENT_LOG_COLORS: Record<"hunt" | "incense" | "candle", string> = {
  hunt: "text-red-400",
  incense: "text-blue-400",
  candle: "text-yellow-300",
};

export const EVENT_LOG_LABELS: Record<"hunt" | "incense" | "candle", string> = {
  hunt: "HuntStartedEvent",
  incense: "IncenceUsedEvent",
  candle: "CandleExtinguishedEvent",
};

export const EVENT_WINDOWS: Record<"hunt" | "incense" | "candle", string> = {
  hunt: "120s",
  incense: "3m",
  candle: "5m",
};

// ── Service ───────────────────────────────────────────────────────────────────

@Injectable({
  providedIn: "root",
})
export class EventService implements OnDestroy {

  // ── Elapsed clock (owned here so any component can read it) ───────────────

  private _elapsed = 0;

  get elapsed(): number {
    return this._elapsed;
  }

  /** Called each second by InvestigationTracker's ticker. */
  tick(): void {
    this._elapsed++;
  }

  /** Called by advanceTime and reset. */
  setElapsed(value: number): void {
    this._elapsed = value;
  }

  // ── State ──────────────────────────────────────────────────────────────────

  private readonly _effects = new BehaviorSubject<ActiveEffect[]>([]);
  private readonly _logs = new BehaviorSubject<LogEntry[]>([]);
  private readonly _flashingKeys = new BehaviorSubject<Record<string, boolean>>({});
  private readonly _traitEvents = new BehaviorSubject<TraitObservedEvent[]>([]);

  readonly effects$ = this._effects.asObservable();
  readonly logs$ = this._logs.asObservable();
  readonly flashingKeys$ = this._flashingKeys.asObservable();
  readonly traitEvents$ = this._traitEvents.asObservable();

  private nextEffectId = 0;
  private expiryTimers: ReturnType<typeof setTimeout>[] = [];
  private flashTimers: ReturnType<typeof setTimeout>[] = [];

  // ── Effects ────────────────────────────────────────────────────────────────

  addEffect(key: "hunt" | "incense" | "candle"): void {
    const id = this.nextEffectId++;
    this._effects.next([...this._effects.value, { id, key }]);

    const expiryTimer = setTimeout(() => {
      this._effects.next(this._effects.value.filter((e) => e.id !== id));
    }, EFFECT_DURATIONS[key] * 1000);
    this.expiryTimers.push(expiryTimer);

    this._flash(key);
    this.addLog(`${EVENT_LOG_LABELS[key]} fired — window: ${EVENT_WINDOWS[key]}`, EVENT_LOG_COLORS[key]);
  }

  // ── Trait observed ─────────────────────────────────────────────────────────

  recordTraitObserved(
    field: string,
    label: string,
    value: boolean | number | string | null,
  ): void {
    const timestamp = this._formatTime(this._elapsed);
    const event: TraitObservedEvent = { field, label, value, timestamp };
    this._traitEvents.next([...this._traitEvents.value, event]);

    const isCleared = value === null || value === false || value === "" || value === 0;
    const message = isCleared
      ? `TraitClearedEvent — ${label}`
      : `TraitObservedEvent — ${label}: ${this._formatTraitValue(value)}`;
    const colorClass = isCleared ? "text-zinc-500" : "text-teal-400";

    this.addLog(message, colorClass);
  }

  // ── Logs ───────────────────────────────────────────────────────────────────

  addLog(message: string, colorClass: string): void {
    const timestamp = this._formatTime(this._elapsed);
    this._logs.next([{ timestamp, message, colorClass }, ...this._logs.value]);
  }

  // ── Flash ──────────────────────────────────────────────────────────────────

  flash(key: string): void {
    this._flash(key);
  }

  private _flash(key: string): void {
    this._flashingKeys.next({ ...this._flashingKeys.value, [key]: true });
    const t = setTimeout(() => {
      this._flashingKeys.next({ ...this._flashingKeys.value, [key]: false });
    }, 400);
    this.flashTimers.push(t);
  }

  // ── Reset ──────────────────────────────────────────────────────────────────

  clear(): void {
    this.expiryTimers.forEach((t) => clearTimeout(t));
    this.flashTimers.forEach((t) => clearTimeout(t));
    this.expiryTimers = [];
    this.flashTimers = [];
    this.nextEffectId = 0;
    this._elapsed = 0;
    this._effects.next([]);
    this._logs.next([]);
    this._flashingKeys.next({});
    this._traitEvents.next([]);
  }

  // ── Helpers ────────────────────────────────────────────────────────────────

  private _formatTime(seconds: number): string {
    const m = Math.floor(seconds / 60).toString().padStart(2, "0");
    const s = (seconds % 60).toString().padStart(2, "0");
    return `${m}:${s}`;
  }

  private _formatTraitValue(value: boolean | number | string | null): string {
    if (value === true) return "yes";
    if (value === false) return "no";
    if (value === null) return "—";
    return String(value);
  }

  ngOnDestroy(): void {
    this.clear();
  }
}