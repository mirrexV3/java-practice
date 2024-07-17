package dev.mirrex.profile;

import java.util.HashMap;
import java.util.Map;

public class ProfileManager {
    private final Map<String, Profile> profiles;

    public ProfileManager() {
        this.profiles = new HashMap<>();
    }

    public void addProfile(String name, Profile profile) {
        profiles.put(name, profile);
    }

    public Profile getProfile(String name) {
        return profiles.get(name);
    }
}
