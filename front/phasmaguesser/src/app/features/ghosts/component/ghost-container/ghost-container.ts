import { Component } from '@angular/core';
import { Ghost } from '../../model/ghost';
import { GhostCard } from "../ghost-card/ghost-card";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-ghost-container',
  imports: [GhostCard, CommonModule],
  templateUrl: './ghost-container.html',
  styleUrl: './ghost-container.css',
})
export class GhostContainer {
  // app.component.ts
  ghosts: Ghost[] = [
    {
      name: 'Spirit',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/c/c1/Spirit_Discovered.jpg/revision/latest?cb=20231115114542',
      confidence:10
    },
    {
      name: 'Wraith',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/c/c1/Wraith_Discovered.jpg/revision/latest?cb=20231115114549',
        confidence:10
    },
    {
      name: 'Phantom',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/2/27/Phantom_Discovered.jpg/revision/latest?cb=20231115114535',
        confidence:10
    },
    {
      name: 'Poltergeist',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/1/10/Poltergeist_Discovered.jpg/revision/latest?cb=20231115114536',
        confidence:10
    },
    {
      name: 'Banshee',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/2/26/Banshee_Discovered.jpg/revision/latest?cb=20231115114509',
        confidence:10
    },
    {
      name: 'Jinn',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/5/56/Jinn_Discovered.jpg/revision/latest?cb=20231115114529',
        confidence:10
    },
    {
      name: 'Mare',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/3/32/Mare_Discovered.jpg/revision/latest?cb=20231115114529',
        confidence:10
    },
    {
      name: 'Revenant',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/1/16/Revenant_Discovered.jpg/revision/latest?cb=20231115114538',
        confidence:10
    },
    {
      name: 'Shade',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/f/ff/Shade_Discovered.jpg/revision/latest?cb=20231115114541',
        confidence:10
    },
    {
      name: 'Demon',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/f/f5/Demon_Discovered.jpg/revision/latest?cb=20231115114516',
        confidence:10
    },
    {
      name: 'Yurei',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/3/31/Yurei_Discovered.jpg/revision/latest?cb=20231115114551',
        confidence:10
    },
    {
      name: 'Oni',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/8/86/Oni_Discovered.jpg/revision/latest?cb=20231115114533',
        confidence:10
    },
    {
      name: 'Yokai',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/2/27/Yokai_Discovered.jpg/revision/latest?cb=20231115114550',
        confidence:10
    },
    {
      name: 'Hantu',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/e/e0/Hantu_Discovered.jpg/revision/latest?cb=20231115114526',
        confidence:10
    },
    {
      name: 'Goryo',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/6/6b/Goryo_Discovered.jpg/revision/latest?cb=20231115114525',
        confidence:10
    },
    {
      name: 'Myling',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/e/e0/Myling_Discovered.jpg/revision/latest?cb=20231115114531',
        confidence:10
    },
    {
      name: 'Onryo',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/f/f3/Onryo_Discovered.jpg/revision/latest?cb=20231115114534',
        confidence:10
    },
    {
      name: 'The Twins',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/f/f8/The_Twins_Discovered.jpg/revision/latest?cb=20231115114546',
        confidence:10
    },
    {
      name: 'Raiju',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/a/aa/Raiju_Discovered.jpg/revision/latest?cb=20231115114537',
        confidence:10
    },
    {
      name: 'Obake',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/7/7e/Obake_Discovered.jpg/revision/latest?cb=20231115114533',
        confidence:10
    },
    {
      name: 'The Mimic',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/e/e0/The_Mimic_Discovered.jpg/revision/latest?cb=20231115114545',
        confidence:10
    },
    {
      name: 'Moroi',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/e/e0/The_Mimic_Discovered.jpg/revision/latest?cb=20231115114545',
        confidence:10
    },
    {
      name: 'Deogen',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/d/dd/Deogen_Discovered.jpg/revision/latest?cb=20231115114517',
        confidence:10
    },
    {
      name: 'Thaye',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/6/6a/Thaye_Discovered.jpg/revision/latest?cb=20231115114544',
        confidence:10
    },
  ];
}
