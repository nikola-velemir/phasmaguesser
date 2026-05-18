import { Component, signal } from '@angular/core';
import { EvidenceBarComponent } from "./features/evidence-bar/evidence-bar";
import { GhostContainer } from "./features/ghosts/component/ghost-container/ghost-container";

@Component({
  selector: 'app-root',
  imports: [EvidenceBarComponent, GhostContainer],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('phasmaguesser');
}
