public class propuestaComercial extends documento{
    public propuestaComercial(String nombre, String numero, String cliente, String descripcion, String valor, String vigencia){

       super(nombre, true);
        definirCampo("Número de propuesta", numero);
        definirCampo("Cliente", cliente);
        definirCampo("Descripción del servicio", descripcion);
        definirCampo("Valor", valor);
        definirCampo("Vigencia", vigencia);
    }

    private propuestaComercial(propuestaComercial o, String nombre){
        super(o, nombre);
    }
 
    @Override public String getTipo(){
        return "Propuesta comercial";
    }

    @Override public documento clonar(String nuevoNombre){
        return new propuestaComercial(this, nuevoNombre);  
    }
}