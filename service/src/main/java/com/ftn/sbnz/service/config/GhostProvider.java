package com.ftn.sbnz.service.config;

import java.util.ArrayList;
import java.util.List;

import com.ftn.sbnz.model.ghosts.Evidence;
import com.ftn.sbnz.model.ghosts.Ghost;

public class GhostProvider {

    public static List<Ghost> getStaticGhostsKnowledgeBase() {
        List<Ghost> ghosts = new ArrayList<>();

        ghosts.add(new Ghost("Spirit", Evidence.EMF_LEVEL_5, Evidence.GHOST_WRITING, Evidence.SPIRIT_BOX, "Long_Smudge_Timer"));
        ghosts.add(new Ghost("Wraith", Evidence.EMF_LEVEL_5, Evidence.ULTRAVIOLET, Evidence.SPIRIT_BOX, "No_Salt_Footprints"));
        ghosts.add(new Ghost("Phantom", Evidence.ULTRAVIOLET, Evidence.GHOST_ORB, Evidence.SPIRIT_BOX, "Photo_Disappearance"));
        ghosts.add(new Ghost("Poltergeist", Evidence.ULTRAVIOLET, Evidence.GHOST_WRITING, Evidence.SPIRIT_BOX, "Multi_Object_Throw"));
        ghosts.add(new Ghost("Banshee", Evidence.ULTRAVIOLET, Evidence.GHOST_ORB, Evidence.DOTS_PROJECTOR, "Parabolic_Scream"));
        ghosts.add(new Ghost("Jinn", Evidence.EMF_LEVEL_5, Evidence.ULTRAVIOLET, Evidence.FREEZING_TEMPERATURES, "Speed_Increase_With_Fuse_Box"));
        ghosts.add(new Ghost("Mare", Evidence.GHOST_ORB, Evidence.GHOST_WRITING, Evidence.SPIRIT_BOX, "Attack_In_Darkness"));
        ghosts.add(new Ghost("Revenant", Evidence.GHOST_ORB, Evidence.GHOST_WRITING, Evidence.FREEZING_TEMPERATURES, "High_Hunt_Speed"));
        ghosts.add(new Ghost("Shade", Evidence.EMF_LEVEL_5, Evidence.GHOST_WRITING, Evidence.FREEZING_TEMPERATURES, "Shy_In_Presence"));
        ghosts.add(new Ghost("Demon", Evidence.ULTRAVIOLET, Evidence.GHOST_WRITING, Evidence.FREEZING_TEMPERATURES, "Early_Hunt_And_Low_Cooldown"));
        ghosts.add(new Ghost("Yurei", Evidence.GHOST_ORB, Evidence.FREEZING_TEMPERATURES, Evidence.DOTS_PROJECTOR, "Door_Manipulation"));
        ghosts.add(new Ghost("Oni", Evidence.EMF_LEVEL_5, Evidence.FREEZING_TEMPERATURES, Evidence.DOTS_PROJECTOR, "High_Activity_Near_Player"));
        ghosts.add(new Ghost("Yokai", Evidence.GHOST_ORB, Evidence.SPIRIT_BOX, Evidence.DOTS_PROJECTOR, "Voice_Sensitivity"));
        ghosts.add(new Ghost("Hantu", Evidence.ULTRAVIOLET, Evidence.GHOST_ORB, Evidence.FREEZING_TEMPERATURES, "Temperature_Based_Speed"));
        ghosts.add(new Ghost("Goryo", Evidence.EMF_LEVEL_5, Evidence.ULTRAVIOLET, Evidence.DOTS_PROJECTOR, "Camera_Only_DOTS"));
        ghosts.add(new Ghost("Myling", Evidence.EMF_LEVEL_5, Evidence.ULTRAVIOLET, Evidence.GHOST_WRITING, "Quiet_Footsteps_During_Hunt"));
        ghosts.add(new Ghost("Onryo", Evidence.GHOST_ORB, Evidence.FREEZING_TEMPERATURES, Evidence.SPIRIT_BOX, "Candle_Extinguish_Trigger"));
        ghosts.add(new Ghost("The Twins", Evidence.EMF_LEVEL_5, Evidence.FREEZING_TEMPERATURES, Evidence.SPIRIT_BOX, "Dual_Interactions"));
        ghosts.add(new Ghost("Raiju", Evidence.EMF_LEVEL_5, Evidence.GHOST_ORB, Evidence.DOTS_PROJECTOR, "Electronics_Speed_Boost"));
        ghosts.add(new Ghost("Obake", Evidence.EMF_LEVEL_5, Evidence.ULTRAVIOLET, Evidence.GHOST_ORB, "Six_Finger_Handprint"));
        ghosts.add(new Ghost("The Mimic", Evidence.ULTRAVIOLET, Evidence.FREEZING_TEMPERATURES, Evidence.SPIRIT_BOX, "Copy_Other_Ghosts"));
        ghosts.add(new Ghost("Moroi", Evidence.GHOST_WRITING, Evidence.FREEZING_TEMPERATURES, Evidence.SPIRIT_BOX, "Curse_And_Speed_Sanity"));
        ghosts.add(new Ghost("Deogen", Evidence.GHOST_WRITING, Evidence.SPIRIT_BOX, Evidence.DOTS_PROJECTOR, "Player_Tracking_And_Close_Slowdown"));
        ghosts.add(new Ghost("Thaye", Evidence.GHOST_ORB, Evidence.GHOST_WRITING, Evidence.DOTS_PROJECTOR, "Aging_Mechanic"));

        return ghosts;
    }
    
}
