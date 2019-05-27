package delivery.lorry.registration.system;

public class Lorry {
    private final String regNum;
    private final String model;
    private final String ton;
    
    
    Lorry(String r, String m, String t){
        this.regNum = r;
        this.model = m;
        this.ton = t;
    }
    
    String getRegNum(){
        return this.regNum;
    }
    
    String getModel(){
        return this.model;
    }
    
    String getTon(){
        return this.ton;
    }
}
