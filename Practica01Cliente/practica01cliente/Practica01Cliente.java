public class Practica01Cliente {
    public static void main(String[] args) throws Exception {
        try {
            IPersonaController personaController =
            (IPersonaController) Naming.lookup("rmi://localhost/PersonaController");
            IPersona persona = personaController.newInstance(); 
            persona.setEmail("alex@mail.com");
            List<IPersona> lista = personaController.find(persona);

            for (IPersona personaTemp : lista){
                System.out.println(personaTemp.getString());
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
