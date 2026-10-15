public class contratoServicio extends documento{
    public contratoServicio(String nombre, String numero, String contratista, String entidad, String valor, String durancion){

        super(nombre, true);
        definirCampo("Número de contrato", numero);
        definirCampo("Contratista", contratista);
        definirCampo("Entidad de contratante", entidad);
        definirCampo("Valor", valor);
        definirCampo("Duración", durancion);
    }
    private contratoServicio(contratoServicio o, String nombre){
        super (o, nombre);
        }

    @Override public String getTipo(){
        return "Contrato de prestación de servicios";
    }

    @Override public documento clonar(String nuevoNombre){
        return new contratoServicio(this, nuevoNombre);
    }
}