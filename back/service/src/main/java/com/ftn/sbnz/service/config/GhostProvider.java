package com.ftn.sbnz.service.config;

import java.util.List;
import java.util.Set;

import com.ftn.sbnz.model.evidence.Evidence;
import com.ftn.sbnz.model.ghosts.Ghost;

public class GhostProvider {
        private static List<Ghost> GHOSTS = List.of(
                        new Ghost("Spirit", Set.of(Evidence.EMF_LEVEL_5, Evidence.GHOST_WRITING, Evidence.SPIRIT_BOX),
                                        Set.of("Long_Smudge_Timer")),
                        new Ghost("Wraith", Set.of(Evidence.EMF_LEVEL_5, Evidence.ULTRAVIOLET, Evidence.SPIRIT_BOX),
                                        Set.of("No_Salt_Footprints", "Normal_Smudge_Timer")),
                        new Ghost("Phantom", Set.of(Evidence.ULTRAVIOLET, Evidence.GHOST_ORB, Evidence.SPIRIT_BOX),
                                        Set.of("Photo_Disappearance", "Normal_Smudge_Timer")),
                        new Ghost("Poltergeist",
                                        Set.of(Evidence.ULTRAVIOLET, Evidence.GHOST_WRITING, Evidence.SPIRIT_BOX),
                                        Set.of("Multi_Object_Throw", "Normal_Smudge_Timer")),
                        new Ghost("Banshee", Set.of(Evidence.ULTRAVIOLET, Evidence.GHOST_ORB, Evidence.DOTS_PROJECTOR),
                                        Set.of("Parabolic_Scream", "Normal_Smudge_Timer")),
                        new Ghost("Jinn",
                                        Set.of(Evidence.EMF_LEVEL_5, Evidence.ULTRAVIOLET,
                                                        Evidence.FREEZING_TEMPERATURES),
                                        Set.of("Speed_Increase_With_Fuse_Box", "Normal_Smudge_Timer")),
                        new Ghost("Mare", Set.of(Evidence.GHOST_ORB, Evidence.GHOST_WRITING, Evidence.SPIRIT_BOX),
                                        Set.of("Attack_In_Darkness", "Normal_Smudge_Timer")),
                        new Ghost("Revenant",
                                        Set.of(Evidence.GHOST_ORB, Evidence.GHOST_WRITING,
                                                        Evidence.FREEZING_TEMPERATURES),
                                        Set.of("High_Hunt_Speed", "Normal_Smudge_Timer")),
                        new Ghost("Shade",
                                        Set.of(Evidence.EMF_LEVEL_5, Evidence.GHOST_WRITING,
                                                        Evidence.FREEZING_TEMPERATURES),
                                        Set.of("Shy_In_Presence", "Normal_Smudge_Timer")),
                        new Ghost("Demon",
                                        Set.of(Evidence.ULTRAVIOLET, Evidence.GHOST_WRITING,
                                                        Evidence.FREEZING_TEMPERATURES),
                                        Set.of("Early_Hunt_And_Low_Cooldown")),
                        new Ghost("Yurei",
                                        Set.of(Evidence.GHOST_ORB, Evidence.FREEZING_TEMPERATURES,
                                                        Evidence.DOTS_PROJECTOR),
                                        Set.of("Door_Manipulation", "Normal_Smudge_Timer")),
                        new Ghost("Oni", Set.of(Evidence.EMF_LEVEL_5, Evidence.FREEZING_TEMPERATURES,
                                        Evidence.DOTS_PROJECTOR),
                                        Set.of("High_Activity_Near_Player", "Normal_Smudge_Timer")),
                        new Ghost("Yokai", Set.of(Evidence.GHOST_ORB, Evidence.SPIRIT_BOX, Evidence.DOTS_PROJECTOR),
                                        Set.of("Voice_Sensitivity", "Normal_Smudge_Timer")),
                        new Ghost("Hantu",
                                        Set.of(Evidence.ULTRAVIOLET, Evidence.GHOST_ORB,
                                                        Evidence.FREEZING_TEMPERATURES),
                                        Set.of("Temperature_Based_Speed", "Normal_Smudge_Timer")),
                        new Ghost("Goryo", Set.of(Evidence.EMF_LEVEL_5, Evidence.ULTRAVIOLET, Evidence.DOTS_PROJECTOR),
                                        Set.of("Camera_Only_DOTS", "Normal_Smudge_Timer")),
                        new Ghost("Myling", Set.of(Evidence.EMF_LEVEL_5, Evidence.ULTRAVIOLET, Evidence.GHOST_WRITING),
                                        Set.of("Quiet_Footsteps_During_Hunt", "Normal_Smudge_Timer")),
                        new Ghost("Onryo",
                                        Set.of(Evidence.GHOST_ORB, Evidence.FREEZING_TEMPERATURES, Evidence.SPIRIT_BOX),
                                        Set.of("Candle_Extinguish_Trigger", "Normal_Smudge_Timer")),
                        new Ghost("The Twins",
                                        Set.of(Evidence.EMF_LEVEL_5, Evidence.FREEZING_TEMPERATURES,
                                                        Evidence.SPIRIT_BOX),
                                        Set.of("Dual_Interactions", "Normal_Smudge_Timer")),
                        new Ghost("Raiju", Set.of(Evidence.EMF_LEVEL_5, Evidence.GHOST_ORB, Evidence.DOTS_PROJECTOR),
                                        Set.of("Electronics_Speed_Boost", "Normal_Smudge_Timer")),
                        new Ghost("Obake", Set.of(Evidence.EMF_LEVEL_5, Evidence.ULTRAVIOLET, Evidence.GHOST_ORB),
                                        Set.of("Six_Finger_Handprint", "Model_Changing_During_Hunt",
                                                        "Normal_Smudge_Timer")),
                        new Ghost("The Mimic",
                                        Set.of(Evidence.ULTRAVIOLET, Evidence.FREEZING_TEMPERATURES,
                                                        Evidence.SPIRIT_BOX,
                                                        Evidence.GHOST_ORB),
                                        Set.of("Copy_Other_Ghosts", "Normal_Smudge_Timer")),
                        new Ghost("Moroi",
                                        Set.of(Evidence.GHOST_WRITING, Evidence.FREEZING_TEMPERATURES,
                                                        Evidence.SPIRIT_BOX),
                                        Set.of("Curse_And_Speed_Sanity", "Normal_Smudge_Timer")),
                        new Ghost("Deogen",
                                        Set.of(Evidence.GHOST_WRITING, Evidence.SPIRIT_BOX, Evidence.DOTS_PROJECTOR),
                                        Set.of("Player_Tracking", "Close_Slowdown", "Normal_Smudge_Timer")),
                        new Ghost("Thaye", Set.of(Evidence.GHOST_ORB, Evidence.GHOST_WRITING, Evidence.DOTS_PROJECTOR),
                                        Set.of("Aging_Mechanic", "Normal_Smudge_Timer"))

        );

        public static List<Ghost> getStaticGhostsKnowledgeBase() {
                return GHOSTS;
        }

        public static int getGhostNumber() {
                return GHOSTS.size();
        }

}
