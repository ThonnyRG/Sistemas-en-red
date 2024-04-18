public class Practica01Cliente {
    public static void main(String[] args) throws Exception {
        try {
            IPersonaController personaController =
            (IPersonaController) Naming.lookup("rmi://localhost/PersonaController");
            List<IPersona> lista = personaController.list();
            for(IPersona persona : lista) {
            System.out.println(persona.getString());
            }
            } catch (NotBoundException ex) {
            Logger.getLogger(Practica0lCliente.class.getName()).log (Level. SEVERE, null, ex);
            } catch (MalformedURLException ex) {
            Logger.getLogger(Practica0lCliente.class.getName()).log(Level. SEVERE, null, ex);
            } catch (RemoteException ex) {
       
                Logger.getLogger(Practica0lCliente.class.getName()).log (Level. SEVERE, null, ex);
            }
        }
    }
