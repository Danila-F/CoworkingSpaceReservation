package com.reservationapp.workspace;

import java.util.Map;

public class WorkspaceService{
    private final WorkspacesMap workspaces = new WorkspacesMapInFile();

    public Workspace createWorkspace(String description) {
        Workspace workspace = new Workspace(workspaces.getNextID(), description);
        try {
            workspaces.add(workspace);
            return workspace;
        } catch (WrongNewWorkspaceID ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Map<Integer, Workspace> getAllWorkspaces() {
        return workspaces.getAll();
    }

    public boolean editWorkspace(int workspaceId, String newDescription) {
        if (workspaces.isExist(workspaceId)) {
            workspaces.get(workspaceId).setDescription(newDescription);
            return true;
        } else {
            return false;
        }
    }
}