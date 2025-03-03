import java.util.Scanner;

public interface WorkspaceService {
    Workspace createWorkspace(String description, Scanner scanner);
    void showAllWorkspaces();
    void editWorkspace(Scanner scanner);
}
