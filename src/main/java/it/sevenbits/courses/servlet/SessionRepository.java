package it.sevenbits.courses.servlet;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SessionRepository {
    private static SessionRepository instance;
    private Map<UUID, String> sessions;
    private UUID ID;
    private String name = null;

    private SessionRepository(){
        sessions = new HashMap<>();
    }

    public static SessionRepository getInstance() {
        if (instance == null) {
            instance = new SessionRepository();
        }
        return instance;
    }

    public Map<UUID, String> getList() {
        return sessions;
    }

    public void addSessoin(String name) {
        ID = UUID.randomUUID();
        sessions.put(ID, name);
    }

    public UUID getID() {
        return ID;
    }

    public String getName(UUID ID) {
        return sessions.get(ID);
    }
}
