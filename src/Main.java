import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static final repositorioDocumentos repo = new repositorioDocumentos();
 
    public static void main(String[] args){ 
        cargarPlantillasIniciales();
        int op;
        do {
            System.out.println("\n===== GESTOR DE DOCUMENTOS =====");
            System.out.println("1. Crear y registrar plantilla");
            System.out.println("2. Generar copia desde una plantilla");
            System.out.println("3. Modificar una copia");
            System.out.println("4. Consultar plantillas");
            System.out.println("5. Consultar documentos generados");
            System.out.println("6. Verificar independencia");
            System.out.println("0. Salir");
            op = leerEntero("Opción: ");

            switch (op) {
                case 1 -> crearPlantilla();
                case 2 -> generarCopia();
                case 3 -> modificarCopia();
                case 4 -> listar(repo.getPlantillas(), "PLANTILLAS");
                case 5 -> listar(repo.getDocumentos(), "DOCUMENTOS GENERADOS");
                case 6 -> verificarIndependencia();
                case 0 -> System.out.println("Hasta luego.");
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 0);
    }
 
    private static void cargarPlantillasIniciales() {
        repo.registrarPlantilla(new propuestaComercial("Propuesta estándar", "P-001", "Siesa SAS",
                "Desarrollo de software", "$15.000.000", "25 días"));
        repo.registrarPlantilla(new contratoServicio("Contrato estándar", "C-001", "Ramiro Andrade",
                "Emcali", "$28.000.000", "8 meses"));
        repo.registrarPlantilla(new actaReunion("Acta estándar", "A-001", "12-10-2026",
                "Andrea Ruiz, Leonardo Lopéz, Maria Zuñiga", "Avance del proyecto", "Entregar informe en 15 días"));
    }
 
    private static void crearPlantilla() {
        System.out.println("Tipo: 1) Contrato  2) Factura  3) Propuesta  4) Acta");
        int tipo = leerEntero("Tipo: ");
        String nombre = leer("Nombre de la plantilla: ");
        documento d;
        switch (tipo) {
            case 1 -> d = new contratoServicio(nombre, leer("Número de contrato: "), leer("Contratista: "),
                    leer("Entidad contratante: "), leer("Valor: "), leer("Duración: "));
            case 2 -> d = new facturaElectronica(nombre, leer("Número de factura: "), leer("Cliente: "),
                    leer("Fecha: "), leer("Subtotal: "), leer("Impuestos: "));
            case 3 -> d = new propuestaComercial(nombre, leer("Número de propuesta: "), leer("Cliente: "),
                    leer("Descripción del servicio: "), leer("Valor: "), leer("Vigencia: "));
            case 4 -> d = new actaReunion(nombre, leer("Número de acta: "), leer("Fecha: "),
                    leer("Participantes: "), leer("Temas tratados: "), leer("Compromisos: "));
            default -> { System.out.println("Tipo inválido."); return; }
        }
        repo.registrarPlantilla(d);
        System.out.println("Plantilla registrada.");
    }
 
    private static void generarCopia() {
        listar(repo.getPlantillas(), "PLANTILLAS");
        int i = leerEntero("Número de plantilla (posición): ") - 1;
        if (!indiceValido(repo.getPlantillas(), i)) return;
        documento copia = repo.generarDesdePlantilla(i, leer("Nombre de la nueva copia: "));
        System.out.println("Copia generada:\n" + copia);
    }
 
    private static void modificarCopia() {
        listar(repo.getDocumentos(), "DOCUMENTOS GENERADOS");
        int i = leerEntero("Número de documento (posición): ") - 1;
        if (!indiceValido(repo.getDocumentos(), i)) return;
        documento d = repo.getDocumentos().get(i);
        String nuevoNombre = leer("Nuevo nombre (Enter para mantener): ");
        if (!nuevoNombre.isBlank()) d.setNombre(nuevoNombre);
        for (Map.Entry<String, String> e : d.getCampos().entrySet()) {
            String v = leer(e.getKey() + " [" + e.getValue() + "] (Enter para mantener): ");
            if (!v.isBlank()) d.modificarCampo(e.getKey(), v);
        }
        System.out.println("Documento actualizado:\n" + d);
    }
 
    private static void verificarIndependencia() {
        if (repo.getPlantillas().isEmpty()) { System.out.println("No hay plantillas."); return; }
        documento original = repo.getPlantillas().get(0);
        documento c1 = repo.generarDesdePlantilla(0, "Verificación - copia 1");
        documento c2 = repo.generarDesdePlantilla(0, "Verificación - copia 2");
        String clave = c1.getCampos().keySet().iterator().next();
        String valorOriginal = original.getCampo(clave);
 
        c1.modificarCampo(clave, "VALOR MODIFICADO EN COPIA 1");
 
        System.out.println("Campo comprobado: " + clave);
        System.out.println("Original : " + original.getCampo(clave));
        System.out.println("Copia 1  : " + c1.getCampo(clave));
        System.out.println("Copia 2  : " + c2.getCampo(clave));
        boolean ok = original.getCampo(clave).equals(valorOriginal)
                && c2.getCampo(clave).equals(valorOriginal)
                && !c1.getCampo(clave).equals(valorOriginal)
                && original != c1 && c1 != c2;
        System.out.println(ok ? "RESULTADO: OK, los objetos son independientes."
                              : "RESULTADO: ERROR, hay estado compartido.");
    }
 
    private static void listar(List<documento> lista, String titulo) {
        System.out.println("\n--- " + titulo + " ---");
        if (lista.isEmpty()){
            System.out.println("(vacío)");
            return;
        }
        for (int i = 0; i < lista.size(); i++) System.out.print("(" + (i + 1) + ") " + lista.get(i));
    }
 
    private static boolean indiceValido(List<?> l, int i) {
        if (i < 0 || i >= l.size()) { 
            System.out.println("Posición inválida.");
        return false;
    }
        return true;
    }
 
    private static String leer(String msg){
        System.out.print(msg); return in.nextLine().trim();
    }
 
    private static int leerEntero(String msg) {
        try {
            return Integer.parseInt(leer(msg));
        } catch (NumberFormatException e) { 
            return -1;
        }
    }
}