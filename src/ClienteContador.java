import ContadorApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

public class ClienteContador{ 
    static Contador contadorImpl;
         public static void main(String args[]){ 
             try
                { 
                    ORB orb = ORB.init(args, null);
                    org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
                    NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
                    String name = "Contador";
                    
                    contadorImpl = ContadorHelper.narrow(ncRef.resolve_str(name));
                   // System.out.println("Obteniendo un manejador para el objeto servidor: " + contadorImpl);
                    for (int i=1;i<=10;i++)
                    {System.out.println("contador de incremeneto "+contadorImpl.inc());}
                    for (int i=1;i<=5;i++)
                    {System.out.println("contador de decremento "+contadorImpl.dec()); }
                   // contadorImpl.shutdown();
                } 
      catch (Exception e) 
      { System.out.println("ERROR : " + e) ;
        e.printStackTrace(System.out);
      }
    }
}
