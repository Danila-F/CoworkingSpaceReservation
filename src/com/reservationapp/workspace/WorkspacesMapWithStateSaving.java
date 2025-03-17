package com.reservationapp.workspace;

class WorkspacesMapWithStateSaving extends WorkspacesMapInMemory {

    public WorkspacesMapWithStateSaving() {
        WorkspacesRepository workspacesRepository = new WorkspacesRepository(this);
        workspacesRepository.load();
        Runtime.getRuntime().addShutdownHook(new Thread(workspacesRepository::save));
    }
}
