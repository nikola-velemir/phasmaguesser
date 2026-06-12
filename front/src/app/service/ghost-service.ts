import { Injectable } from '@angular/core';
import { Ghost } from '../features/ghosts/model/ghost';
import { BehaviorSubject, tap } from 'rxjs';
import { IndentifitionRequest } from '../model/indentification-request';
import { Evidence } from '../features/journal/component/evidence-bar/evidence';
import { Observation } from '../features/journal/component/observation-bar/observation';
import { IndentificationService } from './identification/indentification-service';
import { IndentifitionResponse } from '../model/indentification-response';

@Injectable({
  providedIn: 'root',
})
export class GhostService {

  private readonly ghosts: Ghost[] = [
    {
      name: 'Spirit',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/c/c1/Spirit_Discovered.jpg/revision/latest?cb=20231115114542',
      confidence: 1 / 24 * 100,
      description: 'The most common ghost. Can be temporarily stopped from hunting by burning Incense near it, giving a longer smudge cooldown than other ghosts.'
    },
    {
      name: 'Wraith',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/c/c1/Wraith_Discovered.jpg/revision/latest?cb=20231115114549',
      confidence: 1 / 24 * 100,
      description: 'Can teleport directly to a player without leaving footprints. Never steps in salt — if salt is left undisturbed it confirms or rules out a Wraith.'
    },
    {
      name: 'Phantom',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/2/27/Phantom_Discovered.jpg/revision/latest?cb=20231115114535',
      confidence: 1 / 24 * 100,
      description: 'Taking a photo of a Phantom causes it to temporarily disappear. Decreases sanity faster when looked at during ghost events.'
    },
    {
      name: 'Poltergeist',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/1/10/Poltergeist_Discovered.jpg/revision/latest?cb=20231115114536',
      confidence: 1 / 24 * 100,
      description: 'Throws multiple objects simultaneously and drains sanity when it does. Almost harmless in empty rooms but devastating in cluttered ones.'
    },
    {
      name: 'Banshee',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/2/26/Banshee_Discovered.jpg/revision/latest?cb=20231115114509',
      confidence: 1 / 24 * 100,
      description: 'Picks a single target player and focuses its hunts on them. Can be identified by a unique screech heard through the Parabolic Microphone.'
    },
    {
      name: 'Jinn',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/5/56/Jinn_Discovered.jpg/revision/latest?cb=20231115114529',
      confidence: 1 / 24 * 100,
      description: 'Moves significantly faster when chasing a player from a distance while the power is on. Turning off the fuse box prevents it from using this ability.'
    },
    {
      name: 'Mare',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/3/32/Mare_Discovered.jpg/revision/latest?cb=20231115114529',
      confidence: 1 / 24 * 100,
      description: 'More likely to hunt in darkness and prefers to turn off lights. Keeping lights on reduces its hunt threshold; it will not turn on lights itself.'
    },
    {
      name: 'Revenant',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/1/16/Revenant_Discovered.jpg/revision/latest?cb=20231115114538',
      confidence: 1 / 24 * 100,
      description: 'Extremely slow while roaming, but charges at high speed the moment it spots a player during a hunt. Hiding and staying silent is key.'
    },
    {
      name: 'Shade',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/f/ff/Shade_Discovered.jpg/revision/latest?cb=20231115114541',
      confidence: 1 / 24 * 100,
      description: 'Very shy — less likely to hunt or produce evidence when players are nearby. Easier to investigate alone but harder to gather evidence in groups.'
    },
    {
      name: 'Demon',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/f/f5/Demon_Discovered.jpg/revision/latest?cb=20231115114516',
      confidence: 1 / 24 * 100,
      description: 'One of the most aggressive ghosts — can initiate hunts at any sanity level and has a shorter cooldown between hunts. Crucifix has an extended effectiveness range against it.'
    },
    {
      name: 'Yurei',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/3/31/Yurei_Discovered.jpg/revision/latest?cb=20231115114551',
      confidence: 1 / 24 * 100,
      description: 'Drains player sanity rapidly when they are in the same room. Smudging its ghost room traps it there temporarily, preventing roaming.'
    },
    {
      name: 'Oni',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/8/86/Oni_Discovered.jpg/revision/latest?cb=20231115114533',
      confidence: 1 / 24 * 100,
      description: 'More active when players are present and throws objects with great force. Opposite of the Shade — it thrives around people and is never in its ghost form for long.'
    },
    {
      name: 'Yokai',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/2/27/Yokai_Discovered.jpg/revision/latest?cb=20231115114550',
      confidence: 1 / 24 * 100,
      description: 'Talking near it greatly increases its hunt chance. During a hunt, its hearing range is reduced, so it can only detect players very close to it.'
    },
    {
      name: 'Hantu',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/e/e0/Hantu_Discovered.jpg/revision/latest?cb=20231115114526',
      confidence: 1 / 24 * 100,
      description: 'Moves faster in cold rooms and slower in warm ones. Turning off the fuse box drops temperatures and lets the Hantu accelerate — keeping power on is a good counter.'
    },
    {
      name: 'Goryo',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/6/6b/Goryo_Discovered.jpg/revision/latest?cb=20231115114525',
      confidence: 1 / 24 * 100,
      description: 'DOTS projector activity only appears on video camera footage, not with the naked eye. Rarely strays far from its ghost room and will not roam if players are present.'
    },
    {
      name: 'Myling',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/e/e0/Myling_Discovered.jpg/revision/latest?cb=20231115114531',
      confidence: 1 / 24 * 100,
      description: 'Produces quieter footsteps during hunts, making it harder to hear approaching. Causes more frequent paranormal sounds on the Parabolic Microphone.'
    },
    {
      name: 'Onryo',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/f/f3/Onryo_Discovered.jpg/revision/latest?cb=20231115114534',
      confidence: 1 / 24 * 100,
      description: 'Triggered to hunt when a flame is extinguished nearby instead of by low sanity alone. Lit candles act as a substitute Crucifix — it will not hunt while one is burning.'
    },
    {
      name: 'The Twins',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/f/f8/The_Twins_Discovered.jpg/revision/latest?cb=20231115114546',
      confidence: 1 / 24 * 100,
      description: 'Two linked entities that can interact in different locations simultaneously. Either twin can initiate a hunt — one moves faster and one slower than average.'
    },
    {
      name: 'Raiju',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/a/aa/Raiju_Discovered.jpg/revision/latest?cb=20231115114537',
      confidence: 1 / 24 * 100,
      description: 'Feeds on electricity — moves faster during hunts when active electronic equipment is nearby. Disrupts electronic devices at a greater range than other ghosts.'
    },
    {
      name: 'Obake',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/7/7e/Obake_Discovered.jpg/revision/latest?cb=20231115114533',
      confidence: 1 / 24 * 100,
      description: 'Has a chance to leave unique six-fingered fingerprints instead of the usual five. Can also halve the remaining duration of fingerprint evidence, making it disappear faster.'
    },
    {
      name: 'The Mimic',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/e/e0/The_Mimic_Discovered.jpg/revision/latest?cb=20231115114545',
      confidence: 1 / 24 * 100,
      description: 'Copies the abilities and behaviors of other ghost types, switching periodically. Always produces Ghost Orbs as a bonus tell alongside its other evidence.'
    },
    {
      name: 'Moroi',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/9/95/Moroi_Discovered.jpg/revision/latest?cb=20231115114530',
      confidence: 1 / 24 * 100,
      description: 'Curses players who hear its voice through the Spirit Box, draining their sanity faster until they eat a sanity pill. Moves faster at lower player sanity.'
    },
    {
      name: 'Deogen',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/d/dd/Deogen_Discovered.jpg/revision/latest?cb=20231115114517',
      confidence: 1 / 24 * 100,
      description: 'Always knows exactly where players are — it cannot be hidden from. However, it slows to a crawl when it gets very close to its target, giving a chance to escape.'
    },
    {
      name: 'Thaye',
      icon: 'https://static.wikia.nocookie.net/phasmophobia/images/6/6a/Thaye_Discovered.jpg/revision/latest?cb=20231115114544',
      confidence: 1 / 24 * 100,
      description: 'Ages over time the longer players spend near it, becoming progressively slower and less active. Very aggressive and fast when fresh; relatively docile after prolonged exposure.'
    },
  ];
  constructor(private readonly indentificationService: IndentificationService) {

  }

  private ghostSubject = new BehaviorSubject(this.ghosts);

  private selectedEvidenceSubject = new BehaviorSubject<Evidence[]>([])
  private observationSubject = new BehaviorSubject<Partial<Observation>>({})
  ghosts$ = this.ghostSubject.asObservable();

  selectedEvicence$ = this.selectedEvidenceSubject.asObservable();

  setObservation(o: Partial<Observation>) {
    this.observationSubject.next(o)
  }
  setSelectedEvidence(e: Evidence[]) {
    this.selectedEvidenceSubject.next(e);
  }

  indetify() {
    const evidence = this.selectedEvidenceSubject.getValue().map(e => e.value);
    const observation = this.observationSubject.getValue();
    const request: IndentifitionRequest = {
      evidence,
      ...observation
    };
    return this.indentificationService.indentify(request).pipe(
      tap((response) => this.updateGhosts(response))
    );
  }
  private updateGhosts(response : IndentifitionResponse) {
    {
      const responseGhosts = response.ghosts;
      const updatedGhosts = this.ghosts.map(localGhost => {
        const matchingGhostResponse = responseGhosts.find(
          (rg) => rg.name === localGhost.name
        );
        if (!matchingGhostResponse) return null;
        const clippedScore = Math.max(matchingGhostResponse.score, 0);
        if (clippedScore == 0 && responseGhosts.length != this.ghosts.length) return null;
        return {
          ...localGhost,
          confidence: clippedScore / response.totalScore * 100
        }
      })
        .filter(s => s !== null);

      this.ghostSubject.next(updatedGhosts);
    }
  }
}
