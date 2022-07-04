/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bc.beans;

import bc.entities.Clientes;
import bc.entities.Cuentas;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.el.PropertyNotFoundException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author omar
 */
@Named(value = "cuentaBean")
@RequestScoped
public class cuentaBean {

    @EJB
    private ClientesFacadeLocal clientesFacade;

    @EJB
    private CuentasFacadeLocal cuentasFacade;

    private Clientes numCuenta;
    private Cuentas cuentas_;
    private int codigo;
    private int saldo;
    private int aux;
    private String respuesta;

    public cuentaBean() {
        numCuenta = new Clientes();
//        cuentas_ = new Cuentas();
    }

    public boolean ingreso() {

        boolean verif = true;
        try {
            Cuentas cuenta = this.cuentasFacade.find(codigo);
            cuenta.getCodigo();
            //-------------------------------------------------------------En proceso
            saldo = cuenta.getSaldo();

            return true;
        } catch (PropertyNotFoundException | EJBException e) {

            return false;
        }

    }
    

    public String deposito() {
        
        try {
            
        Cuentas cuenta = this.cuentasFacade.find(codigo);

            if (cuenta.getCodigo()==codigo) {
                
            
            cuenta.setCodigo(cuenta.getCodigo());
            cuenta.setSaldo(cuenta.getSaldo() + aux);
            cuentasFacade.edit(cuenta);
//        clientesFacade.find(cuenta.getCodigo());
//        numCuenta;//aqui quede d20 h13:00
        //-------------------------------------------------------------
            }else{
                setRespuesta("datos incorrectos");
            }
        
        } catch ( Exception e) {
            setRespuesta("error"+e);
        }
//        catch(NullPointerException error){
//            setRespuesta("Revisa los datos Ingresados");
//        }
        
        
        
        return "deposito";
    }

    public String crearCuenta(int codigoC, int saldoC) {
        Cuentas c = new Cuentas();

        c.setCodigo(codigoC);
        c.setSaldo(saldoC);
        cuentasFacade.create(c);
        return "curso";
    }

    public String giro() {
        
        try{
            
        Cuentas cuenta = this.cuentasFacade.find(codigo);
        cuenta.setCodigo(cuenta.getCodigo());

        if (cuenta.getSaldo() >= aux) {
            cuenta.setSaldo(cuenta.getSaldo() - aux);
            cuentasFacade.edit(cuenta);
        } else {
            setRespuesta("Superas el Maximo!!!");
        }
        
        //-------------------------------------------------------------
        } catch (PropertyNotFoundException | EJBException e) {
            setRespuesta("error"+e);
        }catch(NullPointerException error){
            setRespuesta("Revisa los datos Ingresados");
        }
        
        return "giro";
    }

    
    
    
    public List<Cuentas> getCuentas() {
        return cuentasFacade.findAll();
    }

    public ClientesFacadeLocal getClientesFacade() {
        return clientesFacade;
    }

    public void setClientesFacade(ClientesFacadeLocal clientesFacade) {
        this.clientesFacade = clientesFacade;
    }

    public CuentasFacadeLocal getCuentasFacade() {
        return cuentasFacade;
    }

    public void setCuentasFacade(CuentasFacadeLocal cuentasFacade) {
        this.cuentasFacade = cuentasFacade;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getAux() {
        return aux;
    }

    public void setAux(int aux) {
        this.aux = aux;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    
}
