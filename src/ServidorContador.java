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
