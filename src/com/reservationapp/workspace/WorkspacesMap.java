package com.reservationapp.workspace;

import java.util.Map;

interface WorkspacesMap {
    void add(Workspace workspace) throws WrongNewWorkspaceIDException;
    Workspace get(int workspaceID);
    boolean isExist(int workspaceID);
    Map<Integer, Workspace> getAll();
    int getNextID();
    boolean isEmpty();
}
