import java.util.ArrayList;
import java.util.List;

public class repositorioDocumentos {
    private final List<documento> plantillas = new ArrayList<>();
    private final List<documento> documentos = new ArrayList<>();

    public void registrarPlantilla(documento p){
        plantillas.add(p);
    }

    public documento generarDesdePlantilla(int indice, String nombreNuevo) {
        documento copia = plantillas.get(indice).clonar(nombreNuevo);
        documentos.add(copia);
        return copia;
    }

    public List<documento> getPlantillas(){
        return plantillas;
    }

    public List<documento> getDocumentos(){
        return documentos;
    }
}