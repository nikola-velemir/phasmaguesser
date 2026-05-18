import { EvidenceState } from "./evicence-state";

export interface Evidence {
  name: string;
  value:string;
  state: EvidenceState
}