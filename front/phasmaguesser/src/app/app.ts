import { Component, signal } from '@angular/core';
import { GhostContainer } from "./features/ghosts/component/ghost-container/ghost-container";
import { JournalComponent } from "./features/journal/component/journal/journal";

@Component({
  selector: 'app-root',
  imports: [GhostContainer, JournalComponent],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('phasmaguesser');
}
