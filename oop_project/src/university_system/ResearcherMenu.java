package university_system;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Console menu for research profiles.
 */
public class ResearcherMenu {
    private final Researcher researcher;
    private final Scanner scanner;
    private final ResearchService researchService = OfficeRegister.getResearchService();

    public ResearcherMenu(Researcher researcher, Scanner scanner) {
        this.researcher = researcher;
        this.scanner = scanner;
    }

    public void run() {
        while (true) {
            System.out.println("\n--- Researcher Menu: " + researcher.getUser().getFullName() + " ---");
            System.out.println("1. View my papers");
            System.out.println("2. View my projects");
            System.out.println("3. Create project");
            System.out.println("4. Join project");
            System.out.println("5. Write paper");
            System.out.println("6. Add paper to project");
            System.out.println("7. View h-index");
            System.out.println("8. View all papers sorted");
            System.out.println("0. Back");
            System.out.print("Choose option: ");

            try {
                switch (scanner.nextLine().trim()) {
                    case "1" -> viewMyPapers();
                    case "2" -> viewMyProjects();
                    case "3" -> createProject();
                    case "4" -> joinProject();
                    case "5" -> writePaper();
                    case "6" -> addPaperToProject();
                    case "7" -> System.out.println("H-index: " + researcher.getHIndex());
                    case "8" -> viewAllPapersSorted();
                    case "0" -> {
                        return;
                    }
                    default -> System.out.println("Invalid option.");
                }
            } catch (RuntimeException e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }
    }

    private void viewMyPapers() {
        List<ResearchPaper> papers = researchService.getPapers(researcher);
        if (papers.isEmpty()) {
            System.out.println("No papers.");
            return;
        }
        for (ResearchPaper paper : papers) {
            System.out.println(paper);
        }
    }

    private void viewMyProjects() {
        List<ResearchProject> projects = researchService.getProjects(researcher);
        if (projects.isEmpty()) {
            System.out.println("No projects.");
            return;
        }
        for (ResearchProject project : projects) {
            System.out.println(project.getProjectName() + " | members: " + project.getMembers().size()
                    + " | papers: " + project.getPapers().size());
        }
    }

    private void createProject() {
        System.out.print("Project name: ");
        String name = scanner.nextLine().trim();
        ResearchProject project = researchService.createProject(name, researcher);
        System.out.println("Created project: " + project.getProjectName());
    }

    private void joinProject() {
        ResearchProject project = chooseProject();
        if (project == null) return;
        researchService.addMember(project, researcher);
        System.out.println("Joined project: " + project.getProjectName());
    }

    private void writePaper() {
        System.out.print("Title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Journal: ");
        String journal = scanner.nextLine().trim();
        int pages = readInt("Pages: ");

        ResearchPaper paper = researchService.createPaper(title, researcher, LocalDate.now(), pages, journal);
        System.out.println("Created paper: " + paper.getTitle());
    }

    private void addPaperToProject() {
        ResearchProject project = chooseMyProject();
        ResearchPaper paper = chooseMyPaper();
        if (project == null || paper == null) return;
        researchService.addPaper(project, paper);
        System.out.println("Added paper to project.");
    }

    private void viewAllPapersSorted() {
        System.out.println("Sort by: 1.Title 2.Date 3.Citations 4.Pages");
        Comparator<ResearchPaper> comparator = switch (scanner.nextLine().trim()) {
            case "1" -> Comparator.comparing(ResearchPaper::getTitle);
            case "2" -> Comparator.comparing(ResearchPaper::getPublicationDate);
            case "3" -> Comparator.comparingInt(ResearchPaper::getCitationNumber).reversed();
            case "4" -> Comparator.comparingInt(ResearchPaper::getPages);
            default -> null;
        };
        if (comparator == null) {
            System.out.println("Invalid sort option.");
            return;
        }
        researchService.getPapers().stream().sorted(comparator).forEach(System.out::println);
    }

    private ResearchProject chooseProject() {
        if (researchService.getProjects().isEmpty()) {
            System.out.println("No projects.");
            return null;
        }
        for (int i = 0; i < researchService.getProjects().size(); i++) {
            ResearchProject project = researchService.getProject(i);
            System.out.println((i + 1) + ". " + project.getProjectName());
        }
        int index = readInt("Choose project: ") - 1;
        if (index < 0 || index >= researchService.getProjects().size()) {
            System.out.println("Invalid project.");
            return null;
        }
        return researchService.getProject(index);
    }

    private ResearchProject chooseMyProject() {
        List<ResearchProject> projects = researchService.getProjects(researcher);
        if (projects.isEmpty()) {
            System.out.println("No projects.");
            return null;
        }
        for (int i = 0; i < projects.size(); i++) {
            System.out.println((i + 1) + ". " + projects.get(i).getProjectName());
        }
        int index = readInt("Choose project: ") - 1;
        if (index < 0 || index >= projects.size()) {
            System.out.println("Invalid project.");
            return null;
        }
        return projects.get(index);
    }

    private ResearchPaper chooseMyPaper() {
        List<ResearchPaper> papers = researchService.getPapers(researcher);
        if (papers.isEmpty()) {
            System.out.println("No papers.");
            return null;
        }
        for (int i = 0; i < papers.size(); i++) {
            System.out.println((i + 1) + ". " + papers.get(i).getTitle());
        }
        int index = readInt("Choose paper: ") - 1;
        if (index < 0 || index >= papers.size()) {
            System.out.println("Invalid paper.");
            return null;
        }
        return papers.get(index);
    }

    private int readInt(String prompt) {
        System.out.print(prompt);
        return Integer.parseInt(scanner.nextLine().trim());
    }
}
