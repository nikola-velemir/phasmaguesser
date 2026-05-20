import { Component, ElementRef, HostListener, Input, ViewChild } from '@angular/core';
import { Ghost } from '../../model/ghost';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-ghost-card',
  imports: [CommonModule],
  templateUrl: './ghost-card.html',
  styleUrl: './ghost-card.css',
})
export class GhostCard {
  expanded = false;
  @Input({ required: true }) ghost!: Ghost;
  @ViewChild('accordionRef') accordionRef!: ElementRef;

  dropdownTop = 0;
  dropdownLeft = 0;
  dropdownWidth = 0;

  toggleExpanded(): void {
    if (!this.expanded) {
      const rect = (this.accordionRef.nativeElement as HTMLElement).getBoundingClientRect();
      this.dropdownTop = rect.bottom + 8;
      this.dropdownLeft = rect.left;
      this.dropdownWidth = rect.width;
    }
    this.expanded = !this.expanded;
  }

  @HostListener('window:scroll')
  @HostListener('window:resize')
  onScroll(): void {
    if (this.expanded) {
      const rect = (this.accordionRef.nativeElement as HTMLElement).getBoundingClientRect();
      this.dropdownTop = rect.bottom + 8;
      this.dropdownLeft = rect.left;
      this.dropdownWidth = rect.width;
    }
  }

}
