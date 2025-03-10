package com.reservationapp.workspace;

import java.util.HashMap;
import java.util.Map;

class WorkspacesMapInMemory implements WorkspacesMap{
    protected Map<Integer, Workspace> workspaces = new HashMap<>();
    protected Integer lastWorkspaceID = 0;

    @Override
    public void add(Workspace workspace) throws WrongNewWorkspaceID{
        if (workspace.getId() != getNextID()) {
            throw new WrongNewWorkspaceID("You're trying to add workspace with incorrect ID: " + workspace.getId() + ", but it should be: " + getNextID());
        } else {
            lastWorkspaceID = workspace.getId();
            workspaces.put(lastWorkspaceID, workspace);
        }
    }

    @Override
    public Workspace get(int workspaceID) {
        return workspaces.get(workspaceID);
    }

    @Override
    public boolean isExist(int workspaceID) {
        return workspaces.containsKey(workspaceID);
    }

    @Override
    public Map<Integer, Workspace> getAll() {
        return workspaces;
    }

    @Override
    public int getNextID() {
        return isEmpty() ? lastWorkspaceID : lastWorkspaceID + 1;
    }

    @Override
    public boolean isEmpty() {
        return workspaces.isEmpty();
    }
}
