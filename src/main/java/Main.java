import java.util.Scanner;

import model.Categoria;
import model.Curso;
import model.Estudiante;
import repository.CategoriaRepository;
import repository.CursoRepository;
import repository.EstudianteRepository;


public class Main {
    public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

        CategoriaRepository categoriaRepo = new CategoriaRepository();
        CursoRepository cursoRepo = new CursoRepository();
        EstudianteRepository estudianteRepo = new EstudianteRepository();

        int opcion = 0;

        while (opcion != 6) {

            System.out.println("\nPLATAFORMA DE CURSOS");
            System.out.println("1. Gestionar Categorias");
            System.out.println("2. Gestionar Cursos");
            System.out.println("3. Gestionar Estudiantes");
            System.out.println("4. Inscribir estudiante en curso");
            System.out.println("5. Ver listas completas");
            System.out.println("6. Salir");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1:
                    menuCategorias(scanner, categoriaRepo);
                    break;

                case 2:
                    menuCursos(scanner, cursoRepo);
                    break;

                case 3:
                    menuEstudiantes(scanner, estudianteRepo);
                    break;

                case 4:
                    System.out.print("ID del estudiante: ");
                    Long estId = scanner.nextLong();

                    System.out.print("ID del curso: ");
                    Long cursoId = scanner.nextLong();

                    estudianteRepo.inscribir(estId, cursoId);
                    break;

                case 5:
                    System.out.println("\nCategorias");
                    categoriaRepo.listar().forEach(c -> 
                        System.out.println(c.getId() + " - " + c.getNombre()));

                    System.out.println("\nCursos");
                    cursoRepo.listar().forEach(c -> 
                        System.out.println(c.getId() + " - " + c.getTitulo()));

                    System.out.println("\nEstudiantes");
                    estudianteRepo.listar().forEach(e -> 
                        System.out.println(e.getId() + " - " + e.getNombre()));
                    break;

                case 6:
                    System.out.println("Hasta luego");
                    break;

                default:
                    System.out.println("Opcion invalida");
            }
        }

        scanner.close();
    }

    private static void menuCategorias(Scanner scanner, CategoriaRepository repo) {
        System.out.println("\n1. Crear categoria");
        System.out.println("2. Listar categorias");
        System.out.println("3. Editar categoria");
        System.out.println("4. Eliminar categoria");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {

            case 1:
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                repo.insertar(new Categoria(null, nombre));
                break;

            case 2:
                repo.listar().forEach(c ->
                    System.out.println(c.getId() + " - " + c.getNombre()));
                break;

            case 3:
                System.out.print("ID a editar: ");
                Long id = scanner.nextLong();
                scanner.nextLine();

                Categoria categoria = repo.buscarPorId(id);
                if (categoria == null) {
                    System.out.println("No encontrada");
                    return;
                }

                System.out.print("Nuevo nombre: ");
                categoria.setNombre(scanner.nextLine());
                repo.actualizar(categoria);
                break;

            case 4:
                System.out.print("ID a eliminar: ");
                repo.eliminar(scanner.nextLong());
                break;

            default:
                System.out.println("Opcion invalida");
        }
    }  

    private static void menuCursos(Scanner scanner, CursoRepository repo) {

        System.out.println("\n1. Crear curso");
        System.out.println("2. Listar cursos");
        System.out.println("3. Editar curso");
        System.out.println("4. Eliminar curso");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {

            case 1:
                System.out.print("Titulo: ");
                String titulo = scanner.nextLine();
                System.out.print("Descripcion: ");
                String desc = scanner.nextLine();
                System.out.print("ID categoria: ");
                Long catId = scanner.nextLong();
                repo.insertar(new Curso(titulo, desc, catId));
                break;

            case 2:
                repo.listar().forEach(c ->
                    System.out.println(c.getId() + " - " + c.getTitulo()));
                break;

            case 3:
                System.out.print("ID a editar: ");
                Long id = scanner.nextLong();
                scanner.nextLine();

                Curso curso = repo.buscarPorId(id);
                if (curso == null) {
                    System.out.println("No encontrado");
                    return;
                }

                System.out.print("Nuevo titulo: ");
                curso.setTitulo(scanner.nextLine());

                System.out.print("Nueva descripcion: ");
                curso.setDescripcion(scanner.nextLine());

                System.out.print("Nueva categoria ID: ");
                curso.setCategoriaId(scanner.nextLong());

                repo.actualizar(curso);
                break;

            case 4:
                System.out.print("ID a eliminar: ");
                repo.eliminar(scanner.nextLong());
                break;

            default:
                System.out.println("Opcion invalida");
        }
    }

    private static void menuEstudiantes(Scanner scanner, EstudianteRepository repo) {

        System.out.println("\n1. Registrar estudiante");
        System.out.println("2. Listar estudiantes");
        System.out.println("3. Editar estudiante");
        System.out.println("4. Eliminar estudiante");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {

            case 1:
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();

                System.out.print("Email: ");
                String email = scanner.nextLine();

                repo.insertar(new Estudiante(nombre, email));
                break;

            case 2:
                repo.listar().forEach(e ->
                    System.out.println(e.getId() + " - " + e.getNombre()));
                break;

            case 3:
                System.out.print("ID a editar: ");
                Long id = scanner.nextLong();
                scanner.nextLine();

                Estudiante estudiante = repo.buscarPorId(id);
                if (estudiante == null) {
                    System.out.println("No encontrado");
                    return;
                }

                System.out.print("Nuevo nombre: ");
                estudiante.setNombre(scanner.nextLine());

                System.out.print("Nuevo email: ");
                estudiante.setEmail(scanner.nextLine());

                repo.actualizar(estudiante);
                break;

            case 4:
                System.out.print("ID a eliminar: ");
                repo.eliminar(scanner.nextLong());
                break;

            default:
                System.out.println("Opcion invalida");
        }
    }
}

