public class facturaElectronica extends documento {
    public facturaElectronica(String nombre, String numero, String cliente, String fecha, String subtotal, String impuestos){
        
        super (nombre, true);
         definirCampo("Número de factura", numero);
        definirCampo("Cliente", cliente);
        definirCampo("Fecha", fecha);
        definirCampo("Subtotal", subtotal);
        definirCampo("Impuestos", impuestos);
    }
    private facturaElectronica(facturaElectronica o, String nombre){
        super(o, nombre);
    }
 
    @Override public String getTipo(){
         return "Factura electrónica";
        }

    @Override public documento clonar(String nuevoNombre){
         return new facturaElectronica(this, nuevoNombre);
        }
}