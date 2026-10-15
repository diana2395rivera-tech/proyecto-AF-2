import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class documento implements  Prototipo<documento>{
    private static int contador = 0;
    private final int id;
    private String nombre;
    private final LocalDate fechaCreacion;
    private final boolean plantilla;
    private final Map<String, String> campos;

    protected documento (String nombre, boolean plantilla){
        this.id = ++contador;
        this.nombre = nombre;
        this.fechaCreacion = LocalDate.now();
        this.plantilla = plantilla;
        this.campos = new LinkedHashMap<> ();
    }

    protected documento (documento original, String nuevoNombre){
        this.id = ++contador;
        this.nombre = nuevoNombre;
        this.fechaCreacion = LocalDate.now();
        this.plantilla = false;
        this.campos = new LinkedHashMap<>(original.campos);
    }

    public abstract String getTipo();

    public abstract documento clonar(String nuevoNombre);

    @Override 
    public documento clonar(){
        return clonar (nombre + "(copia)");
    }

    protected void definirCampo(String clave, String valor){
        campos.put(clave, valor);
    }

    public boolean modificarCampo (String clave, String valor){
        if(!campos.containsKey(clave)) return false;
        campos.put(clave, valor);
        return true;
    }

    public String getCampo(String clave){
         return campos.get(clave);
    }
    public Map<String, String> getCampos(){
        return new LinkedHashMap<>(campos);
    }
    public int getId(){
        return id;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public boolean esPlantilla(){
        return plantilla;
    }
 
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("[#%d] %s - %s | %s | creado: %s%n",
                id, plantilla ? "PLANTILLA" : "DOCUMENTO", getTipo(), nombre, fechaCreacion));
        campos.forEach((k, v) -> sb.append("      ").append(k).append(": ").append(v).append("\n"));
        return sb.toString();
    }

}
    

