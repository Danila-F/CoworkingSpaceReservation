import java.util.Map;

public interface WorkspaceService {
    Workspace createWorkspace(String description);
    Map<Integer, Workspace> getAllWorkspaces();
    boolean editWorkspace(int workspaceId, String newDescription);
}
