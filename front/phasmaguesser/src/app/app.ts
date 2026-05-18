import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { EvidenceBarComponent } from "./evidence-bar/evidence-bar";

@Component({
  selector: 'app-root',
  imports: [EvidenceBarComponent],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('phasmaguesser');
}
