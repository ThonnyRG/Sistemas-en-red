public class Practica01Cliente {
    public static void main(String[] args) throws Exception {
        try {
            IPersonaController personaController =
            (IPersonaController) Naming.lookup("rmi://localhost/PersonaController");
            IPersona persona = personaController.newInstance();
            persona.setNombre ("Javier");
            persona.setEmail("javier@pino.mx");
            persona.setTelefono ("9210001111");
            int respuesta = personaController.add(persona);
            if (respuesta == IPersonaController. ADD_EXITO) { 
                System.out.println("Operación exitosa.");
            }else{
                System.out.println("Algo pasó");
            }
            } catch (NotBoundException ex) {
                Logger.getLogger (Practica0lCliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                Logger.getLogger (Practica0lCliente.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger (Practica0lCliente.class.getName()).log(Level. SEVERE, null, ex);
            }
    }
}
