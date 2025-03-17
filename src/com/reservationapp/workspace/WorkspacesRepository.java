package com.reservationapp.workspace;

import java.io.*;
import java.util.HashMap;

class WorkspacesRepository implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final String FILE_PATH = "workspaces.ser";
    private final WorkspacesMapWithStateSaving workspacesMap;

    public WorkspacesRepository(WorkspacesMapWithStateSaving workspacesMap) {
        this.workspacesMap = workspacesMap;
    }

    void save() {
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            stream.writeObject(workspacesMap.lastWorkspaceID);
            stream.writeObject(workspacesMap.workspaces);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    void load() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
                workspacesMap.lastWorkspaceID = (Integer) stream.readObject();
                workspacesMap.workspaces = (HashMap<Integer, Workspace>) stream.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }
}
