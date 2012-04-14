import ContadorApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import java.util.Properties;


class ContadorImpl extends ContadorPOA  {  
    private ORB orb;  
    private int valor;
   //metodo constructor
   ContadorImpl(){
       valor=0;
   }
   public void setORB(ORB orb_val){    
       orb = orb_val;   
   }      
   public int valor(){
       return valor;
   }
   public void valor (int newValor){
       valor=newValor;
   }
   public int inc(){    
       return valor++;
   }
   public int dec(){    
       return valor--;
   }
   public void shutdown(){    
       orb.shutdown(false);  
   }
}



public class ServidorContador{  
    public static void main(String args[]){    
     
    try{ 
         ORB orb = ORB.init(args, null);      
         POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));      
         rootpoa.the_POAManager().activate();      
         ContadorImpl contadorImpl = new ContadorImpl();      
         contadorImpl.setORB(orb);       
         org.omg.CORBA.Object ref = rootpoa.servant_to_reference(contadorImpl);      
         Contador href = ContadorHelper.narrow(ref); 
         org.omg.CORBA.Object objRef =orb.resolve_initial_references("NameService");     
         NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);      
         String name = "Contador";      
         NameComponent path[] = ncRef.to_name( name );      
         ncRef.rebind(path, href);      
         System.out.println("Servicio de contador preparado y en espera de peticiones ...");    
         orb.run();    
      }  
     catch (Exception e) 
      {        System.err.println("ERROR: " + e);        
               e.printStackTrace(System.out);      
      }          
     System.out.println("Servidor de contador desconectadose ...");    
 }
}

