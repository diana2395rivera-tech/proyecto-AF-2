public class actaReunion extends documento {
    public actaReunion(String nombre, String numero, String fecha,String participantes, String temas, String compromisos){

        super(nombre, true);
        definirCampo("Número de acta", numero);
        definirCampo("Fecha", fecha);
        definirCampo("Participantes", participantes);
        definirCampo("Temas tratados", temas);
        definirCampo("Compromisos", compromisos);
    }

    private actaReunion(actaReunion o, String nombre){
        super(o, nombre);
    }
 
    @Override public String getTipo(){
        return "Acta de reunión";
    }

    @Override public documento clonar(String nuevoNombre){
        return new actaReunion(this, nuevoNombre);
    }
}
 