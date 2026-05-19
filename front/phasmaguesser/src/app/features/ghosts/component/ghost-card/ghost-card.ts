import { Component, Input } from '@angular/core';
import { Ghost } from '../../model/ghost';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-ghost-card',
  imports: [CommonModule],
  templateUrl: './ghost-card.html',
  styleUrl: './ghost-card.css',
})
export class GhostCard {
  @Input({ required: true }) ghost!: Ghost;

}
