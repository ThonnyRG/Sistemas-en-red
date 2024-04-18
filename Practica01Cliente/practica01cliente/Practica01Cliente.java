public class Practica01Cliente {
    public static void main(String[] args) throws Exception {
        try {
            IPersonaController personaController =
            (IPersonaController) Naming.lookup("rmi://localhost/PersonaController");
            IPersona persona = personaController.newInstance(); persona.setNombre("Alex"); persona.setEmail("alex@mail.com"); persona.setTelefono ("9210011223");
            //Agregar
            personaController.add(persona);
            List<IPersona> lista = personaController.list();
            for(IPersona personaTemp : lista) {
            System.out.println(personaTemp.getString());
            }
            IPersona persona3 = personaController.findOne (4); 
            System.out.println("FindOne:" + persona3.getString());


            persona3.setNombre ("Beto"); persona3.setTelefono ("9221231230");
            int respuesta = personaController.update (persona3);
            System.out.println(respuesta);
            if (respuesta == IPersonaController. UPDATE_EXITO){
                System.out.println("Actualizado con éxito!");
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
