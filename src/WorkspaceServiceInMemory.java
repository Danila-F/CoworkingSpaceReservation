import java.util.Map;
import java.util.TreeMap;

public class WorkspaceServiceInMemory implements WorkspaceService {
    protected Map<Integer, Workspace> workspaces = new TreeMap<>();

    public Workspace createWorkspace(String description) {
        TreeMap<Integer, Workspace> treeWorkspaces = (TreeMap<Integer, Workspace>) workspaces;
        int newId = (treeWorkspaces.isEmpty()) ? 0 : treeWorkspaces.lastKey() + 1;
        Workspace workspace = new Workspace(newId, description);
        workspaces.put(newId, workspace);
        return workspace;
    }

    public Map<Integer, Workspace> getAllWorkspaces() {
        return new TreeMap<>(workspaces);
    }

    public boolean editWorkspace(int workspaceId, String newDescription) {
        Workspace workspace = workspaces.get(workspaceId);
        if (workspace != null) {
            workspace.setDescription(newDescription);
            return true;
        }
        return false;
    }
}