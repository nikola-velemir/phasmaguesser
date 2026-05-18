import { Component, OnInit } from '@angular/core';
import { Ghost } from '../../model/ghost';
import { GhostCard } from "../ghost-card/ghost-card";
import { CommonModule } from '@angular/common';
import { GhostService } from '../../../../service/ghost-service';
import { map, Observable } from 'rxjs';

@Component({
  selector: 'app-ghost-container',
  imports: [GhostCard, CommonModule],
  templateUrl: './ghost-container.html',
  styleUrl: './ghost-container.css',
})
export class GhostContainer{
  /**
   *
   */
  ghosts$: Observable<Ghost[]>;
  constructor(private readonly service: GhostService) {
    this.ghosts$ = this.service.ghosts$.pipe(
      map((ghosts) =>
        [...ghosts].sort((a, b) => b.confidence - a.confidence)
      )
    );
  }
  
}
