import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class WorkspaceServiceInMemory implements WorkspaceService{
    private final Map<Integer, Workspace> workspaces = new TreeMap<>();

    private Workspace addWorkspace(String description) {
        TreeMap<Integer, Workspace> treeWorkspaces = (TreeMap<Integer, Workspace>) workspaces;
        Workspace workspace;

        if (treeWorkspaces.lastEntry() == null) {
            workspace = new Workspace(0, description);
        } else {
            workspace = new Workspace(treeWorkspaces.lastEntry().getKey().intValue() + 1, description);
        }

        workspaces.put(workspace.getId(), workspace);
        return workspace;
    }

    public Workspace createWorkspace(String description, Scanner scanner) {
        System.out.println("Creating workspace...");
        System.out.println("1. Create new workspace");
        System.out.println("0. Back");

        Workspace workspace;
        String userInput = scanner.nextLine();

        switch (userInput) {
            case "0":
                System.out.println("Canceling creation of new workspace...");
                workspace = null;
                break;

            case "1":
                workspace = addWorkspace(description);
                System.out.println("Worksapce " + workspace.getDescription() + " with id " + workspace.getId() + " was created");
                break;

            default:
                System.out.println("Invalid input, canceling creation of new workspace...");
                workspace = null;
                break;
        }
        return workspace;
    }

    public void showAllWorkspaces() {
        for (Map.Entry<Integer, Workspace> entry : workspaces.entrySet()) {
            System.out.println(entry.getValue().toString());
        }
    }

    public void editWorkspace(Scanner scanner) {
        System.out.println("All workspaces:");
        showAllWorkspaces();
        System.out.println("Enter ID of workspace you want to edit:");

        try {
            int workspaceId = Integer.parseInt(scanner.nextLine());
            Workspace workspace = workspaces.get(workspaceId);

            if (workspace != null) {
                System.out.println("Chosen workspace:");
                System.out.println(workspace.toString());
                System.out.println("Write new description:");
                String newDescription = scanner.nextLine();
                workspace.setDescription(newDescription);
            } else {
                System.out.println("Workspace with this ID doesn't exist");
            }

        } catch (NumberFormatException ex) {
            System.out.println("Invalid input.");
        }
    }
}
